package ru.hse.fmcs.Parsing;

import ru.hse.fmcs.Parsing.ASTNode.ASTNode;

import java.util.Iterator;

public class AST implements Iterable<ASTNode> {
  private final ASTNode root;

  public AST(final ASTNode root) {
    this.root = root;
  }

  public Iterator<ASTNode> iterator() {
    return root.iterator();
  }

  @Override
  public String toString() {
    return root.toString();
  }
}
