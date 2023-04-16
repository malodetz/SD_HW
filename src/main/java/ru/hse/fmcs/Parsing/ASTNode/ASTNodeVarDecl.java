package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeVarDecl extends ASTNode {
  public ASTNodeVarDecl(final ASTNodeAssignmentsList assign) {
    if (assign != null) {
      children.add(assign);
    }
  }

  public ASTNodeAssignmentsList assign() {
    if (children.size() != 1) {
      return null;
    }
    return (ASTNodeAssignmentsList) children.get(0);
  }

  @Override
  public String toString() {
    if (assign() == null) {
      return "";
    }
    return "[" + assign().toString() + "]";
  }
}
