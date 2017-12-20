package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.etats.Partie;
import com.mygdx.game.graphique.InFenDebug;
import com.mygdx.game.graphique.Textures;
import com.mygdx.game.modele.Plateau;

import core.mygdx.game.actor.GraphPlateau;

public class Gui implements ApplicationListener {
	
	private Viewport viewport;
	private Stage stageJeu; 
	
	@Override
	public void create() {
		
		Textures.chargerTextures();

		
		Partie partie = Partie.getInstance();
		viewport = new ScreenViewport();
		stageJeu = new Stage(viewport);
		
		// Ajout de l'UI du plateau
		stageJeu.addActor(new GraphPlateau(Plateau.getInstance()));

		
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
						
		
		stageJeu.act();
		stageJeu.draw();
		
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
