package com.mygdx.game.modele;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphique.InWorldObj;

public class Fregate extends Navire {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Fregate(Texture img,int[] posi , Orientation ori){
		super(img,posi,ori);

		PV_MAX=50;
		DEPL_MAX=7;
		TPS_RECH_CAN_PRINC=2; //Valeur à 2 et non à 1 car on compte le tour actuel dans le temps de recharge
		DEG_CAN_PRINC=30;
		TPS_RECH_CAN_SEC=0;
		DEG_CAN_SEC=10;
		//etat variable
		pVAct=PV_MAX;
		deplAct=DEPL_MAX;

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

	public Object[] tirPrincipal(){
		int [][] tabCasePoss=tirPrincipalCasePoss();
		int deg=DEG_CAN_PRINC;
		Object[] res={tabCasePoss,deg};
		aTire=true;
		this.etatCanPrinc = TPS_RECH_CAN_PRINC+1;
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

	public Object[] tirSecondaire(){
		int [][] tabCasePoss=tirSecondaireCasePoss();
		int deg=DEG_CAN_SEC;
		Object[] res={tabCasePoss,deg};
		aTire=true;
		this.etatCanSec = TPS_RECH_CAN_SEC+1;
		return res;
	}
}
