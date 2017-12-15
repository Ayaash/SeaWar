package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.modele.Case;

public abstract class GraphCase extends Group { // TODO passer en ImageButton
	private Case m_case;

	public GraphCase(Case c) {
		this.m_case = c;
	}
}
