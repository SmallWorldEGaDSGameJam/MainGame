package MainGame.GameStates;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.GameState;
import AppletSource.Utilities.Vector2;
import MainGame.Levels.Level;

public class LevelGameState extends GameState {
	
	Level level;
	
	public LevelGameState(MouseState mouse, KeyboardState key, Vector2 camPos) {
		super(mouse, key, camPos);
	}

	@Override
	public void Update(GameTime gameTime) {
		
	}

	@Override
	public void Draw(Graphics2D g) {
		
	}

}
