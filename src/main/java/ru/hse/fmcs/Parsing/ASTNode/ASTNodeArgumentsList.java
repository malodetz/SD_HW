package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeArgumentsList extends ASTNode {

  public ASTNodeArgumentsList(final ASTNodeArgument head, final ASTNodeArgumentsList tail) {
    assert (head != null);
    children.add(head);
    if (tail != null) {
      children.add(tail);
    }
  }

  public ASTNodeArgument head() {
    return (ASTNodeArgument) children.get(0);
  }

  public ASTNodeArgumentsList tail() {
    if (children.size() == 2) {
      return (ASTNodeArgumentsList) children.get(1);
    }
    return null;
  }

  @Override
  public String toString() {
    if (tail() != null) {
      return head().toString() + ", " + tail().toString();
    }
    return head().toString();
  }
}