package ru.hse.fmcs.FunctionCaller;

import ru.hse.fmcs.Core.Environment;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Query {
  final public String name;
  final public List<String> args;

  final public InputStream input;
  final public OutputStream output;

  final public Environment environment;

  public Query(String name, List<String> args, InputStream input, OutputStream output, Environment environment) {
    this.name = name;
    this.args = args;
    this.input = input;
    this.output = output;
    this.environment = environment;
  }
}
