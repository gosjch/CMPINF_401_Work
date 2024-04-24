package lab12;

public class Vector2 
{
	public double x, y;
	
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Generate a random vector2 object
	 * @param max The maximum value for each dimension of the vector
	 * @return The generated Vector2 object
	 */
	public static Vector2 random(double max)
	{
		double x = Math.random()*max;
		double y = Math.random()*max;
		
		//Coin flips to see if we should flip either of the dimensions
		if(Math.random() > 0.5) x = -x;
		if(Math.random() > 0.5) y = -y;
		
		return new Vector2(x,y);
	}
	
	/**
	 * Finds the distance between this and another vector2. 
	 * @param other Another Vector2 object.
	 * @return The euclidean distance between this and other.
	 */
	public double distance(Vector2 other)
	{
		double xDiff = other.x - this.x;
		double yDiff = other.y - this.y;
		return Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
	}
	
	/**
	 * Add another Vector2 to this one.
	 * @param other
	 */
	public void add(Vector2 other)
	{
		x += other.x;
		y += other.y;
	}
	
	/**
	 * Generate a deep copy of this Vector2
	 * @return
	 */
	public Vector2 copy()
	{
		return new Vector2(this.x, this.y);
	}
}
