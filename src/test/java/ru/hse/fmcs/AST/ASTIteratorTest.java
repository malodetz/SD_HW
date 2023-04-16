package ru.hse.fmcs.AST;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTNode.*;

import java.util.ArrayList;
import java.util.List;

public class ASTIteratorTest {
  @Test
  public void TestIteratorSimple() {
    ASTNodeArgument left = new ASTNodeArgument("left");
    ASTNodeArgument right = new ASTNodeArgument("right");
    ASTNodeArgumentsList list = new ASTNodeArgumentsList(right, null);
    list = new ASTNodeArgumentsList(left, list);
    AST ast = new AST(list);
    StringBuilder builder = new StringBuilder();
    boolean firstIteration = true;
    for (ASTNode node : ast) {
      if (!firstIteration) {
        builder.append("; ");
      }
      firstIteration = false;
      builder.append(node.toString());
    }

    Assertions.assertEquals("left; right; right; left, right", builder.toString());
  }

  @Test
  public void TestEnvSetup() {
    ASTNodeAssignment assignA = new ASTNodeAssignment("a", "10");
    ASTNodeAssignment assignB = new ASTNodeAssignment("b", "20");
    ASTNodeAssignment assignC = new ASTNodeAssignment("c", "30");
    ASTNodeAssignment assignD = new ASTNodeAssignment("d", "40");

    ASTNodeAssignmentsList list = new ASTNodeAssignmentsList(assignA, null);
    list = new ASTNodeAssignmentsList(assignB, list);
    list = new ASTNodeAssignmentsList(assignC, list);
    list = new ASTNodeAssignmentsList(assignD, list);

    ASTNodeVarDecl envSetup = new ASTNodeVarDecl(list);
    int iterationsCount = 0;
    List<String> keys = new ArrayList<>();

    for (ASTNode node : envSetup) {
      if (node instanceof ASTNodeAssignment castedNode) {
        keys.add(castedNode.name);
      }
      iterationsCount++;
    }

    Assertions.assertIterableEquals(List.of("d", "c", "b", "a"), keys);
    Assertions.assertEquals(9, iterationsCount);
  }
}
