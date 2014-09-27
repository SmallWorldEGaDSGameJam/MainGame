package MainGame.GameStates;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import AppletSource.GameTime;
import AppletSource.Input.KeyboardState;
import AppletSource.Input.MouseState;
import AppletSource.Utilities.GameState;
import AppletSource.Utilities.Vector2;

public class Menu extends GameState{
	//private Graphics2D menu;
	boolean menuOpen;
	boolean countingDown;
	
	public Menu(MouseState mouse, KeyboardState key, Vector2 camPos) {
		super(mouse, key, camPos);
		countingDown=true;
		menuOpen = true;
	}

	@Override
	public void Update(GameTime gameTime) {
		boolean p = key.isButtonTapped(KeyEvent.VK_P);
		if (countingDown && gameTime.getElapsedGameTime()>=20000) {
			countingDown=false;
		}
		if (p) {
			countingDown=false;
			menuOpen=!menuOpen;
		} 
		
	}

	@Override
	public void Draw(GameTime gameTime, Graphics2D g) {
		if (menuOpen) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(0, 0, 1080, 720);
			g.setColor(Color.BLUE);
			g.drawRect(530, 20, 20, 20);
			g.drawRect(555, 20, 20, 20);
			g.drawRect(515, 45, 20, 20);
			g.drawRect(562, 45, 20, 20);
			g.setColor(Color.WHITE);
			Font f = new Font("serif", Font.PLAIN, 24);
			//Rectangle2D r = f.getStringBounds("Potato", null);
			g.setFont(f);
			g.drawString("W", 535, 25);
			g.drawString("E", 560, 25);
			g.drawString("A", 520, 50);
			g.drawString("D", 567, 50);
			g.setColor(Color.BLACK);
			f = new Font("serif", Font.PLAIN, 30);
			g.setFont(f);
			g.drawString("Weapons:\n\tCrossbow is used to fight live enemies.\n\t"
					+ "Phantom Sword is used to fight ghosts.", 25, 100);
			g.drawString("Ghost Vision: Allows you to see ghosts of fallen "
					+ "enemies.\nTo fight them, your weapon changes from "
					+ "crossbow to phantom sword.", 25, 150);
			
			g.setColor(Color.CYAN);
			g.drawString("Press P to unpause.", 535, 300);
			//g.drawString("Press Q to quit.", 535, 325);
			//menu = g;
		}
	}

	public boolean isOpen(){ return menuOpen; }
	public void open(){ menuOpen = true; }
	
}
