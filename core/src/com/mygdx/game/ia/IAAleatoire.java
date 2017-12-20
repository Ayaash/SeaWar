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
		int mouvement[][] = null;
		Navire navire = partie.getNavireCourant();
		boolean peutBouger = navire.deplacementsRestants() != 0;
		if(peutBouger){
			mouvement = navire.deplacementsPossibles();
		}
		
		return meilleur;
	}

}
