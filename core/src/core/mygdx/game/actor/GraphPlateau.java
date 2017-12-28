package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.modele.Plateau;

public class GraphPlateau extends Group {
	private Plateau plateau;
	private static GraphPlateau mainInstance;//TODO A supprimer si meilleur idée, ou a transformer en vrai singloton
	
	/**Constructeur*/
	public GraphPlateau(Plateau plateau) {
		this.plateau = plateau;
		for(int i = 0; i < Plateau.TAILLE_HORIZONTALE; i++) {
			for(int j = 0; j < Plateau.TAILLE_VERTICALE; j++) {
				this.addActor(plateau.getGraphCase(i, j));
			}
		}
		mainInstance=this;
	}

	
	
	
	
	/*****************************Fonctions de recuperation*************************************/
	
	/**Permet de recuperer une case en connaisant sa position*/
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
	
	/**Permet de recuperer un navire en connaisant sa position*/
	public GraphNavire getGraphNavire(int x, int y){
		
			GraphCase gc=getGraphCase(x,y);
			if(gc!=null){
				if(gc.getChildren()!=null){
					return (GraphNavire) gc.getChildren().get(0);//TODO On part du principe qu'il n'y a qu'un fils, est que c'est le navire, a modifier si ce n'est plus le cas
				}
			}
			
		return null;
	}
	
	/**Renvoie l'instance principale de graphPlateau*/
	public static GraphPlateau getMainInstance(){
		return mainInstance;
	}

	
	
	
	
	
	/*****************************Fonctions de deselections*************************************/

	
	/**Deselectionne toutes les cases*/
	public void deselectAllCases(){
		SnapshotArray<Actor> chidrns = this.getChildren();
		for(int i=0;i<chidrns.size;i++){
			if(chidrns.get(i) instanceof GraphCase){
				GraphCase gc=(GraphCase) chidrns.get(i);
				gc.deselect();
			}
		}
	}
	
	/**Deselectionne tous les navires*/
	public void deselectAllNavire(){
		SnapshotArray<Actor> chidrns = this.getChildren();
		for(int i=0;i<chidrns.size;i++){
			if(chidrns.get(i) instanceof GraphNavire){
				GraphCase gc=(GraphCase) chidrns.get(i);
				gc.deselect();
			}
		}
	}
	

}
