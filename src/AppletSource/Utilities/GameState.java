package AppletSource.Utilities;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;

public abstract class GameState {
	
	protected MouseState mouse;
	protected KeyboardState key;
	
	protected Vector2 camPos;
	
	public GameState(MouseState mouse, KeyboardState key, Vector2 camPos) {
		this.mouse = mouse;
		this.key = key;
		this.camPos = camPos;
	}
	
	public abstract void Update(GameTime gameTime);
	public abstract void Draw(GameTime gameTime, Graphics2D g);
}
