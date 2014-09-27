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

public class Player extends GameObject{

	private static final Vector2 WALKSPEED = new Vector2(1.0, 0);
	private static final Vector2 JUMPVECTOR = new Vector2(0, -1.0);
	private static final Vector2 GRAVITY = new Vector2(0, 0.002);
	
	private static final int MAXHEALTH = 10;
	
	private static final int IDLE = 0,
					         WALKING = 1,
					         JUMPING = 2,
					         ATTACKING = 3,
					         DYING = 4;
	
	private Projectile projectile;
	
	private int health;
	
	private boolean facingRight,
					hasVision,
					dead,
					onGround;
	
	private KeyboardState key;
	private MouseState mouse;
	
	public Player(Sprite sprite, Vector2 initialPos, KeyboardState key, MouseState mouse) {
		super(sprite, initialPos);
		setAcceleration(GRAVITY);
		health = MAXHEALTH;
		facingRight = true;
		//instantiate projectile
		projectile = new Projectile(new Sprite("assets/img/platforms/platform4.png"), new Vector2());
		this.key = key;
		this.mouse = mouse;
		this.onGround = false;
	}
	
	public void Update(GameTime gameTime, ArrayList<Platform> platforms){
		super.Update(gameTime);
		if (health > MAXHEALTH) health = MAXHEALTH;
		if (health <= 0) {
			if (currentState != DYING && !dead){
				die();
			}
		}
		if (getVelocity().x > 0 && !facingRight){
			turnRight();
		} else if (getVelocity().x < 0 && facingRight){
			turnLeft();
		}


		//State machine!
		switch(currentState) {
		case IDLE:
			idleUpdate(gameTime, platforms);
			break;
		case WALKING:
			walkingUpdate(gameTime, platforms);
			break;
		case JUMPING:
			jumpUpdate(gameTime, platforms);
			break;
		case ATTACKING:
			attackUpdate(gameTime, platforms);
			break;
		case DYING:
			dyingUpdate(gameTime, platforms);
		}
		
		
		//Scale images
		if(facingRight)
			scale.x = 1;
		else
			scale.x = -1;
	}
	
	
	private void idleUpdate(GameTime gameTime, ArrayList<Platform> platforms)
	{
		groundStop(gameTime, platforms);
		
		if(key.isButtonDown(KeyEvent.VK_SPACE))
			attack();
		else if(key.isButtonDown(KeyEvent.VK_D))
			turnRight();
		else if(key.isButtonDown(KeyEvent.VK_A))
			turnLeft();
		else if(key.isButtonDown(KeyEvent.VK_W))
			jump();
	}
	
	private void walkingUpdate(GameTime gameTime, ArrayList<Platform> platforms)
	{
		groundStop(gameTime, platforms);
		
		if(key.isButtonDown(KeyEvent.VK_SPACE))
			attack();
		else if(key.isButtonDown(KeyEvent.VK_W))
			jump();
		
		boolean left = key.isButtonDown(KeyEvent.VK_A);
		boolean right = key.isButtonDown(KeyEvent.VK_D);
		
		if(left == right)
			idle();
		else if(left)
			turnLeft();
		else
			turnRight();
	}
	
	private void dyingUpdate(GameTime gameTime, ArrayList<Platform> platforms) 
	{
		//No fucking idea
	}

	private void attackUpdate(GameTime gameTime, ArrayList<Platform> platforms)
	{
		//Ehm... Wait I guess?
	}

	private void jumpUpdate(GameTime gameTime, ArrayList<Platform> platforms)
	{
		//I know! Wait for collision!
		groundStop(gameTime, platforms);
	}
	
	
	public void groundStop(GameTime gameTime, ArrayList<Platform> platforms) {
		for(Platform p : platforms)
		{
			onGround = false;
			collide(p);
		}
	}
	
	
	@Override
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos){
		
		super.Draw(g, gameTime, camPos);
		/*if (projectile.isAirborne()){
			projectile.Update(gameTime);
		}
<<<<<<< Updated upstream
=======
		//account for facing left, right
		if (!facingRight && hasVision){
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
		}*/
	}
	
	public void takeDamage(int damage){
		health -= damage;
	}
	
	private void turnRight(){
		if (currentState != ATTACKING){
			if (currentState != JUMPING){
				currentState = WALKING;
			}
			facingRight = true;
			velocity = WALKSPEED;
			currentState = WALKING;
		}
	}
	
	private void turnLeft(){
		if (currentState != ATTACKING){
			if (currentState != JUMPING){
				currentState = WALKING;
			}
			facingRight = false;
			velocity = WALKSPEED.multiply(-1);
			currentState = WALKING;
		}
	}
	
	private void die() {
		dead = true;
		currentState = DYING;
	}
	
	private void jump() {
		if (currentState != ATTACKING){
			velocity = JUMPVECTOR;
			currentState = JUMPING;
			//restart animations accordingly
		}
	}
	
	private void idle()
	{
		if(currentState != ATTACKING)
		{
			velocity = Vector2.Zero();
			currentState = IDLE;
		}
	}
	
	private void attack() {
		if(currentState != ATTACKING) {
			velocity = Vector2.Zero();
			currentState = ATTACKING;
			//restart animations accordingly
			//start projectile/sword/whatever
		}
	}
	
	public void collide(GameObject go){
		GameRectangle rect = getRekt();
		int code = go.getRekt().intersects(rect);
		if (go instanceof Projectile && go != projectile){
			takeDamage(1);
		} else if (go instanceof DeadEnemy && ((DeadEnemy)go).isAttacking()){
			if (hasVision){
				takeDamage(2);
			} else {
				takeDamage(1);
			}
		} else if (go instanceof Platform){
			
			System.out.println(code);
			
			switch(code){
			case GameRectangle.UP :
				velocity = Vector2.Zero();
				position = new Vector2(position.x, go.getY() - getHeight());
				onGround = true;
				currentState = IDLE;
			case GameRectangle.DOWN :
				
			case GameRectangle.RIGHT :
				
			case GameRectangle.LEFT :
				
			}
		}
	}
	
	public boolean hasVision(){ return hasVision; }
	
}
