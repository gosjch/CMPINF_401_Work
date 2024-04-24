package lab12;

import java.util.ArrayList;

public class AsteroidSpawner implements GameObject
{
	//A reference to the game this spawner belongs to.
	private Asteroids game;
	//Roughly, the maximum number of asteroids that should be on screen.
	private int numberOfAsteroids;
	//A list of all of the asteroids this spawner has spawned
	private ArrayList<Asteroid> spawnedAsteroids;
	
	public AsteroidSpawner(Asteroids game, int numberOfAsteroids)
	{
		this.game = game;
		this.numberOfAsteroids = numberOfAsteroids;
		this.spawnedAsteroids = new ArrayList<Asteroid>();
	}
	
	@Override
	public void update() 
	{
		//If we are not at our maximum number of asteroids, this frame we will frame one asteroid.
		if(spawnedAsteroids.size() < numberOfAsteroids)
			spawnAsteroid();
	}
	
	/**
	 * Spawns an asteroid at a random size at some random position along the edges of the screen and a random velocity.
	 */
	public void spawnAsteroid()
	{
		double x = 0;
		double y = 0;
		int size = (int)(1 + (Math.random()*3));
		
		//coin flip to check if asteroid should be an east-west asteroid or north-south
		if(Math.random() > 0.5)
		{
			//north-south asteroid
			y = (Math.random() > 0.5 ? -10 : game.HEIGHT + 10);
			x = (int)(game.WIDTH * Math.random());
		}
		else
		{
			//east-west asteroid
			x = (Math.random() > 0.5 ? -10 : game.WIDTH + 10);
			y = (int)(game.HEIGHT * Math.random());
		}
		
		this.spawnAsteroid(new Vector2(x,y), size);
	}
	
	/**
	 * Spawns an asteroid at a given position and size, with a random velocity.
	 * @param position
	 * @param size
	 */
	public void spawnAsteroid(Vector2 position, int size)
	{
		Asteroid newAsteroid = new Asteroid(position, Vector2.random(5), size, this, game);
		spawnedAsteroids.add(newAsteroid);
		game.createGameObject(newAsteroid);
	}
	
	/**
	 * Removes an existing asteroid from the game.
	 * @param toDestroy the Asteroid to be removed from the game.
	 */
	public void destroyAsteroid(Asteroid toDestroy)
	{
		spawnedAsteroids.remove(toDestroy);
		game.destroyGameObject(toDestroy);
	}
}
