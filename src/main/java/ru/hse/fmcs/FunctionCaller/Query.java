package ru.hse.fmcs.FunctionCaller;

import java.util.ArrayList;

public class Query {
    String name;
    ArrayList<String> args;

    public Query(String name, ArrayList<String> args) {
        this.name = name;
        this.args = args;
    }
}
