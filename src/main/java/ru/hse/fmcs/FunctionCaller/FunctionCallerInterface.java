package ru.hse.fmcs.FunctionCaller;

import java.util.HashMap;
import java.util.concurrent.Callable;

public interface FunctionCallerInterface {
    HashMap<String, Callable<Query>> functions = new HashMap<>();
    String HandleFunction(Query query);
}
