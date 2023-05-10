package ru.hse.fmcs.FunctionCaller;

import ru.hse.fmcs.Core.Environment;

import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;

public class Query {
  final public String name;
  final public List<String> args;

  final public ReadableByteChannel input;
  final public WritableByteChannel output;

  final public Environment environment;

  public Query(String name, List<String> args, ReadableByteChannel input, WritableByteChannel output, Environment environment) {
    this.name = name;
    this.args = args;
    this.input = input;
    this.output = output;
    this.environment = environment;
  }
}
