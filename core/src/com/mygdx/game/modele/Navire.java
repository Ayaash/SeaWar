package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphique.InWorldObj;


public abstract class Navire extends InWorldObj {
	private static final long serialVersionUID = 1L;
	
	//Constantes du navire
	protected int PVMAX;
	protected int deplMax;
	protected int TpsRechCanPrinc;
	protected int degCanPrinc;
	protected int TpsRechCansec;
	protected int degCanSec;
	
	//Variables du navire
	protected Orientation orientation;
	protected int etatCanPrinc;
	protected int etatCanSec;
	protected int pVAct;
	protected int deplAct;
	protected int[] position;
    
	protected Plateau plateau;

	public Navire(int[] posi, Orientation o){
		this.position = posi;
		this.orientation = o;
		this.plateau = Plateau.getInstance();
	}

	public Navire(Texture img, int x, int y) {
		super(img, x, y);
		// TODO Auto-generated constructor stub
	}
	
}
