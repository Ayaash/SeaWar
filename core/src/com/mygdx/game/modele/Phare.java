package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;

public class Phare extends Mer {

	public Phare(int i, int j) {
		super(i, j);
		// TODO Auto-generated constructor stub
	}
	private int joueur = 0;
	
	public int getJoueur(){
		return joueur;
	}
	public boolean setJoueur(int j){
		if(j>-1 && j<3){
			joueur = j;
			return true;
		}else{
			return false;
		}
	}

}
