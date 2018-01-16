package com.mygdx.game.modele;

import core.mygdx.game.actor.GraphCase;
import core.mygdx.game.actor.GraphPhare;

public class Phare extends Mer {
	
	private static final long serialVersionUID = 1L;

	private Joueur joueur;

	public Phare(int i, int j) {
		super(i, j);
		// TODO Auto-generated constructor stub
	}
	
	public Joueur getJoueur(){
		return joueur;
	}
	public void setJoueur(Joueur j){
			joueur = j;
		
	}
	public boolean estPhare(){
		return true;
	}

	public Case copie(){
		Phare copie = new Phare(this.position[0], this.position[1]);
		copie.setJoueur(this.getJoueur());
		return copie;
	}
	
	@Override
 	public GraphCase getGraphCase() {
		return new GraphPhare(this);
	}
	
}
