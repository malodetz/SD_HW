package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CatFunction implements BuiltinFunction {
  @Override
  public void run(Query query) throws IOException {
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    while (query.input.read(byteBuffer) != -1) {
      byteBuffer.flip();
      query.output.write(byteBuffer);
      byteBuffer.flip();
    }
  }
}
