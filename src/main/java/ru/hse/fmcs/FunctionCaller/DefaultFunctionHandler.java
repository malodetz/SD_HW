package ru.hse.fmcs.FunctionCaller;

import ru.hse.fmcs.FunctionCaller.BuiltinFunctions.*;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultFunctionHandler implements FunctionHandler {
  final private Map<String, BuiltinFunction> builtinFunctions;

  public DefaultFunctionHandler() {
    builtinFunctions = new HashMap<>();
    builtinFunctions.put("cat", new CatFunction());
    builtinFunctions.put("echo", new EchoFunction());
    builtinFunctions.put("exit", new ExitFunction());
    builtinFunctions.put("pwd", new PwdFunction());
    builtinFunctions.put("wc", new WcFunction());
  }

  private static class ExternalFunctionSignalHandler implements SignalHandler {
    final private Process executorProcess;

    public ExternalFunctionSignalHandler(Process process) {
      executorProcess = process;
    }

    @Override
    public void handle(Signal sig) {
      if (!executorProcess.isAlive()) {
        return;
      }
      try {
        String killCommand;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
          killCommand = String.join(" ", "taskkill", "/pid", "/f", Long.toString(executorProcess.pid()));
        } else {
          killCommand = String.join(" ", "kill", "-s", sig.getName(), Long.toString(executorProcess.pid()));
        }
        // FIXME: first invocation almost always fails with unknown pid. But why?
        Runtime.getRuntime().exec(killCommand).waitFor();
        executorProcess.destroy();
      } catch (IOException | InterruptedException ignored) {
        executorProcess.destroyForcibly();
      }
    }
  }

  private boolean isBuiltinFunction(final Query query) {
    return builtinFunctions.containsKey(query.name);
  }


  private int handleExternalCall(final Query query) {
    List<String> flattenQuery = new ArrayList<>();
    flattenQuery.add(query.name);
    flattenQuery.addAll(query.args);

    ProcessBuilder processBuilder = new ProcessBuilder(flattenQuery);
    processBuilder.environment().clear();
    processBuilder.environment().putAll(query.environment.envToMap());

    Signal signalSigInt = new Signal("INT");
    SignalHandler oldSignalHandler = null;

    try {
      Process process = processBuilder.start();

      // Set up signal handler to be able to interrupt process
      // on incoming signals.
      oldSignalHandler = Signal.handle(signalSigInt, new ExternalFunctionSignalHandler(process));

      Thread inputThread = new Thread(() -> {
        while (process.isAlive()) {
          try {
            if (query.input.available() > 0) {
              query.input.transferTo(process.getOutputStream());
            }
          } catch (IOException ignored) {
            break;
          }
        }
      });
      inputThread.start();

      Thread outputThread = new Thread(() -> {
        while (process.isAlive()) {
          try {
            if (process.getInputStream().available() > 0) {
              process.getInputStream().transferTo(query.output);
            }
          } catch (IOException ignored) {
            break;
          }
        }
      });

      outputThread.start();
      try {
        inputThread.join();
        outputThread.join();
        return process.waitFor();
      } catch (InterruptedException ignored) {
        return process.exitValue();
      }
    } catch (IOException exception) {
      return 1;
    } finally {
      // If signal comes just after process start, but
      // before setup handler, we will not set up old.
      if (oldSignalHandler != null) {
        Signal.handle(signalSigInt, oldSignalHandler);
      }
    }
  }

  @Override
  public int handleFunction(Query query) throws ExitException {
    // In this implementation builtins do not require
    // signal handling, except for cat and wc functions.
    // It would be better to compile code for them as for separate utilities,
    // but for now we will leave implementations as builtins without signal handling.
    if (isBuiltinFunction(query)) {
      return builtinFunctions.get(query.name).run(query);
    }
    return handleExternalCall(query);
  }
}
