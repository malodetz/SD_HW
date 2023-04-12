package ru.hse.fmcs.Core;

import ru.hse.fmcs.Parsing.ASTTree;

/**
 * @author sergey
 * Main class that traverse through received
 * Abstract Syntax Tree. Construct commands
 * and pass them to FunctionHandler.
 */
public class Interpreter {
  final Environment env;

  public Interpreter() {
    env = new Environment();
  }

  public void traverse(ASTTree tree) {
    
  }
}
