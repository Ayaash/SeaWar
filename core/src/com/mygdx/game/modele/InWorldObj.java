package com.mygdx.game.modele;

import java.io.Serializable;

/**
 * Classe des objets affichable a l'ecran
 * 
 * */

public class InWorldObj implements Serializable{


	private static final long serialVersionUID = 1L;

	
	protected int[] position;

	
	public InWorldObj() {//TODO temporaire , a regler le porbleme dans navire
		
		position=new int[2];
		position[0]=0;
		position[1]=0;
	}
	
	public InWorldObj(int posWX, int posWY) {		
		position=new int[2];
		position[0]=posWX;
		position[1]=posWY;

		
		
	}
	
	
}
