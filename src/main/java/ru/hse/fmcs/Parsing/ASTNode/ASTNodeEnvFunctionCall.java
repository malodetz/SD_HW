package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeEnvFunctionCall extends ASTNode {
  public ASTNodeEnvFunctionCall(final ASTNodeAssignmentsList assign, final ASTNodeFunctionCall func) {
    children.add(assign);
    children.add(func);
  }

  public ASTNodeAssignmentsList assign() {
    return (ASTNodeAssignmentsList) children.get(0);
  }

  public ASTNodeFunctionCall func() {
    return (ASTNodeFunctionCall) children.get(1);
  }

  @Override
  public String toString() {
    String result = "";
    if (assign() != null) {
      result = result + "[" + assign().toString() + "]";
    }
    if (func() != null) {
      if (!result.isEmpty()) {
        result = result + " ";
      }
      result = result + func().toString();
    }
    return result;
  }
}
