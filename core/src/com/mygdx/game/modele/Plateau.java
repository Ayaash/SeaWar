package com.mygdx.game.modele;

import com.mygdx.game.Jeu;
import com.mygdx.game.graphique.Textures;

public class Plateau {
	public static final int TAILLE_HORIZONTALE = 13;
	public static final int TAILLE_VERTICALE = 12;
	public static final int NOMBRE_PHARE = 3;
	
	Case plateau[][] = new Case[TAILLE_HORIZONTALE][TAILLE_VERTICALE];
	Phare phares[] = new Phare[NOMBRE_PHARE];
	private static boolean exists = false;
	private static Plateau instance = null;
	
	
	//Singleton
	public static Plateau getInstance(){
		if(!exists){
			instance = new Plateau();
			exists = true;
		}
		return instance;
	}
	private Plateau(){
		for(int i=0;i<TAILLE_HORIZONTALE;i++){
			for(int j=0;j<TAILLE_VERTICALE;j++){
				plateau[i][j] = new Mer(Textures.HEXAGON,i,j);
				plateau[i][j].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
				
			}
		}
		int[] posTerre1={3,4};
		placerTerreSym(posTerre1);
		int[] posTerre2={3,3};
		placerTerreSym(posTerre2);
		int[] posTerre3={3,5};
		placerTerreSym(posTerre3);
		placerMerSym(posTerre1);
		/*plateau[6][4] = new Terre(Textures.HEXAGON,6,4);
		plateau[6][4].setColor(0.2f,0.75f,0f,1f);
		plateau[6][5] = new Terre(Textures.HEXAGON,6,5);
		plateau[6][4].setColor(0.2f,0.75f,0f,1f);
		plateau[5][5] = new Terre(Textures.HEXAGON,5,5);
		plateau[5][5].setColor(0.2f,0.75f,0f,1f);
		plateau[7][5] = new Terre(Textures.HEXAGON,7,5);
		plateau[7][5].setColor(0.2f,0.75f,0f,1f);*/
		
		int posX0=(int) (TAILLE_HORIZONTALE/2);
		int posY0=(int) (Math.random()*TAILLE_VERTICALE);
		
		int randomX=(int) (Math.random()*TAILLE_HORIZONTALE/2);
		int randomY=(int) (Math.random()*TAILLE_VERTICALE);
		int posX1=TAILLE_HORIZONTALE/2+randomX;
		int posY1=randomY;
		
		int posX2=TAILLE_HORIZONTALE/2-randomX;
		int posY2=randomY;
		
		while((posX0==posX1 || posX2==posX1 || posX0==posX2) &&(posY0==posY1 || posY2==posY1 || posY0==posY2)){
			randomX=(int) (Math.random()*TAILLE_HORIZONTALE/2);
			randomY=(int) (Math.random()*TAILLE_VERTICALE);
			
			posX1=TAILLE_HORIZONTALE/2+randomX;
			posY1=randomY;
			
			posX2=TAILLE_HORIZONTALE/2-randomX;
			posY2=randomY;
		}
		
		
		phares[0] = new Phare(Textures.PHARE,Textures.HEXAGON,posX0,posY0);
		phares[1] = new Phare(Textures.PHARE,Textures.HEXAGON,posX1,posY1);
		phares[2] = new Phare(Textures.PHARE,Textures.HEXAGON,posX2,posY2);
	
		phares[0].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
		phares[1].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
		phares[2].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
		
		
		plateau[posX0][posY0] = phares[0];
		plateau[posX1][posY1] = phares[1];
		plateau[posX2][posY2] = phares[2];
		
//		phares[0] = new Phare(Textures.PHARE,Textures.HEXAGON,TAILLE_HORIZONTALE-1,0);
//		phares[1] = new Phare(Textures.PHARE,Textures.HEXAGON,TAILLE_HORIZONTALE-2,0);
//		phares[2] = new Phare(Textures.PHARE,Textures.HEXAGON,TAILLE_HORIZONTALE-1,1);
//	
//		phares[0].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
//		phares[1].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
//		phares[2].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
//		
//		
//		plateau[TAILLE_HORIZONTALE-1][0] = phares[0];
//		plateau[TAILLE_HORIZONTALE-2][0] = phares[1];
//		plateau[TAILLE_HORIZONTALE-1][1] = phares[2];
	}
	
	
	public boolean ajouterNavire(int[] t, Navire n){
		return plateau[t[0]][t[1]].ajouterNavire(n);
	}
	public boolean enleverNavire(int[] t){
		return plateau[t[0]][t[1]].enleverNavire();
	}
	
