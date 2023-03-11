package ru.hse.fmcs.Core;

import ru.hse.fmcs.FunctionCaller.*;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTConstructor;
import ru.hse.fmcs.Parsing.ASTNode.*;
import ru.hse.fmcs.Parsing.ParsingException;
import ru.hse.fmcs.Parsing.Preprocessor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author sergey
 * Main class that traverse through received Abstract Syntax Tree. Construct commands
 * and pass them to FunctionHandler maintaing the enviroment variables all the time.
 */
public class Interpreter {
  final private Environment environment;
  final private FunctionCallerInterface functionCaller;
  final private Preprocessor preprocessor;

  public Interpreter() {
    environment = new Environment();
    functionCaller = null;
    preprocessor = new Preprocessor(environment);
  }

  /**
   * Prepares commands from pipe and executes
   * them in separate threads. Output from the first
   * command forwarded to the input of the second
   * command.
   *
   * @param pipedCommands "list" of piped commands.
   */
  private void executeCompoundCommands(final ASTNodePipedCommands pipedCommands) throws UnexpectedFunctionName, WrongArgumentsException, IOException, ExitException {
    final List<ASTNode> commands = pipedCommands.toList();
    for (var command : commands) {
      executeCommand(command);
    }
  }

  private String executeCommand(final ASTNode command) throws UnexpectedFunctionName, WrongArgumentsException, IOException, ExitException {
    if (command instanceof ASTNodeFunctionCall functionCall) {
      String functionName = functionCall.functionName;
      List<String> arguments = functionCall.argumentsList().stream().map(ASTNodeArgument::toString).toList();
      return functionCaller.handleFunction(new Query(functionName, arguments));
      // TODO: wrap result and return
    } else if (command instanceof ASTNodeEnvFunctionCall envFunctionCall) {
      List<ASTNodeAssignment> assignments = envFunctionCall.assignmentList();
      Environment modifiedEnvironment = new Environment(environment);
      for (var assign : assignments) {
        modifiedEnvironment.exportVariable(assign.name, assign.value);
      }
      String functionName = envFunctionCall.function.functionName;
      List<String> arguments = envFunctionCall.function.argumentsList().stream().map(ASTNodeArgument::toString).toList();
      return functionCaller.handleFunction(new Query(functionName, arguments));
      // TODO: wrap result and return
    } else if (command instanceof ASTNodeVarDecl varDecl) {
      List<ASTNodeAssignment> assignments = varDecl.declarations();
      for (var assign : assignments) {
        environment.addVariable(assign.name, assign.value);
      }
    }
    return null;
  }

  /**
   * Processes substitutions in given string query, tokenizes
   * and parses it. Then removes quotes and forms the query
   * to a function caller. Receives result and passes it back.
   *
   * @param query
   */
  public void execute(final String query) {
    String preparedQuery = preprocessor.processSubstitutions(query);

    ByteArrayInputStream is = new ByteArrayInputStream(preparedQuery.getBytes());
    ASTConstructor constructor = new ASTConstructor(is);

    try {
      AST ast = constructor.consumeInput();
      if (ast.root instanceof ASTNodePipedCommands pipedCommands) {
        executeCompoundCommands(pipedCommands);
      } else {
        // TODO: remove
        System.err.println("This should not happen");
        assert false;
      }
    } catch (ParsingException | UnexpectedFunctionName | WrongArgumentsException | IOException |
             ExitException exception) {
      exception.printStackTrace();
      System.exit(1);
    }
  }
}
