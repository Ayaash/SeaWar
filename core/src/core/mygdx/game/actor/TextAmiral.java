package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.modele.Amiral;

public class TextAmiral extends TextNavire {
	Label m_titre;
	Label m_vieLabel;
	Label m_canonPrin;
	Label m_canonSec;

	public TextAmiral(Amiral _navire) {
		super(_navire);
		String canonPrin, canonSec;
		
		
		m_titre = new Label("Amiral :", m_skin);
		this.addActor(m_titre);
		m_titre.setFontScale(1.1F);
		
		m_vieLabel = new Label("Vie : " + m_navire.getVie() + "/" + m_navire.getPV_MAX(), m_skin);
		this.addActor(m_vieLabel);
		m_vieLabel.setFontScale(0.8F);

		if(m_navire.peutTirerPrincipal()) {
			canonPrin = "pret !";
		} else {
			canonPrin = "rechargement (" + m_navire.rechargePrincipal() + "tour)";
		}
		m_canonPrin = new Label("Canon principal : " + canonPrin, m_skin);
		this.addActor(m_canonPrin);
		m_canonPrin.setFontScale(0.8F);

		if(m_navire.peutTirerSecondaire()) {
			canonSec = "pret !";
		} else {
			canonSec = "rechargement (" + m_navire.rechargeSecondaire() + "tour)";
		}
		m_canonSec = new Label("Canon secondaire : " + canonSec, m_skin);
		this.addActor(m_canonSec);
		m_canonSec.setFontScale(0.8F);

	}

	@Override
	public void update() {
		String canonPrin, canonSec;
		m_vieLabel.setText("Vie : " + m_navire.getVie() + "/" + m_navire.getPV_MAX());
		
		if(m_navire.peutTirerPrincipal()) {
			canonPrin = "pret !";
		} else {
			canonPrin = "rechargement (" + m_navire.rechargePrincipal() + "tour)";
		}
		m_canonPrin.setText("Canon principal : " + canonPrin);
		
		if(m_navire.peutTirerSecondaire()) {
			canonSec = "pret !";
		} else {
			canonSec = "rechargement (" + m_navire.rechargeSecondaire() + "tour)";
		}
		m_canonSec.setText("Canon secondaire : " + canonSec);
	}
	
	public void setNavire(Amiral _navire) {
		m_navire = _navire;
	}

}