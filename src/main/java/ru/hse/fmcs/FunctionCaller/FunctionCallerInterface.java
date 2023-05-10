package ru.hse.fmcs.FunctionCaller;

import java.io.IOException;

public interface FunctionCallerInterface {
  String handleFunction(Query query) throws IOException, ExitException;
}
