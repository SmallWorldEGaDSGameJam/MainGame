package MainGame;
import java.awt.Graphics2D;

import AppletSource.AppletCore;
import AppletSource.GameTime;
import AppletSource.Utilities.Vector2;
import MainGame.GameStates.LevelGameState;

public class Game extends AppletCore {

	LevelGameState levelGameState;
	
	@Override
	public void resizeScreen() {
		resize(1080, 720);
		
	}

	@Override
	public void LoadContent() {
		levelGameState = new LevelGameState(mouse, key, new Vector2(0, 0));
		
	}

	@Override
	public void Update(GameTime gameTime) {
		levelGameState.Update(gameTime);
		
	}
	
	@Override
	public void Draw(Graphics2D g, GameTime gameTime) {
		levelGameState.Draw(gameTime, g);
	}

}
