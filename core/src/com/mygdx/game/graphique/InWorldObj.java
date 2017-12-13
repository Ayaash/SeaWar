package com.mygdx.game.graphique;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Jeu;
import com.mygdx.game.modele.Plateau;

/**
 * Classe des objets affichable a l'ecran
 * 
 * */

public class InWorldObj extends Affichable{

	private static final long serialVersionUID = 1L;

	//protected int posWX;
	//protected int posWY;
	protected static int lW=5;
	
	protected int[] position;

	
	public InWorldObj() {//TODO temporaire , a regler le porbleme dans navire
		super(Textures.NOIMAGE, 0, 0);
		
		position=new int[2];
		position[0]=0;
		position[1]=0;
	}
	
	public InWorldObj(int posWX, int posWY) {
		super(Textures.NOIMAGE, posWX*lW, posWY*lW);
		
		position=new int[2];
		position[0]=posWX;
		position[1]=posWY;

		
		
	}
	
	public InWorldObj(Texture img, int posWX, int posWY) {
		super(img, posWX*lW, posWY*lW);
		
		position=new int[2];
		position[0]=posWX;
		position[1]=posWY;
	}
	
	public InWorldObj(Texture img, int posWX, int posWY, int angle) {
		super(img, posWX*lW, posWY*lW,angle);
		
		position=new int[2];
		position[0]=posWX;
		position[1]=posWY;
	}

	
	
	protected void actualizePosSize(){
		//posX=(int) position[0]*10;
		//posY=(int) position[1]*10;
		
		double sx=(Jeu.maxWX-Jeu.minWX+0f)/(Plateau.getInstance().TAILLE_HORIZONTALE+0f);
		double sy=(Jeu.maxWY-Jeu.minWY+0f)/(Plateau.getInstance().TAILLE_VERTICALE+0f);
		
		
		lX=(int) (sx/0.8);
		lY=(int) (sy/1);
		
		posX=(int) (Jeu.minWX  + position[0]*sx );
		posY=(int) ( Jeu.maxWY+Jeu.minWY-1.5*lY-(Jeu.minWY  + position[1]*sy ) );

		
		
		
		//posX=(int) Jeu.minWX + (Jeu.maxWX-Jeu.minWX)*position[0];
		//posY=(int) Jeu.minWY + (Jeu.maxWX-Jeu.minWX)*position[1];

		if(position[0]%2==1){//TODO Peut etre revoir la parit�
			posY+=sy/2;
		}
		
		

		
		
		//this.actualizeSprite(obj);
	}
	
	@Override
	public boolean afficher(Batch b){
		actualizePosSize();
		this.actualizeSprite(obj);
		return super.afficher(b);
		
	}
	


	

	
	

	
	
	
}
