package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;

public class PwdFunction implements BuiltinFunction {
  @Override
  public int run(Query query) {
    try {
      String responseString = System.getProperty("user.dir");
      query.output.write(responseString.getBytes());
      query.output.write('\n');
    } catch (IOException exception) {
      return 1;
    }
    return 0;
  }
}
