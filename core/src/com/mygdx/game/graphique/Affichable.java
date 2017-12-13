package com.mygdx.game.graphique;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
	protected int angle=0;
	
	protected Image obj;
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
	
	protected void init(){
		initColor();
		if(img!=null){
			obj=createSprite(img);
		}

	}
	
	protected Image createSprite(Texture im){
		Image ob=new Image(im);//(img);
		ob.setRotation(angle);
		//spr.setOrigin(0,0);
		ob.setBounds(posX, posY, lX, lY);
		ob.setOrigin(lX/2,lY/2);
		ob.setColor(clr);
		
		return ob;
	}
	
	protected void actualizeSprite(Image ob){
		ob.setRotation(angle);
		ob.setOrigin(lX/2,lY/2);
		ob.setBounds(posX, posY, lX, lY);
		ob.setColor(clr);
	}
	
	/**Permet d'avoir une couleur*/
	protected void initColor(){
		clr=new Color(1,1,1,1);
	}
	
	/**Permet de specifier la couleur*/
	public void setColor(float R, float V, float B, float A){
		clr.set(R, V, B, A);
		actualizeSprite(obj);
	}
	
	/**Renvoie la couleur de l'objet*/
	public Color getColor(){
		return clr;
	}
	
	/**Affiche l'objet a l'ecran*/
	boolean afficher(Batch b){
		try {
			//actualizeSprite();//TODO A gerer ailleurs
			obj.draw(b,1);
			return true;
		} catch (Exception e) {
			return false;
		}	
		
	}

	
	
	
}
