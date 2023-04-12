package ru.hse.fmcs.Parsing;

import ru.hse.fmcs.Core.Environment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsible for taking current environment and
 * making a substitution at all occurrences of "$"
 * followed by variable name. This is stateful entity
 * as it should track enviroment variables.
 *
 * @author sergey
 */
public class Preprocessor {
  /* Matches all entries of "$" followed by non-space symbols. */
  static private final Pattern substitutionPattern = Pattern.compile("\\$[a-zA-Z_]\\w*");
  private final Environment environment;

  /**
   * Receive value of environment variable and removes quotes from the
   * if so exist at the beginning and the end. Additionally, escape special
   * symbols (like a `'`, `"`, and so on).
   *
   * @param key name of environment variable
   * @return evaluated string
   * @author sergey
   */
  private String getEnvVariableForSubstitution(final String key) {
    StringBuilder result = new StringBuilder();
    String envValue = environment.getVariable(key);

    char quote = 0;
    if (envValue.startsWith("\"")) {
      quote = '"';
    } else if (envValue.startsWith("'")) {
      quote = '\'';
    }

    for (char symbol : envValue.toCharArray()) {
      if (quote != 0 && symbol == quote) {
        continue;
      }
      result.append(symbol);
    }

    return result.toString();
  }

  private String escapeSpecialSymbols(final String command) {
    StringBuilder result = new StringBuilder();
    for (int index = 0; index < command.length(); ++index) {
      if (command.charAt(index) == '\'' || command.charAt(index) == '"' || command.charAt(index) == '\\') {
        result.append('\\');
      }
      result.append(command.charAt(index));
    }
    return result.toString();
  }

  public Preprocessor(final Environment environment) {
    this.environment = environment;
  }

  /**
   * Makes a substitution for substrings that matches substitutionPattern
   * according to the following rules:
   * - If pattern match surrounded by quotes, then if the most outer quotes are double,
   * expansion happen. If the most outer quotes are single, then nothing changes.
   * - Otherwise use usual substitution of argument.
   *
   * @param command string represents entire command
   * @return command with substituted arguments.
   * @author sergey
   */
  public String processSubstitutions(final String command) {
    StringBuilder result = new StringBuilder();
    Matcher matcher = substitutionPattern.matcher(command);

    int firstSingleQuote = -1;
    int firstDoubleQuote = -1;

    int lastNonWrittenIndex = 0;
    while (matcher.find()) {
      int matchBeginIndex = matcher.start();
      int matchEndIndex = matcher.end();

      for (int innerIndex = lastNonWrittenIndex; innerIndex < matchBeginIndex; ++innerIndex) {
        switch (command.charAt(innerIndex)) {
          case '\\' -> ++innerIndex;
          case '\'' -> {
            if (firstSingleQuote == -1) {
              firstSingleQuote = innerIndex;
            } else if (firstDoubleQuote == -1 || firstSingleQuote < firstDoubleQuote) {
              firstSingleQuote = -1;
              firstDoubleQuote = -1;
            }
          }
          case '"' -> {
            if (firstDoubleQuote == -1) {
              firstDoubleQuote = innerIndex;
            } else if (firstSingleQuote == -1 || firstDoubleQuote < firstSingleQuote) {
              firstSingleQuote = -1;
              firstDoubleQuote = -1;
            }
          }
        }
      }

      result.append(command, lastNonWrittenIndex, matchBeginIndex);
      if (firstSingleQuote == -1 || (firstDoubleQuote < firstSingleQuote && firstDoubleQuote != -1)) {
        result.append(getEnvVariableForSubstitution(command.substring(matchBeginIndex + 1, matchEndIndex)));
      } else {
        result.append(command, matchBeginIndex, matchEndIndex);
      }
      lastNonWrittenIndex = matchEndIndex;
    }

    result.append(command, lastNonWrittenIndex, command.length());
    return result.toString();
  }
}
