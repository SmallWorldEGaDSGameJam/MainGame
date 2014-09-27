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
	private boolean menuOpen;
	private boolean countingDown;
	private boolean p;
	
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
			g.fillRect(0, 0, 1080, 720);
			g.setColor(Color.BLUE);
			g.drawRect(530, 20, 20, 20);
			g.drawRect(555, 20, 20, 20);
			g.drawRect(515, 45, 20, 20);
			g.drawRect(562, 45, 20, 20);
			g.drawRect(515, 70, 80, 20);
			g.setColor(Color.WHITE);
			Font f = new Font("serif", Font.PLAIN, 24);
			g.setFont(f);
			g.drawString("Jump - W", 458, 38);
			g.drawString("E - Ghost Vision", 557, 38);
			g.drawString("Left - A", 455, 63);
			g.drawString("D - Right", 562, 63);
			g.drawString("Attack -  Space", 435, 88);
			g.setColor(Color.BLACK);
			f = new Font("serif", Font.PLAIN, 30);
			g.setFont(f);
			g.drawString("Weapons:\n\tCrossbow is used to fight live enemies.", 25, 200);
			g.drawString("Phantom Sword is used to fight ghosts.", 160, 250);
			g.drawString("Ghost Vision: Allows you to see ghosts of fallen enemies.", 25, 300);
			g.drawString("To fight them, press E to change your vision." , 200, 350);
			g.drawString("Your weapon changes from the crossbow to the phantom sword.", 200, 400);
			g.setColor(Color.CYAN);
			g.drawString("Press P to unpause.", 450, 500);
		}
	}

	public boolean isOpen(){ return menuOpen; }
	public void open(){
		p = true;
	}
	
}
