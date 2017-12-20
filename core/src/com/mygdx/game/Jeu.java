package com.mygdx.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.graphique.Bouton;
import com.mygdx.game.graphique.InFenDebug;
import com.mygdx.game.graphique.Label;
import com.mygdx.game.graphique.Textures;
import com.mygdx.game.modele.*;



/**Classe s'occupant du rendu*/

public class Jeu extends ApplicationAdapter {
	GameLoop loop;
	SpriteBatch batch;
	//Bouton btn1;
	Label lb1;
	Label infos;
	Label pad0;

	public static Color cmer=new Color(0.06f, 0.38f, 0.58f, 1f);
	public static Color cterre=new Color(0.8f, 0.7f, 0f, 1f);
	public static Color cbrille=new Color(1f, 1f, 0.58f, 1f);

	
	BitmapFont font;
	public static BitmapFont baseFont;

	boolean isClicking; // Clic gauche
	
	boolean gameRuning;
	
	Partie ptmp;//TODO Temporaire, a changer
	Joueur jEnCour;//TODO Temporaire? peut etre a changer ou a mettre dans partie
	private Partie partie;
	private int victoire;
	
	private boolean aKeyIsPressed=false;
	
	
	
	public boolean modeTir1=false;
	public boolean modeTir2=false;
	public boolean modeMvnt=false;
	public boolean modeSelectNav=false;

	
	public static int minWX=20;
	public static int minWY=220;
	public static int maxWX=1180;
	public static int maxWY=780;
	
	public String entree;
	public int eX;
	public int eY;
	
	public static int[][] casesAccessible;
	public static int deg;
	
	@Override
 	public void create () {
		victoire=0;
		entree="";
		eX=-1;
		eY=-1;
		deg=0;
		
		Textures.chargerTextures();
		
		isClicking=false;
		InFenDebug.init();	
		
		loop=new GameLoop("SeaWarUpdate");

		
		
		batch = new SpriteBatch();
		batch.setColor(1,1,1,1);
		
		
		baseFont=new BitmapFont();
		baseFont.setColor(1,0,0,1);
				
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		

		lb1=new Label(Gdx.graphics.getWidth()-100,180,"fps",font);
		infos=new Label(Gdx.graphics.getWidth()-500,150,"Commandes:\n"
				+ "   F: changement de tour \n"
				+ "   N: fin de tour de navire \n"
				+ "   S: selection d'un navires \n"
				+ "   T: tire 1\n"
				+ "   Y: tire 2\n"
				+ "   M: mouvement d'une case\n"
				+ "   Enter: valider une entr�e clavier",font);


		pad0=new Label(Gdx.graphics.getWidth()-200,150,"Entr�es clavier:\n"
												   +   "    7  8  9 \n"
												   +   "    4  5  6 \n"
												   +   "    1  2  3 \n"
												   +   "       0    \n",font);

		
		
		//btn1=new Bouton(Textures.WIMG, 200, 300, 100, 80, "Test", font);
		//btn1.setColor(0.2f, 0.2f, 0.2f, 1f);
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
		pad0.afficher(batch);
		
		InFenDebug.afficher(batch);
		//Fin des affichage
		batch.end();
		
		//btn1.angle+=1;
		
		
	
		
	}
	
	public void colorMapGestion(){
		Plateau pl=Plateau.getInstance();
		
		
		if(casesAccessible!=null){
			for(int i=0;i<pl.TAILLE_HORIZONTALE;i++){
				for(int j=0;j<pl.TAILLE_VERTICALE;j++){
					boolean tmp=false;
					for(int k=0;k<casesAccessible.length;k++){
						if(i==casesAccessible[k][0] &&  j==casesAccessible[k][1]){
							tmp=true;
						}
						if(tmp){
							pl.getCases(i, j).setColor(cbrille.r, cbrille.g, cbrille.b, 1);
						}else{
							if(pl.getCases(i ,j ) instanceof Terre){
								pl.getCases(i, j).setColor(cterre.r, cterre.g, cterre.b, 1);
							}else{
								pl.getCases(i, j).setColor(cmer.r, cmer.g, cmer.b, 1);
							}
						
							
						}
					}
				}
			}
		}else{
			for(int i=0;i<Plateau.TAILLE_HORIZONTALE;i++){
				for(int j=0;j<Plateau.TAILLE_VERTICALE;j++){
					if(pl.getCases(i ,j ) instanceof Terre){
						pl.getCases(i, j).setColor(cterre.r, cterre.g, cterre.b, 1);
					}else{
						pl.getCases(i, j).setColor(cmer.r, cmer.g, cmer.b, 1);
					}
				}
			}
		}
	}
	
