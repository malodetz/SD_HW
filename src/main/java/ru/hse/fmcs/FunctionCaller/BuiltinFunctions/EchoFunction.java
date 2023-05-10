package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;

public class EchoFunction implements BuiltinFunction {
  @Override
  public int run(final Query query) {
    try {
      String responseString = String.join(" ", query.args) + "\n";
      query.output.write(responseString.getBytes());
    } catch (IOException exception) {
      return 1;
    }
    return 0;
  }
}
