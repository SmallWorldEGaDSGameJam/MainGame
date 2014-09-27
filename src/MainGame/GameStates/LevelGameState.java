package MainGame.GameStates;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.GameState;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;
import MainGame.Platform;
import MainGame.GameObjects.Player;
import MainGame.Levels.Level;

public class LevelGameState extends GameState {
	
	Level level;
	Player player;
	
	public LevelGameState(MouseState mouse, KeyboardState key, Vector2 camPos) {
		super(mouse, key, camPos);
		
		ArrayList<Platform> p = new ArrayList<Platform>();
		p.add(new Platform(new Sprite("assets/img/platforms/platform1.png", new Point(1, 1), 200), new Vector2()));
		
		level = new Level(p, new Vector2());
	}

	@Override
	public void Update(GameTime gameTime) {
		level.Update(gameTime);
		//player.Update(gameTime);
	}

	@Override
	public void Draw(GameTime gameTime, Graphics2D g) {
		level.Draw(gameTime, g, camPos);
		//player.Draw(g, gameTime, camPos);
	}

}
