package AppletSource;

/**
 * @version 1
 * @author Fox Tail Games
 */

public class GameTime
{
	private long ElapsedGameTime;
	private long MillisecondsPerFrame;
	private int CurrentFrame;
	private long InitialTime;
	private long Time1;
	private long Time2;
	
	/**
	 * The basic constructor for the {@link GameTime} class. If you are using 
	 * the {@link AppletCore} class then you do not need to create a
	 * {@link gameTime} object.
	 */
	public GameTime()
	{
		ElapsedGameTime = 0;
		MillisecondsPerFrame = 0;
		CurrentFrame = 0;
		InitialTime = System.currentTimeMillis();
		Time1 = System.currentTimeMillis();
		Time2 = Time1;
	}
	//This are the getter methods
	
	/**
	 * Getter method for Current Frame
	 * @return The current frame (first frame in the game being 0)
	 */
	public int getCurrentFrame()
	{
		return CurrentFrame;
	}
	
	/**
	 * Gives you the milliseconds per frame that is calculated with the InitialUpdate() and EndUpdate()
	 * @return The time difference in miliseconds between InitialUpdate() call and EndUpdate()
	 */
	public long getMillisecondsPerFrame()
	{
		return MillisecondsPerFrame;
	}
	
	/**
	 * Gets the length of the running program
	 * @return The time the applet has been running in miliseconds
	 */
	public long getElapsedGameTime()
	{
		return ElapsedGameTime;
	}
	
	/**
	 * It has to be called at the begining of a game loop
	 */
	public void Update()
	{
		Time2 = System.currentTimeMillis();
		CurrentFrame++;
		ElapsedGameTime = System.currentTimeMillis() - InitialTime;
		MillisecondsPerFrame = Math.abs(Time1 - Time2);
		Time1 = System.currentTimeMillis();
	}
	
	public boolean equals(Object objectTwo)
	{
		GameTime gameTime2 = (GameTime)objectTwo;
		return gameTime2.toString() == toString();
	}
	
	public String toString()
	{
		return "Elapsed Time: " + ElapsedGameTime + 
				"\nMilliseconds Per Frame = " + MillisecondsPerFrame + 
				"\nCurrent Frame: " + CurrentFrame;
	}
}
