package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.mygdx.game.modele.Navire;

public abstract class TextNavire extends HorizontalGroup {
	private Navire m_navire;
	
	public TextNavire(Navire _navire) {
		m_navire = _navire;
	}
}
