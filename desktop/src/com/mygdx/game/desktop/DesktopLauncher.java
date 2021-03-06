package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Gui;
/**
 * 
 * Cette classe permet de lancer le jeu sur PC
 * 
 * */
public class DesktopLauncher {
	public static void main (String[] arg) {
		
		
		//Configuration de la fenetre
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.height=720;
		config.width=1280;
		config.resizable=false;
		config.title="Sea-War";
		
		
		//Lancement du jeu
		new LwjglApplication(new Gui(), config);
	}
}
