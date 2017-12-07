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
		tour = 1;
	}
	
	public void ajouterJoueurs(Joueur j1, Joueur j2){
		this.joueur1 = j1;
		this.joueur2 = j2;
	}
	

	public Joueur getCurrentPlayer(){
		if(tour%2 == 1){
			return joueur1;
		}else{
			return joueur2;
		}
	}
	
	public void incrementerTour(){
		tour++;
	}
	
	
	
	
	
	
}
