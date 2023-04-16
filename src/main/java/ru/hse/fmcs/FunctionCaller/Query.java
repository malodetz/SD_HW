package ru.hse.fmcs.FunctionCaller;

import java.util.List;

public class Query {
  final public String name;
  final public List<String> args;

  public Query(String name, List<String> args) {
    this.name = name;
    this.args = args;
  }
}
