package boba_shop;

public class Milk
{
	
	public String name; 
	private double addedCost; 
	private String[] milkTypes = {null, "" /* blank is none */, "Whole Milk", "2% Milk", "Half and Half", "Coconut Milk"};
	
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
	
	public Milk (String milkType) 
	{
		boolean notInList = true;
		
		for(int i = 0; i < milkTypes.length;i++) 
		{
			String thisType = milkTypes[i];
			if(milkType.equals("Half and Half") || milkType.equals("Coconut Milk")) {
				this.name = milkType;
				this.addedCost = 0.50;
				notInList = false;
			}
			else if(milkType.equals(thisType)) 
			{
				this.name = milkType;
				notInList = false;
			}
		
		}
		
		if(notInList) {
			throw new IllegalArgumentException("Invalid milk type");
		}
	}
		
	public Milk (String milkType, double addCost)
	{
		this(milkType);
		
		if(addCost>=0) {
			this.addedCost = addCost;
		}
		else
		{
			throw new IllegalArgumentException("Added cost must be >=0");
		}
	}
}

