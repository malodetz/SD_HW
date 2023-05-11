package ru.hse.fmcs.Parsing;

import ru.hse.fmcs.Parsing.ASTNode.ASTNode;

public class AST {
  public final ASTNode root;

  public AST(final ASTNode root) {
    this.root = root;
  }

  @Override
  public String toString() {
    return root.toString();
  }
}
