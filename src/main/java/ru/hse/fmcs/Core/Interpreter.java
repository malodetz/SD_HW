package ru.hse.fmcs.Core;

import ru.hse.fmcs.FunctionCaller.FunctionCallerInterface;
import ru.hse.fmcs.FunctionCaller.Query;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTConstructor;
import ru.hse.fmcs.Parsing.ASTNode.*;
import ru.hse.fmcs.Parsing.ParsingException;
import ru.hse.fmcs.Parsing.Preprocessor;

import java.io.ByteArrayInputStream;
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
   * Evaluate function traverses through received AST. AST
   * represents set of commands, nested or connected via pipe.
   * Therefore, it needs to maintain a correct order for commands
   * evaluation: firstly execute most nested commands.
   * Commands located on one level must be executed from left
   * to right (in order of arguments of parent ASTNode).
   *
   * @param tree AST representation of command
   */
  public void evaluate(final AST tree) {
    for (ASTNode node : tree) {
      if (node instanceof ASTNodeFunctionCall functionCall) {
        String functionName = functionCall.functionName;
        List<String> arguments = functionCall.args().argsList().stream().map(ASTNodeArgument::toString).toList();
        functionCaller.HandleFunction(new Query(functionName, arguments));
      } else if (node instanceof ASTNodeEnvFunctionCall envFunctionCall) {
        List<ASTNodeAssignment> assignments = envFunctionCall.assign().assignmentsToList();
        Environment modifiedEnvironment = new Environment(environment);
        for (var assign : assignments) {
          modifiedEnvironment.exportVariable(assign.name, assign.value);
        }
        String functionName = envFunctionCall.func().functionName;
        List<String> arguments = envFunctionCall.func().args().argsList().stream().map(ASTNodeArgument::toString).toList();
        functionCaller.HandleFunction(new Query(functionName, arguments));
      } else if (node instanceof ASTNodeVarDecl varDecl) {
        List<ASTNodeAssignment> assignments = varDecl.assign().assignmentsToList();
        for (var assign : assignments) {
          environment.addVariable(assign.name, assign.value);
        }
      } else if (node instanceof ASTNodePipedCommands nodePipedCommands) {

      }
    }
  }

  public void executeCommand(final String query) {
    String preparedQuery = preprocessor.processSubstitutions(query);

    ByteArrayInputStream is = new ByteArrayInputStream(preparedQuery.getBytes());
    ASTConstructor constructor = new ASTConstructor(is);

    try {
      AST ast = constructor.consumeInput();
      evaluate(ast);
    } catch (ParsingException exception) {
      exception.printStackTrace();
      System.exit(1);
    }
  }
}
