package com.mygdx.game.modele;

import com.mygdx.game.Jeu;
import com.mygdx.game.graphique.Textures;

public class Plateau {
	public static final int TAILLE_HORIZONTALE = 13;
	public static final int TAILLE_VERTICALE = 11;
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
		plateau[2][2] = new Terre(Textures.HEXAGON,2,2);
		plateau[2][2].setColor(0.2f,0.75f,0f,1f);
		
		int posX0=(int) (Math.random()*TAILLE_HORIZONTALE);
		int posY0=(int) (Math.random()*TAILLE_VERTICALE);

		int posX1=(int) (Math.random()*TAILLE_HORIZONTALE);
		int posY1=(int) (Math.random()*TAILLE_VERTICALE);
		
		int posX2=(int) (Math.random()*TAILLE_HORIZONTALE);
		int posY2=(int) (Math.random()*TAILLE_VERTICALE);
		
		while((posX0==posX1 || posX2==posX1 || posX0==posX2) &&(posY0==posY1 || posY2==posY1 || posY0==posY2)){
			posX1=(int) (Math.random()*TAILLE_HORIZONTALE);
			posY1=(int) (Math.random()*TAILLE_VERTICALE);
			
			posX2=(int) (Math.random()*TAILLE_HORIZONTALE);
			posY2=(int) (Math.random()*TAILLE_VERTICALE);
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
		

	
}



























