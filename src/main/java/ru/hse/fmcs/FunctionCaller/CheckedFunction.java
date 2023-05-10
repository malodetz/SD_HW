package ru.hse.fmcs.FunctionCaller;

import java.io.IOException;

@FunctionalInterface
public interface CheckedFunction<T, R> {
  R apply(T t) throws IOException, ExitException;
}
