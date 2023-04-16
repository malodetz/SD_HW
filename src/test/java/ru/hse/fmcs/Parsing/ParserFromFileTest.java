package ru.hse.fmcs.Parsing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserFromFileTest {
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

  @Test
  public void pipedCmdTest() {
    prepareInputStream("Parsing/PipedCmd.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("echo(a, b, c, d) | cat() | cat() | cat()", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void incorrectPipedCmdTest() {
    prepareInputStream("Parsing/IncorrectPipedCmd.txt");
    ASTConstructor parser = new ASTConstructor(is);
    Assertions.assertThrows(ParsingException.class, parser::consumeInput);
  }

  @Test
  public void multiplePipedCmdTest() {
    prepareInputStream("Parsing/MultiplePipedCmd.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("[a = 10, b = 20] echo(\"a\") | cat() | echo() | cat() | cat() | echo(10) | [a = 10] | [c = 10] cat() | echo(c)", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }

  @Test
  public void complexTokenTest() {
    prepareInputStream("Parsing/ComplexToken.txt");
    ASTConstructor parser = new ASTConstructor(is);
    try {
      AST ast = parser.consumeInput();
      Assertions.assertEquals("echo(\"aba\"caba) | 'cat'()", ast.toString());
    } catch (ParsingException e) {
      Assertions.assertNull(e);
    }
  }
}
