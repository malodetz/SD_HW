package ru.hse.fmcs.FunctionCaller.BuiltinFunctions;

import ru.hse.fmcs.FunctionCaller.Query;

import java.io.*;

public class WcFunction implements BuiltinFunction {
  private final static int BUFFER_SIZE = 1024;

  private int internalRun(final InputStream inputStream, final OutputStream outputStream) {
    try {
      char[] buffer = new char[BUFFER_SIZE];
      StringBuilder out = new StringBuilder();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      for (int numRead; (numRead = inputStreamReader.read(buffer, 0, buffer.length)) > 0; ) {
        out.append(buffer, 0, numRead);
      }
      String line = out.toString();

      long bytesCount = line.length();
      long linesCount = line.lines().count();
      long wordsCount = line.split("\\s+").length;

      String responseString = String.format("%d %d %d\n", linesCount, wordsCount, bytesCount);
      outputStream.write(responseString.getBytes());
    } catch (IOException exception) {
      return 1;
    }
    return 0;
  }


  @Override
  public int run(Query query) {
    if (query.args.size() == 0) {
      return internalRun(query.input, query.output);
    }
    try (InputStream inputStream = new FileInputStream(query.args.get(0))) {
      return internalRun(inputStream, query.output);
    } catch (IOException exception) {
      return 1;
    }
  }
}
