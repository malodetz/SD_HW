package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ASTNodeEnvFunctionCall implements ASTNode {
  public final ASTNodeFunctionCall function;
  private final ASTNodeAssignmentsList assignment;

  public ASTNodeEnvFunctionCall(final ASTNodeAssignmentsList assignment, final ASTNodeFunctionCall function) {
    this.assignment = assignment;
    this.function = function;
  }

  public List<ASTNodeAssignment> assignmentList() {
    if (assignment == null) {
      return new ArrayList<>();
    }
    return assignment.toList();
  }

  @Override
  public String toString() {
    String result = "";
    if (assignment != null) {
      result = result + "[" + assignment.toString() + "]";
    }
    if (function != null) {
      if (!result.isEmpty()) {
        result = result + " ";
      }
      result = result + function.toString();
    }
    return result;
  }
}