	public Phare[] getPhares(){
		return phares;
	}


	public boolean recevoirTir(int[] pos, int degats){
		if(pos[0] == -1 || pos[1] == -1){
			return false;
		}else{
			return plateau[pos[0]][pos[1]].recevoirTir(degats);
		}
	}
	
	public boolean caseLibre(int[] t){
		if(t[0] == -1 || t[1] == -1){
			return false;
		}else{
			return plateau[t[0]][t[1]].estNavigable();
		}
	}	
	
	//Code meh... Plus propre d'utiliser caseLibre(voisin(coor, orientation))
	/*
	public boolean caseLibreN(int[] t){
		int coor[] = voisinN(t);
		return plateau[coor[0]][coor[1]].estNavigable();
	}
	public boolean caseLibreNE(int[] t){
		int coor[] = voisinNE(t);
		return plateau[coor[0]][coor[1]].estNavigable();
	}
	public boolean caseLibreSE(int[] t){
		int coor[] = voisinSE(t);
		return plateau[coor[0]][coor[1]].estNavigable();
	}
	public boolean caseLibreS(int[] t){
		int coor[] = voisinS(t);
		return plateau[coor[0]][coor[1]].estNavigable();
	}
	public boolean caseLibreSO(int[] t){
		int coor[] = voisinSO(t);
		return plateau[coor[0]][coor[1]].estNavigable();
	}
	public boolean caseLibreNO(int[] t){
		int coor[] = voisinNO(t);
		return plateau[coor[0]][coor[1]].estNavigable();
	}
	*/
	
	
	public Case getCases(int x,int y){
		return plateau[x][y];
	}
	
	//Renvoie un tableau des 2 coordonnées de la case voisine
	protected int[] voisin(int[] t, Orientation o){
		
		int r[] = {-1,-1};
		
		if(t[0] == -1 || t[1] == -1){
			return r;

		}else{
			switch(o){
			case Nord:
				return voisinN(t);
			case NordEst:
				return voisinNE(t);
			case SudEst:
				return voisinSE(t);
			case Sud:
				return voisinS(t);
			case SudOuest:
				return voisinSO(t);
			case NordOuest:
				return voisinNO(t);
			default:
				return r;
	
			}

		}
	}
	
