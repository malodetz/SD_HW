package ru.hse.fmcs.FunctionCaller;

import java.io.IOException;
import java.util.HashMap;

public interface FunctionCallerInterface {
  HashMap<String, CheckedFunction<Query, String>> functions = new HashMap<>();

  String handleFunction(Query query) throws FunctionCallException, UnexpectedFunctionName, IOException, ExitException;
}
