package com.mygdx.game.modele;
import core.mygdx.game.actor.GraphFregate;
import core.mygdx.game.actor.GraphNavire;

public class Fregate extends Navire {
	

	public int getPV_MAX(){
		return 50;
	}
	public int getDEPL_MAX(){
		return 7;
	}
	public int getTPS_RECH_CAN_PRINC(){
		return 1;
	}
	public int getDEG_CAN_PRINC(){
		return 30;
	}
	public int getTPS_RECH_CAN_SEC(){
		return 0;
	}
	public int getDEG_CAN_SEC(){
		return 10;
	}

	private static final long serialVersionUID = 1L;
	public Fregate(int[] posi , Orientation ori, Plateau p){
		super(posi,ori, p);
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

	public Navire copie(Plateau plat) {
    	System.out.println("freg "+this.position[0]+","+this.position[1]);

		Fregate copie = new Fregate(this.position.clone(), this.orientation, plat);
		copie.aideALaCopie(copie);
		return copie;
	}
	
	@Override
	public GraphNavire getGraph() {
		return new GraphFregate(this);
	}
	
	

}