 	protected int[] voisinN(int[] t){
		int[] coor = new int[2];
		int i = t[0];
		int j = t[1];
		if(j==0){
			coor[0] = -1;
			coor[1] = -1;
		}else{
			coor[0] = i;
			coor[1] = j-1;
		}
		return coor;
		
	}
	protected int[] voisinNE(int[] t){
		int[] coor = new int[2];
		int i = t[0];
		int j = t[1];
		//Test de parité des coordonnées
		//Paire
		if(i%2 == 0){
			if(i<TAILLE_HORIZONTALE-1){
				coor[0] = i+1;
				coor[1] = j;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		//Impaire
		}else{
			if(j==0){
				coor[0] = -1;
				coor[1] = -1;
			}else if(i<TAILLE_HORIZONTALE-1){
				coor[0] = i+1;
				coor[1] = j-1;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		}
		return coor;
		
	}	
	protected int[] voisinSE(int[] t){
		int[] coor = new int[2];
		int i = t[0];
		int j = t[1];
		//Test de parité des coordonnées
		//Impaire
		if(i%2 == 1){
			if(i<TAILLE_HORIZONTALE-1){
				coor[0] = i+1;
				coor[1] = j;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		//Paire
		}else{
			if(j>=TAILLE_VERTICALE-1){
				coor[0] = -1;
				coor[1] = -1;
			}else if(i<TAILLE_HORIZONTALE-1){
				coor[0] = i+1;
				coor[1] = j+1;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		}
		return coor;
		
	}
	protected int[] voisinS(int[] t){
		int[] coor = new int[2];
		int i = t[0];
		int j = t[1];
		if(j==TAILLE_VERTICALE-1){
			coor[0] = -1;
			coor[1] = -1;
		}else{
			coor[0] = i;
			coor[1] = j+1;
		}
		return coor;
	}
	protected int[] voisinSO(int[] t){
		int[] coor = new int[2];
		int i = t[0];
		int j = t[1];
		//Test de parité des coordonnées
		//Impaire
		if(i%2 == 1){
			if(i>0){
				coor[0] = i-1;
				coor[1] = j;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		//Paire
		}else{
			if(j>=TAILLE_VERTICALE-1){
				coor[0] = -1;
				coor[1] = -1;
			}else if(i>0){
				coor[0] = i-1;
				coor[1] = j+1;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		}
		return coor;
		
	}
	protected int[] voisinNO(int[] t){
		int[] coor = new int[2];
		int i = t[0];
		int j = t[1];
		//Test de parité des coordonnées
		//Paire
		if(i%2 == 0){
			if(i>0){
				coor[0] = i-1;
				coor[1] = j;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		//Impaire
		}else{
			if(j<=0){
				coor[0] = -1;
				coor[1] = -1;
			}else if(i>0){
				coor[0] = i-1;
				coor[1] = j-1;
			}else{
				coor[0] = -1;
				coor[1] = -1;
			}
		}
		return coor;
		
	}
		

	
	protected void placerTerre(int[] pos){
		plateau[pos[0]][pos[1]] = new Terre(Textures.HEXAGON,pos[0],pos[1]);
		plateau[pos[0]][pos[1]].setColor(0.2f,0.75f,0f,1f);
	}
	
	protected void placerTerreSym(int[] pos){
		if (pos[0]<(TAILLE_HORIZONTALE/2)){
			int[] posSym={0,0};
			int ecartMotie=(TAILLE_HORIZONTALE/2)-pos[0];
			posSym[0]=TAILLE_HORIZONTALE/2+ecartMotie;
			posSym[1]=pos[1];
			placerTerre(pos);
			placerTerre(posSym);
			
		}else{
			int[] posSym={0,0};
			int ecartMotie=pos[0]-(TAILLE_HORIZONTALE/2);
			posSym[0]=TAILLE_HORIZONTALE/2-ecartMotie;
			posSym[1]=pos[1];
			placerTerre(pos);
			placerTerre(posSym);
		}
	}
	
	protected void placerMer(int[] pos){
		plateau[pos[0]][pos[1]] = new Mer(Textures.HEXAGON,pos[0],pos[1]);
		plateau[pos[0]][pos[1]].setColor(Jeu.cmer.r,Jeu.cmer.g,Jeu.cmer.b,Jeu.cmer.a);
	}
	
	
	protected void placerMerSym(int[] pos){
		if (pos[0]<(TAILLE_HORIZONTALE/2)){
			int[] posSym={0,0};
			int ecartMotie=(TAILLE_HORIZONTALE/2)-pos[0];
			posSym[0]=TAILLE_HORIZONTALE/2+ecartMotie;
			posSym[1]=pos[1];
			placerMer(pos);
			placerMer(posSym);
			
		}else{
			int[] posSym={0,0};
			int ecartMotie=pos[0]-(TAILLE_HORIZONTALE/2);
			posSym[0]=TAILLE_HORIZONTALE/2-ecartMotie;
			posSym[1]=pos[1];
			placerTerre(pos);
			placerTerre(posSym);
		}
	}
	
}



























