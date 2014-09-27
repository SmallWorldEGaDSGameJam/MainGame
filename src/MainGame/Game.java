package MainGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import AppletSource.AppletCore;
import AppletSource.GameTime;
import AppletSource.Utilities.Vector2;
import MainGame.GameStates.LevelGameState;
import MainGame.GameStates.Menu;

public class Game extends AppletCore {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8927600578014031512L;
	private LevelGameState levelGameState;
	private Menu menu;
	
	@Override
	public void resizeScreen() {
		resize(1080, 720);
		
	}

	@Override
	public void LoadContent() {
		levelGameState = new LevelGameState(mouse, key, new Vector2(0, 3000 - 720));
		menu = new Menu(mouse, key, new Vector2(0, 3000 - 720));
	}

	@Override
	public void Update(GameTime gameTime) {
		menu.Update(gameTime);
		if (!menu.isOpen()) levelGameState.Update(gameTime);
	}
	
	@Override
	public void Draw(Graphics2D g, GameTime gameTime) {
		g.setColor(new Color(100, 149, 237));
		int w = getWidth(), h = getHeight();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		if (menu.isOpen()){
			menu.Draw(gameTime, g);
		} else {
			levelGameState.Draw(gameTime, g);
		}
	}

}
