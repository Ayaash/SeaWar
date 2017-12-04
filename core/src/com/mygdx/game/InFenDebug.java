package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
/**
 * 
 * Classe permetant d'afficher des messages a l'ecran (pour debugage uniquement )
 * */
public class InFenDebug {

	public static BitmapFont font=new BitmapFont();
	protected static String msg="";
	
	private static Affichable barre;
	
	
	public static void init(){
		font.setColor(Color.GREEN);
		barre=new Affichable(Textures.WIMG, 0, 200, Gdx.graphics.getWidth(), 5,0);
		barre.setColor(0, 1, 0, 1);
	}
	
	public static void println(String s){
		msg=s+'\n'+msg;
	}
	public static void print(String s){
		msg=s+msg;
	}
	
	public static void afficher(Batch b){
		
		//Color ctmp=b.getColor();
		
		//b.setColor(0, 1, 0, 1);
		//b.draw(Textures.WIMG, 0, 200, Gdx.graphics.getWidth(), 5);
		barre.afficher(b);
		font.draw(b,msg,20,180);
		
		//b.setColor(1, 1, 1, 1);

		//b.setColor(ctmp);
		

	}
	
}
