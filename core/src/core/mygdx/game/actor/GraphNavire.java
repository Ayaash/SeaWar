package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.modele.Navire;

public abstract class GraphNavire extends Actor { // TODO passer en Image
	private Navire m_navire;
	
	public GraphNavire(Navire _navire) {
		this.m_navire = _navire;
	}
}
