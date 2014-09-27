package MainGame.GameObjects;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class Enemy{

	private LiveEnemy liveEnemy;
	private DeadEnemy deadEnemy;
	
	private long millCounter; 
	private long millWait;
	
	private boolean isDead,
					isSuperDead,
					playerHasVision;
	
	public Enemy(Sprite liveEnemySprite, Vector2 pos, Sprite deadEnemySprite){
		liveEnemy = new LiveEnemy(liveEnemySprite, pos);
		deadEnemy = new DeadEnemy(deadEnemySprite, pos);
		millWait = 5000;
		millCounter = 0;
	}
	
	public void Update(GameTime gameTime, Player player){
		if (!isSuperDead){
			if(millCounter < millWait)
				millCounter += gameTime.getMillisecondsPerFrame();
			
			if (isDead && millCounter >= millWait){
				deadEnemy.Update(gameTime, player);
				if (deadEnemy.isDead()){
					isSuperDead = true;
				}
			} else {
				liveEnemy.Update(gameTime, player);
				if (liveEnemy.isDead()){
					isDead = true;
				}
			}
		}
	}
	
	public void Draw(Graphics2D g, Vector2 camPos, GameTime gameTime){
		if (!isSuperDead){
			if (isDead && millCounter >= millWait){
				deadEnemy.Draw(g, gameTime, camPos ,playerHasVision);
			} else {
				liveEnemy.Draw(g, gameTime, camPos, playerHasVision);
			}
		}
	}
	
	public boolean isDead(){ return isDead; }
	public boolean isSuperDead(){ return isSuperDead; }
	
}
