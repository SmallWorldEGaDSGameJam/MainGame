package AppletSource.Input;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.*;

/**
 * A class that helps manage mouse input from the user
 * @version 2
 * @author Fox Tail Games
 */

public class MouseState implements MouseListener, MouseMotionListener, MouseWheelListener
{
	
	MouseButton[] Buttons;
	
	Point position = new Point();
	int MouseWheelPosition;

	/**
	 * Basic constructor for the {@link MouseState} class. Only has to be called one in the game. 
	 * @param comp Component to which to add the listener. The most common vatiable used is <code>this</code>.
	 */
	public MouseState(Component comp)
	{
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		comp.addMouseWheelListener(this);
		Buttons = new MouseButton[4];
		
		for(int i = 0; i < Buttons.length; i++)
		{
			String name;
			name = "";
			
			switch (i)
			{
			case 0: name = "NoButton"; break;
			case 1: name = "LeftButton"; break;
			case 2: name = "CenterButton"; break;
			case 3: name = "RightButton"; break;
			}
			
			Buttons[i] = new MouseButton(i, name);
		}
	}
	
	public Point getMousePosition()
	{
		return position;
	}
	
	public boolean isButtonDown(int ButtonCode)
	{
		return Buttons[ButtonCode].isPressed;
	}
	
	public boolean isButtonUp(int ButtonCode)
	{
		return !Buttons[ButtonCode].isPressed;
	}
	
	public boolean isButtonTapped(int ButtonCode)
	{
		return (Buttons[ButtonCode].isPressed && !Buttons[ButtonCode].wasPressed);
	}
	
	public boolean isButtonReleased(int ButtonCode)
	{
		return (!Buttons[ButtonCode].isPressed && Buttons[ButtonCode].wasPressed);
	}
	
	public boolean wasButtonDown(int ButtonCode)
	{
		return Buttons[ButtonCode].wasPressed;
	}
	
	public boolean wasButtonUp(int ButtonCode)
	{
		return !Buttons[ButtonCode].wasPressed;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		MouseWheelPosition += e.getWheelRotation();
		e.consume();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		position = new Point(e.getX(), e.getY());
		e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		position = new Point(e.getX(), e.getY());
		e.consume();
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Do nothing
		e.consume();
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// Do nothing
		e.consume();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		Buttons[e.getButton()].currentIsPressed = true;
		e.consume();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		Buttons[e.getButton()].currentIsPressed = false;
		e.consume();
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		e.consume();
	}
	
	public void Update()
	{
		for(MouseButton button : Buttons)
		{
			button.Update();
		}
	}
}
