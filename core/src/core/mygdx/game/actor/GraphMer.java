package core.mygdx.game.actor;

import com.mygdx.game.modele.Mer;

public class GraphMer extends GraphCase {
	
	public GraphMer(Mer mer) {
		super(mer);
		if(mer.hasNavire()) {
			this.addActor(mer.getNavire().getGraph());
		}
	}
}
