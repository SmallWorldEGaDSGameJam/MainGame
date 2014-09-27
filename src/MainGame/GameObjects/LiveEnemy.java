package MainGame.GameObjects;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.GameRectangle;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class LiveEnemy extends GameObject{

	private static final int MAXHEALTH = 10;
	
	private static final int IDLE = 0,
							ATTACKING = 1,
							DYING = 2;
	
	private int health;
	
	private GameRectangle hitbox;
	
	private Sprite visionSprite;
	
	private boolean facingRight,
					dead;
	
	public LiveEnemy(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
		hitbox = new GameRectangle(initialPos.x, initialPos.y, sprite.getWidth(), sprite.getHeight());
		health = MAXHEALTH;
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
			double distance = Math.sqrt(diff.x * diff.x + diff.y * diff.y);
			double ratio = Math.abs(diff.y / diff.x);
			if (ratio > 1 && distance < 1000 /*value subject to change*/){
				currentState = ATTACKING;
			} else {
				currentState = IDLE;
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

	public void takeDamage(int damage){
		health -= damage;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void die(){
		dead = true;
	}
	
}
