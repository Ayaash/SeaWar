package com.mygdx.game.etats;

import com.mygdx.game.modele.Joueur;
import com.mygdx.game.modele.Navire;
import com.mygdx.game.modele.Plateau;

public class Partie {
	
	private static Partie instance = null;
	private static boolean exists = false;
	
	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private int tour;
	
	private Navire navireCourant;
		

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
	
	
	public boolean selectionnerNavire(Navire n){
		if(getCurrentPlayer().getNavires()[0] != n && getCurrentPlayer().getNavires()[1] != n ){
			return false;
		}else{
			navireCourant = n;
			return true;
		}
	}
	
	
	public int demanderNbDeplacements(){
		return navireCourant.deplacementsRestants();
	}
	public int[][] demanderDeplacementsPossibles(){
		return navireCourant.deplacementsPossibles();
	}
	public boolean deplacerNavire(int choix){
		return navireCourant.deplacer(choix);
	}
	public Object[] demanderTirsPossiblesPrincipal(){
		return navireCourant.tirPrincipal();
	}
	public Object[] demanderTirsPossiblesSecondaire(){
		return navireCourant.tirSecondaire();
	}
	public boolean tirerSurUneCase(int[] position, int degats){
		return plateau.recevoirTir(position, degats);
	}
	
	public void finirTourNavire(){
		navireCourant.finirTour();
	}
	/*
	public int finirTourGlobal(){
		for(int i = 0; i<Joueur.NOMBRE_NAVIRES; i++){
			getCurrentPlayer().getNavires()[i].finirTour();
		}
		
		//Gestion des phares
		int nbPhares = 0;
		for(int i = 0; i<Joueur.NOMBRE_NAVIRES; i++){
			int[] pos = getCurrentPlayer().getNavires()[i].getPosition();
			if(pos == plateau.)
		}
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
