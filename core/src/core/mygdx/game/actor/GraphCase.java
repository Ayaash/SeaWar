package core.mygdx.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.graphique.Textures;
import com.mygdx.game.modele.Case;

public abstract class GraphCase extends ImageButton { // TODO passer en ImageButton
	private Case m_case;
	protected boolean selected;
	

	public GraphCase(Case c) {
		super(new Drawbt(c.getPosition()[0],c.getPosition()[1],c));
		this.m_case = c;
		//this.get
	}
	
	/*public void select(){
	}*/
	

	//@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	public class evt0 implements EventListener{

		@Override
		public boolean handle(Event event) {
			// TODO Auto-generated method stub
			//InFenDebug.println("hello");
			return true;
		}
	
	}


	public static class Drawbt implements Drawable{
	
		private int wx;
		private int wy;
		private int x;
		private int y;
		private int h;
		private int w;
		private Case c;
		private boolean selected;//TODO a ajouter aussi dans case
		
		public Drawbt(int _x,int _y,Case c){
			wx=_x;
			wy=_y;
			x=wx;
			y=wy;
			h=1;
			w=1;
			selected=false;
			
		}
		
		@Override
		public void draw(Batch batch, float x, float y, float width,
				float height) {
			
			Color ctmp=batch.getColor();
			if(selected){
				batch.setColor(1, 0, 0, 1);
			}
			batch.setColor(1, 0, 0, 1);
			batch.draw(Textures.HEXAGON, x, y, w, h);
			batch.setColor(ctmp);
			
		}
	
		@Override
		public float getLeftWidth() {
			// TODO Auto-generated method stub
			return x-w/2;
		}
	
		@Override
		public void setLeftWidth(float leftWidth) {
			// TODO Auto-generated method stub
			
			
		}
	
		@Override
		public float getRightWidth() {
			// TODO Auto-generated method stub
			return x+w/2;
		}
	
		@Override
		public void setRightWidth(float rightWidth) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public float getTopHeight() {
			// TODO Auto-generated method stub
			return  y-h/2;
		}
	
		@Override
		public void setTopHeight(float topHeight) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public float getBottomHeight() {
			// TODO Auto-generated method stub
			return y+h/2;
		}
	
		@Override
		public void setBottomHeight(float bottomHeight) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public float getMinWidth() {
			// TODO Auto-generated method stub
			return 0;
		}
	
		@Override
		public void setMinWidth(float minWidth) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public float getMinHeight() {
			// TODO Auto-generated method stub
			return 0;
		}
	
		@Override
		public void setMinHeight(float minHeight) {
			// TODO Auto-generated method stub
			
		}
	}

}




	