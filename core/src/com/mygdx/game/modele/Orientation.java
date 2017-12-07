package com.mygdx.game.modele;

public enum Orientation {
	Nord,
	NordEst,
	SudEst,
	Sud,
	SudOuest,
	NordOuest;
	
	
	public Orientation incremente(){
		if(ordinal() == values().length -1){
			return values()[0];
		}else{
			return values()[ordinal() +1];
		}
	}
	
	public Orientation decremente(){
		if(ordinal() == 0){
			return values()[values().length -1];
		}else{
			return values()[ordinal() - 1];
		}
	}
}
