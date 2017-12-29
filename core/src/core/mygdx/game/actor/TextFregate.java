package core.mygdx.game.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.modele.Fregate;

public class TextFregate extends TextNavire {
	Label m_titre;
	Label m_vieLabel;
	Label m_canonPrin;
	Label m_canonSec;

	public TextFregate(Fregate _fregate) {
		super(_fregate);
		String canonPrin, canonSec;
		
		
		m_titre = new Label("Fregate", m_skin);
		this.addActor(m_titre);
		m_titre.setFontScale(1.1F);
		
		m_vieLabel = new Label("\tVie : " + m_navire.getVie() + "/" + m_navire.getPV_MAX(), m_skin);
		this.addActor(m_vieLabel);
		
		if(m_navire.peutTirerPrincipal()) {
			canonPrin = "pret !";
		} else {
			canonPrin = "rechargement (" + m_navire.rechargePrincipal() + "tour)";
		}
		m_canonPrin = new Label("\tCanon principal : " + canonPrin, m_skin);
		this.addActor(m_canonPrin);
		
		if(m_navire.peutTirerSecondaire()) {
			canonSec = "pret !";
		} else {
			canonSec = "rechargement (" + m_navire.rechargeSecondaire() + "tour)";
		}
		m_canonSec = new Label("\tCanon secondaire : " + canonSec, m_skin);
		this.addActor(m_canonSec);
	}

	@Override
	public void update() {
		String canonPrin, canonSec;
		m_vieLabel.setText("\tVie : " + m_navire.getVie() + "/" + m_navire.getPV_MAX());
		
		if(m_navire.peutTirerPrincipal()) {
			canonPrin = "pret !";
		} else {
			canonPrin = "rechargement (" + m_navire.rechargePrincipal() + "tour)";
		}
		m_canonPrin.setText("\tCanon principal : " + canonPrin);
		
		if(m_navire.peutTirerSecondaire()) {
			canonSec = "pret !";
		} else {
			canonSec = "rechargement (" + m_navire.rechargeSecondaire() + "tour)";
		}
		m_canonSec.setText("\tCanon secondaire : " + canonSec);
	}

	public void setNavire(Fregate _navire) {
		m_navire = _navire;
	}

}
