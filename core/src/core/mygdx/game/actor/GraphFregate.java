package core.mygdx.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Gui;
import com.mygdx.game.modele.Case;
import com.mygdx.game.modele.Fregate;
import com.mygdx.game.modele.Navire;
import com.mygdx.game.modele.Plateau;
import com.mygdx.game.modele.Textures;

public class GraphFregate extends GraphNavire {

	
	public GraphFregate(Fregate _fregate) {
		this(_fregate,new Drawbt(_fregate.getPosition()[0],_fregate.getPosition()[1],_fregate));
	}
	

	public GraphFregate(Navire _fregate, Drawable dr) {
		super(_fregate,dr);
		

	}

	
	private static class Drawbt implements Drawable{
		
		private int wx;
		private int wy;
		private int m_x;
		private int m_y;
		private int m_h;
		private int m_w;
		private Navire n;
		private GraphNavire gn;

		
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

			Case c= n.getPlateau().getCases(n.getPosition()[0], n.getPosition()[1]); 
			
			wx=n.getPosition()[0];
			wy=n.getPosition()[1];
			
			if(gn==null){//TODO A priori inutile
				gn=GraphPlateau.getMainInstance().getGraphNavire(c.getPosition()[0], c.getPosition()[1]);
			}
			
			
			double sx=(Gui.maxWX-Gui.minWX+0f)/(Plateau.TAILLE_HORIZONTALE+0f);
			double sy=(Gui.maxWY-Gui.minWY+0f)/(Plateau.TAILLE_VERTICALE+0f);
			
			
			m_w=(int) (sx/0.8);//Pour emboiter les hexagones
			m_h=(int) (sy/1);
			
			this.m_x=(int) (Gui.minWX  +  wx*sx );
			//this.m_y=(int) (Gui.minWY  +  wy*sy );
			this.m_y=(int) (Gui.maxWY  -  wy*sy);


			if( wx%2==0){
				m_y-=sy/2f;
			}
		}
		


		
		@Override
		public void draw(Batch batch, float x, float y, float width,
				float height) {
			
			if(n.getVie()>0){

				actualizePosSize();
	
				
				Color ctmp=batch.getColor();
				
				setNavireColor(batch, n);
				
				int angle=-n.getOrientation().ordinal()*60+180;
				
				TextureRegion tr= new TextureRegion(Textures.FREGATE);
				batch.draw(tr, m_x, m_y, m_w/2, m_h/2, m_w, m_h, 1, 1, angle, true);
				batch.setColor(ctmp);
			}
		}
		
		
	
		@Override
		public float getLeftWidth() {
			// TODO Auto-generated method stub
			return m_x;
		}
	
		@Override
		public void setLeftWidth(float leftWidth) {
			// TODO Auto-generated method stub
			m_x=(int) leftWidth;
			
		}
	
		@Override
		public float getRightWidth() {
			// TODO Auto-generated method stub
			return m_x+m_w;
		}
	
		@Override
		public void setRightWidth(float rightWidth) {
			// TODO Auto-generated method stub
			m_w=(int) rightWidth-m_x;

			
		}
	
		@Override
		public float getTopHeight() {
			// TODO Auto-generated method stub
			return  m_y;
		}
	
		@Override
		public void setTopHeight(float topHeight) {
			// TODO Auto-generated method stub
			m_h=(int) topHeight-m_y;
			
		}
	
		@Override
		public float getBottomHeight() {
			// TODO Auto-generated method stub
			return m_y+m_h/2;
		}
	
		@Override
		public void setBottomHeight(float bottomHeight) {
			// TODO Auto-generated method stub
			m_y=(int) bottomHeight;

		}
	
		@Override
		public float getMinWidth() {
			// TODO Auto-generated method stub
			return m_w;
		}
	
		@Override
		public void setMinWidth(float minWidth) {
			// TODO Auto-generated method stub
			m_w=(int) minWidth;
			
		}
	
		@Override
		public float getMinHeight() {
			// TODO Auto-generated method stub
			return m_h;
		}
	
		@Override
		public void setMinHeight(float minHeight) {
			// TODO Auto-generated method stub
			m_h=(int) minHeight;

		}
	}

	

}
