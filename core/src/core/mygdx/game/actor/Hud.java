package core.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mygdx.game.modele.Partie;
import com.mygdx.game.modele.Textures;
import com.mygdx.game.modele.Amiral;
import com.mygdx.game.modele.Fregate;

public class Hud extends Group {
	private Partie m_partie;
	private Skin m_skin;
	
	public Hud(Partie _partie) {
		m_skin = new Skin(Gdx.files.internal("skin/rusty-robot-ui.json"));
		m_partie = _partie;
		
		Image barreHorizImg = new Image(Textures.BARRE_HORIZ);
		barreHorizImg.setSize(1260, 80);
		barreHorizImg.setPosition(10, 630);
		this.addActor(barreHorizImg);
		
		HorizontalGroup barreHoriz = new HorizontalGroup();
		barreHoriz.setSize(1260, 80);
		barreHoriz.setPosition(10,630);
		this.addActor(barreHoriz);
		
		TextButton deplacerBouton = new TextButton("Deplacer",m_skin);
		EvtMove deplacerListener = new EvtMove();
		deplacerBouton.addListener(deplacerListener);
		barreHoriz.addActor(deplacerBouton);

		TextButton tirPrincipal = new TextButton("Tir principal", m_skin);
		EvtTirPrincipal tirPrincipalListener = new EvtTirPrincipal();
		tirPrincipal.addListener(tirPrincipalListener);
		barreHoriz.addActor(tirPrincipal);
		
		TextButton tirSecondaire = new TextButton("Tir secondaire", m_skin);
		EvtTirSecondaire tirSecondaireListener = new EvtTirSecondaire();
		tirSecondaire.addListener(tirSecondaireListener);
		barreHoriz.addActor(tirSecondaire);

		TextButton finTourNavire = new TextButton("Fin du tour du navire", m_skin);
		EvtFinTourNavire finTourNavireListener = new EvtFinTourNavire();
		finTourNavire.addListener(finTourNavireListener);
		barreHoriz.addActor(finTourNavire);
		
		TextButton finTour = new TextButton("Fin du tour", m_skin);
		EvtFinTour finTourListener = new EvtFinTour();
		finTour.addListener(finTourListener) ;
		barreHoriz.addActor(finTour);
		
		Image pannelInfoImg = new Image(Textures.BARRE_HORIZ);
		pannelInfoImg.setSize(300, 550);
		pannelInfoImg.setPosition(950, 40);
		this.addActor(pannelInfoImg);
		
		Group pannelInfo = new Group();
		pannelInfo.setSize(300,550);
		pannelInfo.setPosition(950,40);
		this.addActor(pannelInfo);
		
		VerticalGroup pannelJ1 = new VerticalGroup();
		pannelJ1.setPosition(150, 500);
		
		Label textJ1 = new Label("Joueur 1 :", m_skin);
		textJ1.setFontScale(1.2F);
		pannelJ1.addActor(textJ1);
		
		TextFregate fregateJ1 = new TextFregate((Fregate) m_partie.getPlayer(1).getNavires()[1]);
		pannelJ1.addActor(fregateJ1);
		
		TextAmiral amiralJ1 = new TextAmiral((Amiral) m_partie.getPlayer(1).getNavires()[0]);
		pannelJ1.addActor(amiralJ1);
		
		pannelInfo.addActor(pannelJ1);
		
		VerticalGroup pannelJ2 = new VerticalGroup();
		pannelJ2.setPosition(150, 200);
		
		Label textJ2 = new Label("Joueur 2 :", m_skin);
		textJ2.setFontScale(1.2F);
		pannelJ2.addActor(textJ2);
		
		TextFregate fregateJ2 = new TextFregate((Fregate) m_partie.getPlayer(2).getNavires()[1]);
		pannelJ2.addActor(fregateJ2);
		
		TextAmiral amiralJ2 = new TextAmiral((Amiral) m_partie.getPlayer(2).getNavires()[0]);
		pannelJ2.addActor(amiralJ2);
		
		pannelInfo.addActor(pannelJ2);
		
		VerticalGroup pannelVic = new VerticalGroup();
		pannelVic.setPosition(150, 320);
		pannelInfo.addActor(pannelVic);
		
		TextVictoire textVictoire = new TextVictoire();
		textVictoire.setFontScale(1.4F);
		pannelVic.addActor(textVictoire);
		//TODO textJoueur
		
		finTourListener.setVictoireText(textVictoire);
		finTourListener.setNaviresText(fregateJ1, amiralJ1, fregateJ2, amiralJ2);
		finTourNavireListener.setNaviresText(fregateJ1, amiralJ1, fregateJ2, amiralJ2);
		tirPrincipalListener.setNaviresText(fregateJ1, amiralJ1, fregateJ2, amiralJ2);
		tirSecondaireListener.setNaviresText(fregateJ1, amiralJ1, fregateJ2, amiralJ2);

	}
	
