package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ASTNodeArgumentsList implements ASTNode {
  private final ASTNodeArgument head;
  private final ASTNodeArgumentsList tail;

  public ASTNodeArgumentsList(final ASTNodeArgument head, final ASTNodeArgumentsList tail) {
    this.head = head;
    this.tail = tail;
  }

  public List<ASTNodeArgument> toList() {
    List<ASTNodeArgument> result = new ArrayList<>();
    for (ASTNodeArgumentsList argsList = this; argsList != null; argsList = argsList.tail) {
      result.add(argsList.head);
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