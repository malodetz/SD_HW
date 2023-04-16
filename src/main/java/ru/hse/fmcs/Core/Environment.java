package ru.hse.fmcs.Core;

import java.util.HashMap;

/**
 * @author sergey
 * Represents enviroment variables. At the beginning
 * takes current system enviroment.
 */
public class Environment {
  private HashMap<String, String> environmentVariables;
  private HashMap<String, String> localVariables;

  public Environment() {
    environmentVariables = new HashMap<>(System.getenv());
    localVariables = new HashMap<>();
  }

  public Environment(final Environment env) {
    Environment clone = new Environment();
    clone.environmentVariables = new HashMap<>(env.environmentVariables);
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
}
