package core.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mygdx.game.modele.Partie;
import com.mygdx.game.modele.Amiral;
import com.mygdx.game.modele.Fregate;

public class Hud extends Group {
	private Partie m_partie;
	private Skin m_skin;
	private TextAmiral m_amiralJ1, m_amiralJ2;
	private TextFregate m_fregateJ1, m_fregateJ2;
	
	public Hud(Partie _partie) {
		m_skin = new Skin(Gdx.files.internal("skin/rusty-robot-ui.json"));
		m_partie = _partie;
		
		Image barreHorizImg = new Image(new Texture(Gdx.files.internal("images/barre_horiz.jpg")));
		barreHorizImg.setSize(1280, 80);
		barreHorizImg.setPosition(0, 640);
		this.addActor(barreHorizImg);
		
		HorizontalGroup barreHoriz = new HorizontalGroup();
		barreHoriz.setSize(1280, 80);
		barreHoriz.setPosition(0,640);
		this.addActor(barreHoriz);
		
		TextButton deplacerBouton = new TextButton("Deplacer",m_skin); //TODO add listener
		barreHoriz.addActor(deplacerBouton);
		

		TextButton tirPrincipal = new TextButton("Tir principal", m_skin); //TODO add listener
		barreHoriz.addActor(tirPrincipal);
		
		TextButton tirSecondaire = new TextButton("Tir secondaire", m_skin); //TODO add listener
		barreHoriz.addActor(tirSecondaire);
		
		TextButton finTour = new TextButton("Fin du tour", m_skin); //TODO add listener
		barreHoriz.addActor(finTour);
		
		Image pannelInfoImg = new Image(new Texture(Gdx.files.internal("images/barre_horiz.jpg")));
		pannelInfoImg.setSize(300, 550);
		pannelInfoImg.setPosition(950, 40);
		this.addActor(pannelInfoImg);
		
		VerticalGroup pannelInfo = new VerticalGroup();
		pannelInfo.setSize(300,550);
		pannelInfo.setPosition(950,40);
		this.addActor(pannelInfo);
		
		VerticalGroup pannelJ1 = new VerticalGroup();
		pannelInfo.addActor(pannelJ1);
		
		Label textJ1 = new Label("Joueur 1 :", m_skin);
		textJ1.setFontScale(1.2F);
		pannelJ1.addActor(textJ1);
		/*
		m_fregateJ1 = new TextFregate((Fregate) m_partie.getPlayer(1).getNavires()[1]); //FIXME bateaux à null
		pannelJ1.addActor(m_fregateJ1);
		
		m_amiralJ1 = new TextAmiral((Amiral) m_partie.getPlayer(1).getNavires()[0]);//FIXME bateaux à null
		pannelJ1.addActor(m_amiralJ1);
		*/
		VerticalGroup pannelJ2 = new VerticalGroup();
		pannelInfo.addActor(pannelJ2);
		
		Label textJ2 = new Label("Joueur 2 :", m_skin);
		textJ2.setFontScale(1.2F);
		pannelJ1.addActor(textJ2);
		/*
		m_fregateJ2 = new TextFregate((Fregate) m_partie.getPlayer(2).getNavires()[1]); //FIXME bateaux à null
		pannelJ2.addActor(m_fregateJ2);
		
		m_amiralJ2 = new TextAmiral((Amiral) m_partie.getPlayer(2).getNavires()[0]);//FIXME bateaux à null
		pannelJ2.addActor(m_amiralJ2);
		*/
	}

}