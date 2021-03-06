package com.mygdx.game.modele;

import com.badlogic.gdx.graphics.Texture;

public class Textures {
	public static Texture BACKGROUND;
	private static String ADR_BACKGROUND="images/background.jpg";
	
	public static Texture BOUTON_TEST;
	private static String ADR_BOUTON_TEST="images/bouton_test.png";

	public static Texture WIMG;
	private static String ADR_WIMG="images/WIMG.png";

	public static Texture NOIMAGE;
	private static String ADR_NOIMAGE="images/No_Image.png";

	public static Texture HEXAGON;
	private static String ADR_HEXAGON="images/Hexa_blanc.png";

	public static Texture AMIRAL;
	private static String ADR_AMIRAL="images/Amiral.png";

	public static Texture FREGATE;
	private static String ADR_FREGATE="images/Fregate.png";
	
	public static Texture PHARE;
	private static String ADR_PHARE="images/Phare.png";

	public static Texture BARRE_HORIZ;
	private static String ADR_BARRE_HORIZ="images/barre_horiz.jpg";
		
	public static boolean chargerTextures(){
		
		boolean noErr=true;
		
		try {
			BACKGROUND=new Texture(ADR_BACKGROUND);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_BACKGROUND);
				noErr=false;
		}
		
		try {
			BOUTON_TEST=new Texture(ADR_BOUTON_TEST);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_BOUTON_TEST);
				noErr=false;
		}
		
		try {
			WIMG=new Texture(ADR_WIMG);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_WIMG);
				noErr=false;
		}
		
		try {
			NOIMAGE=new Texture(ADR_NOIMAGE);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_NOIMAGE);
				noErr=false;
		}
		
		
		try {
			HEXAGON=new Texture(ADR_HEXAGON);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_HEXAGON);
				noErr=false;
		}
		
		try {
			FREGATE=new Texture(ADR_FREGATE);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_FREGATE);
				noErr=false;
		}
		
		try {
			AMIRAL=new Texture(ADR_AMIRAL);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_AMIRAL);
				noErr=false;
		}
		
		try {
			PHARE=new Texture(ADR_PHARE);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_PHARE);
				noErr=false;
		}

		try {
			BARRE_HORIZ=new Texture(ADR_BARRE_HORIZ);
		} catch (Exception e) {
				System.out.println("Texture manquante :"+ADR_BARRE_HORIZ);
				noErr=false;
		}
		
		return noErr;
		
	}
	
}
