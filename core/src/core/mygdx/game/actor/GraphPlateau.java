package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.modele.Plateau;

public class GraphPlateau extends Group {
	private Plateau plateau;
	private static GraphPlateau mainInstance;//TODO A supprimer si meilleur idée, ou a transformer en vrai singloton
	
	public GraphPlateau(Plateau plateau) {
		this.plateau = plateau;
		for(int i = 0; i < Plateau.TAILLE_HORIZONTALE; i++) {
			for(int j = 0; j < Plateau.TAILLE_VERTICALE; j++) {
				this.addActor(plateau.getGraphCase(i, j));
			}
		}
		mainInstance=this;
	}

	public GraphCase getGraphCase(int x, int y){
		
		SnapshotArray<Actor> chidrns = this.getChildren();
		for(int i=0;i<chidrns.size;i++){
			if(chidrns.get(i) instanceof GraphCase){
				GraphCase gc=(GraphCase) chidrns.get(i);
				if(gc.getCase().getPosition()[0]==x && gc.getCase().getPosition()[1]==y){
					return gc;
				}
			}
		}
		return null;
	}
	
	public void deselectAll(){
		SnapshotArray<Actor> chidrns = this.getChildren();
		for(int i=0;i<chidrns.size;i++){
			if(chidrns.get(i) instanceof GraphCase){
				GraphCase gc=(GraphCase) chidrns.get(i);
				gc.deselect();
			}
		}
	}
	
	public static GraphPlateau getMainInstance(){
		return mainInstance;
	}
}
