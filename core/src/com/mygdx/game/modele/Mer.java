package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;

public class Mer extends Case {

	private Navire bateau;
	
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

}
