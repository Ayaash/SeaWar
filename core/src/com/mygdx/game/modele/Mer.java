package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import core.mygdx.game.actor.GraphCase;
import core.mygdx.game.actor.GraphMer;

public class Mer extends Case {

	private static final long serialVersionUID = 1L;
	private Navire bateau;
	
	public Mer(int i, int j) {
		super(i,j);
	}

	
	
	public Mer(Texture img, int i, int j) {
		super(img,i,j);
	}



	//Une case mer est toujours navigable, sauf s'il y a un navire sur la case
	public boolean estNavigable() {
		if(bateau == null){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	//Renvoie une erreur si on ajoute un deuxième navire
	public boolean ajouterNavire(Navire n) {
		if(bateau == null){
			bateau = n;
			return true;
		}else{
			return false;
		}
	}

	@Override
	//Renvoie une erreur si on enleve un navire qui n'existe pas
	public boolean enleverNavire() {
		if(bateau == null){
			return false;
		}else{
			bateau = null;
			return true;
		}
	}

	@Override
	public boolean recevoirTir(int degats) {
		if(bateau == null){
			return false;
		}else{
			bateau.encaisserDegats(degats);
			return true;
		}
		
	}

	
	
	@Override
	public boolean afficher(Batch b){
		if(super.afficher(b)){
			if	(bateau!=null){
				return bateau.afficher(b);
			}else{
				return true;
			}
		}else{
			return false;
		}
		
	}
	
	public Navire getNavire() {
		return this.bateau;
	}


	/*
	@Override
	public GraphCase getGraphCase() {
		return new GraphMer(this);
	}
	*/
	
	public boolean hasNavire() {
		return !this.estNavigable();
	}
	
	@Override
	public GraphCase getGraphCase() {
		return new GraphMer(this);
	}
}
