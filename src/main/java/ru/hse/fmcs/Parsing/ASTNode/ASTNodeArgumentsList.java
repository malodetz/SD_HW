package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.List;

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

  public List<ASTNodeArgument> argsList() {
    List<ASTNodeArgument> result = new ArrayList<>();
    for (ASTNodeArgumentsList argsList = this; argsList != null; argsList = argsList.tail()) {
      result.add(argsList.head());
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