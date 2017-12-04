package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * 
 * Affiche un simple text a l'ecran
 * 
 * */


public class Label extends Affichable{

	
	

	String text;
	BitmapFont font;
	
	private static final long serialVersionUID = 1L;


	public Label(int px, int py,String text,BitmapFont font) {
		super(null, px, py);
		
		this.text=text;
		this.font=font;
	}
	

	
	public void setText(String txt){
		this.text=txt;
	}
	
	@Override
	public boolean afficher(Batch b){
		try {
			font.draw(b,text, posX, posY);
			return true;
		} catch (Exception e) {
			return false;

		}
	}
}
