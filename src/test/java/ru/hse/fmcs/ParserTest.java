package ru.hse.fmcs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Parsing.ASTConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
  Path resourceDirectory = Paths.get("src", "test", "resources");

  private InputStream getInputStream(final String filename) {
    final Path sourcePath = Paths.get(resourceDirectory.toString(), filename);
    InputStream is = null;
    try {
      is = new FileInputStream(sourcePath.toAbsolutePath().toString());
    } catch (IOException e) {
      fail("Can not open resource file");
    }
    return is;
  }

  private void closeInputStream(InputStream reader) {
    try {
      reader.close();
    } catch (IOException e) {
      fail("Can not close resource file");
    }
  }

  @Test
  public void echoTest() {
    InputStream is = getInputStream("Echo.txt");
    ASTConstructor parser = new ASTConstructor(is);
    Assertions.assertDoesNotThrow(parser::consumeInput);
    closeInputStream(is);
  }
}
