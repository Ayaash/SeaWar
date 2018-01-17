package core.mygdx.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Gui;
import com.mygdx.game.modele.Amiral;
import com.mygdx.game.modele.Navire;
import com.mygdx.game.modele.Plateau;
import com.mygdx.game.modele.Textures;


public class GraphAmiral extends GraphNavire {

	public GraphAmiral(Amiral _amiral) {
		this(_amiral,new Drawbt(_amiral.getPosition()[0],_amiral.getPosition()[1],_amiral));
	}
	

	public GraphAmiral(Navire _amiral, Drawable dr) {
		super(_amiral,dr);
		

	}

	

	private static class Drawbt implements Drawable{
	
		private int wx;
		private int wy;
		private int m_x;
		private int m_y;
		private int m_h;
		private int m_w;
		private Navire n;

		
		public Drawbt(int _x,int _y,Navire _n){
			wx=_x;
			wy=_y;
			m_x=wx*100;
			m_y=wy*100;
			m_h=100;
			m_w=100;
			n=_n;

			
		}
		
		protected void actualizePosSize(){


			wx=n.getPosition()[0];
			wy=n.getPosition()[1];
			
			double sx=(Gui.maxWX-Gui.minWX+0f)/(Plateau.TAILLE_HORIZONTALE+0f);
			double sy=(Gui.maxWY-Gui.minWY+0f)/(Plateau.TAILLE_VERTICALE+0f);
			
			
			m_w=(int) (sx/0.78);//Pour emboiter les hexagones
			m_h=(int) (sy/1.05);
			
			this.m_x=(int) (Gui.minWX  +  wx*sx );
			this.m_y=(int) (Gui.maxWY  -  wy*sy);


			if( wx%2==0){
				m_y-=sy/2f;
			}
		}
		


		
		@Override
		public void draw(Batch batch, float x, float y, float width,
				float height) {
			
			if(!n.estMort()){

				actualizePosSize();
		
				
				Color ctmp=batch.getColor();
				
				setNavireColor(batch, n);
				
				int angle=-n.getOrientation().ordinal()*60+180;
				
				TextureRegion tr= new TextureRegion(Textures.AMIRAL);
				batch.draw(tr, m_x, m_y, m_w/2, m_h/2, m_w, m_h, 1, 1, angle, true);
				batch.setColor(ctmp);
			}
			
		}
		
		
	
		@Override
		public float getLeftWidth() {
			return m_x;
		}
	
		@Override
		public void setLeftWidth(float leftWidth) {
			m_x=(int) leftWidth;
			
		}
	
		@Override
		public float getRightWidth() {
			return m_x+m_w;
		}
	
		@Override
		public void setRightWidth(float rightWidth) {
			m_w=(int) rightWidth-m_x;

			
		}
	
		@Override
		public float getTopHeight() {
			return  m_y;
		}
	
		@Override
		public void setTopHeight(float topHeight) {
			m_h=(int) topHeight-m_y;
			
		}
	
		@Override
		public float getBottomHeight() {
			return m_y+m_h/2;
		}
	
		@Override
		public void setBottomHeight(float bottomHeight) {
			m_y=(int) bottomHeight;

		}
	
		@Override
		public float getMinWidth() {
			return m_w;
		}
	
		@Override
		public void setMinWidth(float minWidth) {
			m_w=(int) minWidth;
			
		}
	
		@Override
		public float getMinHeight() {
			return m_h;
		}
	
		@Override
		public void setMinHeight(float minHeight) {
			m_h=(int) minHeight;

		}
	}

	

}
