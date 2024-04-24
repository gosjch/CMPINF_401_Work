package lab3;

import java.util.Scanner;

public class Logarithm 
{

	public static void main(String[] args) 
	{
		// Declaring variables
		Scanner sc = new Scanner(System.in);
		String inputString = "";
		boolean validX = false;
		boolean validBase = false;
		int i = 0;
		int q = 0;
		int x = 0;
		int b = 0;
		
		System.out.println("This program calculates the floor of a logarithm based on a value and a base.");
		while(!validX) 
		{
			System.out.println("Give me a value (x)");
			inputString = sc.nextLine();
			if(Character.isLetter(inputString.charAt(i)))
			{
				System.out.println("Input numbers only.");
			} 
			else if(inputString.indexOf(".") >= 0) 
			{
				System.out.println("Input integers only.");
			} 
			else 
			{
				x = Integer.parseInt(inputString);
			}
			
			if(x>0) 
			{
				validX = true;
				System.out.println(x);
			} 
			else 
			{
				System.out.println("Invalid input. Try again.");
			}
			
		}
		
		while(!validBase) 
		{
			System.out.println("Give me a base (b)");
			inputString = sc.nextLine();
			if(Character.isLetter(inputString.charAt(i)))
			{
				System.out.println("Input numbers only.");
			} 
			else if(inputString.indexOf(".") >= 0) 
			{
				System.out.println("Input integers only.");
			} 
			else 
			{
				b = Integer.parseInt(inputString);
			}
			
			if (b>1) 
			{
				validBase = true;
				System.out.println(b);
			} 
			else 
			{
				System.out.println("Invalid input. Try again.");
			}
			
		}
		
		q = x;
		while(!(q < b))
		{
			System.out.print(q + "/"+b +"=");
			q /= b;
			System.out.println(q);
			i++;
		}
		System.out.println("Log base " + b + " of " + x + " is about " + i);

	}

}
