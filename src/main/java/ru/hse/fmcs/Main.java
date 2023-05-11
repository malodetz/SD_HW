package ru.hse.fmcs;

import ru.hse.fmcs.Core.Environment;
import ru.hse.fmcs.Core.Interpreter;
import ru.hse.fmcs.FunctionCaller.ExitException;
import sun.misc.Signal;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    /*
     * Starts infinite execution cycle until termination command
     * will be received. Note, that execution command can be either
     * direct call to "exit" or signal from OS.
     */

    Signal signalSigInt = new Signal("INT");
    // Not the same as SignalHandler.SIG_IGN.
    Signal.handle(signalSigInt, (Signal) -> {
    });

    Interpreter interpreter = new Interpreter(System.in, System.out, new Environment());
    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");
    while (scanner.hasNextLine()) {
      try {
        interpreter.execute(scanner.nextLine());
      } catch (ExitException exception) {
        System.exit(0);
      }
      System.out.print("> ");
    }
    System.out.println();
  }
}