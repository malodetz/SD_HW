package ru.hse.fmcs;

import ru.hse.fmcs.Core.Environment;
import ru.hse.fmcs.Core.Interpreter;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    /*
     * Starts infinite execution cycle until termination command
     * will be received. Note, that execution command can be either
     * direct call to "exit" or signal from OS.
     */

    Interpreter interpreter = new Interpreter(System.in, System.out, new Environment());
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      interpreter.execute(scanner.nextLine());
    }
  }
}