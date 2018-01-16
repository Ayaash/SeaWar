package com.mygdx.game.modele;


import java.util.Arrays;

import core.mygdx.game.actor.GraphAmiral;
import core.mygdx.game.actor.GraphNavire;

public class Amiral extends Navire {
	
	public int getPV_MAX(){
		return 100;
	}
	public int getDEPL_MAX(){
		return 3;
	}
	public int getTPS_RECH_CAN_PRINC(){
		return 3;
	}
	public int getDEG_CAN_PRINC(){
		return 50;
	}
	public int getTPS_RECH_CAN_SEC(){
		return 1;
	}
	public int getDEG_CAN_SEC(){
		return 30;
	}


	private static final long serialVersionUID = 1L;

	public Amiral(int[] posi , Orientation ori, Plateau p){
    	super(posi,ori, p);
    }
	public int[][] tirPrincipalCasesPos(){
        int [][] res={{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        Orientation av=orientation;

        int[] case0=plateau.voisin(position,av);
        int[] case1=plateau.voisin(case0,av);
        int[] case2=plateau.voisin(case1,av);
        int[] case3=plateau.voisin(case2,av);

        res[0]=case0;
        res[1]=case1;
        res[2]=case2;
        res[3]=case3;
        return res;
    }

    public int[][] tirSecondaireCasesPos(){
        int [][] res={{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        Orientation av=orientation;
        Orientation tav=av.incremente();
        Orientation tar=tav.incremente();
        Orientation ar=tar.incremente();
        Orientation bar=ar.incremente();
        Orientation bav=bar.incremente();
        
        int[] case0;
    	int[] case1;
    	int[] case2;
    	int[] case3;
    	int[] case4;
    	int[] case5;
        
        
        int[] caseErr={-1,-1};
        if (Arrays.equals(plateau.voisin(position,tav), caseErr)){
        	case0=plateau.voisin(position,av);
	    	case1=plateau.voisin(plateau.voisin(position,av),av);
	    	case2=plateau.voisin(position,tav);
	    	case3=plateau.voisin(plateau.voisin(position,av),tav);
	    	case4=plateau.voisin(position,bav);
	    	case5=plateau.voisin(plateau.voisin(position,bav),av);
        }
        else if (Arrays.equals(plateau.voisin(position,bav), caseErr)){
        	case0=plateau.voisin(position,av);
	    	case1=plateau.voisin(plateau.voisin(position,av),av);
	    	case2=plateau.voisin(position,tav);
	    	case3=plateau.voisin(plateau.voisin(position,av),tav);
	    	case4=plateau.voisin(position,bav);
	    	case5=plateau.voisin(plateau.voisin(position,av),bav);
        	
        }else{
	        case0=plateau.voisin(position,av);
	    	case1=plateau.voisin(plateau.voisin(position,av),av);
	    	case2=plateau.voisin(position,tav);
	    	case3=plateau.voisin(plateau.voisin(position,tav),av);
	    	case4=plateau.voisin(position,bav);
	    	case5=plateau.voisin(plateau.voisin(position,bav),av);
        }
        res[0]=case0;
        res[1]=case1;
        res[2]=case2;
        res[3]=case3;
        res[4]=case4;
        res[5]=case5;
        return res;
    }
	
    public Navire copie(Plateau plat) {
		Amiral copie = new Amiral(this.position.clone(), this.orientation, plat);
		copie.aideALaCopie(copie);
		return copie;
	}
    
    
    @Override
	public GraphNavire getGraph() {
		return new GraphAmiral(this);
	}


}


