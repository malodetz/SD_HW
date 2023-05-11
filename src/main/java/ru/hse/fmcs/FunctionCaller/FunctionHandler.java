package ru.hse.fmcs.FunctionCaller;

public interface FunctionHandler {
  int handleFunction(Query query) throws ExitException;
}
