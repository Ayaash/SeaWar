package com.mygdx.game.ia;

import com.mygdx.game.modele.Partie;

public class Coup {

	private TypeCoup typeCoup;
	private int coordonnees[];
	private int degats;
	
	public Coup(TypeCoup type, int coordonnees[]){
		this.typeCoup = type;
		this.coordonnees = coordonnees.clone();
	}
	public Coup(TypeCoup type){
		this.typeCoup = type;
	}
	public Coup(TypeCoup type, int coordonnees[], int degats){
		this.typeCoup = type;
		this.coordonnees = coordonnees.clone();
		this.degats = degats;
	}
	
	public void executerCoup(Partie p){
		switch(typeCoup){
		case FinTour:
			p.finirTourNavire();
			break;
		case Mouvement:
			p.deplacerNavire(coordonnees);
			break;
		case TirPrincipal:
			p.demanderTirsPossiblesPrincipal();
			p.tirerSurUneCase(coordonnees, degats);
			break;
		case TirSecondaire:			
			p.demanderTirsPossiblesSecondaire();
			p.tirerSurUneCase(coordonnees, degats);
			break;
		}
	}
	
	public TypeCoup getType(){
		return typeCoup;
	}
	
	
	
}
