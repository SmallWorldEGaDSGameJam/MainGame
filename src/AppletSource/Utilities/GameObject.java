package AppletSource.Utilities;

import java.awt.Graphics2D;
import java.awt.Point;

import AppletSource.GameTime;

public class GameObject
{
	protected Point size;
	protected Vector2 scale;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;
	
	protected Sprite sprite;
	
	/**
	 * Main constructor for the GameObject class.
	 * @param image The fixed image for the game object
	 * @param initialPos The starting position that can change with the acceleration and velocity.
	 * @param imageSize The fixed scale of the image.
	 */
	public GameObject(Sprite sprite, Vector2 initialPos)
	{
		this.position = initialPos;
		this.velocity = Vector2.Zero();
		this.acceleration = Vector2.Zero();
		this.scale = new Vector2(1, 1);
		this.sprite = sprite;
		
	}
	
	public void Update(GameTime gameTime)
	{
		velocity = velocity.add(acceleration.multiply(gameTime.getMillisecondsPerFrame()));
		position = position.add(velocity.multiply(gameTime.getMillisecondsPerFrame()));
	}
	
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos)
	{
		sprite.Draw(g, gameTime, position.substract(camPos), scale);
	}

	/**
	 * @return the scale
	 */
	public Vector2 getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(Vector2 scale) {
		this.scale = scale;
	}

	/**
	 * @return the position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	/**
	 * @return the velocity
	 */
	public Vector2 getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the acceleration
	 */
	public Vector2 getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}
}
