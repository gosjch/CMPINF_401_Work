package boba_shop;

public class Flavoring 
{
	
	public String name; 
	private double addedCost; 
	private String[] flavorTypes = {null, "" /* blank is none */ , "Blueberry", "Coconut", "Honey", "Lemon", "Lychee", "Mango", "Peach", "Strawberry", "Pineapple", "Vanilla", "Taro", "Ube"};
	
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
	
	public Flavoring (String flavorType) 
	{
		boolean notInList = true;
		
		for(int i = 0; i < flavorTypes.length;i++) 
		{
			String thisType = flavorTypes[i];
			if(flavorType.equals(thisType)) 
			{
				this.name = flavorType;
				notInList = false;
			}
		}
		
		if(notInList) {
			throw new IllegalArgumentException("Invalid flavor type");
		}
	}
		
	public Flavoring (String flavorType, double addCost)
	{
		this(flavorType);
		
		if(addCost>=0) {
			this.addedCost = addCost;
		}
		else
		{
			throw new IllegalArgumentException("Added cost must be >=0");
		}
	}

}
