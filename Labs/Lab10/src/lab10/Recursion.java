package lab10;

public class Recursion 
{

	public static int productOfDigits(int x)
	{
		if(x < 0) x *= -1; // ignore negative
		// sub-problem
		int lastDigit = x % 10;
		x /= 10;
		
		//base case
		if(x == 0) return lastDigit;
		
		// recursion
		return lastDigit * productOfDigits(x);
	}
	
	public static String arrayToString(int[] a, int index)
	{
		if(index == a.length-1) return Integer.toString(a[index]);
		return a[index] + ", " + arrayToString(a, (index += 1));
	}
	
	public static int log(int base, int value)
	{
		// problem
		value /= base;
		
		//base call
		if(value < base) return 1;
		
		// recursion
		return 1 + log(base, value);
	}
	
	public static void main(String args[]) 
	{
		int[] a = {1, 2, 3, 4, 5, 6};
		int index = 0;
		System.out.print(productOfDigits(7832));
	}

}
