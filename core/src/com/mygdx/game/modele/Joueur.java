package com.mygdx.game.modele;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Color;

public class Joueur implements Serializable{

	private static final long serialVersionUID = 1L;

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
	}
	public Color getColor(){
		return new Color(R,V,B,1);
	}
	
	
	public Joueur copie(Plateau plat) {
		Navire[] copieNavires = new Navire[Joueur.NOMBRE_NAVIRES];
		for(int i=0; i<Joueur.NOMBRE_NAVIRES; i++){
			copieNavires[i] = this.navires[i].copie(plat);
		}
		Joueur copie = new Joueur(this.nom, copieNavires, this.id);
		for(int i=0; i<Joueur.NOMBRE_NAVIRES; i++){
			copieNavires[i].setJoueur(copie);
		}
		return copie;
	}
}













