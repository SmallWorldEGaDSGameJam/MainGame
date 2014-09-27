package AppletSource.Utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import AppletSource.AppletCore;
import AppletSource.GameTime;

public class Sprite {
	protected Image image;
	
	private final Point frameNumbers;
	private Point currentFrame;
	private final Vector2 frameSize;
	
	private final int MillisecondsPerFrame;
	private int MillisecondsSinceLastFrame;
	
	public Sprite(String file, Point frameNumbers, int MillisecondsPerFrame)
	{
		this.image = AppletCore.staticLoadImage(file);
		this.frameNumbers = frameNumbers;
		currentFrame = new Point(0, 0);
		frameSize = new Vector2(image.getWidth(null)/ frameNumbers.x, image.getHeight(null) / frameNumbers.y);
		this.MillisecondsPerFrame = MillisecondsPerFrame;
	}
	
	public void Update(GameTime gameTime)
	{
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
	
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 position, Vector2 scale)
	{
		g.drawImage(image, (int)position.x, (int)position.y,
				(int)(position.x + frameSize.x * scale.x), (int)(position.x + frameSize.y * scale.y),
				(int)(frameSize.x * currentFrame.x), (int)(frameSize.y * currentFrame.y),
				(int)(frameSize.x * currentFrame.x + frameSize.x),  
				(int)(frameSize.y * currentFrame.y + frameSize.y), null);
	}
	
	public int getWidth() { return image.getWidth(null); }
	public int getHeight() { return image.getHeight(null); }
}
