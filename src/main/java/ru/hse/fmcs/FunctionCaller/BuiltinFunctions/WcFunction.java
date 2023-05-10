package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;

public class WcFunction implements BuiltinFunction {
  static private final int BUFFER_SIZE = 1024;

  @Override
  public int run(Query query) {
    try {
      long bytesCount = 0;
      long linesCount = 0;
      long wordsCount = 0;

      int prevSymbol = -1;

      byte[] byteBuffer = new byte[BUFFER_SIZE];
      int readBytes = query.input.read(byteBuffer);
      while (readBytes != -1) {
        for (byte symbol : byteBuffer) {
          if (Character.isSpaceChar(symbol)) {
            if (Character.isLetter(prevSymbol)) {
              ++wordsCount;
            }
            ++linesCount;
          }
          prevSymbol = symbol;
        }
        bytesCount += readBytes;
        readBytes = query.input.read(byteBuffer);
      }

      String responseString = String.format("%d %d %d\n", linesCount, wordsCount, bytesCount);
      query.output.write(responseString.getBytes());
    } catch (IOException exception) {
      return 1;
    }
    return 0;
  }
}
