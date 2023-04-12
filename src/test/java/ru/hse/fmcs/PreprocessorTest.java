package ru.hse.fmcs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Core.Environment;
import ru.hse.fmcs.Parsing.Preprocessor;

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
  public void removeQuotesFromEnvVarTest() {
    env.addVariable("a", "\"echo a\"");
    String query = "$a";
    String expectedQuery = "echo a";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));

    env.addVariable("b", "'echo b'");
    String anotherQuery = "$b";
    String expectedAnotherQuery = "echo b";
    System.out.println(preprocessor.processSubstitutions(anotherQuery));
    assert (expectedAnotherQuery.equals(preprocessor.processSubstitutions(anotherQuery)));
  }

  @Test
  public void removeAllQuotesFromEnvVarTest() {
    env.addVariable("a", "'a'b'c''d''e'f'g'");
    String query = "$a";
    String expectedQuery = "abcdefg";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));
  }

  @Test
  public void escapeSpecialCharactersTest() {
    env.addVariable("a", "|");
    String query = "echo a $a cat";
    String expectedQuery = "echo a \\| cat";
    assert (expectedQuery.equals(preprocessor.processSubstitutions(query)));
  }

  @Test
  public void removeAllQuotesFromEnvVarWithEscapingTest() {
    env.addVariable("a", "\"Special 'case \"of 'string'\"'!\"");
    String query = "$a";
    String expectedQuery = "Special \\'case of \\'string\\'\\'!";
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
