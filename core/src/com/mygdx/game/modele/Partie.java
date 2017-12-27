package com.mygdx.game.modele;
import java.io.Serializable;
import java.util.Arrays;


public class Partie implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static Partie instance = null;
	private static boolean exists = false;
	
	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private int tour;
	private int victoire;
	
	private Navire navireCourant = null;
	private boolean tourEnCours = false;
		

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
	/* //TODO
	public boolean sauvegarderPartie(){
		try{
			FileOutputStream fileOut =
					new FileOutputStream("/users/elo/lpicholl/Reptravail/SeaWarSave.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ~/Reptravail");
		}catch (FileNotFoundException f){
			
		} catch (IOException i) {
			i.printStackTrace();
		}


		
		return true;
	}
	*/
	
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
				if(n.encaisserDegats(0) == true){
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
			int[] pos = getCurrentPlayer().getNavires()[i].getPosition().clone();
			for(int j = 0; j<Plateau.NOMBRE_PHARE; j++){
				if(Arrays.equals(pos, phares[j].getPosition())){
					phares[j].setJoueur(getCurrentPlayer());
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
				if(adversaire.getNavires()[i].encaisserDegats(0) == true){
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
}
		