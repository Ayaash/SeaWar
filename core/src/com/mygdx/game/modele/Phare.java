package com.mygdx.game.modele;

import core.mygdx.game.actor.GraphCase;
import core.mygdx.game.actor.GraphPhare;

public class Phare extends Mer {
	@SuppressWarnings("unused")
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
	
	
	@Override
	public GraphCase getGraphCase() {
		return new GraphPhare(this);
	}
	
}
