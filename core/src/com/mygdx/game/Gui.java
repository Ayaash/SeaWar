package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.modele.Partie;
import com.mygdx.game.graphique.Textures;
import com.mygdx.game.modele.Plateau;
import core.mygdx.game.actor.GraphPlateau;
import core.mygdx.game.actor.Hud;

public class Gui implements ApplicationListener {
	private Hud m_hud;
	private Viewport m_viewport;
	private Stage m_stageJeu; 
	private Partie m_partie;
	
	//520x614
	public static int minWX=333;//0+200;
	public static int minWY=100;//0+200;
	public static int maxWX=1280-333;//1280-200;
	public static int maxWY=620;//720-200;
	
	@Override
	public void create() {
		Textures.chargerTextures();
		
		m_partie = Partie.getInstance();
		m_viewport = new ScreenViewport();
		m_stageJeu = new Stage(m_viewport);
		Gdx.input.setInputProcessor(m_stageJeu);
		
		// Ajout de l'UI du plateau
		m_stageJeu.addActor(new GraphPlateau(Plateau.getInstance()));
		
		// Ajout du HUD
		m_hud = new Hud(m_partie);
		m_stageJeu.addActor(m_hud);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		//batch.begin();
						
		
		m_stageJeu.act();
		m_stageJeu.draw();
		
		//Fin des affichage
		//batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
