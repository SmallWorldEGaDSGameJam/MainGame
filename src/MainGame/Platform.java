package MainGame;

import java.awt.Image;

import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.GameRectangle;
import AppletSource.Utilities.Vector2;

public class Platform extends GameObject {
	GameRectangle rect;
	
	public Platform(Image image, Vector2 initialPos) {
		super(image, initialPos);
		rect = new GameRectangle();
	}
	
	public GameRectangle getRectangle() {
		return rect;
	}
	
}
