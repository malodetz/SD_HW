package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeSingleCommand extends ASTNode {
  public ASTNodeSingleCommand(final ASTNodeAssignmentsList assign, final ASTNodeFunctionCall func) {
    children.add(assign);
    if (func != null) {
      children.add(func);
    }
  }

  public ASTNodeAssignmentsList assign() {
    return (ASTNodeAssignmentsList) children.get(0);
  }

  public ASTNodeFunctionCall func() {
    if (children.size() != 2) {
      return null;
    }
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
