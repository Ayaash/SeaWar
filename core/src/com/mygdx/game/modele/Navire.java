package com.mygdx.game.modele;

import java.util.Arrays;


import core.mygdx.game.actor.GraphNavire;


public abstract class Navire extends InWorldObj {
	
	//Constantes du navire
	public abstract int getPV_MAX();
	public abstract int getDEPL_MAX();
	public abstract int getTPS_RECH_CAN_PRINC();
	public abstract int getDEG_CAN_PRINC();
	public abstract int getTPS_RECH_CAN_SEC();
	public abstract int getDEG_CAN_SEC();
	
	//Variables du navire
	protected Orientation orientation;
	protected int etatCanPrinc;
	protected int etatCanSec;
	protected int pVAct;
	protected int deplAct;
	protected Boolean aTire;
	protected Boolean mort;	
	protected Boolean dejaTouche;
	protected Joueur joueur;
	protected Plateau plateau;
	
	public  abstract Navire copie(Plateau plat);
	
	
	public Navire(int[] posi, Orientation o, Plateau p){
		super(posi[0],posi[1]);
		this.position = posi.clone();
		this.orientation = o;
		this.etatCanPrinc=0;
		this.etatCanSec=0;
		this.aTire=false;
		this.mort = false;
		this.dejaTouche = false;
		this.plateau = p;
		
		//etat variable
		pVAct=getPV_MAX();
		deplAct=getDEPL_MAX();
		
		plateau.ajouterNavire(position, this);
	}

	
	public void setJoueur(Joueur j){
		joueur=j;
	}
	
	public Joueur getJoueur(){
		return joueur;
	}
	
	public Plateau getPlateau(){
		return plateau;
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
		return (etatCanPrinc == 0) && !aTire && !mort && (peutSeDeplacer() || sEstDeplace());
	}
	public boolean peutTirerSecondaire(){
		return (etatCanSec == 0) && !aTire && !mort && (peutSeDeplacer() || sEstDeplace());
	}
	
	public abstract int[][] tirPrincipalCasesPos();
	public abstract int[][] tirSecondaireCasesPos();
	public int rechargePrincipal() {
		return etatCanPrinc;
	}
	
	public int rechargeSecondaire() {
		return etatCanSec;
	}
	
	public Tir tirPrincipal(){
        int [][] tabCasePoss=tirPrincipalCasesPos();
        int deg=getDEG_CAN_PRINC();
        Tir res=new Tir(tabCasePoss,deg);
        return res;
    }
	public void tirPrincipalEffectue(){
		aTire=true;
        this.etatCanPrinc = getTPS_RECH_CAN_PRINC()+1;
	}
	public Tir tirSecondaire(){
        int [][] tabCasePoss=tirSecondaireCasesPos();
        int deg=getDEG_CAN_SEC();
        Tir res=new Tir(tabCasePoss,deg);
        return res;
    }
	public void tirSecondaireEffectue(){
		aTire=true;
        this.etatCanSec = getTPS_RECH_CAN_SEC()+1;
	}
	public void miseEnRechargementCanPrinc(){
		etatCanPrinc=getTPS_RECH_CAN_PRINC()+1;
		aTire=true;
	}
	public void miseEnRechargementCanSec(){
		etatCanSec=getTPS_RECH_CAN_SEC()+1;
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
 		if(dejaTouche && degats > 0){
 			degats = degats * 3;
 		}else if(degats > 0){
 			dejaTouche = true;
 		}
    	pVAct -= degats;
    	if(pVAct > 0){
    		return true;
    	}else{
    		plateau.enleverNavire(position);
    		this.mort = true;
    		this.position[0] = -1;
    		this.position[1] = -1;
    		return false;
    	}
    }
 	
 	
 	
 	public boolean peutSeDeplacer(){
 		return plateau.caseLibre(plateau.voisin(position, orientation.decremente()))
 				||plateau.caseLibre(plateau.voisin(position, orientation))
 				||plateau.caseLibre(plateau.voisin(position, orientation.incremente()))
 				;
 	}
 	
 	public boolean sEstDeplace(){
 		return !(getDEPL_MAX() == deplAct) || mort;
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
			if(deplAct == getDEPL_MAX()) retournerNavire();
			deplAct = 0;
			aTire = true;
			return null;
		}
		
	}
	
	private void retournerNavire(){
		this.orientation = orientation.incremente().incremente().incremente();
		
	}
	
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
		
		if(pos[0] == -1 && pos[1] == -1){
			retournerNavire();
			deplAct = 0;
			aTire = true;
			return true;
		}
		
		
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
		if(deplAct != getDEPL_MAX()){
			aTire = true;
			deplAct = 0;
		}
		
	}

	public void commencerTour(){
		if(!mort){
			this.recharger();
			this.deplAct = getDEPL_MAX();
			this.dejaTouche = false;
		}
	}
	
	public int getVie() {
		return this.pVAct;
	}
	
	public boolean estMort(){
		return this.mort;
	}
	
	public Orientation getOrientation(){
		return orientation;
	}
		
	protected void aideALaCopie(Navire navire){
		navire.orientation = this.orientation;
		navire.etatCanPrinc = this.etatCanPrinc;
		navire.etatCanSec = this.etatCanSec;
		navire.pVAct = this.pVAct;
		navire.deplAct = this.deplAct;
		navire.aTire = this.aTire;
		navire.mort = this.mort;
		navire.dejaTouche = this.dejaTouche;
	}

	public abstract GraphNavire getGraph();
	
	
}
