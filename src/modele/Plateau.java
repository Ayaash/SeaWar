package modele;

public class Plateau {
	public static final int TAILLE_HORIZONTALE = 13;
	public static final int TAILLE_VERTICALE = 11;
	
	Case plateau[][] = new Case[TAILLE_HORIZONTALE][TAILLE_VERTICALE];
	
	public Plateau(){
		for(int i=0;i<TAILLE_HORIZONTALE;i++){
			for(int j=0;j<TAILLE_VERTICALE;j++){
				plateau[i][j] = new Mer();
			}
		}
	}
	
	
	public boolean ajouterNavire(int[] t, Navire n){
		return plateau[t[0]][t[1]].ajouterNavire(n);
	}
	public boolean enleverNavire(int[] t){
		return plateau[t[0]][t[1]].enleverNavire();
	}

	
	public boolean caseLibre(int[] t){
		return plateau[t[0]][t[1]].estNavigable();
	}	
	
	//Renvoie true si la case voisine est libre
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
	
	//Renvoie un tableau des 2 coordonnées de la case voisine
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
		if(j%2 == 0){
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
		if(j%2 == 1){
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
		if(j%2 == 1){
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
		if(j%2 == 0){
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



























