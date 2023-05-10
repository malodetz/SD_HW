package ru.hse.fmcs.FunctionCaller;

import ru.hse.fmcs.FunctionCaller.BuiltinFunctions.*;

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

  private int handleExternalCall(final Query query) {
    try {
      List<String> flattenQuery = new ArrayList<>();
      flattenQuery.add(query.name);
      flattenQuery.addAll(query.args);

      ProcessBuilder processBuilder = new ProcessBuilder(flattenQuery);
      processBuilder.environment().clear();
      processBuilder.environment().putAll(query.environment.envToMap());

      Process process = processBuilder.start();
      Thread inputThread = new Thread(() -> {
        try {
          query.input.transferTo(process.getOutputStream());
        } catch (IOException exception) {

        }
      });
      inputThread.start();

      Thread outputThread = new Thread(() -> {
        try {
          process.getInputStream().transferTo(query.output);
        } catch (IOException exception) {

        }
      });
      outputThread.start();

      process.waitFor();
      return process.exitValue();
    } catch (IOException | InterruptedException exception) {
      return 1;
    }
  }

  @Override
  public int handleFunction(Query query) throws ExitException {
    if (builtinFunctions.containsKey(query.name)) {
      return builtinFunctions.get(query.name).run(query);
    }
    return handleExternalCall(query);
  }
}
