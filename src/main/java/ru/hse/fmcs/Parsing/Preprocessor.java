package ru.hse.fmcs.Parsing;

import ru.hse.fmcs.Core.Environment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsible for taking current environment and
 * making a substitution at all occurrences of "$"
 * followed by variable name. This is stateful entity
 * as it should track environment variables.
 *
 * @author sergey
 */
public class Preprocessor {
  /* Matches all entries of "$" followed by non-space symbols. */
  static private final Pattern substitutionPattern = Pattern.compile("\\$[a-zA-Z_][a-z_A-Z0-9]*");
  private final Environment environment;

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
        result.append(environment.getVariable(command.substring(matchBeginIndex + 1, matchEndIndex)));
      } else {
        result.append(command, matchBeginIndex, matchEndIndex);
      }
      lastNonWrittenIndex = matchEndIndex;
    }

    result.append(command, lastNonWrittenIndex, command.length());
    return result.toString();
  }

  public static String removeQuotes(String token) {
    StringBuilder builder = new StringBuilder();

    int firstSingleQuote = -1;
    int firstDoubleQuote = -1;

    for (int innerIndex = 0; innerIndex < token.length(); ++innerIndex) {
      switch (token.charAt(innerIndex)) {
        case '\\' -> {
          builder.append(token.charAt(innerIndex));
          ++innerIndex;
        }
        case '\'' -> {
          if (firstSingleQuote == -1) {
            if (firstDoubleQuote == -1) {
              firstSingleQuote = innerIndex;
              continue;
            }
          } else {
            firstSingleQuote = -1;
            continue;
          }
        }
        case '"' -> {
          if (firstDoubleQuote == -1) {
            if (firstSingleQuote == -1) {
              firstDoubleQuote = innerIndex;
              continue;
            }
          } else {
            firstDoubleQuote = -1;
            continue;
          }
        }
      }
      builder.append(token.charAt(innerIndex));
    }
    return builder.toString();
  }
}
