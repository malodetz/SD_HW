package ru.hse.fmcs;

import ru.hse.fmcs.Core.Interpreter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
  public static void main(String[] args) {
    /*
     * Starts infinite execution cycle until termination command
     * will be received. Note, that execution command can be either
     * direct call to "exit" or signal from OS.
     */

    Interpreter interpreter = new Interpreter();
    while (true) {
      BufferedInputStream bufReader = new BufferedInputStream(System.in);
      try {
        String query = new String(bufReader.readAllBytes(), StandardCharsets.UTF_8);
        interpreter.executeCommand(query);
      } catch (IOException exception) {
        exception.printStackTrace();
        break;
      }
    }
  }
}