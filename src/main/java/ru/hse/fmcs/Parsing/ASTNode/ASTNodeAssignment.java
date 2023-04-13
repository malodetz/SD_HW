package ru.hse.fmcs.Parsing.ASTNode;

public class ASTNodeAssignment extends ASTNode {
  public final String name;
  public final String value;

  public ASTNodeAssignment(final String name, final String value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString() {
    return name + " = " + value;
  }
}
