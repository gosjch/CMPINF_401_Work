package lab2;
import java.util.Scanner;
import java.lang.String;

public class UnitConverter 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Give a number, a space, and a unit of measurement to for the unit converter. Accepted units include: in, cm, yd, m, oz, g, lb, and kg. (e.g. 12.5 m)");
		
		String input = sc.nextLine();
		
		
		double convertedNum = 0;
		
		int space = input.indexOf(" ");
		double number = Double.parseDouble(input.substring(0, space));
		
		String unit = input.substring(space+1);
		
		String convertedUnit = "";
		final double INTOCMCONVERSION = 2.54;
		final double YDTOMCONVERSION = 0.9144;
		final double OZTOGCONVERSION = 28.349523125;
		final double LBTOKGCONVERSION = 0.45359237;
		
		if(unit.equals("in"))
		{
			convertedNum = number*INTOCMCONVERSION;
			convertedUnit = "cm";
		} 
		else if(unit.equals("cm"))
		{
			convertedNum = number/INTOCMCONVERSION;
			convertedUnit = "in";
		}
		
		if(unit.equals("yd"))
		{
			convertedNum = number*YDTOMCONVERSION;
			convertedUnit = "m";
		} 
		else if(unit.equals("m"))
		{
			convertedNum = number/YDTOMCONVERSION;
			convertedUnit = "yd";
		}
		
		if(unit.equals("oz"))
		{
			convertedNum = number*OZTOGCONVERSION;
			convertedUnit = "g";
		} 
		else if(unit.equals("g"))
		{
			convertedNum = number/OZTOGCONVERSION;
			convertedUnit = "oz";
		}

		if(unit.equals("lb"))
		{
			convertedNum = number*LBTOKGCONVERSION;
			convertedUnit = "kg";
		} 
		else if(unit.equals("kg"))
		{
			convertedNum = number/LBTOKGCONVERSION;
			convertedUnit = "lb";
		}
		
		double roundedNum = Math.round(convertedNum * 100.0) / 100.0;
	
		
		System.out.println(input+ " = " + roundedNum + " " + convertedUnit);
	}

}
