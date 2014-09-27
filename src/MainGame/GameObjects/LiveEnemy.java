package MainGame.GameObjects;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class LiveEnemy extends GameObject{

	private static final int MAXHEALTH = 10;
	
	private static final int IDLE = 0,
							ATTACKING = 1,
							DYING = 2;
	
	private int health;
	
	//how long to wait in between each shot while active.
	private int numFramesPerShotWait, 
				numFramesPerShotElapsed;
	
	private Sprite visionSprite;
	
	private boolean facingRight,
					dead;
	
	public LiveEnemy(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
		health = MAXHEALTH;
		numFramesPerShotElapsed = 0;
		numFramesPerShotElapsed = 120; //2 seconds at 60fps
		//instantiate visionSprites
	}
	
	public void Update(GameTime gameTime, Player player){
		super.Update(gameTime);
		if (health > MAXHEALTH) health = MAXHEALTH;
		if (health <= 0) {
			if (dead){
				//dead person stuff
			} else {
				die();
			}
		}
		if (!dead){
			Vector2 diff = position.subtract(player.getPosition()).normalize();
			if (isAttacking() && numFramesPerShotElapsed++ == numFramesPerShotWait){
				//set projectile speed to diff.normalize().multiply(2?);
				numFramesPerShotElapsed = 0;
				currentState = IDLE; //reset to idle to reevaluate if you can fire a shot again
			} else {
				double distance = Math.sqrt(diff.x * diff.x + diff.y * diff.y);
				double ratio = Math.abs(diff.y / diff.x);
				//when ratio is greater than 1 that means the absolute value of the y is greater than the absolute value of the x. we can change this later.
				if (ratio > 1 && distance < 1000 /*value subject to change*/){
					currentState = ATTACKING;
					numFramesPerShotElapsed = 0;
				} else {
					currentState = IDLE;
				}
			}
		}
	}
	
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos, boolean playerHasVision){
		if (playerHasVision){
			visionSprite.Draw(g, gameTime, position, camPos);
		} else {
			sprite.Draw(g, gameTime, position, camPos);
		}
	}

	public void takeDamage(int damage){ health -= damage; }
	
	public boolean isDead(){ return dead; }
	public boolean isAttacking(){ return currentState == ATTACKING; }
	
	public void die(){ dead = true; }
	
}
