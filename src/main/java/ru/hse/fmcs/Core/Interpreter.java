package ru.hse.fmcs.Core;

import ru.hse.fmcs.Parser.AST;

/**
 * @author sergey
 * Main class that traverse through received
 * Abstract Syntax Tree. Construct commands
 * and pass them to FunctionHandler.
 */
public class Interpreter {
  final Environment env;
  final AST ast;

  public Interpreter(final AST ast) {
    env = new Environment();
    this.ast = ast;
  }
}
