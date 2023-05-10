package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PwdFunction implements BuiltinFunction {
  @Override
  public void run(Query query) throws IOException {
    String responseString = System.getProperty("user.dir");
    ByteBuffer byteBuffer = ByteBuffer.wrap(responseString.getBytes());
    query.output.write(byteBuffer);
  }
}
