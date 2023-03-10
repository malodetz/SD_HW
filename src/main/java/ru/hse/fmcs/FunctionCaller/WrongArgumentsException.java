package ru.hse.fmcs.FunctionCaller;

public class WrongArgumentsException extends Exception {
    public WrongArgumentsException(int need_number_arguments, int have_number_arguments) {
        super("Expected " + need_number_arguments + " argument! Got " + have_number_arguments + ".");
    }
}
