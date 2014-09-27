package MainGame.Levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import AppletSource.GameTime;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;
import MainGame.GameObjects.Platform;

public class Level {
	protected ArrayList<Platform> platforms;
	protected Vector2 spawn;
	
	public Level()
	{
		platforms = new ArrayList<Platform>();
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform1.png"), new Vector2(750, 1400)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform1.png"), new Vector2(2250, 1600)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform1.png"), new Vector2(5250, 1300)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform1.png"), new Vector2(2250, 2000)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform1.png"), new Vector2(5500, 2400)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform1.png"), new Vector2(4250, 2600)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform1.png"), new Vector2(3000, 2800)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform2.png"), new Vector2(3500, 2200)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform2.png"), new Vector2(3500, 1700)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform3.png"), new Vector2(6250, 1200)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform3.png"), new Vector2(1500, 1800)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform4.png"), new Vector2(5000, 1500)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/platform4.png"), new Vector2(6750, 1000)));
		platforms.add(new Platform(new Sprite("assets/img/platforms/ground.png"), new Vector2(0, 3000 - 30)));
		spawn = new Vector2();
	}
	
	public Level(ArrayList<Platform> platforms, Vector2 spawn)
	{
		this.platforms = platforms;
		this.spawn = spawn;
		
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
