package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeArgumentsList extends ASTNode {

  public ASTNodeArgumentsList(final ASTNodeArgument head, final ASTNodeArgumentsList tail) {
    children.add(head);
    children.add(tail);
  }

  public ASTNodeArgument head() {
    return (ASTNodeArgument) children.get(0);
  }

  public ASTNodeArgumentsList tail() {
    return (ASTNodeArgumentsList) children.get(1);
  }

  @Override
  public String toString() {
    if (tail() != null) {
      return head().toString() + ", " + tail().toString();
    }
    return head().toString();
  }
}