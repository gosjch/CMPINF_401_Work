package lab12;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Ship extends PhysicsObject implements RenderedObject, PlayerController 
{

	public Ship(Vector2 position, Vector2 velocity, double collisionRadius) 
	{
		super(position, velocity, collisionRadius);
	}

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics2D g) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleCollision(PhysicsObject other) 
	{
		// TODO Auto-generated method stub
		
	}



}
