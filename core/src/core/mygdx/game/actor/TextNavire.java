package core.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.modele.Navire;

public abstract class TextNavire extends HorizontalGroup {
	protected Skin m_skin;
	protected Navire m_navire;
	
	public TextNavire(Navire _navire) {
		m_navire = _navire;
		m_skin = new Skin(Gdx.files.internal("skin/rusty_robot_ui.json"));
	}
	
	public abstract void update();
	
}
