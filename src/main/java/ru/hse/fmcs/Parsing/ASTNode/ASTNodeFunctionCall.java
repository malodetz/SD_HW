package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeFunctionCall extends ASTNode {
  final String functionName;

  public ASTNodeFunctionCall(final String functionName, final ASTNodeArgumentsList args) {
    children.add(args);
    this.functionName = functionName;
  }

  public ASTNodeArgumentsList args() {
    return (ASTNodeArgumentsList) children.get(0);
  }

  @Override
  public String toString() {
    if (args() != null) {
      return functionName + "(" + args().toString() + ")";
    }
    return functionName + "()";
  }
}