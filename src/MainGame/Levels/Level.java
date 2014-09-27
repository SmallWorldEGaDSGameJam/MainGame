package MainGame.Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import AppletSource.GameTime;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;
import MainGame.GameObjects.Enemy;
import MainGame.GameObjects.Platform;
import MainGame.GameObjects.Player;

public class Level {
	protected ArrayList<Platform> platforms;
	protected ArrayList<Enemy> enemy;
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
		platforms.add(new Platform(new Sprite("assets/img/platforms/ground.png"), new Vector2(0, 2970)));
		
		enemy = new ArrayList<Enemy>();
		enemy.add(new Enemy(new Sprite("assets/img/enemies/enemy1.png"), new Vector2(2000, 2790), new Sprite("assets/img/enemies/enemy1.png")));
		enemy.add(new Enemy(new Sprite("assets/img/enemies/enemy1.png"), new Vector2(4250, 2790), new Sprite("assets/img/enemies/enemy1.png")));
		enemy.add(new Enemy(new Sprite("assets/img/enemies/enemy1.png"), new Vector2(6250, 1020), new Sprite("assets/img/enemies/enemy1.png")));
		enemy.add(new Enemy(new Sprite("assets/img/enemies/enemy1.png"), new Vector2(5000, 1320), new Sprite("assets/img/enemies/enemy1.png")));
		enemy.add(new Enemy(new Sprite("assets/img/enemies/enemy1.png"), new Vector2(1650, 1620), new Sprite("assets/img/enemies/enemy1.png")));
		spawn = new Vector2();
	}
	
	public Level(ArrayList<Platform> platforms, Vector2 spawn)
	{
		this.platforms = platforms;
		this.spawn = spawn;
		
	}
	
	public ArrayList<Platform> getPlatforms() { return platforms; }
	
	public void Update(GameTime gameTime, Player player)
	{
		for(Platform p : platforms)
			p.Update(gameTime);
		
		for(Enemy e : enemy)
			e.Update(gameTime, player, platforms);
	}
	
	public void Draw(GameTime gameTime, Graphics2D g, Vector2 camPos)
	{
		for(Platform p : platforms)
			p.Draw(g, gameTime, camPos);
		
		for(Enemy e : enemy)
			e.Draw(g, camPos, gameTime);
		
		g.setFont(new Font("serif", Font.PLAIN, 24));
		g.setColor(new Color(128, 0, 0));
		g.drawString("Press P to pause", 25, 25);
		
		g.setColor(Color.BLACK);
		g.drawString("1. Find Key", 25, 50);
		g.drawString("2. Open door after killing enemies", 25, 75);
	}
}
