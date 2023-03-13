package ru.hse.fmcs.FunctionCaller;

public class UnexpectedFunctionName extends Exception {
    public UnexpectedFunctionName(String errorMessage) {
        super(errorMessage);
    }
}
