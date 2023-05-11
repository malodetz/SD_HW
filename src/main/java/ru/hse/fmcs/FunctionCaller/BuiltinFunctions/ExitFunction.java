package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.FunctionCaller.Query;

public class ExitFunction implements BuiltinFunction {
  @Override
  public int run(Query query) throws ExitException {
    throw new ExitException();
  }
}
