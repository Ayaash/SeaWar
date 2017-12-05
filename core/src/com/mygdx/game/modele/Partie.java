package com.mygdx.game.modele;

public class Partie {
	
	private static Partie instance = null;
	private static boolean exists = false;
	
	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private int tour;
	
	
	
	//Singleton
	public static Partie getInstance(){
		if(!exists){
			instance = new Partie();
			exists = true;
		}
		return instance;
	}
	private Partie(){
		plateau = Plateau.getInstance();
	}
	
	
	
	private Joueur getCurrentPlayer(){
		if(tour%2 == 1){
			return joueur1;
		}else{
			return joueur2;
		}
	}
	
	
	
	
	
	
}
