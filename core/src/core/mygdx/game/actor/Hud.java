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
	private TextAmiral m_amiralJ1, m_amiralJ2;	
	private TextFregate m_fregateJ1, m_fregateJ2;
	
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
		
		TextButton deplacerBouton = new TextButton("Deplacer",m_skin); //TODO add listener
		deplacerBouton.addListener(new evtMove());
		barreHoriz.addActor(deplacerBouton);

		TextButton tirPrincipal = new TextButton("Tir principal", m_skin); //TODO add listener
		tirPrincipal.addListener(new evtTirPrincipal());
		barreHoriz.addActor(tirPrincipal);
		
		TextButton tirSecondaire = new TextButton("Tir secondaire", m_skin); //TODO add listener
		tirSecondaire.addListener(new evtTirSecondaire());
		barreHoriz.addActor(tirSecondaire);

		TextButton finTourNavire = new TextButton("Fin du tour du navire", m_skin); //TODO add listener
		finTourNavire.addListener(new evtFinTourNavire());
		barreHoriz.addActor(finTourNavire);
		
		TextButton finTour = new TextButton("Fin du tour", m_skin); //TODO add listener
		finTour.addListener(new evtFinTour());
		barreHoriz.addActor(finTour);
		
		Image pannelInfoImg = new Image(Textures.BARRE_HORIZ);
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
		
		m_fregateJ1 = new TextFregate((Fregate) m_partie.getPlayer(1).getNavires()[1]); //FIXME bateaux à null
		pannelJ1.addActor(m_fregateJ1);
		
		m_amiralJ1 = new TextAmiral((Amiral) m_partie.getPlayer(1).getNavires()[0]);//FIXME bateaux à null
		pannelJ1.addActor(m_amiralJ1);
		
		VerticalGroup pannelJ2 = new VerticalGroup();
		pannelInfo.addActor(pannelJ2);
		
		Label textJ2 = new Label("Joueur 2 :", m_skin);
		textJ2.setFontScale(1.2F);
		pannelJ1.addActor(textJ2);
		
		m_fregateJ2 = new TextFregate((Fregate) m_partie.getPlayer(2).getNavires()[1]); //FIXME bateaux à null
		pannelJ2.addActor(m_fregateJ2);
		
		m_amiralJ2 = new TextAmiral((Amiral) m_partie.getPlayer(2).getNavires()[0]);//FIXME bateaux à null
		pannelJ2.addActor(m_amiralJ2);
		
		
		VerticalGroup pannelVic = new VerticalGroup();
		pannelInfo.addActor(pannelVic);
		
		VictoireText textVictoire = new VictoireText("Pas encore de vainqueur", m_skin);
		textVictoire.setFontScale(1.4F);
		pannelVic.addActor(textVictoire);
		
		((evtFinTour)(finTour.getListeners().get(1))).setVictoireText(textVictoire);
	
		((evtFinTour)(finTour.getListeners().get(1))).setNaviresText(m_fregateJ1,m_amiralJ1,m_fregateJ2,m_amiralJ2);
		((evtFinTourNavire)(finTourNavire.getListeners().get(1))).setNaviresText(m_fregateJ1,m_amiralJ1,m_fregateJ2,m_amiralJ2);
		((evtTirPrincipal)(tirPrincipal.getListeners().get(1))).setNaviresText(m_fregateJ1,m_amiralJ1,m_fregateJ2,m_amiralJ2);
		((evtTirSecondaire)(tirSecondaire.getListeners().get(1))).setNaviresText(m_fregateJ1,m_amiralJ1,m_fregateJ2,m_amiralJ2);

		
	}
	
	public class evtMove extends InputListener{
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {//TODO
			if(!(GraphPlateau.vainqueur>0)){
				GraphPlateau.getMainInstance().askMove();
				return true;
			}
			return false;
	 	}
	}
	
	public class evtTirPrincipal extends InputListener{
		TextNavire tn1;
		TextNavire tn2;
		TextNavire tn3;
		TextNavire tn4;

		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {//TODO
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
	
	public class evtTirSecondaire extends InputListener{
		TextNavire tn1;
		TextNavire tn2;
		TextNavire tn3;
		TextNavire tn4;

		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {//TODO
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
	
	public class evtFinTour extends InputListener{
		VictoireText vt;
		
		TextNavire tn1;
		TextNavire tn2;
		TextNavire tn3;
		TextNavire tn4;


		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {//TODO
			
			//if(!(GraphPlateau.vainqueur>0)){
				int vq=GraphPlateau.getMainInstance().finTour();
				//if(vq>0){
					if(vt!=null){
						vt.update(vq);
					}
					tn1.update();
					tn2.update();
					tn3.update();
					tn4.update();

				//}
				//textVictoire.update();
			//}
			
			return true;
		 }
		
		public void setVictoireText(VictoireText v){
			vt=v;
		}
		public void setNaviresText(TextNavire t1,TextNavire t2,TextNavire t3,TextNavire t4){
			tn1=t1;
			tn2=t2;
			tn3=t3;
			tn4=t4;
		}
	}
	
	public class evtFinTourNavire extends InputListener{
		TextNavire tn1;
		TextNavire tn2;
		TextNavire tn3;
		TextNavire tn4;

		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {//TODO
			
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

	public class VictoireText extends Label{
		public VictoireText(CharSequence text, Skin skin) {
			super(text, skin);
		}
		public void update(int vaq){
			if(vaq>0){
				this.setText("Victoire du Joueur "+vaq);
			}else{
				this.setText("Pas encore de vainqueur");

			}
		}
		
	}
	
}
