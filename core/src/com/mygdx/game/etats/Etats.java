package com.mygdx.game.etats;

import com.mygdx.game.modele.*;

interface Etats {
	
	public boolean selectionnerNavire(Navire n);
	public int demanderNbDeplacements();
	public int[][] demanderDeplacementsPossibles();
	public boolean deplacerNavire(int choix);
	public Object[] demanderTirsPossiblesPrincipal();
	public Object[] demanderTirsPossiblesSecondaire();
	public boolean tirerSurUneCase(int[] position, int degats);
	public void FinirTourNavire();
	
	
	
	
	

}
