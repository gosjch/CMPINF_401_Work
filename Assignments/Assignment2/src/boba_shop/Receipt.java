package boba_shop;
import java.lang.Math;

public class Receipt {
	
	private int receiptNumber;
	public double getReceiptNumber() 
	{
		return this.receiptNumber;
	}
	public void setReceiptNumber(int receiptNumber)
	{
		if(receiptNumber>=0)
		{
			this.receiptNumber = receiptNumber;
		}
		else
		{
			throw new IllegalArgumentException("Receipt ID cannot be negative.");
		}
	}
	private Cashier cashier;
	public Cashier getCashier() 
	{
		return this.cashier;
	}
	private BubbleTea tea;
	public BubbleTea getTea() 
	{
		return this.tea;
	}
	private double subtotal;
	public double getSubtotal() 
	{
		return this.subtotal;
	}
	private double tax;
	public double getTax() 
	{
		return this.tax;
	}
	private double tip;
	public double getTip() 
	{
		return this.tip;
	}
	private double total;
	public double getTotal() 
	{
		return this.total;
	}
	
	public Receipt(int recNum, Cashier cashier, BubbleTea bTea, double tip)
	{
		if(recNum <= 0) 
		{
			throw new IllegalArgumentException("Invalid receipt number, must be > 0");
		}
		else
		{
			this.receiptNumber = recNum;
		}
		
		this.cashier = cashier;
		if(bTea == null)
		{
			throw new IllegalArgumentException("BubbleTea cannot be null");
		}
		else
		{
			this.tea = bTea;
		}
		
		if(tip < 0)
		{
			throw new IllegalArgumentException("Tip must be greater than zero");
		}
		
		subtotal = tea.calculatePrice();
		tax = Math.ceil(subtotal * 0.06 * 100) / 100.0;
		this.tip = Math.ceil((tip / 100.0) * (subtotal + tax) * 100) / 100.0;
		this.total = this.subtotal + this.tax + this.tip;
	}
	
	public Receipt(int recNum, Cashier cashier, BubbleTea bTea)
	{
		this(recNum, cashier, bTea, 0);
	}
	
	public String toString()
	{
		return"Receipt ID: " + this.receiptNumber 
				+"\nCashier: " + this.cashier.getName()
				+"\nBubble Tea: " + this.tea
				+"\n\tSubtotal: " + subtotal
				+"\n\tTax: " + tax
				+"\n\tTip: " + tip
				+"\nTotal: " + total;
	}

}
