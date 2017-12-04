package com.mygdx.game;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * 
 * Classe des objets affichable a l'ecran
 * 
 * */

public class Affichable implements Serializable{

	
	private static final long serialVersionUID = 1L;
	//Parametres d'image 
	protected Texture img;
	protected int posX;
	protected int posY;
	protected int lX;
	protected int lY;
	protected int angle;
	
	protected Sprite spr;
	protected Color clr;

	
	public Affichable(Texture img,int x,int y){
		this.img=img;
		this.posX=x;
		this.posY=y;
		this.lX=10;
		this.lY=10;
		this.angle=0;
		init();

	}
	
	public Affichable(Texture img,int x,int y,int angle){
		this.img=img;
		this.posX=x;
		this.posY=y;
		this.lX=10;
		this.lY=10;
		this.angle=angle;
		init();

		
		
	}
	
	public Affichable(Texture img,int x,int y,int lX,int lY,int angle){
		this.img=img;
		this.posX=x;
		this.posY=y;
		this.lX=lX;
		this.lY=lY;
		this.angle=angle;
		init();


		
	}
	
	private void init(){
		initColor();
		if(img!=null){
			createSprite();
		}

	}
	
	private void createSprite(){
		spr=new Sprite(img);
		spr.rotate(angle);
		spr.setOrigin(lX/2,lY/2);
		spr.setBounds(posX, posY, lX, lY);
		spr.setColor(clr);
	}
	
	private void actualizeSprite(){
		spr.setRotation(angle);
		spr.setOrigin(lX/2,lY/2);
		spr.setBounds(posX, posY, lX, lY);
		spr.setColor(clr);
	}
	
	/**Permet d'avoir une couleur*/
	private void initColor(){
		clr=new Color(1,1,1,1);
	}
	
	/**Permet de specifier la couleur*/
	public void setColor(float R, float V, float B, float A){
		clr.set(R, V, B, A);
		actualizeSprite();
	}
	
	/**Renvoie la couleur de l'objet*/
	public Color getColor(){
		return clr;
	}
	
	/**Affiche l'objet a l'ecran*/
	boolean afficher(Batch b){
		try {
			actualizeSprite();
			spr.draw(b);
			return true;
		} catch (Exception e) {
			return false;
		}	
		
	}

	
	
	
}
