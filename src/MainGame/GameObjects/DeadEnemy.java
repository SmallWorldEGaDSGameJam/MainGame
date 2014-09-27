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
	
	private static final Vector2 WALKVEL = new Vector2(0.5, 0);
	
	private int health;
	
	private Sprite sword;
	
	private boolean facingRight,
					dead;
	
	private double fov;

	public DeadEnemy(Sprite sprite, Vector2 initialPos, double fov){
		super(sprite, initialPos);
		health = MAXHEALTH;
		this.fov = fov;
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
		
		velocity.x = 0;
		
		if(Math.abs(position.y - player.getY()) < getHeight()) {
			if(Math.abs(position.x - player.getX()) < fov)
				velocity.x = WALKVEL.multiply(Math.signum(player.getX() - position.x)).x;
		}
		
		
		if (!dead){
			Vector2 diff = position.subtract(player.getPosition());
			if (isAttacking()){
				//attacking animation stuff?
			} else {
				double distance = Math.sqrt(diff.x * diff.x + diff.y * diff.y);
				if (distance < 100 /*value subject to change */ && diff.y == 0){
					currentState = ATTACKING;
				} else {
					position.add(diff.normalize());
				}
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

	public void takeDamage(int damage){ health -= damage; }
	
	public boolean isDead(){ return dead; }
	public boolean isAttacking(){ return currentState == ATTACKING; }
	
	public void die(){ dead = true; }

	@Override
	public void collide(GameObject go){
		int code = getRekt().intersects(go.getRekt());
		if (code != -1 &&
			go instanceof Player &&
			((Player)go).hasVision() &&
			((Player)go).isAttacking()){
			takeDamage(1);
		}
		
		 if (go instanceof Platform) {
			switch(code) {
			case GameRectangle.UP:
				velocity.y = 0;
				position.y = go.getY() - getHeight();
				currentState = IDLE;
				break;
			case GameRectangle.DOWN:
				position.y = go.getY() + go.getHeight();
				if(velocity.y < 0)
					velocity.y *= -1/3;
				break;
			case GameRectangle.RIGHT:
				position.x = go.getX() + go.getWidth();
				//velocity.x = 0;
				break;
			case GameRectangle.LEFT:
				position.x = go.getX() - getWidth();
				//velocity.x = 0;
				break;
			}
		}
	}
	
}
