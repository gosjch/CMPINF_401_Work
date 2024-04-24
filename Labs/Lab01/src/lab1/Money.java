package lab1;
import java.util.Scanner;



public class Money 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter an integer (no decimals) representing an amount of money from 1 dollar to 9999 dollars.");
		String inputInt = sc.nextLine();
		int dollars = Integer.parseInt(inputInt);
		
		int grands = dollars/1000;
		int bens = (dollars/100)%10;
		int sawbucks = (dollars/10)%10;
		int bucks = dollars%10;
		
		System.out.print("You have " + grands + " grands, " + bens + " Benjamins, " + sawbucks + " sawbucks, and " + bucks + " bucks for a total of " + dollars + " dollars.");

	}

}
