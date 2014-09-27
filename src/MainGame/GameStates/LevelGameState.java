package MainGame.GameStates;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.GameState;
import AppletSource.Utilities.Vector2;
import MainGame.GameObjects.Player;
import MainGame.Levels.Level;

public class LevelGameState extends GameState {
	
	Level level;
	Player player;
	
	public LevelGameState(MouseState mouse, KeyboardState key, Vector2 camPos) {
		super(mouse, key, camPos);
		level = new Level();
	}

	@Override
	public void Update(GameTime gameTime) {
		player.Update(gameTime);
	}

	@Override
	public void Draw(GameTime gameTime, Graphics2D g) {
		player.Draw(g, gameTime, camPos);
	}

}
