package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodePipedCommands extends ASTNode {
  public ASTNodePipedCommands(final ASTNode head, final ASTNodePipedCommands tail) {
    children.add(head);
    if (tail != null) {
      children.add(tail);
    }
  }

  public ASTNode head() {
    return children.get(0);
  }

  public ASTNodePipedCommands tail() {
    if (children.size() != 2) {
      return null;
    }
    return (ASTNodePipedCommands) children.get(1);
  }

  @Override
  public String toString() {
    if (tail() != null) {
      return head().toString() + " | " + tail().toString();
    }
    return head().toString();
  }
}
