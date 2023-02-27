package ru.hse.fmcs.Core;

import java.util.HashMap;

/**
 * @author sergey
 * Represents enviroment variables. At the beginning
 * takes current system enviroment.
 */
public class Environment {
  private final HashMap<String, String> environmentValues;

  public Environment() {
    environmentValues = new HashMap<>(System.getenv());
  }

  public void addVariable(final String variable, final String value) {
    environmentValues.put(variable, value);
  }

  public String getVariable(final String variable) {
    return environmentValues.getOrDefault(variable, "");
  }
}
