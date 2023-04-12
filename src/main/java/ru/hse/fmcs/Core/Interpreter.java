package ru.hse.fmcs.Core;

import ru.hse.fmcs.Parsing.AST;

/**
 * @author sergey
 * Main class that traverse through received Abstract Syntax Tree. Construct commands
 * and pass them to FunctionHandler maintaing the enviroment variables all the time.
 */
public class Interpreter {
  final Environment env;

  public Interpreter() {
    env = new Environment();
  }

  /**
   * Evaluate function traverses through received AST. AST
   * represents set of commands, nested or connected via pipe.
   * Therefore, it needs to maintain a correct order for commands
   * evaluation: firstly execute most nested commands.
   * Commands located on one level must be executed from left
   * to right (in order of arguments of parent ASTNode).
   *
   * @param tree
   */
  public void evaluate(final AST tree) {

  }
}
