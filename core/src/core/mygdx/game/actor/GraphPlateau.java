package core.mygdx.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.modele.Navire;
import com.mygdx.game.modele.Partie;
import com.mygdx.game.modele.Plateau;
import com.mygdx.game.modele.Tir;



public class GraphPlateau extends Group {
	private Partie m_partie;
	private Plateau m_plateau;
	private static GraphPlateau mainInstance;//TODO A supprimer si meilleur idée, ou a transformer en vrai singloton
	
	private Tir tirPossible=null;
	
	private static ModeAction modeAction=ModeAction.SELECTIONNAVIRE;
	
	public static final Color J1COLOR=new Color(0f,0f,1f,1f);
	public static final Color J2COLOR=new Color(1f,0f,0f,1f);

	
	/**Constructeur*/
	public GraphPlateau(Partie partie) {
		this.m_partie=partie;
		this.m_plateau = partie.getPlateau();
		for(int i = 0; i < Plateau.TAILLE_HORIZONTALE; i++) {
			for(int j = 0; j < Plateau.TAILLE_VERTICALE; j++) {
				this.addActor(m_plateau.getGraphCase(i, j));
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
			/*GraphCase gc=getGraphCase(x,y);
			SnapshotArray<Actor> chidrns = gc.getChildren();

			if(gc!=null){
				for(int i=0;i<chidrns.size;i++){
					if( chidrns.get(i) instanceof GraphNavire){
						//System.out.println(2);
						return (GraphNavire) chidrns.get(i);
					}
				}
			}*/
		SnapshotArray<Actor> chidrns = getChildren();
		for(int i=0;i<chidrns.size;i++){
			if( chidrns.get(i) instanceof GraphNavire){
				//System.out.println(2);
				GraphNavire gn=(GraphNavire) chidrns.get(i);
				if(gn.getNavire().getPosition()[0]==x && gn.getNavire().getPosition()[1]==y){
					return gn;
				}
			}
		}
		
			
		return null;
	}
	
	/**Renvoie l'instance principale de graphPlateau*/
	public static GraphPlateau getMainInstance(){
		return mainInstance;
	}

	public void getNavire(){
		m_partie.getNavireCourant();
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
				GraphNavire gn=(GraphNavire) chidrns.get(i);
				gn.deselect();
			}
		}
	}
	
	
	
	/****************************Actions provoquées par les listeners**************************/

	//TODO Ameliorer les controles de la selection
	/**Renvoie true si la selection a eu lieux, false sinon*/
	public boolean selectNavire(Navire n){
			if(modeAction==ModeAction.SELECTIONNAVIRE && n.getJoueur()==m_partie.getCurrentPlayer()){
			deselectAllNavire();//un seul navire selectionnée a la fois
			deselectAllCases();//une seule case selectionnée a la fois
		
			m_partie.selectionnerNavire(n);
			askTirPrincipal();//TODO A Apeller dans un listener, ici UNIQUEMENT pour les tests
			return true;
		}
		return false;
	}
	
	/**Renvoie true si mouvement possible*/
	public boolean askMove(){
		clearAllHighlight();
		int[][] casesAccessible = m_partie.demanderDeplacementsPossibles();
		if(casesAccessible!=null && m_partie.demanderNbDeplacements()>0){
			for(int i=0;i<casesAccessible.length;i++){
				if(casesAccessible[i][0]!=-1 && casesAccessible[i][1]!=-1){
					getGraphCase(casesAccessible[i][0],casesAccessible[i][1]).highlight();
				}
			}
				
			modeAction=ModeAction.DEPLACEMENT;
			return true;
		}
		verifyEndNavireTour();
		return false;
	}
	
	/**Action de deplacement, renvoie true si le deplacement a bien lieux*/
	public boolean move(int x, int y){
		if(modeAction==ModeAction.DEPLACEMENT){
			int[] d={x,y};
			m_partie.deplacerNavire(d);
			
			deselectAllCases();
			getGraphCase(x, y).forceSelect();
			
			askMove();
			return true;
		}
		return false;
		
	}
	
