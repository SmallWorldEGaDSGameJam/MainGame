package MainGame.Levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import AppletSource.GameTime;
import AppletSource.Utilities.Vector2;
import MainGame.Platform;;

public class Level {
	protected ArrayList<Platform> platforms;
	protected Vector2 spawn;
	
	public Level()
	{
		platforms = new ArrayList<Platform>();
		spawn = new Vector2();
	}
	
	public void Update(GameTime gameTime)
	{
		for(Platform p : platforms)
			p.Update(gameTime);
	}
	
	public void Draw(GameTime gameTime, Graphics2D g, Vector2 camPos)
	{
		for(Platform p : platforms)
			p.Draw(g, gameTime, camPos);
	}
}