	public void gameLoop(){
		
		
		colorMapGestion();
		
		lb1.setText("fps:"+Integer.toString(Gdx.graphics.getFramesPerSecond()));
		
		if(victoire<=0){
			controles();
		}
		//InFenDebug.println("("+Gdx.graphics.getHeight()+","+Gdx.graphics.getHeight()+")");
		//InFenDebug.println("("+Gdx.input.getX()+","+Gdx.input.getY()+")");

		
		
	}
	
	@Override
	public void resize(int widht,int height){
		//TODO a completer
		//InFenDebug.println("12");
	}
	
	public void controles(){
		//clic droit de la souris
		if(Gdx.input.isButtonPressed(0)){
			if(isClicking==false){

				
				
				//btn1.tryClic(Gdx.input.getX(), Gdx.input.getY());
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
			Partie pte=Partie.getInstance();
			//COMMANDES DE BASE
			if(Gdx.input.isKeyPressed(Input.Keys.F)){
				InFenDebug.println("Fin du tour");
				casesAccessible=null;

				victoire=pte.finirTourGlobal();
				if(victoire==0){
					InFenDebug.println("Nouveau tour");
				}else if(victoire>0){
					InFenDebug.println("Joueur "+victoire+" a gagn�");
				}else{
					InFenDebug.println("Tout les navires doivent se deplacer avant de finir le tour");
				}
				
				aKeyIsPressed=true;
			}
			
			else if(Gdx.input.isKeyPressed(Input.Keys.N)){
				
				boolean possible = partie.finirTourNavire();
				if(possible){
					InFenDebug.println("Fin du tour du navire");
					casesAccessible=null;
				}else{
					InFenDebug.println("Le navire doit se deplacer avant de finir son tour");
				}
				

				
				aKeyIsPressed=true;
	
			}
			
			else if(Gdx.input.isKeyPressed(Input.Keys.T)){
				if(partie.getNavireCourant()!=null){
					if(partie.getNavireCourant().peutTirerPrincipal()){	
						if(partie.getNavireCourant().deplacementsPossibles() == null){
							InFenDebug.println("Deplacement impossible, le navire passe son tour et se retourne");
						}else{
							InFenDebug.println("Tir Principal, entrez la premi�re coordonn�e de la case");
							
							Tir tir = partie.demanderTirsPossiblesPrincipal();
							casesAccessible = tir.cases;
							deg = tir.degats;
							
							modeMvnt=false;
							modeSelectNav=false;
							modeTir1=true;
							modeTir2=false;
						}
					}else{
						InFenDebug.println("Ce navire ne peut pas tirer son canon principal");
					}

				}else{
					InFenDebug.println("S�lectionnez un navire");

				}
				
				
				aKeyIsPressed=true;
	
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.Y)){
				if(partie.getNavireCourant()!=null){
					if(partie.getNavireCourant().peutTirerSecondaire()){
						InFenDebug.println("Tir secondaire, entrez la premi�re coordonn�e de la case");

						Tir tir = partie.demanderTirsPossiblesSecondaire();
						casesAccessible = tir.cases;
						deg = tir.degats;
						
						modeMvnt=false;
						modeSelectNav=false;
						modeTir1=false;
						modeTir2=true;
					}else{
						InFenDebug.println("Ce navire ne peut pas tirer son canon secondaire");

					}

				}else{
					InFenDebug.println("S�lectionnez un navire");

				}
				
				
				
				aKeyIsPressed=true;
	
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S)){
				if(partie.getNavireCourant()==null){
					InFenDebug.println("S�lectionnez un navire: 1=Amiral, 2=Fregate");
					modeSelectNav=true;
					modeTir1=false;
					modeTir2=false;
					modeMvnt=false;

				}else{
					InFenDebug.println("Un autre navire est en cours d'utilisation");

				}
				aKeyIsPressed=true;

	
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.M)){
				if(partie.getNavireCourant()!=null){
					if(partie.getNavireCourant().deplacementsRestants()>0){
						InFenDebug.println("D�placement, entrez la premi�re coordonn�e de la case");
						casesAccessible=partie.demanderDeplacementsPossibles();
						modeMvnt=true;
						modeSelectNav=false;
						modeTir1=false;
						modeTir2=false;
					}else{
						InFenDebug.println("Ce navire ne peut plus ce d�placer");

					}

				}else{
					InFenDebug.println("S�lectionnez un navire");

				}
				
				aKeyIsPressed=true;
	
			}
			
