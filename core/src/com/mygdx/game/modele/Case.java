package com.mygdx.game.modele;

import core.mygdx.game.actor.GraphCase;

public abstract class Case extends InWorldObj{
	
	
	public Case(int posWX, int posWY) {
		super(posWX, posWY);
	}
	
	public int[] getPosition(){
		return this.position;
	}

	private static final long serialVersionUID = 1L;
	public abstract boolean estNavigable();
	public abstract boolean ajouterNavire(Navire n);
	public abstract boolean enleverNavire();
	public abstract boolean recevoirTir(int degats);
	public abstract Case copie();
	
	public boolean estPhare(){
		return false;
	}
	
	public abstract GraphCase getGraphCase();

	
}
