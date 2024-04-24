package boba_shop;

public class Menu {
	
	public static BubbleTea makeBubbleTea(String size, Tea tea, Milk milk, Flavoring flavor, int sugar, Topping topping, boolean hasBoba)
	{
		try 
		{
			BubbleTea newTea = new BubbleTea(size, tea, milk, flavor, sugar, topping, hasBoba);
			return newTea;
		}
		catch(Exception e)
		{
			System.out.print("Something went wrong.");
			return null;
		}
	}

	public static BubbleTea getSpecial(String size, int specialNum)
	{
		BubbleTea newTea = null;
		if(specialNum < 1 || specialNum >5)
		{
			throw new IllegalArgumentException("Special number outside of 1-5 range");
		}
		
		if(specialNum==1)
		{
			newTea = new BubbleTea(size, new Tea("Black Tea"), new Milk("Whole Milk"), null, 2, null, true);
			
		}
		else if(specialNum==2)
		{
			newTea = new BubbleTea(size, new Tea("Star anise spiced assam tea"), new Milk("Half and Half"), null, 3, null, true);
			
		}
		else if(specialNum==3)
		{
			newTea = new BubbleTea(size, new Tea("Masala spiced assam tea"), new Milk("Whole Milk"), null, 1, null, false);
			
		}
		else if(specialNum==4)
		{
			newTea = new BubbleTea(size, new Tea("Matcha green tea"), new Milk("Whole Milk"), null, 2, null, false);
			
		}
		else if(specialNum==5)
		{
			newTea = new BubbleTea(size, new Tea("Black Tea"), new Milk("Half and Half"), new Flavoring("Taro"), 3, null, true);
			
		}
		
		return newTea;
	}
	
	public static String getSpecials()
	{
		return "#1: Original Milk Tea\n#2: Thai Iced Tea\n#3: Chai Latte\n#4: Matcha Latte\n#5: Taro Milk Tea";
	}

}
