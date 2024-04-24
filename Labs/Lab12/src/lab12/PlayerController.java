package lab12;

import java.awt.event.KeyEvent;

public interface PlayerController extends GameObject
{
	/**
	 * This method will be called if the player presses a key on their keyboard.
	 * @param e An object representing the event of the player pressing a key. You can look into this object to figure out which key was pressed.
	 */
	public void handleKeyPressed(KeyEvent e);
	
	/**
	 * This method will be called if the player releases a key they had previous pressed on their keyboard.
	 * @param e An object representing the event of the player releasing a key.
	 */
	public void handleKeyReleased(KeyEvent e);
}
