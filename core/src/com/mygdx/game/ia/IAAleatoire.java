package com.mygdx.game.ia;

import java.util.Random;

import com.mygdx.game.modele.Joueur;
import com.mygdx.game.modele.Partie;
import com.mygdx.game.modele.Tir;

public class IAAleatoire extends AbstractIA {

	
	public IAAleatoire(Partie p) {
		super(p);
	}

	@Override
	//TODO: Selectionner le navire
	public Coup getCoup() {
		
		Coup meilleur = null;
		Partie copie = copiePartie();
		Random rand = new Random();
		
		//Si aucun navire selectionn�, on en prend un au hasard.
		if(copie.getNavireCourant() == null){
			int navire = rand.nextInt(Joueur.NOMBRE_NAVIRES);
			if(copie.selectionnerNavire(copie.getCurrentPlayer().getNavires()[navire]) == false){
				
				navire = (navire == 0) ? 1 : 0;		//Oui, du ternaire (on passe de 0 � 1 ou de 1 � 0)
				copie.selectionnerNavire(copie.getCurrentPlayer().getNavires()[navire]);
				
			}
		}
		
		
		
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
		}else if(copie.peutSeDeplacer()){
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

		int selection = rand.nextInt(nbCoupPossibles + 1);
		
		if(selection < tailleMouvement){
			meilleur = new Coup(TypeCoup.Mouvement, mouvement[selection]);
		}else if(selection < (tailleMouvement + tailleTirP )){
			meilleur = new Coup(TypeCoup.TirPrincipal,
					TirPrincipal.cases[selection-tailleMouvement],
					TirPrincipal.degats
					);
		}else if(selection < (tailleMouvement + tailleTirP + tailleTirS )){
			meilleur = new Coup(TypeCoup.TirSecondaire, 
					TirSecondaire.cases[selection-tailleMouvement-tailleTirP],
					TirSecondaire.degats);
		}else{
			meilleur = new Coup(TypeCoup.FinTour);
		}

		
		return meilleur;
	}
	
	private long randSeed(){
		return (long) (Long.MAX_VALUE*Math.random());
	}

}
