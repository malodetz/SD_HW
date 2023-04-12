package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeArgument extends ASTNode {
  final String arg;

  public ASTNodeArgument(final String arg) {
    this.arg = arg;
  }

  @Override
  public String toString() {
    return arg;
  }
}