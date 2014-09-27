package MainGame.GameStates;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.GameState;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;
import MainGame.GameObjects.Player;
import MainGame.Levels.Level;

public class LevelGameState extends GameState {
	
	Level level;
	Player player;
	
	public LevelGameState(MouseState mouse, KeyboardState key, Vector2 camPos) {
		super(mouse, key, camPos);
		
		level = new Level();
		player = new Player(new Sprite("assets/img/player/player.png"), new Vector2(0, 2000), key, mouse);
	}

	@Override
	public void Update(GameTime gameTime) {
		level.Update(gameTime, player);
		player.Update(gameTime, level.getPlatforms());
		
		camPos = new Vector2(player.getX() - 1080/2, player.getY() - 720/2);
	}

	@Override
	public void Draw(GameTime gameTime, Graphics2D g) {
		level.Draw(gameTime, g, camPos);
		player.Draw(g, gameTime, camPos);
	}

}
