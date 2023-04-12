package ru.hse.fmcs.Parsing;

public class ParsingException extends Exception {
  private final Exception cause;

  ParsingException(Exception cause) {
    this.cause = cause;
  }
}
