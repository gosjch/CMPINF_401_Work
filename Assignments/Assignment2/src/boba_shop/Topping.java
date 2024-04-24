package boba_shop;

public class Topping 
{
	
	public String name; 
	private double addedCost = 0.75;
	private String[] toppingTypes = {null, "" /* blank is none */, "Mango Popping Boba", "Lychee Popping Boba", "Strawberry Popping Boba", "Mango Jelly", "Lychee Jelly", "Grass Jelly", "Red Bean", "Aloe Vera", "Pudding"};
	
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
	
	public Topping (String toppingType) 
	{
		boolean notInList = true;
		
		for(int i = 0; i < toppingTypes.length;i++) 
		{
			String thisType = toppingTypes[i];
			if(toppingType.equals(thisType)) {
				this.name = toppingType;
				notInList = false;
			}
			else if(toppingType.equals("") || toppingType == null) 
			{
				this.name = toppingType;
				this.addedCost = 0;
				notInList = false;
			}
		}
		
		if(notInList) {
			throw new IllegalArgumentException("Invalid topping type");
		}
	}
		
	public Topping (String toppingType, double addCost)
	{
		this(toppingType);
		
		if(addCost>=0) {
			this.addedCost = addCost;
		}
		else
		{
			throw new IllegalArgumentException("Added cost must be >=0");
		}
	}

}
