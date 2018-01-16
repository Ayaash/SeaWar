package com.mygdx.game.ia;

import com.mygdx.game.modele.Partie;

public abstract class AbstractIA {
	
	protected Partie partie;
	
	public AbstractIA(Partie p){
		this.partie = p;
	}

	public abstract Coup getCoup();
	

	protected Partie copiePartie(){
		Partie copie = partie.copieProfonde();
		return copie;
	}
	/*
	private String GetExecutionPath(){
	    String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
	    absolutePath = absolutePath.replaceAll("%20"," "); // Surely need to do this here
	    return absolutePath;
	}
	*/
}
