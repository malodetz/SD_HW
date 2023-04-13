package ru.hse.fmcs.AST;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTNode.ASTNode;
import ru.hse.fmcs.Parsing.ASTNode.ASTNodeArgument;
import ru.hse.fmcs.Parsing.ASTNode.ASTNodeArgumentsList;

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


}
