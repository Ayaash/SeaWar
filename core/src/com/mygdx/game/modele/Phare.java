package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Phare extends Mer {

	protected Texture tPhare;
	protected Image obj2;

	
	public Phare(int i, int j) {
		super(i, j);
		// TODO Auto-generated constructor stub
	}
	
	public Phare(Texture img2, Texture img1, int i, int j) {
		super(img1, i, j);
		// TODO Auto-generated constructor stub
		obj2=createSprite(img2);
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
	

	@Override
	public boolean afficher(Batch b){
		super.afficher(b);
		try {
			this.actualizeSprite(obj2);
			obj2.draw(b, 1);
			
			return true;
		} catch (Exception e) {
			return false;
		}	
		
	}
	
}
