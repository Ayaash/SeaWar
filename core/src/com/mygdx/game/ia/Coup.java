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
	
	public void faireCoup(Partie p){
		switch(typeCoup){
		case FinTour:
			p.finirTourNavire();
			break;
		case Mouvement:
			p.deplacerNavire(coordonnees);
			break;
		case TirPrincipal:
			p.tirerSurUneCase(coordonnees, degats);
			p.demanderTirsPossiblesPrincipal();
			break;
		case TirSecondaire:
			p.tirerSurUneCase(coordonnees, degats);
			p.demanderTirsPossiblesSecondaire();
			break;
		}
	}
	
	
	
}
