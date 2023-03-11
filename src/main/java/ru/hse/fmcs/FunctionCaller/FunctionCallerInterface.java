package ru.hse.fmcs.FunctionCaller;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.Function;

public interface FunctionCallerInterface {
    HashMap<String, CheckedFunction<Query, String>> functions = new HashMap<>();
    String handleFunction(Query query) throws WrongArgumentsException, UnexpectedFunctionName, IOException, ExitException;
}
