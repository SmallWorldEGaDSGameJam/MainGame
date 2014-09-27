package AppletSource.Input;

/**
 * @version 1
 * @author Fox Tail Games
 */

class MouseButton
{
	public boolean currentIsPressed;
	public boolean isPressed;
	public boolean wasPressed;
	public int MouseCode;
	public String name;
	
	public MouseButton(int MouseCode, String name)
	{
		this.MouseCode = MouseCode;
		isPressed = false;
		currentIsPressed = false;
		wasPressed = false;
		this.name = name;
	}
	
	public void Update()
	{
		wasPressed = isPressed;
		isPressed = currentIsPressed;
	}
}
