package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WcFunction implements BuiltinFunction {
  static private final int BUFFER_SIZE = 1024;

  private int internalRun(final InputStream inputStream, final OutputStream outputStream) {
    try {
      long bytesCount = 0;
      long linesCount = 0;
      long wordsCount = 0;

      int prevSymbol = -1;

      byte[] byteBuffer = new byte[BUFFER_SIZE];
      int readBytes = inputStream.read(byteBuffer);
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
        readBytes = inputStream.read(byteBuffer);
      }

      String responseString = String.format("%d %d %d\n", linesCount, wordsCount, bytesCount);
      outputStream.write(responseString.getBytes());
    } catch (IOException exception) {
      return 1;
    }
    return 0;
  }


  @Override
  public int run(Query query) {
    if (query.args.size() == 0) {
      return internalRun(query.input, query.output);
    }
    try (InputStream inputStream = new FileInputStream(query.args.get(0))) {
      return internalRun(inputStream, query.output);
    } catch (IOException exception) {
      return 1;
    }
  }
}
