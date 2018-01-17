package com.mygdx.game.modele;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;


public class Partie implements Serializable{

	private static final long serialVersionUID = 1L;

	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private int tour;
	private int victoire;

	private Navire navireCourant = null;
	private boolean tourEnCours = false;



	public Partie(){
		plateau = new Plateau();
		tour = 1;

		//Ajout des navires
		Navire naviresJ1[] = new Navire[2];
		Navire naviresJ2[] = new Navire[2];
		int position[] = {0,5};
		naviresJ1[0] = new Amiral(position, Orientation.SudEst, plateau);

		position[0] = 1;
		//position[1] = 6;
		naviresJ1[1] = new Fregate(position, Orientation.SudEst, plateau);

		position[0] = Plateau.TAILLE_HORIZONTALE - 1;
		position[1] = Plateau.TAILLE_VERTICALE - 1;
		naviresJ2[0] = new Amiral(position, Orientation.NordOuest, plateau);

		position[1] = Plateau.TAILLE_VERTICALE - 2;
		naviresJ2[1] = new Fregate(position, Orientation.NordOuest, plateau);

		joueur1 = new Joueur("Nimitz", naviresJ1, 1);
		joueur2 = new Joueur("Yamamoto", naviresJ2, 2);

		naviresJ1[0].setJoueur(joueur1);
		naviresJ1[1].setJoueur(joueur1);
		naviresJ2[0].setJoueur(joueur2);
		naviresJ2[1].setJoueur(joueur2);
	}

	//TODO: Tester ces fonctions
	public boolean sauvegarderPartie(){
		try{
			FileOutputStream fileOut =
					new FileOutputStream("/tmp/SeaWarSave.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/SeaWarSave.ser");
		}catch (FileNotFoundException f){
			return false;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		}



		return true;
	}
	public static Partie chargerPartie(){
		Partie partie = null;
		try{
			FileInputStream fileIn =
					new FileInputStream("/tmp/SeaWarSave.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			partie = (Partie) in.readObject();
			in.close();
			fileIn.close();
		}catch (FileNotFoundException f){
			return null;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return partie;
	}

	public Partie(Plateau plat, int tour, int victoire, Navire navCourant, boolean tourEnCours){
		this.plateau = plat;
		this.tour = tour;
		this.victoire = victoire;
		this.navireCourant = navCourant;
		this.tourEnCours = tourEnCours;
	}


	public Plateau getPlateau() {
		return plateau;
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

	public Joueur getPlayer(int i) {
		switch(i) {
		case 1:
			return this.joueur1;
		case 2: 
			return this.joueur2;
		default:
			return null;	
		}
	}

	public void incrementerTour(){
		tour++;
	}

	public Navire getNavireCourant(){
		return navireCourant;
	}
	public boolean getTourEnCours(){
		return tourEnCours;
	}

	public boolean selectionnerNavire(Navire n){
		if(n!=null){
			if(getCurrentPlayer().getNavires()[0] != n && getCurrentPlayer().getNavires()[1] != n ){
				return false;
			}else{
				if(!n.estMort()){
					navireCourant = n;
					return true;
				}else{
					return false;
				}

			}
		}else{
			return false;
		}
	}

	public boolean peutSeDeplacer(){
		if(navireCourant == null){
			return false;
		}
		return navireCourant.peutSeDeplacer();
	}
	public boolean sEstDeplace(){
		if(navireCourant == null){
			return false;
		}
		return navireCourant.sEstDeplace();
	}
	public int demanderNbDeplacements(){
		if(navireCourant == null){
			return -1;
		}
		return navireCourant.deplacementsRestants();
	}
	public int[][] demanderDeplacementsPossibles(){
		if(navireCourant == null){
			return null;
		}
		return navireCourant.deplacementsPossibles();
	}
	public boolean deplacerNavire(int[] choix){
		if(navireCourant == null){
			return false;
		}
		return navireCourant.deplacer(choix);
	}
	public boolean peutTirerPrincipal(){
		if(navireCourant == null){
			return false;
		}else{
			return navireCourant.peutTirerPrincipal();
		}
	}
	public boolean peutTirerSecondaire(){
		if(navireCourant == null){
			return false;
		}else{
			return navireCourant.peutTirerSecondaire();
		}
	}

	public Tir demanderTirsPossiblesPrincipal(){
		if(navireCourant == null){
			return null;
		}
		return navireCourant.tirPrincipal();
	}
	public Tir demanderTirsPossiblesSecondaire(){
		if(navireCourant == null){
			return null;
		}
		return navireCourant.tirSecondaire();
	}
	public void tirPrincipalEffectue(){
		if(navireCourant != null){
			navireCourant.tirPrincipalEffectue();
		}
	}
	public void tirSecondaireEffectue(){
		if(navireCourant != null){
			navireCourant.tirSecondaireEffectue();
		}
	}

	public boolean tirerSurUneCase(int[] position, int degats){
		return plateau.recevoirTir(position, degats);
	}

	public boolean finirTourNavire(){
		if(navireCourant != null){
			if(navireCourant.sEstDeplace()){
				navireCourant.finirTour();
				tourEnCours = false;
				navireCourant = null;
				return true;
			}else{
				return false;
			}

		}else{
			return false;
		}

	}

	public int finirTourGlobal(){

		for(int i=0; i< Joueur.NOMBRE_NAVIRES;i++){
			if(getCurrentPlayer().getNavires()[i].sEstDeplace() == false){
				return -1;
			}
		}


		for(int i = 0; i<Joueur.NOMBRE_NAVIRES; i++){
			//getCurrentPlayer().getNavires()[i].recharger();
			getCurrentPlayer().getNavires()[i].commencerTour();

		}

		//Gestion des phares
		int nbPhares = 0;
		Phare[] phares = plateau.getPhares();
		for(int i = 0; i<Joueur.NOMBRE_NAVIRES; i++){
			Navire nav =  getCurrentPlayer().getNavires()[i];
			if(!nav.estMort()){
				int[] pos = nav.getPosition().clone();
				for(int j = 0; j<Plateau.NOMBRE_PHARE; j++){
					if(Arrays.equals(pos, phares[j].getPosition())){
						phares[j].setJoueur(getCurrentPlayer());
					}
				}
			}

		}
		for(int i=0;i<Plateau.NOMBRE_PHARE;i++){
			if(phares[i].getJoueur() == getCurrentPlayer()){
				nbPhares++;
			}
		}
		if(nbPhares == Plateau.NOMBRE_PHARE){
			victoire = getCurrentPlayer().getId();
		}

		if(victoire == 0){
			//Gestion table rase
			Joueur adversaire;
			if(tour%2 == 0){
				adversaire = joueur1;
			}else{
				adversaire = joueur2;
			}
			boolean mort = true;
			for(int i=0; i<Joueur.NOMBRE_NAVIRES;i++){
				if(adversaire.getNavires()[i].estMort() == false){
					mort = false;
				}
			}

			if(mort){
				victoire = getCurrentPlayer().getId();
			}
		}




		tour++;
		tourEnCours = false;
		navireCourant = null;
		return victoire;
	}



	public Partie copieProfonde(){
		Plateau copiePlateau = this.plateau.copie();
		Joueur j1 = this.joueur1.copie(this.plateau);
		Joueur j2 = this.joueur2.copie(this.plateau);

		Partie copie = new Partie(copiePlateau, tour, victoire, navireCourant, tourEnCours);
		copie.ajouterJoueurs(j1, j2);

		return copie;
	}

}


