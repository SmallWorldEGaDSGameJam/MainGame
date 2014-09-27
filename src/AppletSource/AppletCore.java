package AppletSource;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.applet.Applet;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;

/**
 * A class that, when extended, can be used to create an applet easily that
 * updates and draws continuously.
 * @version 2
 * @author Fox Tail Games
 */

public abstract class AppletCore extends Applet implements Runnable
{
	private static final long serialVersionUID = -5335799696359941507L;
	
	private BufferStrategy bufferStrategy;
	private Canvas drawArea;
	private boolean stopped = false;
	protected Point screenSize;
	
	protected MouseState mouse;
	protected KeyboardState key;
	
	/**
	 * Method basic for creation of applet. Please do not override it.
	 */
	public void init()
	{
		Thread t = new Thread(this);
		drawArea = new Canvas();
		setIgnoreRepaint(true);
		t.start();
	}

	/**
	 * Method basic for creation of applet. Please do not override it.
	 */
	public void destroy()
	{
		stopped = true;
		super.destroy();
	}

	/**
	 * Method basic for creation of applet. Please do not override it.
	 */
	public void bufferUpdate()
	{
		if(!bufferStrategy.contentsLost())
		{
			bufferStrategy.show();
		}
	}

	/**
	 * To get the bufferStrategy used in the applet. Mainly internal uses
	 * @return The buffer strategy this applet uses
	 */
	public BufferStrategy getBufferStrategy()
	{
		return bufferStrategy;
	}

	/**
	 * Method basic for creation of applet. Please do not override it.
	 */
	public void createBufferStrategy(int numBuffers)
	{
		drawArea.createBufferStrategy(numBuffers);
	}

	public Image loadImage(String name)
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(name);
		try {
			return ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); System.exit(0); }
		return null;
	}
	
	public static Image staticLoadImage(String name)
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(name);
		try {
			return ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); System.exit(0); }
		return null;
	}
	
/**
 * Aids in drawing the applet do not override
 * @param g The graphics object where all is drawn
 */
	public void update(Graphics2D g)
	{
		g.setColor(g.getBackground());
		g.fillRect(0,0,getWidth(),getHeight());
	}

	/**
	 * If you want to draw in methods other than Draw, like loadContent,
	 * you can use this.
	 */
	public Graphics2D getGraphics()
	{
		return (Graphics2D)bufferStrategy.getDrawGraphics();
	}

	/**
	 * Method basic for creation of applet. Please do not override it.
	 */    
	public void run()
	{
		resizeScreen();
		screenSize = new Point(getWidth(), getHeight());
		drawArea.setSize(new Dimension(getWidth(),getHeight()));
		drawArea.setFocusable(true);
		add(drawArea);
		key = new KeyboardState(drawArea);
		mouse = new MouseState(drawArea);
		createBufferStrategy(2);
		bufferStrategy = drawArea.getBufferStrategy();
		
		LoadContent();
		
		GameTime gameTime = new GameTime();
		
		while(!stopped)
		{
			try {Thread.sleep(6);} catch (InterruptedException e) {}
			gameTime.Update();
			
			mouse.Update();
			key.Update();
			//Flip or show the back buffer
			bufferUpdate();

			//Update any sprites or other graphical objects
			Update(gameTime);

			//Handle Drawing
			Graphics2D g = getGraphics();
			g.setBackground(Color.black);
			update(g);
			Draw(g, gameTime);

			//Dispose of graphics context
			g.dispose();
		}
	}

	/**
	 * Method where all game logic goes. Called in a loop.
	 * @param gameTime The object with the information about the time that has passed.
	 */
	public abstract void Update(GameTime gameTime);

	/**
	 * Method where all the object drawing goes. Called in a loop.
	 * @param g The graphics object representing a double buffer.
	 * @param gameTime The object with the information about the time that has passed.
	 */
	public abstract void Draw(Graphics2D g, GameTime gameTime);
	
	/**
	 * Method where all the content is loaded and the variables and object
	 * instances are initialized. Here you can use the getgraphics method to
	 * draw a loading bar.
	 */
	public abstract void LoadContent();
	
	/**
	 * A method where only resize(x, y) should be called.
	 */
	public abstract void resizeScreen();
}
