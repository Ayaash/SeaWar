package com.mygdx.game.modele;

public class Joueur {

	public static final int NOMBRE_NAVIRES = 2;
	
	private String nom;
	protected Navire navires[];
	
	public String getNom(){
		return nom;
	}
	public Navire[] getNavires(){
		return navires;
	}
	
	public int nbNaviresActifs(){
		int res = 0;
		for(int i=0; i<NOMBRE_NAVIRES; i++){
			if(navires[i].encaisserDegats(0)) res++;
		}
		return res;
	}
	
	
	public Joueur(String nom, Navire[] navires){
		this.nom = nom;
		this.navires = navires.clone();
	}
}
