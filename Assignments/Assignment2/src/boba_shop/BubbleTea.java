package boba_shop;

public class BubbleTea 
{
	
	private String size;
	public String getSize() 
	{
		return this.size;
	}
	public void setSize(String size)
	{
		if(size.equals("Large") || size.equals("Small") || size.equals("large") || size.equals("small"))
		{
			this.size = size;
		}
		else
		{
			throw new IllegalArgumentException("Size must be large or small");
		}
	}
	private Tea tea;
	public Tea getTea() 
	{
		return this.tea;
	}
	public void setTea(Tea tea)
	{
		if(tea == null)
		{
			throw new IllegalArgumentException("Tea cannot be null.");
		}
		else
		{
			this.tea = tea;
		}
	}
	public Milk milk;
	public Flavoring flavor;
	private int sugar;
	public int getSugar() 
	{
		return this.sugar;
	}
	public void setSugar(int sugar)
	{
		if(sugar < 0 || sugar > 3)
		{
			throw new IllegalArgumentException("Sugar must be in 0 - 3 range");
		}
		else
		{
			this.sugar = sugar;
		}
	}
	public boolean hasBoba;
	public Topping otherTopping;
	
	public BubbleTea(String cupSize, Tea teaType, Milk milkType, Flavoring flavorType, int sugarLevel, Topping toppingType, boolean isBoba) 
	{
		if(cupSize.equals("Large") || cupSize.equals("Small") || cupSize.equals("large") || cupSize.equals("small")) 
		{
			this.size = cupSize;
		}
		else
		{
			throw new IllegalArgumentException("Invalid cup size: ex 'Large' or 'Small'"); 
		}
		
		if(teaType == null) 
		{
			throw new IllegalArgumentException("Invalid tea type");
		}
		else
		{
			this.tea= teaType; 
		}
		
		this.milk = milkType;
		this.flavor = flavorType;
		
		if( sugarLevel >= 0 && sugarLevel <= 3)
		{
			this.sugar = sugarLevel;
		}
		else
		{
			throw new IllegalArgumentException("Invalid sugar level, range from 0 to 3"); 
		}
		
		this.hasBoba = isBoba;
		this.otherTopping = toppingType;
	}

	public double calculatePrice()
	{
		double total = 0;
		if (size.equals("Large"))
		{
			total +=5;
		}
		else
		{
			total +=4; // no other options should get through
		}
		if(this.milk == null && this.otherTopping == null && this.flavor == null)
		{
			total += ( tea.getAddedCost() );
		}
		else if(this.otherTopping == null && this.flavor == null)
		{
			total += ( tea.getAddedCost() + milk.getAddedCost() );
		}
		else if(this.milk== null && this.otherTopping == null)
		{
			total += ( tea.getAddedCost() + flavor.getAddedCost() );
		}
		else if(this.flavor == null && this.milk == null)
		{
			total += ( tea.getAddedCost() + otherTopping.getAddedCost() );
		}
		else if(this.otherTopping == null)
		{
			total += ( tea.getAddedCost() + milk.getAddedCost() + flavor.getAddedCost() );
		}
		else if(this.milk == null)
		{
			total += ( tea.getAddedCost() + flavor.getAddedCost() + otherTopping.getAddedCost() );
		}
		else if(this.flavor == null)
		{
			total += ( tea.getAddedCost() + otherTopping.getAddedCost() + milk.getAddedCost() );
		}
		else
		{
			total += ( tea.getAddedCost() + otherTopping.getAddedCost() + flavor.getAddedCost() + milk.getAddedCost() );
		}
		
		return total;
	}
	
	public String toString() {
	    String desc = "A " + size + " " + tea.name;
	    
	    if (milk != null) 
	    {
	        desc += " with " + milk.name;
	    }
	    
	    if (flavor != null) 
	    {
	        if (milk != null) 
	        {
	            desc += ", " + flavor.name;
	        } 
	        else 
	        {
	            desc += " with " + flavor.name;
	        }
	    }
	    
	    if (otherTopping != null) 
	    {
	        if (milk != null || flavor != null) 
	        { 
	            desc += ", " + otherTopping.name;
	        } 
	        else 
	        {
	            desc += " with " + otherTopping.name;
	        }
	    }
	    
	    if (sugar == 0) 
	    {
	        desc += ", no sweetness";
	    } 
	    else if (sugar == 1) 
	    {
	        desc += ", low sweetness";
	    } 
	    else if (sugar == 2) 
	    {
	        desc += ", medium sweetness";
	    } 
	    else if (sugar == 3) 
	    {
	        desc += ", high sweetness";
	    }
	    
	    if (hasBoba) 
	    {
	        desc += ", and boba.";
	    } 
	    else 
	    {
	        desc += ", and no boba.";
	    }
	    
	    return desc;
	}
}
