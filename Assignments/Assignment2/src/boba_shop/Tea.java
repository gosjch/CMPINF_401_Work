package boba_shop;

public class Tea
{
	
	public String name; // Name of this variety of tea
	private double addedCost; // Cost associated with this type of tea (most don't have added cost)
	private String[] teaTypes = {"Masala spiced assam tea", "Matcha green tea", "Star anise spiced assam tea", "Original Milk Tea", "Thai Iced Tea", "Chai Latte", "Matcha Latte", "Taro Milk Tea", "Black Tea", "Green Tea"};
	
	public double getAddedCost() 
	{
		return this.addedCost;
	}
	public void setAddedCost(double addedCost)
	{
		if(addedCost>=0)
		{
			this.addedCost = addedCost;
		}
		else
		{
			throw new IllegalArgumentException("Added cost cannot be negative.");
		}
	}
	
	
	public Tea (String teaType) 
	{
		boolean notInList = true;
		
		for(int i = 0; i < teaTypes.length;i++) 
		{
			String thisType = teaTypes[i];
			if(teaType.equals("Thai Iced Tea") || teaType.equals("Taro Milk Tea") || teaType.equals("Star anise spiced assam tea")) {
				this.name = teaType;
				this.addedCost = 0.50;
				notInList = false;
			}
			else if(teaType.equals(thisType)) 
			{
				this.name = teaType;
				notInList = false;
			}
			
		}
		
		if(notInList) {
			throw new IllegalArgumentException("Invalid tea type");
		}
	}
		
	public Tea (String teaType, double addCost)
	{
		this(teaType);
		
		if(addCost>=0) {
			this.addedCost = addCost;
		}
		else
		{
			throw new IllegalArgumentException("Added cost must be >=0");
		}
	}
	
	public String toString(Tea exampleTea) {
		if(name.equals("Original Milk Tea")) {
			return "Black tea with whole milk, medium sweetness, and boba";
		}
		else if(name.equals("Thai Iced Tea"))
		{
			return "Star anise spiced assam tea with half and half (+$0.50), high sweetness, and boba";
		}
		else if(name.equals("Chai Latte"))
		{
			return "Masala spiced assam tea with whole milk, and low sweetness (no boba)";
		}
		else if(name.equals("Matcha Latte"))
		{
			return "Matcha green tea with whole milk and medium sweetness (no boba)";
		}
		else if(name.equals("Taro Milk Tea"))
		{
			return "Black tea with half and half  (+$0.50), high sweetness, taro flavoring, and boba";
		}
		else
		{
			return null;
		}
	}
}

