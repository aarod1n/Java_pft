package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void distanceTest(){
    Point p1 = new Point(2,4);
    Point p2 = new Point(3,5);
    double distance = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y),2));

    Assert.assertEquals(p1.distance(p2), MyFirstProgram.distance(p1, p2));
    Assert.assertEquals(p1.distance(p2), distance);
  }

}
