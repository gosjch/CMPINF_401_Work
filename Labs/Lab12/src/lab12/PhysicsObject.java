package lab12;

public abstract class PhysicsObject implements GameObject
{
	protected Vector2 position, velocity;
	protected double collisionRadius;
	
	public PhysicsObject(Vector2 position, Vector2 velocity, double collisionRadius)
	{
		this.position = position;
		this.velocity = velocity;
		
		if(collisionRadius < 0) 
			throw new IllegalStateException("collisionRadius for physicsobject cannot be negative.");
		this.collisionRadius = collisionRadius;
	}
	
	/**
	 * To move a physics object, we add its velocity to its position.
	 */
	public void move()
	{
		position.add(velocity);
	}
	
	/**
	 * Checks if this physics object is overlapping another given Physics object. Triggers the handling of the collision if appropriate.
	 * @param other
	 * @return true if there was a collision, false otherwise.
	 */
	public boolean checkCollision(PhysicsObject other)
	{
		//Notice how we can access this other object's private variables.
		//Private visibility doesn't hide the variable from other objects if they're of the same class!
		double distance = this.position.distance(other.position);
		if(distance <= collisionRadius || distance <= other.collisionRadius)
		{
			this.handleCollision(other);
			return true;
		}
		return false;
	}
	
	/**
	 * This method will be called if this object collides with some other object.
	 * @param other The object this object collided with.
	 */
	public abstract void handleCollision(PhysicsObject other);
}
