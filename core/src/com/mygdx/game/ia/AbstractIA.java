package com.mygdx.game.ia;

import com.mygdx.game.modele.Partie;

public abstract class AbstractIA {
	
	protected Partie partie;
	
	public AbstractIA(Partie p){
		this.partie = p;
	}

	public abstract Coup getCoup();

}
