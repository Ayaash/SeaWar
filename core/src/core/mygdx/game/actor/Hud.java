package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.Group;

public class Hud extends Group {
	private static Hud instance = null;
	
	public static Hud getInstance() {
		if(instance == null) {
			instance = new Hud();
		}
		return instance;
	}
	
	private Hud() {
		super();
	}
}
