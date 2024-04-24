package boba_shop;

public class Cashier {
	
	private String name;
	public String getName()
	{
		return this.name;
	}
	public Receipt mostRecentTransaction;
	private int lastReceiptID = 0;
	public int getLastReceiptID()
	{
		return this.lastReceiptID;
	}
	
	public Cashier(String name)
	{
		if(name == null || name.equals("")) {
			throw new IllegalArgumentException("Cashier must have a name.");
		}
		else
		{
			this.name = name;
		}
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk, Flavoring flavor, int sugar, Topping top, boolean hasBoba, double tip)
	{
		BubbleTea newBTea = null;
		
		try
		{
			newBTea = new BubbleTea(size, tea, milk, flavor, sugar, top, hasBoba);
		}
		catch(Exception e) 
		{
			System.out.println("Parameters don't make sense.");
			return null;
		}
		
		lastReceiptID++;
		
		Receipt thisTransac = new Receipt(lastReceiptID, this, newBTea, tip);
		this.mostRecentTransaction = thisTransac;
		return thisTransac;
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk, Flavoring flavor, int sugar, Topping top, boolean hasBoba)
	{
		return orderBubbleTea(size, tea, milk, flavor, sugar, top, hasBoba, 0);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk, Flavoring flavor, int sugar, Topping top, double tip)
	{
		return orderBubbleTea(size, tea, milk, flavor, sugar, top, true, tip);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk, Flavoring flavor, int sugar, Topping top)
	{
		return orderBubbleTea(size, tea, milk, flavor, sugar, top, true, 0);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk, Flavoring flavor, int sugar, double tip)
	{
		return orderBubbleTea(size, tea, milk, flavor, sugar, null, true, tip);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk, Flavoring flavor, int sugar)
	{
		return orderBubbleTea(size, tea, milk, flavor, sugar, null, true, 0);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk, double tip)
	{
		return orderBubbleTea(size, tea, milk, null, 2, null, true, tip);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, Milk milk)
	{
		return orderBubbleTea(size, tea, milk, null, 2, null, true, 0);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea, double tip)
	{
		return orderBubbleTea(size, tea, null, null, 2, null, true, tip);
	}
	
	public Receipt orderBubbleTea(String size, Tea tea)
	{
		return orderBubbleTea(size, tea, null, null, 2, null, true, 0);
	}
	
	public Receipt orderBubbleTea(String size, int sugar, double tip)
	{
		return orderBubbleTea(size, null, null, null, 2, null, true, tip);
	}
	
	public Receipt orderBubbleTea(String size, int sugar)
	{
		return orderBubbleTea(size, null, null, null, 2, null, true, 0);
	}
	
	public Receipt orderSpecial(String size, int specNum, double tip)
	{
		BubbleTea newTea = null;
		
		try
		{
			newTea = Menu.getSpecial(size, specNum);
		}
		catch(Exception e) 
		{
			System.out.println("Parameters don't make sense.");
			return null;
		}
		
		lastReceiptID++;
		
		Receipt thisTransac = new Receipt(lastReceiptID, this, newTea, tip);
		this.mostRecentTransaction = thisTransac;
		return thisTransac;
	}
	
	public Receipt orderSpecial(String size, int specNum)
	{
		return orderSpecial(size, specNum, 0);
	}
	
	public static String getSpecials()
	{
		return "#1: Original Milk Tea\n#2: Thai Iced Tea\n#3: Chai Latte\n#4: Matcha Latte\n#5: Taro Milk Tea";
	}
	
	public static void greet() 
	{
		System.out.println("Welcome to our Boba Cafe!");
	}
	
	public String toString()
	{
		return "Name: " + this.name
				+"Most Recent Transaction: " + this.mostRecentTransaction;
	}

}
