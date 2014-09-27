package AppletSource.Input;

/**
 * @version 1
 * @author Fox Tail Games
 */

class Key
{
	public boolean currentIsPressed;
	public boolean isPressed;
	public boolean wasPressed;
	public int KeyCode;
	
	public Key(int KeyCode)
	{
		this.KeyCode = KeyCode;
		this.isPressed = false;
		this.currentIsPressed = false;
		this.wasPressed = false;
	}
	
	public void Update()
	{
		wasPressed = isPressed;
		isPressed = currentIsPressed;
	}
	
	public String toString()
	{
		return "Is Pressed: " + isPressed +
				"\n Currently is Pressed: " + currentIsPressed +
				"\n WasPressed: " + wasPressed +
				"\nKey Code: " + KeyCode;
	}
}
