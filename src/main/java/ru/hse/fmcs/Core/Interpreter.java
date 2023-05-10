package ru.hse.fmcs.Core;

import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.FunctionCaller.FunctionCallerInterface;
import ru.hse.fmcs.FunctionCaller.FunctionHandler;
import ru.hse.fmcs.FunctionCaller.Query;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTBuilder;
import ru.hse.fmcs.Parsing.ASTNode.*;
import ru.hse.fmcs.Parsing.ParsingException;
import ru.hse.fmcs.Parsing.Preprocessor;

import java.io.IOException;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * Main class that traverse through received Abstract Syntax Tree. Construct commands
 * and pass them to FunctionHandler maintaing the enviroment variables all the time.
 * It can run its own subshells in order to handle subprograms, input substitutions, pipes etc.
 */
public class Interpreter {
  final private Environment environment;
  final private FunctionCallerInterface functionCaller;
  final private Preprocessor preprocessor;

  final private ReadableByteChannel standardInputChannel;
  final private WritableByteChannel standardOutputChannel;

  public Interpreter(final ReadableByteChannel standardInputChannel,
                     final WritableByteChannel standardOutputChannel,
                     final Environment environment) {
    this.standardInputChannel = standardInputChannel;
    this.standardOutputChannel = standardOutputChannel;
    this.environment = environment;

    preprocessor = new Preprocessor(environment);
    functionCaller = new FunctionHandler();
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

    ReadableByteChannel nextSubInterpreterInputChannel = standardInputChannel;
    for (int i = 0, size = pipedCommandsList.size(); i < size; ++i) {
      ReadableByteChannel subInterpreterInputChannel = nextSubInterpreterInputChannel;
      WritableByteChannel subInterpreterOutputChannel = standardOutputChannel;

      // Forward current command stdout to stdin of next command
      // via pipe. Pipes open n-1 times, where `n` is a number of
      // commands in pipeline.
      if (i != size - 1) {
        Pipe pipe = Pipe.open();
        nextSubInterpreterInputChannel = pipe.source();
        subInterpreterOutputChannel = pipe.sink();
      }

      Interpreter subInterpreter = new Interpreter(
          subInterpreterInputChannel,
          subInterpreterOutputChannel,
          new Environment(environment)
      );

      boolean shouldCloseStdin = (i != 0);
      boolean shouldCloseStdout = (i + 1 != size);

      ASTNode commandRoot = pipedCommandsList.get(i);
      Thread thread = new Thread(() -> {
        try {
          subInterpreter.execute(commandRoot);
          if (shouldCloseStdin) {
            subInterpreter.standardInputChannel.close();
          }
          if (shouldCloseStdout) {
            subInterpreter.standardOutputChannel.close();
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
      String functionName = functionCall.functionName;
      List<String> arguments = functionCall.argumentsList().stream().map(ASTNodeArgument::toString).map(Preprocessor::removeQuotes).toList();
      functionCaller.handleFunction(new Query(Preprocessor.removeQuotes(functionName), arguments, standardInputChannel, standardOutputChannel, new Environment(environment)));
    } else if (command instanceof ASTNodeEnvFunctionCall envFunctionCall) {
      List<ASTNodeAssignment> assignments = envFunctionCall.assignmentList();
      Environment modifiedEnvironment = new Environment(environment);
      for (var assign : assignments) {
        modifiedEnvironment.exportVariable(Preprocessor.removeQuotes(assign.name), Preprocessor.removeQuotes(assign.value));
      }
      String functionName = envFunctionCall.function.functionName;
      List<String> arguments = envFunctionCall.function.argumentsList().stream().map(ASTNodeArgument::toString).map(Preprocessor::removeQuotes).toList();
      functionCaller.handleFunction(new Query(Preprocessor.removeQuotes(functionName), arguments, standardInputChannel, standardOutputChannel, modifiedEnvironment));
    } else if (command instanceof ASTNodeVarDecl varDeclaration) {
      List<ASTNodeAssignment> assignments = varDeclaration.declarations();
      for (var assign : assignments) {
        environment.addVariable(Preprocessor.removeQuotes(assign.name), Preprocessor.removeQuotes(assign.value));
      }
    }
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
