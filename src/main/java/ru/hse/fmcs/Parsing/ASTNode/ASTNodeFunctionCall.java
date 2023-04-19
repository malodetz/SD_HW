package ru.hse.fmcs.Parsing.ASTNode;

import java.util.List;

public class ASTNodeFunctionCall implements ASTNode {
  public final String functionName;
  private final ASTNodeArgumentsList args;

  public ASTNodeFunctionCall(final String functionName, final ASTNodeArgumentsList args) {
    this.args = args;
    this.functionName = functionName;
  }

  public List<ASTNodeArgument> argumentsList() {
    return args.toList();
  }

  @Override
  public String toString() {
    if (args != null) {
      return functionName + "(" + args.toString() + ")";
    }
    return functionName + "()";
  }
}