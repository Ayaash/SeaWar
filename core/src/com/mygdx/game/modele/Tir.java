package com.mygdx.game.modele;

public class Tir {
	
	public int[][] cases;
	public int degats;
	
	public Tir(int [][] cases, int degats){
		int nbCases = cases.length;
		this.cases = new int[nbCases][2];
		for(int i=0; i<nbCases; i++){
			System.arraycopy(cases[i], 0, this.cases[i], 0, 2);
		}
		
		this.degats = degats;
		
	}

}
