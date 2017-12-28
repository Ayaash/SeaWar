package com.mygdx.game.modele;


import core.mygdx.game.actor.GraphCase;
import core.mygdx.game.actor.GraphMer;

public class Mer extends Case {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private Navire bateau;
	
	public Mer(int i, int j) {
		super(i,j);
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

	public Navire getNavire() {
		return this.bateau;
	}

	
	public boolean hasNavire() {
		return !this.estNavigable();
	}
	
	@Override
	public GraphCase getGraphCase() {
		return new GraphMer(this);
	}
}
