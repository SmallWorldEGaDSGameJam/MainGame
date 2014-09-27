package MainGame.GameStates;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

import AppletSource.AppletCore;
import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.GameState;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;
import MainGame.GameObjects.Player;
import MainGame.Levels.Level;

class AudioListener implements LineListener{

	private boolean done = false;
	
	
	@Override
	public void update(LineEvent event) {
		Type eventType = event.getType();
		if (eventType == Type.STOP || eventType == Type.CLOSE){
			done = true;
			notifyAll();
		}
	}
	
	public synchronized void waitUntilDone() throws InterruptedException {
		while(!done){
			wait();
		}
	}
	
}

public class LevelGameState extends GameState {


	private Sprite background;
	private static Thread sound;
	private static Clip clip;
		
	Level level;
	Player player;
	
	public LevelGameState(MouseState mouse, KeyboardState key, Vector2 camPos) {
		super(mouse, key, camPos);
		
		level = new Level();
		player = new Player(new Sprite("assets/img/player/Rob.png", new Point(10, 1), 100), 
				new Sprite("assets/img/player/walk.png", new Point(11, 1), 20), new Vector2(0, 2000), key, mouse);
		playSound("/assets/audio/music/background.wav");
		background = new Sprite("assets/img/background/Background.png");
	}

	@Override
	public void Update(GameTime gameTime) {
		
		level.Update(gameTime, player);
		player.Update(gameTime, level.getPlatforms());
		
		camPos = new Vector2(player.getX() - 1080/2, player.getY() - 720/2);
	}

	@Override
	public void Draw(GameTime gameTime, Graphics2D g) {
		background.Draw(g, gameTime, new Vector2(), new Vector2(1, 1));
		level.Draw(gameTime, g, camPos);
		player.Draw(g, gameTime, camPos);
	}

	public static synchronized void playSound(final String string) {
		  sound = new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		    	  InputStream audioSrc = getClass().getResourceAsStream(string);
		    	  BufferedInputStream inputStream = new BufferedInputStream(audioSrc);
		    	  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);

		    	  AudioListener listener = new AudioListener();

		    	  clip = AudioSystem.getClip();
		    	  clip.addLineListener(listener);
		    	  clip.open(audioInputStream);
		    	  clip.loop(Clip.LOOP_CONTINUOUSLY);

		    	  clip.start();

		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		    }
		  });
		  sound.start();
	}

	
}
