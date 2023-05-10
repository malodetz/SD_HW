package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;

public class WcFunction implements BuiltinFunction {
  static private final int BUFFER_SIZE = 1024;

  @Override
  public int run(Query query) {
    try {
      long charCount = 0;
      long linesCount = 0;
      long wordsCount = 0;

      int prevSymbol = -1;

      byte[] byteBuffer = new byte[1024];
      while (query.input.read(byteBuffer) != -1) {
        for (byte symbol : byteBuffer) {
          ++charCount;
          if (Character.isSpaceChar(symbol)) {
            if (Character.isLetter(prevSymbol)) {
              ++wordsCount;
            }
            ++linesCount;
          }
          prevSymbol = symbol;
        }
      }

      String responseString = String.format("%d %d %d\n", linesCount, wordsCount, charCount);
      query.output.write(responseString.getBytes());
    } catch (IOException exception) {
      return 1;
    }
    return 0;
  }
}
