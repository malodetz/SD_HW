package ru.hse.fmcs.Core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sergey
 * Represents enviroment variables. At the beginning
 * takes current system enviroment.
 */
public class Environment {
  private final HashMap<String, String> environmentVariables;
  private final HashMap<String, String> localVariables;

  public Environment() {
    environmentVariables = new HashMap<>(System.getenv());
    localVariables = new HashMap<>();
  }

  public Environment(final Environment env) {
    environmentVariables = new HashMap<>(env.environmentVariables);
    localVariables = new HashMap<>();
  }

  public void exportVariable(final String variable, final String value) {
    localVariables.remove(variable);
    environmentVariables.put(variable, value);
  }

  public void addVariable(final String variable, final String value) {
    if (environmentVariables.containsKey(variable)) {
      environmentVariables.put(variable, value);
    } else {
      localVariables.put(variable, value);
    }
  }

  public String getVariable(final String variable) {
    if (environmentVariables.containsKey(variable)) {
      return environmentVariables.get(variable);
    }
    return localVariables.getOrDefault(variable, "");
  }

  public Map<String, String> envToMap() {
    return new HashMap<>(environmentVariables);
  }

  public Map<String, String> localToMap() {
    return new HashMap<>(localVariables);
  }
}
