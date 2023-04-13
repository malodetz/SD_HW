package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeAssignmentsList extends ASTNode {
  public ASTNodeAssignmentsList(final ASTNodeAssignment head, final ASTNodeAssignmentsList tail) {
    assert (head != null);
    children.add(head);
    if (tail != null) {
      children.add(tail);
    }
  }

  public ASTNodeAssignment head() {
    return (ASTNodeAssignment) children.get(0);
  }

  public ASTNodeAssignmentsList tail() {
    if (children.size() != 2) {
      return null;
    }
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