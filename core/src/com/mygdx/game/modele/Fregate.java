package com.mygdx.game.modele;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphique.InWorldObj;

public class Fregate extends Navire {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Fregate(int[] posi , Orientation ori){
		super(posi,ori);
	
        PVMAX=50;
        deplMax=7;
        TpsRechCanPrinc=1;
        degCanPrinc=30;
        TpsRechCansec=0;
        degCanSec=10;
        //etat variable
        etatCanPrinc=0;
        etatCanSec=0;
        pVAct=50;
        deplAct=7;

    }

    int[][] tirPrincipalCasePoss(){
        int [][] res={{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        Orientation av=orientation;
        Orientation tav=av.incremente();
        Orientation tar=tav.incremente();
        Orientation ar=tar.incremente();
        Orientation bar=ar.incremente();
        Orientation bav=bar.incremente();
        int[] case0=plateau.voisin(position,av);
        int[] case1=plateau.voisin(position,tav);
        int[] case2=plateau.voisin(position,tar);
        int[] case3=plateau.voisin(plateau.voisin(position,av) , av);
        int[] case4=plateau.voisin(position,bar);
        int[] case5=plateau.voisin(position,bav);

        res[0]=case0;
        res[1]=case1;
        res[2]=case2;
        res[3]=case3;
        res[4]=case4;
        res[5]=case5;
        return res;
    }

    Object[] tirPrincipal(){
        int [][] tabCasePoss=tirPrincipalCasePoss();
        int deg=degCanPrinc;
        Object[] res={tabCasePoss,deg};
        return res;
    }


    int[][] tirSecondaireCasePoss(){
        int [][] res={{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        Orientation av=orientation;
        Orientation tav=av.incremente();
        Orientation tar=tav.incremente();
        Orientation ar=tar.incremente();
        Orientation bar=ar.incremente();
        Orientation bav=bar.incremente();
        int[] case0=plateau.voisin(position,av);
        int[] case1=plateau.voisin(position,tav);
        int[] case2=plateau.voisin(position,tar);
        int[] case3=plateau.voisin(position,ar );
        int[] case4=plateau.voisin(position,bar);
        int[] case5=plateau.voisin(position,bav);

        res[0]=case0;
        res[1]=case1;
        res[2]=case2;
        res[3]=case3;
        res[4]=case4;
        res[5]=case5;
        return res;
    }

    Object[] tirSecondaire(){
        int [][] tabCasePoss=tirSecondaireCasePoss();
        int deg=degCanSec;
        Object[] res={tabCasePoss,deg};
        return res;
    }
}
