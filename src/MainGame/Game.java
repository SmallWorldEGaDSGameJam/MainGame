package MainGame;
import java.awt.Color;
import java.awt.Graphics2D;

import AppletSource.AppletCore;
import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.Vector2;
import MainGame.GameStates.LevelGameState;

public class Game extends AppletCore {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8927600578014031512L;
	LevelGameState levelGameState;
	
	@Override
	public void resizeScreen() {
		resize(1080, 720);
		
	}

	@Override
	public void LoadContent() {
		levelGameState = new LevelGameState(mouse, key, new Vector2(0, 3000 - 720));
		
	}

	@Override
	public void Update(GameTime gameTime) {
		levelGameState.Update(gameTime);
		
	}
	
	@Override
	public void Draw(Graphics2D g, GameTime gameTime) {
		g.setColor(new Color(100, 149, 237));
		int w = getWidth(), h = getHeight();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		
		levelGameState.Draw(gameTime, g);
	}

}
