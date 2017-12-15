package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.modele.Plateau;

import core.mygdx.game.actor.GraphPlateau;

public class Gui implements ApplicationListener {
	
	private Viewport viewport;
	private Stage stageJeu; 
	
	@Override
	public void create() {
		viewport = new ScreenViewport();
		stageJeu = new Stage(viewport);
		stageJeu.addActor(new GraphPlateau(Plateau.getInstance()));

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

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
