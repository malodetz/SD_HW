package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;

public class ExitFunction implements BuiltinFunction {
  @Override
  public void run(Query query) throws IOException, ExitException {
    throw new ExitException();
  }
}
