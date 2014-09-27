package MainGame.GameObjects;

import AppletSource.GameTime;

public class Enemy{

	private LiveEnemy liveEnemy;
	private DeadEnemy deadEnemy;
	
	private boolean isDead,
					isSuperDead,
					playerHasVision;
	
	public void Update(GameTime gameTime){
		if (!isSuperDead){
			if (isDead){
				deadEnemy.Update(gameTime, playerHasVision);
			} else {
				liveEnemy.Update(gameTime, playerHasVision);
			}
		}
	}
	
}
