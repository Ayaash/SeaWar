package com.mygdx.game.modele;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.graphique.InWorldObj;

import core.mygdx.game.actor.GraphNavire;


public abstract class Navire extends InWorldObj {
	private static final long serialVersionUID = 1L;
	
	//Constantes du navire
	public static int PV_MAX;
	public static int DEPL_MAX;
	public static int TPS_RECH_CAN_PRINC;
	public static int DEG_CAN_PRINC;
	public static int TPS_RECH_CAN_SEC;
	public static int DEG_CAN_SEC;
	
	//Variables du navire
	protected Orientation orientation;
	protected int etatCanPrinc;
	protected int etatCanSec;
	protected int pVAct;
	protected int deplAct;
	protected Boolean aTire;
    
	protected Plateau plateau;
	
	protected Joueur joueur;

	public Navire(Texture img,int[] posi, Orientation o){
		super(img,posi[0],posi[1],0);
		this.position = posi;
		this.orientation = o;
		this.aTire=false;
		this.etatCanPrinc=0;
		this.etatCanSec=0;
		this.plateau = Plateau.getInstance();
		plateau.ajouterNavire(position, this);
	}

	
	public void setJoueur(Joueur j){
		joueur=j;
	}
	
	public int[] getPosition(){
		return this.position;
	}
	
	/**
	 * @return
	 */
	public boolean getATire(){
		return aTire;
	}
	
	public boolean peutTirerPrincipal(){
		return etatCanPrinc == 0 || !aTire;
	}
	public boolean peutTirerSecondaire(){
		return etatCanSec == 0 || !aTire;
	}
	
	public int rechargePrincipal() {
		return etatCanPrinc;
	}
	
	public int rechargeSecondaire() {
		return etatCanSec;
	}
	
	//Ces fonctions renvoient un tableau de coordonnées de cases (int[][]) et les dégats du tir (int) 
	public abstract Object[] tirPrincipal();
	public abstract Object[] tirSecondaire();
	
	
	public void miseEnRechargementCanPrinc(){
		etatCanPrinc=TPS_RECH_CAN_PRINC+1;
		aTire=true;
	}
	
	public void miseEnRechargementCanSec(){
		etatCanSec=TPS_RECH_CAN_SEC+1;
		aTire=true;
	}
	
	
	public void recharger(){
		this.etatCanPrinc = (etatCanPrinc == 0) ? 0: etatCanPrinc-1;
		this.etatCanSec = (etatCanSec == 0) ? 0: etatCanSec-1;
		aTire=false;
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
    		joueur.enleverNavire(this);
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
			if(deplAct == DEPL_MAX) retournerNavire();
			deplAct = 0;
			aTire = true;
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
	public boolean deplacer(int pos[]){
		/*switch(i){
		case 0:
			plateau.enleverNavire(position);
			this.orientation = orientation.decremente();
			this.position = plateau.voisin(position, orientation);
			plateau.ajouterNavire(position, this);
			break;
		case 1:
			plateau.enleverNavire(position);
			this.position = plateau.voisin(position, orientation);
			plateau.ajouterNavire(position, this);
			break;
		case 2:
			plateau.enleverNavire(position);
			this.orientation = orientation.incremente();
			this.position = plateau.voisin(position, orientation);
			plateau.ajouterNavire(position, this);
			break;
		default:
			//Erreur
			return false;
		}*/
		int[] nouvelleCase = plateau.voisin(position, orientation.decremente());
		if(Arrays.equals(pos, nouvelleCase)){
			plateau.enleverNavire(position);
			this.orientation = orientation.decremente();
			this.position = plateau.voisin(position, orientation);
			plateau.ajouterNavire(position, this);
		}else{
			nouvelleCase = plateau.voisin(position, orientation);
			if(Arrays.equals(pos, nouvelleCase)){
				plateau.enleverNavire(position);
				this.position = plateau.voisin(position, orientation);
				plateau.ajouterNavire(position, this);
			}else{
				nouvelleCase = plateau.voisin(position, orientation.incremente());
				if(Arrays.equals(pos, nouvelleCase)){
					plateau.enleverNavire(position);
					this.orientation = orientation.incremente();
					this.position = plateau.voisin(position, orientation);
					plateau.ajouterNavire(position, this);
				}else{
					return false;
				}
			}
		}
		
		this.deplAct--;
		return true;
	}
	
	public void finirTour(){
		if(deplAct != DEPL_MAX){
			aTire = true;
			deplAct = 0;
		}
		
	}

	public void commencerTour(){
		this.recharger();
		this.deplAct = DEPL_MAX;
	}
	
	public int getVie() {
		return this.pVAct;
	}
	
	@Override
	protected void actualizeSprite(Image ob){
		angle=-orientation.ordinal()*60-90+180;
		super.actualizeSprite(ob);
	}
	
	public abstract GraphNavire getGraph(); 
	
}
