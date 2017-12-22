package com.mygdx.game.ia;

import java.util.Random;

import com.mygdx.game.modele.Navire;
import com.mygdx.game.modele.Partie;
import com.mygdx.game.modele.Tir;

public class IAAleatoire extends AbstractIA {

	
	public IAAleatoire(Partie p) {
		super(p);
	}

	@Override
	public Coup getCoup() {
		Coup meilleur = null;
		Partie copie = copiePartie();
		int mouvement[][] = null;
		Tir TirPrincipal = null;
		Tir TirSecondaire = null;
		boolean finTour = false;
		
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
		if(copie.peutTirerPrincipal()){
			TirPrincipal = copie.demanderTirsPossiblesPrincipal();
			copie = copiePartie();
		}
		if(copie.peutTirerSecondaire()){
			TirSecondaire = copie.demanderTirsPossiblesSecondaire();
			copie = copiePartie();
		}
		
		//Fin du tour
		if(copie.sEstDeplace()){
			finTour = true;
		}
		
		int nbCoupPossibles = 0;
		int tailleMouvement = 0;
		int tailleTirP = 0;
		int tailleTirS = 0;
		if(mouvement != null) tailleMouvement = mouvement.length;
		if(TirPrincipal != null) tailleTirP = TirPrincipal.cases.length;
		if(TirSecondaire != null) tailleTirS = TirSecondaire.cases.length;
		if(finTour) nbCoupPossibles += 1;
		
		nbCoupPossibles += (tailleMouvement + tailleTirP + tailleTirS);
		
		if(nbCoupPossibles == 0) return null; //Aucun coup possible, c'est impossible

		Random rand = new Random();
		int selection = rand.nextInt(nbCoupPossibles + 1);
		if(selection < tailleMouvement){
			meilleur = new Coup(TypeCoup.Mouvement, mouvement[selection-1]);
		}else if(selection < (tailleMouvement + tailleTirP )){
			meilleur = new Coup(TypeCoup.TirPrincipal,
					TirPrincipal.cases[selection-tailleMouvement-1],
					TirPrincipal.degats
					);
		}else if(selection < (tailleMouvement + tailleTirP + tailleTirS )){
			meilleur = new Coup(TypeCoup.TirSecondaire, 
					TirSecondaire.cases[selection-tailleMouvement-tailleTirP-1],
					TirSecondaire.degats);
		}else{
			meilleur = new Coup(TypeCoup.FinTour);
		}

		
		return meilleur;
	}

}
