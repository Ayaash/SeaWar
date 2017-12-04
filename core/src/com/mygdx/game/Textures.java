package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Textures {
	public static Texture BOUTON_TEST;
	private static String ADR_BOUTON_TEST="../images/bouton_test.png";

	public static Texture WIMG;
	private static String ADR_WIMG="../images/WIMG.png";

	public static Texture NOIMAGE;
	private static String ADR_NOIMAGE="../images/No_Image.png";

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
		
		return noErr;
		
	}
	
}
