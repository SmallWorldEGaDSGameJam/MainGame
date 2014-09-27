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
	
	private static final int MAXHEALTH = 4;
	
	private static final int IDLE = 0,
					         WALKING = 1,
					         JUMPING = 2,
					         ATTACKING = 3,
					         DYING = 4;
	
	private ArrayList<Projectile> projectiles;
	
	private int health;

	private int numFramesToFire,
				numFramesElapsed;
	
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
		projectiles = new ArrayList<Projectile>();
		numFramesElapsed = 30;
		numFramesToFire = 30;
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
		if (numFramesElapsed != numFramesToFire) numFramesElapsed++;
		if (getVelocity().x > 0 && !facingRight){
			turnRight();
		} else if (getVelocity().x < 0 && facingRight){
			turnLeft();
		}
		for (int x = 0; x < projectiles.size(); x++){
			if (projectiles.get(x).isAirborne()){
				projectiles.get(x).Update(gameTime);
			} else {
				projectiles.remove(x);
			}
		}
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
		groundStop(gameTime, platforms);
		//Ehm... Wait I guess?
	}

	private void jumpUpdate(GameTime gameTime, ArrayList<Platform> platforms)
	{
		boolean left = key.isButtonDown(KeyEvent.VK_A);
		boolean right = key.isButtonDown(KeyEvent.VK_D);
		
		if(left == right)
			velocity.x = 0;
		else if(right)
			turnRight();
		else if(left)
			turnLeft();
		
		groundStop(gameTime, platforms);
	}
	
	
	public void groundStop(GameTime gameTime, ArrayList<Platform> platforms) {
		onGround = false;
		for(Platform p : platforms)
		{
			collide(p);
		}
	}
	
	
	@Override
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos){
		
		super.Draw(g, gameTime, camPos);
		for (Projectile projectile : projectiles){
			if (projectile.isAirborne()){
				projectile.Draw(g, gameTime, camPos);
			}
		}
		/*
		 * account for facing left, right
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
			velocity.x = WALKSPEED.x;
		}
	}
	
	private void turnLeft(){
		if (currentState != ATTACKING){
			if (currentState != JUMPING){
				currentState = WALKING;
			}
			facingRight = false;
			velocity.x = WALKSPEED.multiply(-1).x;
		}
	}
	
	private void die() {
		dead = true;
		currentState = DYING;
	}
	
	private void jump() {
		if (currentState != ATTACKING){
			velocity.y = JUMPVECTOR.y;
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
		if(numFramesElapsed == numFramesToFire && currentState != ATTACKING) {
			numFramesElapsed = 0;
			velocity = Vector2.Zero();
			currentState = ATTACKING;
			Projectile p = new Projectile(new Sprite("assets/img/projectiles/test.png"), new Vector2(), this);
			p.setDestination(position, facingRight);
			projectiles.add(p);
		}
	}
	
	public void collide(GameObject go){
		GameRectangle rect = getRekt();
		int code = go.getRekt().intersects(rect);
		//TODO: Check logic of these ifs (they don't actually use code)
		if (go instanceof Projectile && !projectiles.contains(go)){
			takeDamage(1);
		} else if (go instanceof DeadEnemy && ((DeadEnemy)go).isAttacking()){
			if (hasVision){
				takeDamage(2);
			} else {
				takeDamage(1);
			}
		} else if (go instanceof Platform){
			
			switch(code){
			case GameRectangle.UP:
				velocity = Vector2.Zero();
				position = new Vector2(position.x, go.getY() - getHeight());
				onGround = true;
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
	
	public boolean hasVision(){ return hasVision; }
	public boolean isAttacking(){ return currentState == ATTACKING; }
	
}
