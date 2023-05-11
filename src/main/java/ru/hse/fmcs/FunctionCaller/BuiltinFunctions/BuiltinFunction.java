package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.FunctionCaller.Query;

public interface BuiltinFunction {
  int run(final Query query) throws ExitException;
}
