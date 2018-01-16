package core.mygdx.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Gui;
import com.mygdx.game.modele.Case;
import com.mygdx.game.modele.Phare;
import com.mygdx.game.modele.Plateau;
import com.mygdx.game.modele.Textures;

public class GraphPhare extends GraphMer {

	public static final Color BASECOLOR=new Color(0.06f, 0.38f, 0.58f, 1f);


	
	public GraphPhare(Phare p) {
		this(p,new Drawbt(p.getPosition()[0],p.getPosition()[1],p));
		
	}
	
	public GraphPhare(Case t,Drawable dr){
		super(t,dr);
	}
	
	protected static void setPhareColor(Batch batch, Phare p){
		if(p.getJoueur()==null){
			batch.setColor(Color.WHITE);
		}else{
			if(p.getJoueur().getId()==1){
				batch.setColor(GraphPlateau.J1COLOR);
			}else{
				batch.setColor(GraphPlateau.J2COLOR);
			}
		}
	}


	private static class Drawbt implements Drawable{
	
		private int wx;
		private int wy;
		private int m_x;
		private int m_y;
		private int m_h;
		private int m_w;
		private Phare c;
		private GraphCase gc;
		
		public Drawbt(int _x,int _y,Phare _c){
			wx=_x;
			wy=_y;
			m_x=wx*100;
			m_y=wy*100;
			m_h=100;
			m_w=100;
			c=_c;

			
		}
		
		protected void actualizePosSize(){
			
			if(gc==null){
				gc=GraphPlateau.getMainInstance().getGraphCase(c.getPosition()[0], c.getPosition()[1]);
			}
			
			
			//posX=(int) position[0]*10;
			//posY=(int) position[1]*10;
			double sx=(Gui.maxWX-Gui.minWX+0f)/(Plateau.TAILLE_HORIZONTALE+0f);
			double sy=(Gui.maxWY-Gui.minWY+0f)/(Plateau.TAILLE_VERTICALE+0f);
			
			
			m_w=(int) (sx/0.78);//Pour emboiter les hexagones
			m_h=(int) (sy/1.05);
			
			this.m_x=(int) (Gui.minWX  +  wx*sx );
			//this.m_y=(int) (Gui.minWY  +  wy*sy );
			this.m_y=(int) (Gui.maxWY  -  wy*sy);
			
			
			
			//posX=(int) Jeu.minWX + (Jeu.maxWX-Jeu.minWX)*position[0];
			//posY=(int) Jeu.minWY + (Jeu.maxWX-Jeu.minWX)*position[1];

			if( wx%2==0){//TODO Peut etre revoir la paritï¿½
				m_y-=sy/2f;
				//System.out.println(1);
			}else{
				//System.out.println(2);

			}
		
			
			
			//this.actualizeSprite(obj);
		}
		

		
		@Override
		public void draw(Batch batch, float x, float y, float width,
				float height) {
			
			actualizePosSize();

			
			Color ctmp=batch.getColor();
			setCaseColor(batch,gc);

			

			batch.draw(Textures.HEXAGON, m_x, m_y, m_w, m_h);
			
			setPhareColor(batch,c);
			batch.draw(Textures.PHARE, m_x, m_y, m_w, m_h);
			batch.setColor(ctmp);
			
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