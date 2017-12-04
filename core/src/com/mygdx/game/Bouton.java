package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**Classe des boutons*/
public class Bouton extends Affichable{
	
	String text;
	BitmapFont font;
	
	private static final long serialVersionUID = 1L;


	public Bouton(Texture img, int px, int py,int lx,int ly,String text,BitmapFont font) {
		super(img, px, py,0);
		this.lX=lx;
		this.lY=ly;
		
		this.text=text;
		this.font=font;
	}
	
	@Override
	public boolean afficher(Batch b){
		
		
		super.afficher(b);
		try {
			font.draw(b,text, (posX+lX/2), (posY+lY/2));
			return true;
		} catch (Exception e) {
			return false;

		}
	}
	
	
}
