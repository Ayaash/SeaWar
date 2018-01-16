package com.mygdx.game.modele;


import core.mygdx.game.actor.GraphCase;
import core.mygdx.game.actor.GraphTerre;

public class Terre extends Case {

	
	private static final long serialVersionUID = 1L;

	public Terre(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public boolean estNavigable() {
		return false;
	}
	
	@Override
	//Ce n'est pas sensé arriver
	public boolean ajouterNavire(Navire n) {
		return false;
	}

	@Override
	//Ce n'est pas sensé arriver
	public boolean enleverNavire() {
		return false;
	}

	@Override
	public boolean recevoirTir(int degats) {
		return false;
	}
	
	
	@Override
	public GraphCase getGraphCase() {
		return new GraphTerre(this);
	}

	@Override
	public Case copie() {
		Terre copie = new Terre(this.position[0], this.position[1]);
		return copie;
	}

}
