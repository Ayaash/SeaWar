package com.mygdx.game.ia;

import com.mygdx.game.modele.Navire;
import com.mygdx.game.modele.Partie;

public class IAAleatoire extends AbstractIA {

	
	public IAAleatoire(Partie p) {
		super(p);
	}

	@Override
	public Coup getCoup() {
		Coup meilleur = null;
		Partie copie = copiePartie();
		int mouvement[][] = null;
		
		//Deplacements
		if(!copie.peutSeDeplacer() && !copie.sEstDeplace()){
			int demiTour[] = {-1,-1};
			meilleur = new Coup(TypeCoup.Mouvement, demiTour);
			return meilleur;
		}
		if(!copie.peutSeDeplacer() && copie.sEstDeplace()){
			//mouvement = null;
		}else if(copie.peutSeDeplacer() && copie.sEstDeplace()){
			mouvement = copie.demanderDeplacementsPossibles();
		}
		
		//Tirs
		//if(copie.)
		
		
		
		
		
		
		Navire navire = partie.getNavireCourant();
		boolean peutBouger = navire.deplacementsRestants() != 0;
		if(peutBouger){
			mouvement = navire.deplacementsPossibles();
		}
		
		return meilleur;
	}

}
