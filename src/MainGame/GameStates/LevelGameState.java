package MainGame.GameStates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

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
		level.Update(gameTime);
		
		boolean left = key.isButtonDown(KeyEvent.VK_LEFT);
		boolean right = key.isButtonDown(KeyEvent.VK_RIGHT);
		boolean up = key.isButtonDown(KeyEvent.VK_UP);
		boolean down = key.isButtonDown(KeyEvent.VK_DOWN);
		
		if(left != right) {
			if(left)
				camPos.x -= 3;
			else
				camPos.x += 3;
		}
		
		if(up != down) {
			if(up)
				camPos.y -= 3;
			else
				camPos.y += 3;
		}
		
		player.Update(gameTime, level.getPlatforms());
	}

	@Override
	public void Draw(GameTime gameTime, Graphics2D g) {
		level.Draw(gameTime, g, camPos);
		player.Draw(g, gameTime, camPos);
	}

}
