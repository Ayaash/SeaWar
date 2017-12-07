package com.mygdx.game.modele;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphique.InWorldObj;

public class Amiral extends Navire {

	private static final long serialVersionUID = 1L;

	public Amiral(int[] posi , Orientation ori){
    	super(posi,ori);    	
        PVMAX=50;
        deplMax=7;
        TpsRechCanPrinc=3; //Valeur à 2 et non à 1 car on compte le tour actuel dans le temps de recharge
        degCanPrinc=30;
        TpsRechCansec=1;
        degCanSec=10;

        //etat variable
        etatCanPrinc=0;
        etatCanSec=0;
        pVAct=50;
        deplAct=7;

    }
	private int[][] tirPrincipalCasePoss(){
        int [][] res={{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        Orientation av=orientation;
        Orientation tav=av.incremente();
        Orientation tar=tav.incremente();
        Orientation ar=tar.incremente();
        Orientation bar=ar.incremente();
        Orientation bav=bar.incremente();
        int[] case0=plateau.voisin(position,av);
        int[] case1=plateau.voisin(plateau.voisin(position,av),av);
        int[] case2=plateau.voisin(plateau.voisin(position,av),av);
        int[] case3=plateau.voisin(plateau.voisin(position,av) , av);

        res[0]=case0;
        res[1]=case1;
        res[2]=case2;
        res[3]=case3;
        return res;
    }

	public Object[] tirPrincipal(){
        int [][] tabCasePoss=tirPrincipalCasePoss();
        int deg=degCanPrinc;
        Object[] res={tabCasePoss,deg};
        return res;
    }


    private int[][] tirSecondaireCasePoss(){
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
        
        if (plateau.voisin(position,tav)==caseErr){
        	case0=plateau.voisin(position,av);
	    	case1=plateau.voisin(plateau.voisin(position,av),av);
	    	case2=plateau.voisin(position,tav);
	    	case3=plateau.voisin(plateau.voisin(position,av),tav);
	    	case4=plateau.voisin(position,bav);
	    	case5=plateau.voisin(plateau.voisin(position,bav),av);
        }
        else if (plateau.voisin(position,bav)==caseErr){
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

    public Object[] tirSecondaire(){
        int [][] tabCasePoss=tirSecondaireCasePoss();
        int deg=degCanSec;
        Object[] res={tabCasePoss,deg};
        return res;
    }
    
    
    

  
    
    
    
    
    
    
    
    
    
    

}


