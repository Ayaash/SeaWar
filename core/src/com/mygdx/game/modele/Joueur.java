package com.mygdx.game.modele;

public class Joueur {

	public static final int NOMBRE_NAVIRES = 2;
	
	private String nom;
	protected Navire navires[];
	private int id;
	
	
	private float R;
	private float V;
	private float B;
	
	public String getNom(){
		return nom;
	}
	public Navire[] getNavires(){
		return navires;
	}
	public int getId(){
		return id;
	}
	public int nbNaviresActifs(){
		int res = 0;
		for(int i=0; i<NOMBRE_NAVIRES; i++){
			if(navires[i].encaisserDegats(0)) res++;
		}
		return res;
	}
	
	
	public Joueur(String nom, Navire[] navires, int id){
		this.nom = nom;
		this.navires = navires.clone();
		this.id = id;
		R=1;V=1;B=1;
	}
	
	public void setColor(float R,float V,float B){
		this.R=R;
		this.V=V;
		this.B=B;
		int i=0;
		for(i=0;i<navires.length;i++){
			navires[i].setColor(R, V, B, 1);
		}
	}
}
