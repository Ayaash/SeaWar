package com.mygdx.game.graphique;

import com.badlogic.gdx.graphics.Texture;

public class Textures {
	public static Texture BOUTON_TEST;
	private static String ADR_BOUTON_TEST="../images/bouton_test.png";

	public static Texture WIMG;
	private static String ADR_WIMG="../images/WIMG.png";

	public static Texture NOIMAGE;
	private static String ADR_NOIMAGE="../images/No_Image.png";

	public static Texture HEXAGON;
	private static String ADR_HEXAGON="../images/Hexagon.png";

	public static Texture AMIRAL;
	private static String ADR_AMIRAL="../images/Amiral.png";

	public static Texture FREGATE;
	private static String ADR_FREGATE="../images/Fregate.png";
	
	public static Texture PHARE;
	private static String ADR_PHARE="../images/Phare.png";

	
	public static boolean chargerTextures(){
		
		boolean noErr=true;
		
		try {
			BOUTON_TEST=new Texture(ADR_BOUTON_TEST);
		} catch (Exception e) {
				InFenDebug.println("Texture manquante :"+ADR_BOUTON_TEST);
				noErr=false;
		}
		
		try {
			WIMG=new Texture(ADR_WIMG);
		} catch (Exception e) {
				InFenDebug.println("Texture manquante :"+ADR_WIMG);
				noErr=false;
		}
		
		try {
			NOIMAGE=new Texture(ADR_NOIMAGE);
		} catch (Exception e) {
				InFenDebug.println("Texture manquante :"+ADR_NOIMAGE);
				noErr=false;
		}
		
		
		try {
			HEXAGON=new Texture(ADR_HEXAGON);
		} catch (Exception e) {
				InFenDebug.println("Texture manquante :"+ADR_HEXAGON);
				noErr=false;
		}
		
		try {
			FREGATE=new Texture(ADR_FREGATE);
		} catch (Exception e) {
				InFenDebug.println("Texture manquante :"+ADR_FREGATE);
				noErr=false;
		}
		
		try {
			AMIRAL=new Texture(ADR_AMIRAL);
		} catch (Exception e) {
				InFenDebug.println("Texture manquante :"+ADR_AMIRAL);
				noErr=false;
		}
		
		try {
			PHARE=new Texture(ADR_PHARE);
		} catch (Exception e) {
				InFenDebug.println("Texture manquante :"+ADR_PHARE);
				noErr=false;
		}
		
		
		return noErr;
		
	}
	
}
