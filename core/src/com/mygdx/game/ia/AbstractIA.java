package com.mygdx.game.ia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.mygdx.game.modele.Partie;

public abstract class AbstractIA {
	
	protected Partie partie;
	
	public AbstractIA(Partie p){
		this.partie = p;
	}

	public abstract Coup getCoup();
	
	//TODO: faire une copie profonde autrement qu'en serialisant parce que la c'est vorace en ressources et en temps
	protected Partie copiePartie(){
		String currentDirectoty = GetExecutionPath();
		Partie copie = null;
		try{
			FileOutputStream fileOut =
					new FileOutputStream(currentDirectoty + "/SeaWarTmp.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(partie);
			out.close();
			fileOut.close();
		}catch (FileNotFoundException f){
			
		} catch (IOException i) {
			i.printStackTrace();
		}
		
		try {
			FileInputStream fileIn = new FileInputStream(currentDirectoty + "/SeaWarTmp.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			copie = (Partie) in.readObject();
			in.close();
			fileIn.close();
		}catch (FileNotFoundException f){
			partie = null;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return null;
		}
		
		
		return copie;
	}

	private String GetExecutionPath(){
	    String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
	    absolutePath = absolutePath.replaceAll("%20"," "); // Surely need to do this here
	    return absolutePath;
	}
}