			else if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
				//FIXME finir la getion des entrees
				if(entree!=""){
					if(eX==-1){
						eX=Integer.parseInt(entree);
						entree="";
						InFenDebug.println("entrez la 2e coordonn�e");
					}else{
	
						eY=Integer.parseInt(entree);
						entree="";
						boolean tmp=false;
						int i=0;
						if(casesAccessible!=null){
							for(i=0;i<casesAccessible.length;i++){
								if(eX==casesAccessible[i][0] &&  eY==casesAccessible[i][1]){
									tmp=true;
								}
							}
						}
						
						if(tmp==false){
							InFenDebug.println("case non accessible");
							
						}else{
							if(modeMvnt){
								//TODO ajouter fct 
								modeMvnt=false;
								int[] pos={eX, eY};

								partie.deplacerNavire(pos);

							}else if(modeTir1 || modeTir2){
								int[] pos={eX, eY};
								boolean b=partie.tirerSurUneCase(pos,deg);
								if(b){
									InFenDebug.println("Navire touch�, "+deg+" d�g�t");
								}
								modeTir2=false;
								modeTir1=false;
									
							}
							casesAccessible=null;
						}
						eX=-1;
						eY=-1;
					}
					
	
				}
				aKeyIsPressed=true;

			}
			
			//COMMANDES DE SELECTION DE CASE
			else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
				if(modeSelectNav){
					if(pte.selectionnerNavire(pte.getCurrentPlayer().getNavires()[0])){
						
						InFenDebug.println("Amiral s�lectionn�");
						modeSelectNav=false;
					}else{
						InFenDebug.println("Le navire Amiral a ete coule");
					}
					

				}else{
					entree+="1";
					InFenDebug.println(entree);

				}
				
				
				
				
				aKeyIsPressed=true;
	
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
				if(modeSelectNav){
					if(pte.selectionnerNavire(pte.getCurrentPlayer().getNavires()[1])){
						InFenDebug.println("Fr�gate s�lectionn�");
					}else{
						InFenDebug.println("Ce navire ne peut pas �tre selectionn�");
					}
					modeSelectNav=false;

				}else{
					entree+="2";
					InFenDebug.println(entree);

				}
				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
				
				entree+="3";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)){
				
				entree+="4";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5)){
				
				entree+="5";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)){
				
				entree+="6";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)){
				
				entree+="7";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)){
				
				entree+="8";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)){
				
				entree+="9";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
	
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0)){
				
				entree+="0";
				InFenDebug.println(entree);

				
				aKeyIsPressed=true;
			/*
			}else if(Gdx.input.isKeyPressed(Input.Keys.A)){
				if(partie.sauvegarderPartie() == true){
					InFenDebug.println("Partie sauvegardée");
				}else{
					InFenDebug.println("Echec de la sauvegarde");
				}
				
				aKeyIsPressed=true;
				*/
			}else{
				
			}
		}else{
			if(!(      Gdx.input.isKeyPressed(Input.Keys.F)
					|| Gdx.input.isKeyPressed(Input.Keys.T)
					|| Gdx.input.isKeyPressed(Input.Keys.N)
					|| Gdx.input.isKeyPressed(Input.Keys.Y)
					|| Gdx.input.isKeyPressed(Input.Keys.S)
					|| Gdx.input.isKeyPressed(Input.Keys.M)
					
					|| Gdx.input.isKeyPressed(Input.Keys.ENTER)

					
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)
					|| Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0)


				)){
				aKeyIsPressed=false;
			}
		}

	}

	
	//Une seule partie possible pour le moment
	public void setupGame(){
		//Plateau créé dans Partie
		
		
		/*try {
			FileInputStream fileIn = new FileInputStream("/users/elo/lpicholl/Reptravail/SeaWarSave.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			partie = (Partie) in.readObject();
			in.close();
			fileIn.close();
		}catch (FileNotFoundException f){
			partie = null;
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}

		if(partie != null){
			return;
		}
*/

		
		partie = Partie.getInstance();
		
		int[] pos0 = {0,0};
		int[] pos1 = {1,0};
		int[] pos2 = {Plateau.TAILLE_HORIZONTALE-1,Plateau.TAILLE_VERTICALE-1};
		int[] pos3 = {Plateau.TAILLE_HORIZONTALE-2,Plateau.TAILLE_VERTICALE-1};

		
		Amiral J1Amiral = new Amiral(Textures.AMIRAL,pos0, Orientation.SudEst);
		//pos[0] = 1;
		Fregate J1Fregate = new Fregate(Textures.FREGATE,pos1, Orientation.SudEst);
		//pos[0] = Plateau.TAILLE_HORIZONTALE-1;
		//pos[1] = Plateau.TAILLE_VERTICALE-1;
		Amiral J2Amiral = new Amiral(Textures.AMIRAL,pos2, Orientation.NordOuest);
		//pos[1] = Plateau.TAILLE_VERTICALE-2;
		Fregate J2Fregate = new Fregate(Textures.FREGATE,pos3, Orientation.NordOuest);
		
		Navire[] naviresJ1 = {J1Amiral, J1Fregate};
		Navire[] naviresJ2 = {J2Amiral, J2Fregate};
		
		Joueur j1 = new Joueur("Nimitz", naviresJ1, 1);
		Joueur j2 = new Joueur("Yamamoto", naviresJ2, 2);
		
		j1.setColor(0, 0, 1);
		j2.setColor(1, 0, 0);

		
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