package com.mygdx.game.modele;

public class Terre extends Case {

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

}
