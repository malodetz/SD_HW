package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.List;

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

  public List<ASTNodeAssignment> assignmentsToList() {
    List<ASTNodeAssignment> result = new ArrayList<>();
    for (ASTNodeAssignmentsList itAssignment = this; itAssignment != null; itAssignment = itAssignment.tail()) {
      result.add(itAssignment.head());
    }
    return result;
  }

  @Override
  public String toString() {
    if (tail() != null) {
      return head().toString() + ", " + tail().toString();
    }
    return head().toString();
  }
}