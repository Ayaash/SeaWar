package core.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TextVictoire extends Label {
	
	public TextVictoire() {
		super("Pas de vainqueur", new Skin(Gdx.files.internal("skin/rusty-robot-ui.json")));
	}
	public void update(int vaq){
		if(vaq>0){
			this.setText("Victoire de Joueur "+vaq);
		}else{
			this.setText("Pas de vainqueur");

		}
	}
	
}
