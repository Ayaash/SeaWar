package com.mygdx.game.modele;

public class Joueur {

	public static final int NOMBRE_NAVIRES = 2;
	
	private String nom;
	protected Navire navires[];
	
	float R;float V;float B;
	
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
	
	public void setColor(float R,float V, float B){
		this.R=R;
		this.V=V;
		this.B=B;
		int i=0;
		for(i=0;i<navires.length;i++){
			navires[i].setColor(R, V, B, 1);
		}

	}
	
	public Joueur(String nom, Navire[] navires){
		this.nom = nom;
		this.navires = navires.clone();
		R=0;V=0;B=0;
	}
}
