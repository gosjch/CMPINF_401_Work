package lab1;
import java.util.Scanner;
import java.lang.Math;

public class Circle 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input a number for the radius of the circle.");
		String inputRadius = sc.nextLine();
		double r = Double.parseDouble(inputRadius);
		
		double perim = (2*Math.PI*r);
		double area = (Math.PI*(r*r));
		
		System.out.println("The circle with radius " + r + " has an area of " + area + " and a perimeter of " + perim + ".");

	}

}
