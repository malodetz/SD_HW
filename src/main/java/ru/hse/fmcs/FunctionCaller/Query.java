package ru.hse.fmcs.FunctionCaller;

import java.util.ArrayList;

public class Query {
    String name;
    ArrayList<String> args;

    public Query(String name_, ArrayList<String> args_) {
        name = name_;
        args = args_;
    }
}
