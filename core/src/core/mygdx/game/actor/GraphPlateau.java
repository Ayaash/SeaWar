package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.modele.Plateau;

public class GraphPlateau extends Group {
	private Plateau plateau;
	
	public GraphPlateau(Plateau plateau) {
		this.plateau = plateau;
		for(int i = 0; i < Plateau.TAILLE_HORIZONTALE; i++) {
			for(int j = 0; j < Plateau.TAILLE_VERTICALE; j++) {
				this.addActor(plateau.getGraphCase(i, j));
			}
		}
		
	}

}
