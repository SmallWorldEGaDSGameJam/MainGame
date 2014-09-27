package AppletSource.Utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import AppletSource.GameTime;

public class GameObject
{
	protected Point size;
	protected Point scale;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;
	
	protected Image image;
	
	/**
	 * Main constructor for the GameObject class.
	 * @param image The fixed image for the game object
	 * @param initialPos The starting position that can change with the acceleration and velocity.
	 * @param imageSize The fixed scale of the image.
	 */
	public GameObject(Image image, Vector2 initialPos)
	{
		this.position = initialPos;
		this.velocity = Vector2.Zero();
		this.acceleration = Vector2.Zero();
		this.size =  new Point(image.getWidth(null), image.getHeight(null));
		this.scale = new Point(1, 1);
		this.image = image;
	}
	
	public void Update(GameTime gameTime)
	{
		velocity = velocity.add(acceleration.multiply(gameTime.getMillisecondsPerFrame()));
		position = position.add(velocity.multiply(gameTime.getMillisecondsPerFrame()));
	}
	
	public void Draw(Graphics2D g, GameTime gameTime, Vector2 camPos)
	{
		g.drawImage(image, (int)(position.x - camPos.x), (int)(position.y - camPos.y),
				size.x * scale.x, size.y * scale.y, null);
	}

	/**
	 * @return the scale
	 */
	public Point getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(Point scale) {
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
