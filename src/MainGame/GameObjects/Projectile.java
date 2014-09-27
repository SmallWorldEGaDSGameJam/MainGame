package MainGame.GameObjects;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class Projectile extends GameObject{
	
	private Vector2 destination;
	
	public Projectile(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
		destination = initialPos.multiply(1);
	}

	@Override
	public void Update(GameTime gameTime){
		if (!position.equals(destination)){
			Vector2 diff = position.subtract(destination);
		}
	}
	
}
