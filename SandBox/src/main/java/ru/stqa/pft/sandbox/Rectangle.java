package ru.stqa.pft.sandbox;

public class Rectangle {
  public double a, b;

  public Rectangle(double a1, double b1) {
    a = a1;
    b = b1;
  }

  public double area(){
    return a * b;
  }
}
