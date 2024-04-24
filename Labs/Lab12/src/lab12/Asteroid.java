package lab12;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Asteroid extends PhysicsObject because it has simulated velocity and collisions.
 * Asteroid implements RenderedObject because it is visually displayed on the screen.
 */
public class Asteroid extends PhysicsObject implements RenderedObject
{
	//A reference to the game this asteroid belongs to
	private Asteroids game;
	//A reference to the asteroid spawner that created this asteroid
	private AsteroidSpawner spawner;
	//This asteroid's image
	private BufferedImage img;
	//The size of this asteroid. 1 is small, 2 is medium, 3 is large.
	private int size;
	
	private double rotation;
	
	public Asteroid(Vector2 position, Vector2 velocity, int size, AsteroidSpawner spawner, Asteroids game)
	{
		super(position, velocity, 0);
		
		this.game = game;
		this.size = size;
		this.spawner = spawner;
		//Randomly set the rotation of this asteroid.
		this.rotation = Math.random() * 180;
		
		try 
		{
			//Read in the appropriate image for this size of asteroid
			if(size == 3) this.img = ImageIO.read(new File("data/largeAsteroid.png"));
			if(size == 2) this.img = ImageIO.read(new File("data/mediumAsteroid.png"));
			if(size == 1) this.img = ImageIO.read(new File("data/smallAsteroid.png"));
		}
		catch(IOException e)
		{
			System.out.println("Could not read in asteroid image");
		}
		
		//Set the collision radius of this object based on its visual information
		this.collisionRadius = this.img.getWidth() / 2.0;
	}
	
	@Override
	public void update() 
	{
		if(this.position.x < -50 || this.position.x > game.WIDTH + 50)
			spawner.destroyAsteroid(this);
		if(this.position.y < -50 || this.position.y > game.HEIGHT + 50)
			spawner.destroyAsteroid(this);
	}
	
	public void breakUpAsteroid()
	{
		game.addScore(size * 10);
		if(size > 1)
		{
			spawner.spawnAsteroid(position.copy(), size-1);
			spawner.spawnAsteroid(position.copy(), size-1);
		}
		spawner.destroyAsteroid(this);
	}
	
	@Override
	public void handleCollision(PhysicsObject other) 
	{
		//If we hit a missile that has not already exploded
		if(other instanceof Missile && ((Missile) other).isActive)
		{
			((Missile) other).isActive = false;
			breakUpAsteroid();
			game.destroyGameObject(other);
		}
		//if we hit a ship, destroy the player
		if(other instanceof Ship)
			game.destroyPlayer();
	}

	@Override
	public void paint(Graphics2D g) 
	{
		//Use the helper method in Game.java to draw our asteroid at a given rotation
		game.drawImageWithRotation(g, img, position, rotation);
		
		// We could visualize the collision radius with this:
		//g.setColor(Color.red);
		//g.fillOval((int)position.x - img.getWidth()/2, (int)position.y - img.getHeight()/2, (int)(this.collisionRadius*2), (int)(this.collisionRadius*2));
	}
}
