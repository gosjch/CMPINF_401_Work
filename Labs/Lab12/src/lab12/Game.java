package lab12;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * We extend Frame to make our game have graphics. Frames are one of Java's ways of displaying graphics in a window.
 * We implement KeyListener to allow for user keyboard input.
 */
public abstract class Game extends Frame implements KeyListener
{
	private static final long serialVersionUID = 1L;
	
	//Constants that define the size and framerate of the game
	public final int WIDTH = 1000;
	public final int HEIGHT = 1000;
	public final int FRAME_RATE = 30;

	//The objects that exist in the world of the game
	public ArrayList<GameObject> objects;
	//Objects that will be added or removed from the game in the next frame update
	protected ArrayList<GameObject> toBeCreated, toBeDestroyed;
	//If true, the game is running. If false, the game is paused
	protected boolean running = true;
	//The player's score
	public int score;

	public Game(String gameName, Color background)
	{
		super(gameName);
		this.setBounds(200, 200, WIDTH, HEIGHT);
		this.setResizable(false);
		this.setBackground(background);
		this.setVisible(true);

		//make the X button close the window
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				running = false;
			}
		});

		this.addKeyListener(this);
		
		objects = new ArrayList<GameObject>();
		toBeCreated = new ArrayList<GameObject>();
		toBeDestroyed = new ArrayList<GameObject>();
	}

	//The start method runs at the beginning of the game
	public abstract void start();

	
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) 
	{
		for(GameObject go : objects)
		{
			if(go instanceof PlayerController)
				((PlayerController) go).handleKeyPressed(e);
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		for(GameObject go : objects)
		{
			if(go instanceof PlayerController)
				((PlayerController) go).handleKeyReleased(e);
		}
	}

	/**
	 * Add a given number to the player's score.
	 * @param addedScore the amount to be added to the score.
	 */
	public void addScore(int addedScore)
	{
		this.score += addedScore;
	}
	
	/**
	 * Paints the player's score to the screen in the top-left corner.
	 * @param g The window's graphics.
	 */
	public void paintScore(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, 50);
		g.setColor(Color.WHITE);
		g.drawString("" + score, 10, 50);
	}

	/**
	 * Paints a game over message to the screen.
	 * @param g The window's graphics.
	 */
	public void paintGameOver(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, HEIGHT/2, WIDTH, 20);

		g.setColor(Color.RED);
		String gameOver = "GAME OVER";
		g.drawString(gameOver, (WIDTH/2) - (5*gameOver.length()), (HEIGHT/2) + 15);
	}
	
	/**
	 * Queue up a new game object to be added to the game during the next frame.
	 * @param go The gameobject to be added.
	 */
	public void createGameObject(GameObject go)
	{
		toBeCreated.add(go);
	}
	
	/**
	 * Queue up a new game object to be removed from the game during the next frame.
	 * @param go The gameobject to be removed.
	 */
	public void destroyGameObject(GameObject go)
	{
		toBeDestroyed.add(go);
	}
	
	/**
	 * Called once per frame.
	 */
	public void update()
	{
		//remove any game objects previously marked for removal in the previous frame
		objects.removeAll(toBeDestroyed);
		toBeDestroyed.clear();
				
		//Add all newly created gameobjects from the previous frame
		objects.addAll(toBeCreated);
		toBeCreated.clear();
				
		//update all active game objects
		for(GameObject go : objects)
		{
			go.update();
		}
	}
	
	/**
	 * Simulates all physics objects in the game.
	 */
	public void simulatePhysics()
	{
		for(int i = 0; i < objects.size(); i++)
		{
			if(!(objects.get(i) instanceof PhysicsObject))
				continue;
			
			PhysicsObject ob = (PhysicsObject)objects.get(i);
			
			//Move every physics object in objects
			ob.move();
			
			for(int j = i+1; j<objects.size(); j++)
			{
				if(!(objects.get(j) instanceof PhysicsObject))
					continue;
				PhysicsObject other = (PhysicsObject)objects.get(j);
				//Check collisions between every pair of physics objects in objects
				ob.checkCollision(other);
			}
		}
	}
	
	/**
	 * Draws an image on the screen at a given position and rotation.
	 * @param g The graphics context of this window.
	 * @param img The image to be drawn to the screen.
	 * @param position The position on the screen where the image will appear. This should be the center of the image, as an offset is calculated to handle the upper-left-corner-origin problem.
	 * @param rotation The rotation in degrees. Increasing rotation rotates counter-clockwise.
	 */
	public void drawImageWithRotation(Graphics2D g, BufferedImage img, Vector2 position, double rotation)
	{
		double radians = Math.toRadians(rotation);
		
		double locationX = img.getWidth() / 2;
		double locationY = img.getHeight() / 2;
		
		int drawLocationX = (int)(position.x - locationX);
		int drawLocationY = (int)(position.y - locationY);
		
		AffineTransform tx = AffineTransform.getRotateInstance(radians, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(img, null), null, drawLocationX, drawLocationY);
	}
	
	/**
	 * Called once per frame. Paints all visual information to the screen
	 * @param g The graphics context of the screen.
	 */
	public void paint(Graphics2D g)
	{
		//Clear all images from the previous frame, excluding the top 50 pixels (where the score is displayed).
		g.clearRect(0, 50, WIDTH, HEIGHT);
		
		//Draw every game object that can be rendered
		for(GameObject go : objects)
		{
			if(go instanceof RenderedObject)
				((RenderedObject) go).paint(g);
		}
		
		//Paint the score to the screen
		this.paintScore(g);
	}

	/**
	 * The main gameplay loop.
	 * @throws InterruptedException The loop waits to keep whatever frame rate is offered as a constant.
	 */
	public void run() throws InterruptedException
	{
		this.start();
		Graphics2D g = (Graphics2D)this.getGraphics();
		
		//Keep running the game until 'running' is false
		while(running)
		{
			//update the game runner
			this.update();
			
			//simulate physics on all PhysicsObjects
			this.simulatePhysics();

			//paint the screen
			this.paint(g);

			//sleep to maintain framerate
			TimeUnit.MILLISECONDS.sleep(1000/FRAME_RATE);
		}
		this.paintGameOver(g);
	}
}
