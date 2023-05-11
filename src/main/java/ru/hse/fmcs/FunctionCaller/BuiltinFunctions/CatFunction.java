package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CatFunction implements BuiltinFunction {
  static private final int BUFFER_SIZE = 1024;

  private int internalRun(final InputStream inputStream, final OutputStream outputStream) {
    try {
      byte[] byteBuffer = new byte[BUFFER_SIZE];
      int readBytes = inputStream.read(byteBuffer);
      while (readBytes != -1) {
        outputStream.write(byteBuffer, 0, readBytes);
        readBytes = inputStream.read(byteBuffer);
      }
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
