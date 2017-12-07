package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphique.InWorldObj;
import com.mygdx.game.graphique.Textures;

public abstract class Case extends InWorldObj{

	
	public Case(int posWX, int posWY) {
		super(posWX, posWY);
	}
	private static final long serialVersionUID = 1L;
	public abstract boolean estNavigable();
	public abstract boolean ajouterNavire(Navire n);
	public abstract boolean enleverNavire();
	public abstract boolean recevoirTir(int degats);
	
}
