package ru.hse.fmcs.Parsing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Core.Environment;

public class PreprocessorTest {
  private Environment env;
  private Preprocessor preprocessor;

  @BeforeEach
  public void prepareEnvAndPreprocessor() {
    env = new Environment();
    preprocessor = new Preprocessor(env);
  }

  @Test
  public void simpleStringTest() {
    String query = "echo 'Hello world!'";
    assert (query.equals(preprocessor.processSubstitutions(query)));
  }

  @Test
  public void simpleSubstitution() {
    env.addVariable("a", "10");
    String query = "$a";
    String expectedQuery = "10";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));
  }

  @Test
  public void escapeSpecialCharactersTest() {
    env.addVariable("a", "\\|");
    String query = "echo a $a cat";
    String expectedQuery = "echo a \\| cat";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));
  }

  @Test
  public void substitutionInDoubleQuotes() {
    env.addVariable("a", "10");
    String query = "\"$a\"";
    String expectedQuery = "\"10\"";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));
  }

  @Test
  public void substitutionInSingleQuotes() {
    env.addVariable("a", "10");
    String query = "'$a'";
    String expectedQuery = "'$a'";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));
  }

  @Test
  public void emptyVarTest() {
    String query = "echo $";
    String expectedQuery = "echo $";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));
  }
}
