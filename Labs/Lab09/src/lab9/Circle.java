package lab9;

public class Circle 
{
	
	private double radius = 5.0;
	private String color = "Green";
	
	public Circle()
	{
	}
	
	public Circle(double radius)
	{
		this.radius = radius;
	}
	
	public Circle(double radius, String color)
	{
		this.radius = radius;
		this.color = color;
	}
	
	public double getRadius()
	{
		return this.radius;
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public void setColor(String color)
	{
		this.color = color;
	}

	public double getArea()
	{
		return Math.PI*(this.radius*this.radius);
	}
}
