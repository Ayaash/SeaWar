package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.etats.Partie;
import com.mygdx.game.graphique.Bouton;
import com.mygdx.game.graphique.InFenDebug;
import com.mygdx.game.graphique.Label;
import com.mygdx.game.graphique.Textures;
import com.mygdx.game.modele.*;



/**Classe s'occupant du rendu*/

public class Jeu extends ApplicationAdapter {
	GameLoop loop;
	SpriteBatch batch;
	Bouton btn1;
	Label lb1;
	Label infos;

	BitmapFont font;
	boolean isClicking; // Clic gauche
	
	boolean gameRuning;
	
	Partie ptmp;//TODO Temporaire, a changer
	Joueur jEnCour;//TODO Temporaire? peut etre a changer ou a mettre dans partie
	private Partie partie;
	private int victoire;
	
	private boolean aKeyIsPressed=false;
	
	
	public static int minWX=20;
	public static int minWY=220;
	public static int maxWX=1180;
	public static int maxWY=780;

	
	@Override
 	public void create () {
		
		
		Textures.chargerTextures();
		
		isClicking=false;
		InFenDebug.init();	
		
		loop=new GameLoop("SeaWarUpdate");

		
		
		batch = new SpriteBatch();
		batch.setColor(1,1,1,1);
		
		
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		

		lb1=new Label(20,Gdx.graphics.getHeight()-20,"fps",font);
		infos=new Label(500,Gdx.graphics.getHeight()-20,"Commandes:\n"
				+ "   F: changement de tour \n"
				+ "   S: selection bateau \n"
				+ "   T: tire \n"
				+ "   M: mouvement d'une case",font);

		
		btn1=new Bouton(Textures.WIMG, 200, 300, 100, 80, "Test", font);
		btn1.setColor(0.2f, 0.2f, 0.2f, 1f);
		//btn1.setColor(1, 0, 0, 1);
		
		setupGame();
		
		loop.start();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//Gdx.gl.glBlendColor(0, 0, 0, 1);
		
		//debut des affichages
		batch.begin();
				
		//batch.draw(img, 0, 0);
		/*for(int i=0;i<1;i++){//TODO A gerer
			//btn1.afficher(batch);
		}*/
		
		Plateau pl=Plateau.getInstance();
		for(int i=0;i<pl.TAILLE_HORIZONTALE;i++){
			for(int j=0;j<pl.TAILLE_VERTICALE;j++){
				pl.getCases(i, j).afficher(batch);
			}
		}
		

		lb1.afficher(batch);
		infos.afficher(batch);
		
		InFenDebug.afficher(batch);
		//Fin des affichage
		batch.end();
		
		//btn1.angle+=1;
		
		
	
		
	}
	
	public void gameLoop(){
		lb1.setText("fps:"+Integer.toString(Gdx.graphics.getFramesPerSecond()));
		controles();
		
		//InFenDebug.println("("+Gdx.graphics.getHeight()+","+Gdx.graphics.getHeight()+")");
		//InFenDebug.println("("+Gdx.input.getX()+","+Gdx.input.getY()+")");

		
		
	}
	
	@Override
	public void resize(int widht,int height){
		//TODO a completer
		InFenDebug.println("12");
	}
	
	public void controles(){
		//clic droit de la souris
		if(Gdx.input.isButtonPressed(0)){
			if(isClicking==false){

				
				
				btn1.tryClic(Gdx.input.getX(), Gdx.input.getY());
				isClicking=true;
			}
		}else{
			if(isClicking==true){
				isClicking=false;
			}
		}
		
		
		//TODO clic droit nettoi la console, a supprimer dans le jeu final
		if(Gdx.input.isButtonPressed(1)){
			InFenDebug.nettoyer();
		}
		
		
		if(aKeyIsPressed==false){
			if(Gdx.input.isKeyPressed(Input.Keys.F)){
				//TODO ajouter la fonction de gestion de changement de tour dans partie
				InFenDebug.println("Fin de tour");
				aKeyIsPressed=true;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.T)){
				//TODO ajouter la fonction de gestion de tirs
				InFenDebug.println("Tire");
				aKeyIsPressed=true;
	
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S)){
				//TODO ajouter la fonction de gestion de selection de navire
				InFenDebug.println("Selection navire");
				aKeyIsPressed=true;
	
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.M)){
				//TODO ajouter la fonction de gestion de mouvement d'une case
				InFenDebug.println("Mouvement");
				aKeyIsPressed=true;
	
			}else{
				
			}
		}else{
			if(!(      Gdx.input.isKeyPressed(Input.Keys.F)
					|| Gdx.input.isKeyPressed(Input.Keys.T)
					|| Gdx.input.isKeyPressed(Input.Keys.S)
					|| Gdx.input.isKeyPressed(Input.Keys.M)
				)){
				aKeyIsPressed=false;
			}
		}

	}

	public void gKey(){
		
	}
	
	//Une seule partie possible pour le moment
	public void setupGame(){
		//Plateau créé dans Partie
		partie = Partie.getInstance();
		
		int[] pos = {0,0};
		Amiral J1Amiral = new Amiral(pos, Orientation.SudEst);
		pos[0] = 1;
		Amiral J1Fregate = new Amiral(pos, Orientation.SudEst);
		pos[0] = Plateau.TAILLE_HORIZONTALE-1;
		pos[1] = Plateau.TAILLE_VERTICALE-1;
		Amiral J2Amiral = new Amiral(pos, Orientation.NordOuest);
		pos[1] = Plateau.TAILLE_VERTICALE-2;
		Amiral J2Fregate = new Amiral(pos, Orientation.NordOuest);
		
		Navire[] naviresJ1 = {J1Amiral, J1Fregate};
		Navire[] naviresJ2 = {J2Amiral, J2Fregate};
		
		Joueur j1 = new Joueur("Nimitz", naviresJ1);
		Joueur j2 = new Joueur("Yamamoto", naviresJ2);
		
		partie.ajouterJoueurs(j1, j2);
		
		victoire = 0;
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
		//TODO crere une fonction pour disposer tt les textures
		gameRuning=false;
	}


	public class GameLoop extends Thread{
		
		long t0;
		long t1;
		
		public GameLoop(String name){
			super(name);
		}
			 
		public void run(){
			t0=System.currentTimeMillis();
			gameRuning=true;
			while(gameRuning==true){
				t1=System.currentTimeMillis();
				if(t1-t0>100){
					
					gameLoop();
					t0=100-(t0+t1);
				}else{
					//t0=System.currentTimeMillis();
				}
			}
			
			
		}
			
	}

}