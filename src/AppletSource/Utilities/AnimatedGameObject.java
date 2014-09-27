package AppletSource.Utilities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Image;

import AppletSource.GameTime;

/**
 * This class lets you create an object that draws on the screen and is made up of frames
 * @author Fox Tail Games
 * @version 1
 */
public class AnimatedGameObject extends GameObject
{	
	private final Point frameNumbers;
	private Point currentFrame;
	private final Vector2 frameSize;
	
	private final int MillisecondsPerFrame;
	private int MillisecondsSinceLastFrame;
	
	/**
	 * The Main constructor for the object
	 * @param image The image sprite that will be used for this object
	 * @param initialPos The Start position
	 * @param frameNumbers The number of frames in the x and y.
	 * @param MillisecondsPerFrame The time between two frames in milliseconds
	 */
	public AnimatedGameObject(Image image, Vector2 initialPos, Point frameNumbers, int MillisecondsPerFrame)
	{
		super(image, initialPos);
		this.frameNumbers = frameNumbers;
		currentFrame = new Point(0, 0);
		frameSize = new Vector2(image.getWidth(null)/ frameNumbers.x, image.getHeight(null) / frameNumbers.y);
		this.MillisecondsPerFrame = MillisecondsPerFrame;
	}
	
	/**
	 * The method where the position and current frame is set. The frames take
	 * on the logic of writing. In other words, the frames go to the right (x)
	 * until they reach the end where they go to the next line (y)
	 * @param gameTime The object that stores the time passed
	 */
	public void Update(GameTime gameTime)
	{
		super.Update(gameTime);
		
		MillisecondsSinceLastFrame+= gameTime.getMillisecondsPerFrame();
		
		if(MillisecondsSinceLastFrame >= MillisecondsPerFrame)
		{
			currentFrame.x++;
			if(currentFrame.x >= frameNumbers.x)
			{
				currentFrame.y++;
				currentFrame.x = 0;
				if(currentFrame.y >= frameNumbers.y)
					currentFrame.y = 0;
			}
		}
	}
	
	/**
	 * The method that draws the object on the screen.
	 * @param g The graphics where the object will be drawn.
	 * @param gameTime The object that stores the time passed
	 */
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos)
	{
		g.drawImage(image, (int)(position.x - camPos.x), (int)(position.y - camPos.y),
				(int)(position.x - camPos.x + frameSize.x), (int)(position.x - camPos.y + frameSize.y),
				(int)(frameSize.x * currentFrame.x), (int)(frameSize.y * currentFrame.y),
				(int)(frameSize.x * currentFrame.x + frameSize.x),  
				(int)(frameSize.y * currentFrame.y + frameSize.y), null);
	}
	
	/**
	 * Getter for position
	 * @return the current position
	 */
	public Vector2 getPosition() {return position;}
	/**
	 * Getter for velocity
	 * @return the current velocity
	 */
	public Vector2 getVelocity() {return velocity;}
	/**
	 * Getter for acceleration
	 * @return the current acceleration
	 */
	public Vector2 getAcceleration() {return acceleration;}
	
	
	/**
	 * Sets the position to a new value
	 * @param newValue The new value to which the position will be set
	 */
	public void setPosition(Vector2 newValue) {position = newValue;}
	/**
	 * Sets the velocity to a new value
	 * @param newValue The new value to which the velocity will be set
	 */
	public void setVelocity(Vector2 newValue) {velocity = newValue;}
	/**
	 * Sets the acceleration to a new value
	 * @param newValue The new value to which the acceleration will be set
	 */
	public void setAcceleration(Vector2 newValue) {acceleration = newValue;}
}
