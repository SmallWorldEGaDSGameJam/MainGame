package MainGame.GameObjects;

import java.awt.Graphics2D;

import AppletSource.GameTime;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class Enemy{

	private LiveEnemy liveEnemy;
	private DeadEnemy deadEnemy;
	
	private int numFramesWait;
	
	private boolean isDead,
					isSuperDead,
					playerHasVision;
	
	public Enemy(Sprite liveEnemySprite, Vector2 liveEnemyPos, Sprite deadEnemySprite, Vector2 deadEnemyPos){
		liveEnemy = new LiveEnemy(liveEnemySprite, liveEnemyPos);
		deadEnemy = new DeadEnemy(deadEnemySprite, deadEnemyPos);
		numFramesWait = 5000; //5 seconds
	}
	
	public void Update(GameTime gameTime, Player player){
		if (!isSuperDead){
			if (isDead && numFramesWait-- <= 0){
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
			if (isDead && numFramesWait-- <= 0){
				deadEnemy.Draw(g, gameTime, camPos ,playerHasVision);
			} else {
				liveEnemy.Draw(g, gameTime, camPos, playerHasVision);
			}
		}
	}
	
	public boolean isDead(){ return isDead; }
	public boolean isSuperDead(){ return isSuperDead; }
	
}
