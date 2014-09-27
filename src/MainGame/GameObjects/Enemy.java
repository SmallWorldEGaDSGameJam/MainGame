package MainGame.GameObjects;

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
	
	public void Update(GameTime gameTime){
		if (!isSuperDead){
			if (isDead && numFramesWait-- <= 0){
				deadEnemy.Update(gameTime, playerHasVision);
				if (deadEnemy.isDead()){
					isSuperDead = true;
				}
			} else {
				liveEnemy.Update(gameTime, playerHasVision);
				if (liveEnemy.isDead()){
					isDead = true;
				}
			}
		}
	}
	
	public void Draw(GameTime gameTime){
		if (!isSuperDead){
			if (isDead && numFramesWait-- <= 0){
				deadEnemy.Update(gameTime, playerHasVision);
			} else {
				liveEnemy.Update(gameTime, playerHasVision);
			}
		}
	}
	
	public boolean isDead(){ return isDead; }
	public boolean isSuperDead(){ return isSuperDead; }
	
}
