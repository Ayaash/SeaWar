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
	
	public int[] getPosition(){
		return this.position;
	}
	
	public boolean peutTirerPrincipal(){
		return etatCanPrinc == 0;
	}
	public boolean peutTirerSecondaire(){
		return etatCanSec == 0;
	}
	
	//Ces fonctions renvoient un tableau de coordonnées de cases (int[][]) et les dégats du tir (int) 
	public abstract Object[] tirPrincipal();
	public abstract Object[] tirSecondaire();
	
	public void recharger(){
		this.etatCanPrinc = (etatCanPrinc == 0) ? 0: etatCanPrinc-1;
		this.etatCanSec = (etatCanSec == 0) ? 0: etatCanSec-1;
	}
	
	/**
	 * 
	 * @param degats
	 * @return Renvoie True si le navie est encore actif, False sinon. A noter que degats<0 repare le navire
	 */
 	public boolean encaisserDegats(int degats){
    	this.pVAct -= degats;
    	if(pVAct > 0){
    		return true;
    	}else{
    		plateau.enleverNavire(position);
    		return false;
    	}
    }
 	
 	public int deplacementsRestants(){
		return this.deplAct;
	}
	
	public int[][] deplacementsPossibles(){
		int[][] res = {{-1,-1}, {-1,-1}, {-1,-1}};
		int temp[][] = new int[3][2];
		temp[0] = plateau.voisin(position, orientation.decremente());
		temp[1] = plateau.voisin(position, orientation);
		temp[2] = plateau.voisin(position, orientation.incremente());
		
		boolean possible = false;
		for(int i = 0; i<3;i++){
			if(plateau.caseLibre(temp[i])){
				possible = true;
				res[i] = temp[i].clone();
			}
		}
		
		
		if(possible){
			return res;
		}else{
			if(deplAct == deplMax) retournerNavire();
			deplAct = 0;
			return null;
		}
		
	}
	
	private void retournerNavire(){
		this.orientation = orientation.incremente().incremente().incremente();
	}
	
	/**
	 * 
	 * @param l'indice de la case selectionnée dans le tableau renvoyé par deplacementsPossibles
	 * @return False si i != 0, 1, ou 2.
	 */
	public boolean deplacer(int i){
		switch(i){
		case 0:
			this.orientation = orientation.decremente();
			this.position = plateau.voisin(position, orientation);
			break;
		case 1:
			this.position = plateau.voisin(position, orientation);
			break;
		case 2:
			this.orientation = orientation.incremente();
			this.position = plateau.voisin(position, orientation);
			break;
		default:
			//Erreur
			return false;
		}
		
		this.deplAct--;
		return true;
	}
	
	
	
	
	
	
}
