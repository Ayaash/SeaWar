package com.mygdx.game.modele;
import core.mygdx.game.actor.GraphFregate;
import core.mygdx.game.actor.GraphNavire;

public class Fregate extends Navire {
	
	public static final int PV_MAX = 50;
	public static final int DEPL_MAX = 7;
	public static final int TPS_RECH_CAN_PRINC = 1;
	public static final int DEG_CAN_PRINC = 30;
	public static final int TPS_RECH_CAN_SEC = 0;
	public static final int DEG_CAN_SEC = 10;

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	public Fregate(int[] posi , Orientation ori){
		super(posi,ori);
		//etat variable
		pVAct=PV_MAX;
		deplAct=DEPL_MAX;

	}

	public int[][] tirPrincipalCasesPos(){
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


	public int[][] tirSecondaireCasesPos(){
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

	@Override
	public GraphNavire getGraph() {
		return new GraphFregate(this);
	}

}
