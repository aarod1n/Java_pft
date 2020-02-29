package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {

  @Test
  public void testPrime(){
    Assert.assertTrue(Prime.isPrime(Integer.MAX_VALUE));
  }

  @Test(enabled = false)
  public void testPrimeLong(){
    long lNamber = Integer.MAX_VALUE;
    Assert.assertTrue(Prime.isPrime(lNamber));
  }

  @Test
  public void testPrimeFast(){
    Assert.assertTrue(Prime.isPrimeFast(Integer.MAX_VALUE));
  }
}
