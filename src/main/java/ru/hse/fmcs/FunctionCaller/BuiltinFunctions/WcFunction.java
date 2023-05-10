package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;
import java.nio.ByteBuffer;

public class WcFunction implements BuiltinFunction {
  @Override
  public void run(Query query) throws IOException {
    long charCount = 0;
    long linesCount = 0;
    long wordsCount = 0;

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    int prevSymbol = -1;
    while (query.input.read(byteBuffer) != -1) {
      byteBuffer.flip();
      for (byte symbol : byteBuffer.array()) {
        ++charCount;
        if (Character.isSpaceChar(symbol)) {
          if (Character.isLetter(prevSymbol)) {
            ++wordsCount;
          }
          ++linesCount;
        }
        prevSymbol = symbol;
      }
      byteBuffer.flip();
    }

    String responseString = String.format("%d %d %d\n", linesCount, wordsCount, charCount);
    byteBuffer = ByteBuffer.wrap(responseString.getBytes());
    query.output.write(byteBuffer);
  }
}
