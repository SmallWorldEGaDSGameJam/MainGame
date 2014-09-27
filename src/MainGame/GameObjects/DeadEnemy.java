package MainGame.GameObjects;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.GameRectangle;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class DeadEnemy extends GameObject{
	
	private static final int MAXHEALTH = 10;
	
	private static final int IDLE = 0,
							MOVING = 1,
							ATTACKING = 2,
							DYING = 3;
	
	private int health;
	
	private GameRectangle hitbox;
	
	private Sprite sword;
	
	private boolean facingRight,
					dead;

	public DeadEnemy(Sprite sprite, Vector2 initialPos){
		super(sprite, initialPos);
		hitbox = new GameRectangle(initialPos.x, initialPos.y, sprite.getWidth(), sprite.getHeight());
		health = MAXHEALTH;
		//instantiate sword
	}
	
	public void Update(GameTime gameTime, Player player){
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
			if (distance < 100 /*value subject to change */ && diff.y == 0){
				currentState = ATTACKING;
			} else {
				position.add(diff);
			}
		}
	}
	
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos, boolean playerHasVision){
		if (playerHasVision){
			sprite.Draw(g, gameTime, position, camPos);
			if (!facingRight){
				sword.Draw(
						g, 
						gameTime, 
						position.subtract(new Vector2(sword.getWidth(), 0)).subtract(camPos), 
						scale
						);
			} else {
				sword.Draw(
						g, 
						gameTime, 
						position.add(new Vector2(sprite.getWidth(), 0)).subtract(camPos), 
						scale
						);
			}
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
