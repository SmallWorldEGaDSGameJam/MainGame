package MainGame.GameObjects;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.GameRectangle;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;
import MainGame.Platform;

public class Player extends GameObject{

	private static final Vector2 WALKSPEED = new Vector2(5.0, 0);
	private static final Vector2 JUMPVECTOR = new Vector2(0, -10.0);
	private static final Vector2 GRAVITY = new Vector2(0, 10.0);
	
	private static final int MAXHEALTH = 10;
	
	private static int IDLE = 0,
					    WALKING = 1,
					    JUMPING = 2,
					    ATTACKING = 3,
					    DYING = 4;
	
	private GameRectangle hitbox;
	
	private Sprite sword; 
	
	private int health;
	
	private boolean facingRight,
					hasVision,
					dead;
	private int currentState;
	
	private KeyboardState key;
	private MouseState mouse;
	
	public Player(Sprite sprite, Vector2 initialPos, KeyboardState key, MouseState mouse) {
		super(sprite, initialPos);
		setAcceleration(GRAVITY);
		hitbox = new GameRectangle(getX(), getY(), getWidth(), getHeight());
		health = MAXHEALTH;
		facingRight = true;
		
		this.key = key;
		this.mouse = mouse;
	}
	
	public void Update(GameTime gameTime, ArrayList<Platform> platforms){
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
		
		//Keyboard update
		if (!dead){
			if(key.isButtonDown(KeyEvent.VK_W)) {
				if(currentState != ATTACKING) {
					jump();
				}
			} else if(key.isButtonDown(KeyEvent.VK_A)) {
				turnLeft();
			} else if(key.isButtonDown(KeyEvent.VK_D)) {
				turnRight();
			}
		}
	}
	
	@Override
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos){
		super.Draw(g, gameTime, camPos);
		//account for facing left, right
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
	
	public void takeDamage(int damage){
		health -= damage;
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
	
	public boolean hasVision(){ return hasVision; }
	
}
