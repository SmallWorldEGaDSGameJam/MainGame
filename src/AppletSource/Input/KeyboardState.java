package AppletSource.Input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * An object that helps manage keyboard input from the user.
 * @version 2
 * @author Fox Tail Games
 */

public class KeyboardState implements KeyListener {
	Key[] key;

	/**
	 * Basic constructor for the {@link KeyboardState} class. Only has to be called
	 * one in the game.
	 * 
	 * @param comp
	 *            Component to which to add the listener. The most common
	 *            way used is:<br>
	 *            <code>KeyboardState <font color="blue">key</font> = <font color="purple"><b>new</b></font> KeyboardState(<font color ="purple"><b>this</b></font>)</code>.
	 */
	public KeyboardState(Component comp) {
		comp.addKeyListener(this);
		key = new Key[600];

		for (int i = 0; i < key.length; i++) {
			key[i] = new Key(i);
		}
	}

	/**
	 * Checked if a certain key is currently pressed.
	 * 
	 * @param ButtonCode
	 *   An integer representing a key code found. Number entered in
	 *   the format of <code>KeyEvent.VK_NAMEOFKEYHERE</code>
	 * @return a boolean which returns true if key is pressed and false if not
	 */
	public boolean isButtonDown(int ButtonCode) {
		return key[ButtonCode].isPressed;
	}

	/**
	 * Checked if a certain key is currently not pressed.
	 * 
	 * @param ButtonCode
	 *   An integer representing a key code found. Number entered in
	 *   the format of <code>KeyEvent.VK_NAMEOFKEYHERE</code>
	 * @return a boolean which returns false if key is pressed and true if not
	 */
	public boolean isButtonUp(int ButtonCode)
	{
		return !key[ButtonCode].isPressed;
	}
	
	/**
	 * Checks if a certain was just pressed
	 * @param ButtonCode
	 *   An integer representing a key code found. Number entered in
	 *   the format of <code>KeyEvent.VK_NAMEOFKEYHERE</code>
	 * @return Return a boolean value that says whether the key is currently pressed but not the frame before
	 */
	public boolean isButtonTapped(int ButtonCode) {
		return (key[ButtonCode].isPressed && !key[ButtonCode].wasPressed);
	}

	
	/**
	 * Is to be called at the start of a game update
	 */
	public void Update() {
		for (Key button : key) {
			button.Update();
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		key[e.getKeyCode()].currentIsPressed = true;
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		key[e.getKeyCode()].currentIsPressed = false;
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		e.consume();
	}
}
