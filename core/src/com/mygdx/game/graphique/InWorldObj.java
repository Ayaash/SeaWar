package com.mygdx.game.graphique;

import com.badlogic.gdx.graphics.Texture;

/**
 * Classe des objets affichable a l'ecran
 * 
 * */

public class InWorldObj extends Affichable{

	private static final long serialVersionUID = 1L;

	protected int posWX;
	protected int posWY;
	protected int lW;
	
	
	public InWorldObj() {
		super(Textures.NOIMAGE, 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	public InWorldObj(Texture img, int x, int y) {
		super(img, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public InWorldObj(Texture img, int x, int y, int angle) {
		super(img, x, y,angle);
		// TODO Auto-generated constructor stub
	}

	


	

	
	

	
	
	
}
