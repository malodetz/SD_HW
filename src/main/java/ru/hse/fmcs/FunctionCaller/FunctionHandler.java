package ru.hse.fmcs.FunctionCaller;

import ru.hse.fmcs.FunctionCaller.BuiltinFunctions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FunctionHandler implements FunctionCallerInterface {
  final private Map<String, BuiltinFunction> builtinFunctions;

  public FunctionHandler() {
    builtinFunctions = new HashMap<>();
    builtinFunctions.put("cat", new CatFunction());
    builtinFunctions.put("echo", new EchoFunction());
    builtinFunctions.put("exit", new ExitFunction());
    builtinFunctions.put("pwd", new PwdFunction());
    builtinFunctions.put("wc", new WcFunction());
  }

  @Override
  public String handleFunction(Query query) throws IOException, ExitException {
    builtinFunctions.getOrDefault(query.name, new SystemFunction()).run(query);
    return "";
  }
}
