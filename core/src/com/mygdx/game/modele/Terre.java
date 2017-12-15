package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;

public class Terre extends Case {

	public Terre(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Terre(Texture img, int i, int j) {
		super(img,i,j);
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
