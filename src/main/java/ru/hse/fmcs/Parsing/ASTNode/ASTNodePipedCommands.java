package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ASTNodePipedCommands implements ASTNode {
  private final ASTNode head;
  private final ASTNodePipedCommands tail;

  public ASTNodePipedCommands(final ASTNode head, final ASTNodePipedCommands tail) {
    this.head = head;
    this.tail = tail;
  }

  public List<ASTNode> toList() {
    List<ASTNode> result = new ArrayList<>();
    for (ASTNodePipedCommands commandIterator = this; commandIterator != null; commandIterator = commandIterator.tail) {
      result.add(commandIterator.head);
    }
    return result;
  }

  @Override
  public String toString() {
    if (tail != null) {
      return head.toString() + " | " + tail.toString();
    }
    return head.toString();
  }
}
