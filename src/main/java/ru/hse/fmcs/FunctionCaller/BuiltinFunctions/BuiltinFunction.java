package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;

public interface BuiltinFunction {
  void run(final Query query) throws IOException, ExitException;
}
