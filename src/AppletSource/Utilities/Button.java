package AppletSource.Utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import AppletSource.AppletCore;
import AppletSource.Input.MouseState;

/**
 * This class is a basic class made to draw a button with the Graphics2D object.
 * @author ricardodelfingarcia
 * @version 2.0
 */
public class Button
{
	protected Image unselectedImage;
	protected Image selectedImage;
	
	protected Rectangle rect;
	
	protected boolean isPressed;
	protected boolean isMouseOver;
	protected boolean isTapped;
	protected boolean useMouseOver;
	
	
	/**
	 * Main constructor for the class <code>Button</code>.
	 * @param unselectedImage The image of the button when not pressed.
	 * @param selectedImage The image of the button when pressed.
	 * @param rect The containing rectangle of the images for the rest of the program
	 */
	public Button(Image unselectedImage, Image selectedImage, Rectangle rect, boolean turnOnOnMouseOver)
	{
		this.unselectedImage = unselectedImage;
		this.selectedImage = selectedImage;
		this.rect = rect;
		this.isPressed = false;
		this.isMouseOver = false;
		this.isTapped = false;
		this.useMouseOver = turnOnOnMouseOver;
	}
	
	/**
	 * Method like the main contructor for the class <code>Button</code> but uses a name
	 * to generate a default path.
	 * @param name The name of the image, where the path would be:
	 * "sprites/Buttons/" + name + "Released" + extension; for the released image
	 * and "sprites/Buttons/" + name + "Pressed" + extension; for the pressed image
	 * @param extension The image extension or format, like ".png" or ".jpg" without the ".".
	 * @param rect The containing rectangle for the images for the rest of the program
	 */
	public Button(String name, String extension, Rectangle rect, boolean turnOnOnMouseOver)
	{
		this.unselectedImage = AppletCore.staticLoadImage("sprites/Buttons/" + name +
				"Released." + extension);
		this.selectedImage = AppletCore.staticLoadImage("sprites/Buttons/" + name +
				"Pressed." + extension);
		this.rect = rect;
		this.isPressed = false;
		this.isMouseOver = false;
		this.isTapped = false;
		this.useMouseOver = turnOnOnMouseOver;
	}
	
	public void Update(MouseState mouse)
	{
		if(mouse.isButtonDown(MouseEvent.BUTTON1) && rect.contains(mouse.getMousePosition()))
			isPressed = true;
		else
			isPressed = false;
		
		if(rect.contains(mouse.getMousePosition()))
			isMouseOver = true;
		else
			isMouseOver = false;
		
		if(mouse.isButtonUp(MouseEvent.BUTTON1) && mouse.wasButtonDown(MouseEvent.BUTTON1) && rect.contains(mouse.getMousePosition()))
			isTapped = true;
		else
			isTapped = false;
	}
	
	public boolean isTapped()
	{
		return isTapped;
	}
	
	public boolean isPressed()
	{
		return isPressed;
	}
	
	public void Draw(Graphics2D g)
	{
		if(useMouseOver)
		{
			if(isMouseOver)
				g.drawImage(selectedImage, rect.x, rect.y, rect.width, rect.height, null);
			else
				g.drawImage(unselectedImage, rect.x, rect.y, rect.width, rect.height, null);
		}
		else
		{
			if(isPressed)
				g.drawImage(selectedImage, rect.x, rect.y, rect.width, rect.height, null);
			else
				g.drawImage(unselectedImage, rect.x, rect.y, rect.width, rect.height, null);
		}
	}
}
