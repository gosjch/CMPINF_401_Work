package lab1;
import java.util.Scanner;
import java.lang.Math;


public class Pythagorean 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input a number for the first leg of the right triangle.");
		String firstLeg = sc.nextLine();
		System.out.println("Input a number for the second leg of the right triangle.");
		String secondLeg = sc.nextLine();
		
		int legA = Integer.parseInt(firstLeg);
		int legB = Integer.parseInt(secondLeg);
		
		double hypo = Math.sqrt( (Math.pow(legA, 2.0)) + (Math.pow(legB, 2.0)) ); 
		
		double hypoRounded = ((Math.round(hypo*100))/100);
		
		System.out.println("The hypotenuse of the right triangle is " + hypo + ". Rounded  to " + hypoRounded + " units.");
	}
}
