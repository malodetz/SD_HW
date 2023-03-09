package ru.hse.fmcs.FunctionCaller;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws WrongArgumentsException;
}
