package core.mygdx.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Gui;
import com.mygdx.game.modele.Case;
import com.mygdx.game.modele.Navire;
import com.mygdx.game.modele.Plateau;
import com.mygdx.game.modele.Textures;

public abstract class GraphNavire extends Image { // TODO passer en Image
	private Navire m_navire;
	
	protected float m_x;
	protected float m_y;
	protected float m_w;
	protected float m_h;
	
	protected boolean selected=false;
	protected boolean clicked=false;
	
	
	public GraphNavire(Navire _navire) {
		this(_navire,new Drawbt(_navire.getPosition()[0],_navire.getPosition()[1],_navire));
	}
	

	public GraphNavire(Navire _navire, Drawable dr) {
		super(dr);
		
		this.m_navire = _navire;

		actualizePosSize();
		this.setBounds(m_x, m_y, m_w, m_h);
		this.toFront();
	}


	protected void actualizePosSize(){
		//posX=(int) position[0]*10;
		//posY=(int) position[1]*10;
		double sx=(Gui.maxWX-Gui.minWX+0f)/(Plateau.TAILLE_HORIZONTALE+0f);
		double sy=(Gui.maxWY-Gui.minWY+0f)/(Plateau.TAILLE_VERTICALE+0f);
		
		
		Case c=m_navire.getPlateau().getCases(m_navire.getPosition()[0], m_navire.getPosition()[1]);
		
		m_w=(int) (sx/0.8);//Pour emboiter les hexagones
		m_h=(int) (sy/1);
		
		this.m_x=(int) (Gui.minWX  +  c.getPosition()[0]*sx );
		//this.m_y=(int) ( Gui.maxWY+Gui.minWY-1.5*m_h-(Gui.minWY  + wy*sy ) );
		this.m_y=(int) (Gui.minWY  +  c.getPosition()[1]*sy);
		
		
		
		//posX=(int) Jeu.minWX + (Jeu.maxWX-Jeu.minWX)*position[0];
		//posY=(int) Jeu.minWY + (Jeu.maxWX-Jeu.minWX)*position[1];

		if( c.getPosition()[0]%2==0){//TODO Peut etre revoir la paritÃ¯Â¿Â½
			m_y+=sy/2f;
			//System.out.println(1);
		}else{
			//System.out.println(2);

		}
	
		
		
		//this.actualizeSprite(obj);
	}
	
	/**envoie true ssi la selection a bien eu lieu*/
	public boolean select(){
		//this.setColor(SELECTEDCOLOR);
		boolean btmp;
		//((GraphPlateau)this.getParent().getParent()).deselectAllNavire();//un seul navire selectionnée a la fois
		btmp=((GraphPlateau)this.getParent()).selectNavire(m_navire);//un seul navire selectionnée a la fois
		if(btmp){
			selected=true;
		return true;
		}
		return false;
	}
	public void deselect(){
		//this.setColor(BASECOLOR);
		selected=false;
	}


	protected static void setNavireColor(Batch batch,Navire n){
		if(n.getJoueur().getId()==1){
			batch.setColor(GraphPlateau.J1COLOR);
		}else{
			batch.setColor(GraphPlateau.J2COLOR);
		}
	}
	
	public Navire getNavire(){
		return m_navire;
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
			
			if(gn==null){
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
			
			actualizePosSize();

			
			Color ctmp=batch.getColor();
			
			setNavireColor(batch, n);
			
			int angle=n.getOrientation().ordinal()*60+270;
			
			TextureRegion tr= new TextureRegion(Textures.NOIMAGE);
			batch.draw(tr, m_x, m_y, m_w/2, m_h/2, m_w, m_h, 1, 1, angle, true);
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