package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;

public class CatFunction implements BuiltinFunction {
  static private final int BUFFER_SIZE = 1024;

  @Override
  public int run(Query query) {
    try {
      byte[] byteBuffer = new byte[BUFFER_SIZE];
      int readBytes = query.input.read(byteBuffer);
      while (readBytes != -1) {
        query.output.write(byteBuffer, 0, readBytes);
        readBytes = query.input.read(byteBuffer);
      }
    } catch (IOException exception) {
      return 1;
    }
    return 0;
  }
}
