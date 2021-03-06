package MainGame.GameObjects;

import java.awt.Graphics2D;
import java.util.ArrayList;

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
	
	private static final Vector2 WALKVEL = new Vector2(0.5, 0);
	
	private Projectile projectile;
	
	private int health;
	
	//how long to wait in between each shot while active.
	private int millPerShotWait, 
				millPerShotElapsed;
	
	private Sprite visionSprite;
	
	private boolean facingRight,
					dead;
	//Field of vision
	double fov;
	
	public LiveEnemy(Sprite sprite, Vector2 initialPos, double fov) {
		super(sprite, initialPos);
		projectile = new Projectile(new Sprite("assets/img/projectiles/test.png"), initialPos, this);
		health = MAXHEALTH;
		millPerShotElapsed = 0;
		millPerShotWait = 2000;
		this.fov = fov;
		//instantiate visionSprites
	}
	
	public void Update(GameTime gameTime, Player player, ArrayList<Platform> platforms){
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
			millPerShotElapsed += gameTime.getMillisecondsPerFrame();
			
			
			
			if (isAttacking() && millPerShotElapsed >= millPerShotWait){
				projectile.setDestination(player.getPosition(), facingRight);
				millPerShotElapsed = 0;
				currentState = IDLE; //reset to idle to reevaluate if you can fire a shot again
			} else {
				double distance = Math.sqrt(diff.x * diff.x + diff.y * diff.y);
				double ratio = Math.abs(diff.y / diff.x);
				//when ratio is greater than 1 that means the absolute value of the y is greater than the absolute value of the x. we can change this later.
				if (projectile.isAirborne()){
					projectile.Update(gameTime);
				}
				if (ratio > 1 && distance < 1000 /*value subject to change*/){
					currentState = ATTACKING;
					millPerShotElapsed = 0;
				} else {
					currentState = IDLE;
				}
			}
		}
		
		for(Platform p : platforms)
		{
			collide(p);
		}
	}
	
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos, boolean playerHasVision){
		if (playerHasVision){
			visionSprite.Draw(g, gameTime, position, camPos);
		} else {
			super.Draw(g, gameTime, camPos);
		}
		if (projectile.isAirborne()){
			projectile.Update(gameTime);
		}
	}

	public void takeDamage(int damage){ health -= damage; }
	
	public boolean isDead(){ return dead; }
	public boolean isAttacking(){ return currentState == ATTACKING; }
	
	public void die(){ dead = true; }
	
	@Override
	public void collide(GameObject go){
		int code = go.getRekt().intersects(getRekt());
		if (go instanceof Projectile && ((Projectile)go).getSource() instanceof Player){
			takeDamage(1);
		}
	}
	
}
