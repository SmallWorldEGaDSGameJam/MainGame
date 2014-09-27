package MainGame;

import java.awt.Image;

import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.GameRectangle;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class Platform extends GameObject {
	private GameRectangle rect;
	
	public Platform(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
		rect = new GameRectangle(initialPos.x, initialPos.y, sprite.getWidth(), sprite.getHeight() );
	}
	
	public GameRectangle getRectangle() {
		return rect;
	}
	
}
