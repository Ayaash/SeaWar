package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.etats.Partie;
import com.mygdx.game.graphique.Textures;
import com.mygdx.game.modele.Plateau;

import core.mygdx.game.actor.GraphPlateau;

public class Gui implements ApplicationListener {
	
	private Viewport viewport;
	private Stage stageJeu; 
	
	@Override
	public void create() {
		
		Textures.chargerTextures();
		Skin skin = new Skin(Gdx.files.internal("skin/rusty-robot-ui.json"));
		
		Partie partie = Partie.getInstance();
		viewport = new ScreenViewport();
		stageJeu = new Stage(viewport);
		
		// Ajout de l'UI du plateau
		//stageJeu.addActor(new GraphPlateau(Plateau.getInstance()));
		
		// Ajout du HUD
		Group hud = new Group();
		
		Image barreHorizImg = new Image(new Texture(Gdx.files.internal("images/barre_horiz.jpg")));
		barreHorizImg.setSize(1280, 80);
		barreHorizImg.setPosition(0, 640);
		hud.addActor(barreHorizImg);
		
		HorizontalGroup barreHoriz = new HorizontalGroup();
		barreHoriz.setSize(1280, 80);
		barreHoriz.setPosition(0,640);
		hud.addActor(barreHoriz);
		
		TextButton deplacerBouton = new TextButton("Déplacer",skin); //TODO add listener
		barreHoriz.addActor(deplacerBouton);
		

		TextButton tirPrincipal = new TextButton("Tir principal", skin); //TODO add listener
		barreHoriz.addActor(tirPrincipal);
		
		TextButton tirSecondaire = new TextButton("Tir secondaire", skin); //TODO add listener
		barreHoriz.addActor(tirSecondaire);
		
		TextButton finTour = new TextButton("Fin du tour", skin); //TODO add listener
		barreHoriz.addActor(finTour);
		
		Image pannelInfoImg = new Image(new Texture(Gdx.files.internal("images/barre_horiz.jpg")));
		pannelInfoImg.setSize(300, 550);
		pannelInfoImg.setPosition(950, 40);
		hud.addActor(pannelInfoImg);
		
		VerticalGroup pannelInfo = new VerticalGroup();
		pannelInfo.setSize(300,550);
		pannelInfo.setPosition(950,40);
		hud.addActor(pannelInfo);
		
		
		stageJeu.addActor(hud);
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
