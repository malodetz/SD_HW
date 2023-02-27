package ru.hse.fmcs;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.Core.Environment;

public class EnvironmentTest {

  @Test
  public void constructorTest() {
    Environment env = new Environment();
    assert !SystemUtils.IS_OS_UNIX || (!env.getVariable("PWD").isEmpty());
  }

  @Test
  public void simplePutTest() {
    Environment env = new Environment();
    env.addVariable("a", "10");
    env.addVariable("b", "20");
    env.addVariable("ab", "30");
    assert (env.getVariable("a").equals("10"));
    assert (env.getVariable("b").equals("20"));
    assert (env.getVariable("ab").equals("30"));
  }

  @Test
  public void doublePutTest() {
    Environment env = new Environment();
    env.addVariable("a", "10");
    assert (env.getVariable("a").equals("10"));
    env.addVariable("a", "20");
    env.addVariable("a", "30");
    assert (env.getVariable("a").equals("30"));
  }
}
