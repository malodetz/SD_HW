package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeAssignmentsList extends ASTNode {
  public ASTNodeAssignmentsList(final ASTNodeAssignment head, final ASTNodeAssignmentsList tail) {
    children.add(head);
    children.add(tail);
  }

  public ASTNodeAssignment head() {
    return (ASTNodeAssignment) children.get(0);
  }

  public ASTNodeAssignmentsList tail() {
    return (ASTNodeAssignmentsList) children.get(1);
  }

  @Override
  public String toString() {
    if (tail() != null) {
      return head().toString() + ", " + tail().toString();
    }
    return head().toString();
  }
}