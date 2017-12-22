package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Jeu;
import com.mygdx.game.graphique.InWorldObj;
import core.mygdx.game.actor.GraphCase;

public abstract class Case extends InWorldObj{
	
	GraphCase gc;
	boolean selected=false;
	private boolean clicked=false;
	
	public Case(int posWX, int posWY) {
		super(posWX, posWY);
	}
	public Case(Texture img,int posWX, int posWY) {
		super(img, posWX, posWY);
	}
	
	public int[] getPosition(){
		return this.position;
	}
	
	private static final long serialVersionUID = 1L;
	public abstract boolean estNavigable();
	public abstract boolean ajouterNavire(Navire n);
	public abstract boolean enleverNavire();
	public abstract boolean recevoirTir(int degats);
	
	@Override
	public boolean afficher(Batch b){
		if(super.afficher(b)){
			try {
				Jeu.baseFont.draw(b,(Integer.toString(position[0])+","+Integer.toString(position[1])), posX+40, posY+30);//TODO A suprimer
				return true;
			} catch (Exception e) {
				return false;
	
			}
		}
		return false;
		
	}
	public void select(){
		Plateau pl=Plateau.getInstance();
		for(int i=0;i<Plateau.TAILLE_HORIZONTALE;i++){
			for(int j=0;j<Plateau.TAILLE_VERTICALE;j++){
				pl.getCases(i, j).deselect();
			}
		}
		selected=true;
		if(gc!=null){
			gc.select();
		}
	}
	public void deselect(){
		//System.out.println(1);
		selected=false;
		if(gc!=null){
			gc.deselect();
		}
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void clicOn(){
		Plateau pl=Plateau.getInstance();
		for(int i=0;i<Plateau.TAILLE_HORIZONTALE;i++){
			for(int j=0;j<Plateau.TAILLE_VERTICALE;j++){
				pl.getCases(i, j).clickOff();
			}
		}
		clicked=true;
		if(gc!=null){
			gc.clickOff();
		}
	}
	public void clickOff(){
		clicked=false;
		if(gc!=null){
			gc.clickOff();
		}
	}
	
	public boolean isclicked(){
		return clicked;
	}
	
	public abstract GraphCase getGraphCase();
}
