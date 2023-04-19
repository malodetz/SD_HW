package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ASTNodeAssignmentsList implements ASTNode {
  private final ASTNodeAssignment head;
  private final ASTNodeAssignmentsList tail;

  public ASTNodeAssignmentsList(final ASTNodeAssignment head, final ASTNodeAssignmentsList tail) {
    this.head = head;
    this.tail = tail;
  }

  public List<ASTNodeAssignment> toList() {
    List<ASTNodeAssignment> result = new ArrayList<>();
    for (ASTNodeAssignmentsList itAssignment = this; itAssignment != null; itAssignment = itAssignment.tail) {
      result.add(itAssignment.head);
    }
    return result;
  }

  @Override
  public String toString() {
    if (tail != null) {
      return head.toString() + ", " + tail.toString();
    }
    return head.toString();
  }
}