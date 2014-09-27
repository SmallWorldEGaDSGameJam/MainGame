package MainGame.GameObjects;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;

import AppletSource.GameTime;
import AppletSource.Utilities.AnimatedGameObject;
import AppletSource.Utilities.Vector2;

public class Player extends AnimatedGameObject{

	private static final Vector2 WALKSPEED = new Vector2(5.0, 0);
	private static final Vector2 JUMPVECTOR = new Vector2(0, -10.0);
	private static final Vector2 GRAVITY = new Vector2(0, 10.0);
	
	private static final Point FRAMENUMS = new Point(0, 0);
	
	private static final int MSPERFRAMES = 17;
	private static int MAXHEALTH = 10;
	
	private static int IDLE = 0,
					    WALKING = 1,
					    JUMPING = 2,
					    ATTACKING = 3,
					    DYING = 4;
	
	private int health;
	
	private boolean facingRight,
					hasVision,
					dead;
	private int currentState;
	
	public Player(Image image, Vector2 initialPos) {
		super(image, initialPos, FRAMENUMS, MSPERFRAMES);
		setAcceleration(GRAVITY);
		health = MAXHEALTH;
		facingRight = true;
	}
	
	@Override
	public void Update(GameTime gameTime){
		super.Update(gameTime);
		if (health > MAXHEALTH) health = MAXHEALTH;
		if (health <= 0) {
			if (dead){
				//death animation blah blah
			} else {
				die();
			}
		}
		if (getVelocity().x > 0 && !facingRight){
			turnRight();
		} else if (getVelocity().x < 0 && facingRight){
			turnLeft();
		}
	}
	
	public void turnRight(){
		if (currentState != ATTACKING){
			facingRight = true;
			getVelocity().add(WALKSPEED);
		}
	}
	
	public void turnLeft(){
		if (currentState != ATTACKING){
			facingRight = false;
			getVelocity().add(WALKSPEED.multiply(-1));
		}
	}
	
	public void die(){
		dead = true;
	}
	
	public void jump(){
		if (currentState != ATTACKING){
			getVelocity().add(JUMPVECTOR);
		}
	}
	
	public void keyPressed(int k){
		if (!dead){
			switch(k){
			case KeyEvent.VK_UP :
				if (currentState != ATTACKING){
					jump();
				}
			case KeyEvent.VK_LEFT : 
				turnLeft();
			case KeyEvent.VK_RIGHT :
				turnRight();
			}
		}
	}

}
