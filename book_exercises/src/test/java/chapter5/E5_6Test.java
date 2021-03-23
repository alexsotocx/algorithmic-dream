package chapter5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class E5_6Test {

  @Test
  public void minConversion() {
    assertEquals(2, new E5_6().minConversion(29, 15));
    assertEquals(2, new E5_6().minConversion(15, 29));
    assertEquals(0, new E5_6().minConversion(15, 15));
    assertEquals(0, new E5_6().minConversion(0, 0));
    assertEquals(1, new E5_6().minConversion(1, 0));
  }
}