	public class EvtMove extends InputListener{
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(!(GraphPlateau.vainqueur>0)){
				GraphPlateau.getMainInstance().askMove();
				return true;
			}
			return false;
	 	}
	}
	
	public class EvtTirPrincipal extends InputListener{
		TextNavire tn1;
		TextNavire tn2;
		TextNavire tn3;
		TextNavire tn4;

		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			tn1.update();
			tn2.update();
			tn3.update();
			tn4.update();
			if(!(GraphPlateau.vainqueur>0)){
				GraphPlateau.getMainInstance().askTirPrincipal();
				return true;
			}
			return false;
	 	}
		
		public void setNaviresText(TextNavire t1,TextNavire t2,TextNavire t3,TextNavire t4){
			tn1=t1;
			tn2=t2;
			tn3=t3;
			tn4=t4;
		}
	}
	
	public class EvtTirSecondaire extends InputListener{
		TextNavire tn1;
		TextNavire tn2;
		TextNavire tn3;
		TextNavire tn4;

		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			tn1.update();
			tn2.update();
			tn3.update();
			tn4.update();
			if(!(GraphPlateau.vainqueur>0)){
				GraphPlateau.getMainInstance().askTirSecondaire();
				return true;
			}
			return false;
		 }
		
		public void setNaviresText(TextNavire t1,TextNavire t2,TextNavire t3,TextNavire t4){
			tn1=t1;
			tn2=t2;
			tn3=t3;
			tn4=t4;
		}
	}
	
	public class EvtFinTour extends InputListener{
		private TextVictoire victoireText;
		
		private TextNavire m_textNavire1;
		private TextNavire m_textNavire2;
		private TextNavire m_textNavire3;
		private TextNavire m_textNavire4;


		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			
			//if(!(GraphPlateau.vainqueur>0)){
				int vq=GraphPlateau.getMainInstance().finTour();
				//if(vq>0){
					if(victoireText!=null){
						victoireText.update(vq);
					}
					m_textNavire1.update();
					m_textNavire2.update();
					m_textNavire3.update();
					m_textNavire4.update();

				//}
				//textVictoire.update();
			//}
			
			return true;
		 }
		
		public void setVictoireText(TextVictoire textVictoire){
			victoireText=textVictoire;
		}
		public void setNaviresText(TextNavire t1,TextNavire t2,TextNavire t3,TextNavire t4){
			m_textNavire1=t1;
			m_textNavire2=t2;
			m_textNavire3=t3;
			m_textNavire4=t4;
		}
	}
	
	public class EvtFinTourNavire extends InputListener{
		TextNavire tn1;
		TextNavire tn2;
		TextNavire tn3;
		TextNavire tn4;

		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			
			tn1.update();
			tn2.update();
			tn3.update();
			tn4.update();
			
			if(!(GraphPlateau.vainqueur>0)){
				GraphPlateau.getMainInstance().abandonTourNavire();
				return true;
			}
			return false;
	 	}
		
		public void setNaviresText(TextNavire t1,TextNavire t2,TextNavire t3,TextNavire t4){
			tn1=t1;
			tn2=t2;
			tn3=t3;
			tn4=t4;
		}
	}

	
}
