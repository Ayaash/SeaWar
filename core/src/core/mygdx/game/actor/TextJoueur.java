package core.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.modele.Joueur;

public class TextJoueur extends Label {
	public TextJoueur(Joueur joueur) {
		super(joueur.getNom() + ", a toi !", new Skin(Gdx.files.internal("skin/rusty-robot-ui.json")));
	}
	
	public void setJoueur(Joueur joueur) {
		if(joueur == null) {
			this.setText("");
		} else {
			this.setText(joueur.getNom() + ", a toi !");
		}
	}
}
