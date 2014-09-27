package MainGame.GameObjects;

import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.GameRectangle;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class Platform extends GameObject {
	
	public Platform(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
	}
	
	public GameRectangle getRectangle() {
		return rect;
	}
	
}
