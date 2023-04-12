package ru.hse.fmcs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Parsing.AST;
import ru.hse.fmcs.Parsing.ASTConstructor;
import ru.hse.fmcs.Parsing.ParsingException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
  private final Path resourceDirectory = Paths.get("src", "test", "resources");
  private InputStream is;

  private void prepareInputStream(final String filename) {
    final Path sourcePath = Paths.get(resourceDirectory.toString(), filename);
    is = null;
    try {
      is = new FileInputStream(sourcePath.toAbsolutePath().toString());
    } catch (IOException e) {
      fail("Can not open resource file");
    }
  }

  @AfterEach
  public void closeInputStream() {
    try {
      if (is != null) {
        is.close();
      }
    } catch (IOException e) {
      fail("Can not close resource file");
    }
  }

  @Test
  public void echoTest() {
    prepareInputStream("Parsing/Echo.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("echo(a, b, c, d)", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void envSimpleTest() {
    prepareInputStream("Parsing/EnvSingleVariable.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("[a = abacaba]", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void envMultipleVariablesTest() {
    prepareInputStream("Parsing/EnvMultipleVariables.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("[a = 10, b = 20, c = 30, d = 0]", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void envWithCmdWithoutArgsTest() {
    prepareInputStream("Parsing/EnvWithCmdWithoutArgs.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("[a = 10, b = 20] echo()", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void envWithCmdWithArgsTest() {
    prepareInputStream("Parsing/EnvWithCmdWithArgs.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("[a = 10, b = 20] echo(a, b, c, d)", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void emptyLineTest() {
    prepareInputStream("Parsing/EmptyLine.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void incorrectCmdTest() {
    prepareInputStream("Parsing/IncorrectCmd.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("=asd()", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

}
