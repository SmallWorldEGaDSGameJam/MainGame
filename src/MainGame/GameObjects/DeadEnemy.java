package MainGame.GameObjects;

import AppletSource.GameTime;
import AppletSource.Utilities.GameObject;
import AppletSource.Utilities.Sprite;
import AppletSource.Utilities.Vector2;

public class DeadEnemy extends GameObject{

	public DeadEnemy(Sprite sprite, Vector2 initialPos) {
		super(sprite, initialPos);
		// TODO Auto-generated constructor stub
	}
	
	public void Update(GameTime gameTime, boolean playerHasVision){
		super.Update(gameTime);
	}

}