	/**Renvoie true si tir principal possible*/
	public boolean askTirPrincipal(){
		clearAllHighlight();
		tirPossible=m_partie.demanderTirsPossiblesPrincipal();
		int[][] casesAccessible = tirPossible.cases;
		if(casesAccessible!=null && m_partie.peutTirerPrincipal()){
			for(int i=0;i<casesAccessible.length;i++){
				if(casesAccessible[i][0]!=-1 && casesAccessible[i][1]!=-1){
					getGraphCase(casesAccessible[i][0],casesAccessible[i][1]).highlight();
				}
			}
				
			modeAction=ModeAction.TIRPRINCIPAL;
			return true;
		}
		verifyEndNavireTour();
		return false;
	}
	
	/**Renvoie true si tir principal possible*/
	public boolean askTirSecondaire(){
		clearAllHighlight();
		tirPossible=m_partie.demanderTirsPossiblesSecondaire();
		int[][] casesAccessible = tirPossible.cases;
		if(casesAccessible!=null && m_partie.peutTirerSecondaire()){
			for(int i=0;i<casesAccessible.length;i++){
				if(casesAccessible[i][0]!=-1 && casesAccessible[i][1]!=-1){
					getGraphCase(casesAccessible[i][0],casesAccessible[i][1]).highlight();
				}
			}
				
			modeAction=ModeAction.TIRSECONDAIRE;
			return true;
		}
		verifyEndNavireTour();
		return false;
	}
	
	/**Action de tir principal, renvoie true si le deplacement a bien lieux*/
	public boolean tirer(int x, int y){
		if(tirPossible!=null && 
				(modeAction==ModeAction.TIRPRINCIPAL || modeAction==ModeAction.TIRSECONDAIRE)){
			
			
			
			int[] d={x,y};
			m_partie.tirerSurUneCase(d, tirPossible.degats);
			
			modeAction=ModeAction.NONE;
			return true;
		}
		return false;
		
	}
	
	
	
	
	/**Action effectué lors d'un clic sur une case*/
	public void actionOnClickOnCase(GraphCase gc){
		if(gc.isHighlighted()){
			if(modeAction==ModeAction.DEPLACEMENT){
				move(gc.getCase().getPosition()[0],gc.getCase().getPosition()[1]);
			}else if(modeAction==ModeAction.TIRPRINCIPAL || modeAction==ModeAction.TIRSECONDAIRE){
				tirer(gc.getCase().getPosition()[0],gc.getCase().getPosition()[1]);
			}
		}		
	}
	
	/**enleve la surbrillance de toutes les cases*/
	public void clearAllHighlight(){
		SnapshotArray<Actor> chidrns = this.getChildren();
		for(int i=0;i<chidrns.size;i++){
			if(chidrns.get(i) instanceof GraphCase){
				GraphCase gc=(GraphCase) chidrns.get(i);
				gc.stopHighlight();
			}
		}
	}
	
	/**Verifie si un navire a encore des action, et si non, on demande un changement de navire*/
	public boolean verifyEndNavireTour(){
		if(m_partie.peutSeDeplacer()
				|| m_partie.peutTirerPrincipal()
				|| m_partie.peutTirerSecondaire()){
			
			modeAction=ModeAction.NONE;
			return false;
		}
		m_partie.finirTourNavire();
		modeAction=ModeAction.SELECTIONNAVIRE;
		return true;
	}
	
	public boolean finTour(){
		boolean btmp=true;
		for(int i=0;i<m_partie.getCurrentPlayer().getNavires().length;i++){//TODO Verifier si valide
			if(	m_partie.getCurrentPlayer().getNavires()[i].sEstDeplace()){
				btmp=false;
			}
		}
		
		btmp=true;//TODO enlever
		if(btmp){
			deselectAllCases();
			deselectAllNavire();
			modeAction=ModeAction.SELECTIONNAVIRE;
			System.out.println(m_partie.finirTourGlobal());
			return true;
		}
		return false;
	}
	
	/**Fonction pour mettre fin au tour d'un navire*/
	public boolean abandonTourNavire(){
		boolean btmp=true;//TODO!m_partie.finirTourNavire();
		//System.out.println(btmp);
		if(btmp){
			deselectAllCases();
			deselectAllNavire();
			modeAction=ModeAction.SELECTIONNAVIRE;
			return true;
		}
		return false;
	}
	
}
