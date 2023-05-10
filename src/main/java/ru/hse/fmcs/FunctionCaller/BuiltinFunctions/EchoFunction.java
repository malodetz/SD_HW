package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;
import java.nio.ByteBuffer;

public class EchoFunction implements BuiltinFunction {
  @Override
  public void run(final Query query) throws IOException {
    String responseString = String.join(" ", query.args);
    ByteBuffer byteBuffer = ByteBuffer.wrap(responseString.getBytes());
    query.output.write(byteBuffer);
  }
}
