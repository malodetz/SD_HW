package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemFunction implements BuiltinFunction {
  @Override
  public void run(Query query) throws IOException {
    List<String> flattenQuery = new ArrayList<>();
    flattenQuery.add(query.name);
    flattenQuery.addAll(query.args);
    ProcessBuilder processBuilder = new ProcessBuilder(flattenQuery);

    Process process = processBuilder.start();

    throw new RuntimeException("Not implemented");
//    inputStream.transferTo(process.getOutputStream());
//    process.getInputStream().transferTo(outputStream);
  }
}
