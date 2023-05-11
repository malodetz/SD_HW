package ru.hse.fmcs;

import ru.hse.fmcs.Core.Environment;
import ru.hse.fmcs.Core.Interpreter;
import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.Parsing.Preprocessor;
import sun.misc.Signal;

import java.util.Scanner;

public class Main {
  final private static String proposal = StringUtil.colorString(">>> ", StringUtil.Color.ANSI_GREEN);
  final private static String additionalProposal = StringUtil.colorString("+++ ", StringUtil.Color.ANSI_GREEN);


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

    Interpreter interpreter = new Interpreter(System.in, System.out, System.err, new Environment());
    Scanner scanner = new Scanner(System.in);
    System.out.print(proposal);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      if (Preprocessor.needMoreInput(line)) {
        System.out.print(additionalProposal);
        while (scanner.hasNextLine()) {
          line += scanner.nextLine();
          if (!Preprocessor.needMoreInput(line)) {
            break;
          }
          System.out.print(additionalProposal);
        }
      }

      try {
        interpreter.execute(line);
      } catch (ExitException exception) {
        System.exit(0);
      }
      System.out.print(proposal);
    }
    System.out.println();
  }
}