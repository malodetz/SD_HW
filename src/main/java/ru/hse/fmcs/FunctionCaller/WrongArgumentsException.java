package ru.hse.fmcs.FunctionCaller;

public class WrongArgumentsException extends Exception {
    public WrongArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}
