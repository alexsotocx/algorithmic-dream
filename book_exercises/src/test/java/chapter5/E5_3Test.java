package chapter5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class E5_3Test {

  @Test
  public void longestOneSequence() {
    assertEquals(8, new E5_3().longestOneSequence(1775));
  }
}