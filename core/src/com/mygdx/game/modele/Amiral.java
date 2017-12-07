package com.mygdx.game.modele;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphique.InWorldObj;

public class Amiral extends Navire {

	private static final long serialVersionUID = 1L;

	public Amiral(int[] posi , Orientation ori){
    	super(posi,ori);    	
    	PV_MAX=50;
        DEPL_MAX=3;
        TPS_RECH_CAN_PRINC=3;
        DEG_CAN_PRINC=30;
        TPS_RECH_CAN_SEC=1;
        DEG_CAN_SEC=10;

        //etat variable
        
        pVAct=PV_MAX;
        deplAct=DEPL_MAX;
        aTire=false;
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
        int deg=DEG_CAN_PRINC;
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
        int deg=DEG_CAN_SEC;
        Object[] res={tabCasePoss,deg};
        return res;
    }
    
    
    

  
    
    
    
    
    
    
    
    
    
    

}


