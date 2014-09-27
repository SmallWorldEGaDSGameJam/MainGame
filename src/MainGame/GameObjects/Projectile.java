package MainGame.GameObjects;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class Projectile extends GameObject{
	
	private GameObject source;
	
	private Vector2 destination;
	
	private int numFramesToMove,
				numFramesElapsed;
	
	public Projectile(Sprite sprite, Vector2 initialPos, GameObject source) {
		super(sprite, initialPos);
		this.source = source;
		destination = initialPos;
		numFramesToMove = 1;
	}

	@Override
	public void Update(GameTime gameTime){
		if (numFramesElapsed++ >= numFramesToMove){
			numFramesElapsed = 0;
			super.Update(gameTime);
		}
		if (position.equals(destination)){
			velocity = Vector2.Zero();
		}
	}
	
	public void setDestination(Vector2 source, boolean facingRight){
		position = source;
		if (facingRight){
			destination = new Vector2(source.x + 100, source.y);
			velocity = destination.subtract(source).normalize().multiply(3);
		} else {
			destination = new Vector2(source.x - 100, source.y);
			velocity = destination.subtract(source).normalize().multiply(3);
		}
	}
	
	public boolean isAirborne(){
		return !position.equals(destination);
	}
	
	public GameObject getSource(){ return source; }
	
}