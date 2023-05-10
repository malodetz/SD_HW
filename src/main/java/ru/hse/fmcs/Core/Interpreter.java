package ru.hse.fmcs.Core;

import org.apache.commons.io.input.CloseShieldInputStream;
import org.apache.commons.io.output.CloseShieldOutputStream;
import ru.hse.fmcs.FunctionCaller.DefaultFunctionHandler;
import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.FunctionCaller.FunctionHandler;
import ru.hse.fmcs.FunctionCaller.Query;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTBuilder;
import ru.hse.fmcs.Parsing.ASTNode.*;
import ru.hse.fmcs.Parsing.ParsingException;
import ru.hse.fmcs.Parsing.Preprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class that traverse through received Abstract Syntax Tree. Construct commands
 * and pass them to FunctionHandler maintaing the enviroment variables all the time.
 * It can run its own subshells in order to handle subprograms, input substitutions, pipes etc.
 *
 * @author sergey
 */
public class Interpreter {
  final private Environment environment;
  final private FunctionHandler functionHandler;
  final private Preprocessor preprocessor;

  final private InputStream standardInput;
  final private OutputStream standardOutput;

  public Interpreter(final InputStream standardInput,
                     final OutputStream standardOutput,
                     final Environment environment) {
    this.standardInput = standardInput;
    this.standardOutput = standardOutput;
    this.environment = environment;

    preprocessor = new Preprocessor(environment);
    functionHandler = new DefaultFunctionHandler();
  }

  /**
   * Prepares commands from pipe and executes
   * them in separate threads. Output from the first
   * command forwarded to the input of the second
   * command.
   *
   * @param pipedCommands "list" of piped commands.
   */
  private void executePipedCommands(final ASTNodePipedCommands pipedCommands) throws IOException, ExitException {
    List<ASTNode> pipedCommandsList = pipedCommands.toList();
    List<Thread> threadsCommandsList = new ArrayList<>();

    InputStream nextSubInterpreterInput = standardInput;
    for (int i = 0, size = pipedCommandsList.size(); i < size; ++i) {
      InputStream subInterpreterInput = nextSubInterpreterInput;
      OutputStream subInterpreterOutput = standardOutput;

      // Forward current command stdout to stdin of next command
      // via pipe. Pipes open n-1 times, where `n` is a number of
      // commands in pipeline.
      if (i != size - 1) {
        Pipe pipe = Pipe.open();
        nextSubInterpreterInput = Channels.newInputStream(pipe.source());
        subInterpreterOutput = Channels.newOutputStream(pipe.sink());
      }

      Interpreter subInterpreter = new Interpreter(
          subInterpreterInput,
          subInterpreterOutput,
          new Environment(environment)
      );

      boolean shouldCloseStdin = (i != 0);
      boolean shouldCloseStdout = (i + 1 != size);

      ASTNode commandRoot = pipedCommandsList.get(i);
      Thread thread = new Thread(() -> {
        try {
          subInterpreter.execute(commandRoot);
          if (shouldCloseStdin) {
            subInterpreter.standardInput.close();
          }
          if (shouldCloseStdout) {
            subInterpreter.standardOutput.close();
          }
        } catch (IOException | ExitException exception) {
          exception.printStackTrace();
          System.exit(1);
        }
      });
      threadsCommandsList.add(thread);
      thread.start();
    }

    for (Thread thread : threadsCommandsList) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }
  }

  private void executeSingleCommand(final ASTNode command) throws IOException, ExitException {
    if (command instanceof ASTNodeFunctionCall functionCall) {
      executeFunctionCall(functionCall, new Environment(environment));
    } else if (command instanceof ASTNodeEnvFunctionCall envFunctionCall) {
      Environment modifiedEnvironment = new Environment(environment);
      for (var assign : envFunctionCall.assignmentList()) {
        modifiedEnvironment.exportVariable(Preprocessor.removeQuotes(assign.name), Preprocessor.removeQuotes(assign.value));
      }
      executeFunctionCall(envFunctionCall.function, modifiedEnvironment);
    } else if (command instanceof ASTNodeVarDecl varDeclaration) {
      List<ASTNodeAssignment> assignments = varDeclaration.declarations();
      for (var assign : assignments) {
        environment.addVariable(Preprocessor.removeQuotes(assign.name), Preprocessor.removeQuotes(assign.value));
      }
    }
  }

  private int executeFunctionCall(ASTNodeFunctionCall node, Environment functionCallEnvironment) throws ExitException {
    CloseShieldInputStream shieldedInputStream = CloseShieldInputStream.wrap(standardInput);
    CloseShieldOutputStream shieldedOutputStream = CloseShieldOutputStream.wrap(standardOutput);

    List<String> arguments = node.argumentsList().stream().map(ASTNodeArgument::toString).map(Preprocessor::removeQuotes).toList();
    Query query = new Query(Preprocessor.removeQuotes(node.functionName),
        arguments,
        shieldedInputStream,
        shieldedOutputStream,
        functionCallEnvironment);
    return functionHandler.handleFunction(query);
  }


  /**
   * Processes substitutions in given string query, tokenizes
   * and parses it. Then removes quotes and forms the query
   * to a function caller. Receives result and passes it back.
   *
   * @param query given string representation of query
   */
  public void execute(final String query) {
    ASTBuilder builder = new ASTBuilder(preprocessor.processSubstitutions(query));

    try {
      AST ast = builder.build();
      execute(ast.root);
    } catch (ParsingException | IOException | ExitException exception) {
      exception.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Executes the command encoded in AST.
   */
  public void execute(ASTNode root) throws IOException, ExitException {
    if (root instanceof ASTNodePipedCommands pipedCommands) {
      executePipedCommands(pipedCommands);
    } else {
      executeSingleCommand(root);
    }
  }

}
