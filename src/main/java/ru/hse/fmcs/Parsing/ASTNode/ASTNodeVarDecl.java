package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ASTNodeVarDecl implements ASTNode {
  private final ASTNodeAssignmentsList assign;

  public ASTNodeVarDecl(final ASTNodeAssignmentsList assign) {
    this.assign = assign;
  }

  public List<ASTNodeAssignment> declarations() {
    if (assign == null) {
      return new ArrayList<>();
    }
    return assign.toList();
  }

  @Override
  public String toString() {
    if (assign == null) {
      return "";
    }
    return "[" + assign.toString() + "]";
  }
}
