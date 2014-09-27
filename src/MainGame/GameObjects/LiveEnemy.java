package MainGame.GameObjects;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.GameRectangle;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class LiveEnemy extends GameObject{

	private static final int MAXHEALTH = 10;
	
	private int health;
	
	private GameRectangle hitbox;
	
	private boolean facingRight,
					dead;
	
	public LiveEnemy(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
		health = MAXHEALTH;
	}
	
	public void Update(GameTime gameTime, boolean playerHasVision){
		super.Update(gameTime);
		if (health > MAXHEALTH) health = MAXHEALTH;
		if (health <= 0) {
			if (dead){
				//dead person stuff
			} else {
				die();
			}
		}
	}

	public void takeDamage(int damage){
		health -= damage;
	}
	
	public void die(){
		dead = true;
	}
	
}
