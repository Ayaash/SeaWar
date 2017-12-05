package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphique.InWorldObj;

public abstract class Case extends InWorldObj{

	private static final long serialVersionUID = 1L;
	public abstract boolean estNavigable();
	public abstract boolean ajouterNavire(Navire n);
	public abstract boolean enleverNavire();
	
}
