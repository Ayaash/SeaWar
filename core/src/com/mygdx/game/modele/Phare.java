package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import core.mygdx.game.actor.GraphCase;
import core.mygdx.game.actor.GraphPhare;

public class Phare extends Mer {
	private static final long serialVersionUID = 1L;
	protected Texture tPhare;
	protected Image obj2;

	private Joueur joueur;

	
	public Phare(int i, int j) {
		super(i, j);
		// TODO Auto-generated constructor stub
	}
	
	public Phare(Texture img2, Texture img1, int i, int j) {
		super(img1, i, j);
		// TODO Auto-generated constructor stub
		obj2=createSprite(img2);
	}
	
	/*private int joueur = 0;
	
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
	}*/
	
	
	public Joueur getJoueur(){
		return joueur;
	}
	public void setJoueur(Joueur j){
			joueur = j;
		
	}
	@Override
	public boolean afficher(Batch b){
		super.afficher(b);
		try {
			this.actualizeSprite(obj2);
			if(joueur!=null){
				obj2.setColor(joueur.getColor());
			}else{
				obj2.setColor(1, 1, 1, 1);
			}
			obj2.draw(b, 1);
			
			return true;
		} catch (Exception e) {
			return false;
		}	
		
	}
	
	@Override
	public GraphCase getGraphCase() {
		return new GraphPhare(this);
	}
	
}
