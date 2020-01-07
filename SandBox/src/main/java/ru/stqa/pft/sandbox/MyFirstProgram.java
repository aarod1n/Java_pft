package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {

		Square square = new Square(5);
		Rectangle rectangle = new Rectangle(3, 7);

		System.out.println(square.area());
		System.out.println(rectangle.area());

		Point p1 = new Point(7,7);
		Point p2 = new Point(12,4);

		System.out.println("Расстояние от p1 до p2 = " + distance(p1, p2));
		System.out.println("Расстояние от p1 до p2 = " + p1.distance(p2));

		//Должно быть равно p1 до p2
		System.out.println("Расстояние от p2 до p1 = " + p2.distance(p1));

	}

	//Задание №2
	public static double distance(Point p1, Point p2){
		return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y),2));
	}

}