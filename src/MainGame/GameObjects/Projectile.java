package MainGame.GameObjects;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class Projectile extends GameObject{
	
	private Vector2 destination;
	
	public Projectile(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
		destination = initialPos;
	}

	@Override
	public void Update(GameTime gameTime){
		super.Update(gameTime);
		if (position.equals(destination)){
			velocity = Vector2.Zero();
		}
	}
	
	public void setDestination(Vector2 destination){
		this.destination = destination;
		velocity = position.subtract(destination).normalize();
	}
	
	public boolean isAirborne(){
		return !position.equals(destination);
	}
	
}