package core.mygdx.game.actor;


import java.util.Observer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Gui;
import com.mygdx.game.graphique.Textures;
import com.mygdx.game.modele.Case;
import com.mygdx.game.modele.Plateau;

public abstract class GraphCase extends ImageButton implements Observer { 
	private Case m_case;
	protected boolean selected=false;
	protected boolean clicked=false;

	
	protected float m_x;
	protected float m_y;
	protected float m_w;
	protected float m_h;
	public static final Color BASECOLOR=new Color(1f, 0f, 0f, 1f);
	public static final Color CLICCOLOR=new Color(0.50f, 1f, 0.50f, 1f);
	public static final Color SELECTEDCOLOR=new Color(1f, 1f, 0.50f, 1f);

	
	
	
	public GraphCase(Case c) {
		this(c,new Drawbt(c.getPosition()[0],c.getPosition()[1],c));
	}
	

	public GraphCase(Case c, Drawable dr) {
		super(dr);
		
		this.m_case = c;

		actualizePosSize();
		this.setBounds(m_x, m_y, m_w, m_h);
		this.addListener(new evt0());
	}


	protected void actualizePosSize(){
		//posX=(int) position[0]*10;
		//posY=(int) position[1]*10;
		double sx=(Gui.maxWX-Gui.minWX+0f)/((float)Plateau.TAILLE_HORIZONTALE);
		double sy=(Gui.maxWY-Gui.minWY+0f)/((float)Plateau.TAILLE_VERTICALE);
		
		
		m_w=(int) (sx/0.8);//Pour emboiter les hexagones
		m_h=(int) (sy/1);
		
		this.m_x=(int) (Gui.minWX  +  m_case.getPosition()[0]*sx );
		//this.m_y=(int) ( Gui.maxWY+Gui.minWY-1.5*m_h-(Gui.minWY  + wy*sy ) );
		this.m_y=(int) (Gui.minWY  +  m_case.getPosition()[1]*sy);
		
		
		
		//posX=(int) Jeu.minWX + (Jeu.maxWX-Jeu.minWX)*position[0];
		//posY=(int) Jeu.minWY + (Jeu.maxWX-Jeu.minWX)*position[1];

		if( m_case.getPosition()[0]%2==0){//TODO Peut etre revoir la parit�
			m_y+=sy/2f;
			//System.out.println(1);
		}else{
			//System.out.println(2);

		}
	
		
		
		//this.actualizeSprite(obj);
	}
	
	public void select(){
		//this.setColor(SELECTEDCOLOR);
		((GraphPlateau)this.getParent()).deselectAll();
		selected=true;

	}
	public void deselect(){
		//this.setColor(BASECOLOR);
		selected=false;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void clickOn(){
		//this.setColor(CLICCOLOR);
		clicked=true;
	}
	public void clickOff(){
		//this.setColor(BASECOLOR);
		clicked=false;
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public void actionOnClick(){
		//TODO A remplir
	}

	public class evt0 extends InputListener{

		/*@Override
		public boolean handle(Event event) {
			// TODO Auto-generated method stub
			//InFenDebug.println("hello");
			System.out.println(1);
			return true;
		}*/
		
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {//TODO
			clickOn();
			select();
	 		return true;
	 	}
	 
	 	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	 		//handle(event);
			clickOff();
	 	
	 	}
	 	

	
	}


	public Case getCase(){
		return m_case;
	}
	
	private static class Drawbt implements Drawable{
	
		private int wx;
		private int wy;
		private int m_x;
		private int m_y;
		private int m_h;
		private int m_w;
		private Case c;
		private GraphCase gc;
		public Drawbt(int _x,int _y,Case _c){
			wx=_x;
			wy=_y;
			m_x=wx*100;
			m_y=wy*100;
			m_h=100;
			m_w=100;
			c=_c;

			
		}
		
		protected void actualizePosSize(){
			//posX=(int) position[0]*10;
			//posY=(int) position[1]*10;
			
			if(gc==null){
				gc=GraphPlateau.getMainInstance().getGraphCase(c.getPosition()[0], c.getPosition()[1]);
			}
			
			
			double sx=(Gui.maxWX-Gui.minWX+0f)/(Plateau.TAILLE_HORIZONTALE+0f);
			double sy=(Gui.maxWY-Gui.minWY+0f)/(Plateau.TAILLE_VERTICALE+0f);
			
			
			m_w=(int) (sx/0.8);//Pour emboiter les hexagones
			m_h=(int) (sy/1);
			
			this.m_x=(int) (Gui.minWX  +  wx*sx );
			//this.m_y=(int) ( Gui.maxWY+Gui.minWY-1.5*m_h-(Gui.minWY  + wy*sy ) );
			this.m_y=(int) (Gui.minWY  +  wy*sy);
			
			
			
			//posX=(int) Jeu.minWX + (Jeu.maxWX-Jeu.minWX)*position[0];
			//posY=(int) Jeu.minWY + (Jeu.maxWX-Jeu.minWX)*position[1];

			if( wx%2==0){//TODO Peut etre revoir la parit�
				m_y+=sy/2f;
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
			if(gc.isClicked()){
				batch.setColor(CLICCOLOR);
			}else{
				if(gc.isSelected()){
					batch.setColor(SELECTEDCOLOR);
				}else{
					batch.setColor(BASECOLOR);
	
				}
			}

			//System.out.println(45);

			
			/*m_x=wx*100;
			m_y=wy*100;*/
			//System.out.println(m_x+","+m_y+","+m_w+","+m_h);

			//System.out.println(x+","+y+","+width+","+height);
			//batch.draw(Textures.HEXAGON, x, y, width, height);
			batch.draw(Textures.HEXAGON, m_x, m_y, m_w, m_h);
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




	