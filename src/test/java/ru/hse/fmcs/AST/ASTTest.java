package ru.hse.fmcs.AST;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTNode.*;

import java.util.List;

public class ASTTest {
  @Test
  public void TestIteratorSimple() {
    ASTNodeArgument left = new ASTNodeArgument("left");
    ASTNodeArgument right = new ASTNodeArgument("right");
    ASTNodeArgumentsList list = new ASTNodeArgumentsList(right, null);
    list = new ASTNodeArgumentsList(left, list);
    AST ast = new AST(list);
    Assertions.assertEquals("left, right", ast.toString());
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

    ASTNodeVarDecl varDecl = new ASTNodeVarDecl(list);

    Assertions.assertIterableEquals(List.of("d = 40", "c = 30", "b = 20", "a = 10"), varDecl.declarations().stream().map(ASTNodeAssignment::toString).toList());
  }
}
