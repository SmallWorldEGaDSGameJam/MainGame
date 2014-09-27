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
	
	public Sprite(String file)
	{
		this(file, new Point(1, 1), 0);
	}
	
	public void Update(GameTime gameTime)
	{
		MillisecondsSinceLastFrame+= gameTime.getMillisecondsPerFrame();
		
		if(MillisecondsSinceLastFrame >= MillisecondsPerFrame)
		{
			MillisecondsSinceLastFrame = 0;
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
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
		GameRectangle dRect = new GameRectangle(0, 0, frameSize.x * scale.x, frameSize.y * scale.y);
		if(scale.x > 0)
			dRect.setX(position.x);
		else 
			dRect.setX(position.x - dRect.getWidth());
		
		if(scale.y > 0)
			dRect.setY(position.y);
		else
			dRect.setY(position.y - dRect.getHeight());
		
		g.drawImage(image, (int)dRect.getX(), (int)dRect.getY(),
				(int)(dRect.getX() + dRect.getWidth()), (int)(dRect.getY() + dRect.getHeight()),
				(int)(frameSize.x * currentFrame.x), (int)(frameSize.y * currentFrame.y),
				(int)(frameSize.x * currentFrame.x + frameSize.x),  
				(int)(frameSize.y * currentFrame.y + frameSize.y), null);
	}
	
	public int getWidth() { return (int) frameSize.x; }
	public int getHeight() { return (int) frameSize.y; }
}
