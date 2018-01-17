package core.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.modele.Joueur;

public class TextVictoire extends Label {
	
	public TextVictoire() {
		super("Pas de vainqueur", new Skin(Gdx.files.internal("skin/rusty-robot-ui.json")));
	}
	public void update(int vaq, Joueur joueur){
		if(vaq>0){
			this.setText("Victoire de " + joueur.getNom());
		}else{
			this.setText("Pas de vainqueur");

		}
	}
	
}
