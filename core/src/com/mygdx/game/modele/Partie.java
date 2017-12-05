package com.mygdx.game.modele;

public class Partie {
	
	private static Partie instance = null;
	private static boolean exists = false;
	
	private Plateau plateau;
	
	
	
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
	
	
	
	
	
	
	
	
	
	
}
