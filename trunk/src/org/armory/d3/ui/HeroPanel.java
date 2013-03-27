package org.armory.d3.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.armory.d3.beans.Hero;
import org.armory.d3.services.D3ArmoryControler;

import com.sdfteam.d3armory.service.remote.exception.D3ServerCommunicationException;

public class HeroPanel extends JPanel {

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Hero hero=null;
		try {
			hero = D3ArmoryControler.getInstance().getSelectedHero(false);
		} catch (D3ServerCommunicationException e1) {
			e1.printStackTrace();
		}
		if(hero!=null){
				String classe = hero.getClazz();
				String sexe = hero.getSexe();
				imagePath="http://eu.battle.net/d3/static/images/profile/hero/paperdoll/"+classe+"-"+sexe+".jpg";
				Image image = null;
		
			 	try 
			 	{
			 	    URL url = new URL(imagePath);
			 	    image = ImageIO.read(url);
			 	    g.drawImage(image, 0, 0, null);
			 	  
			 	} 
			 	catch (IOException e) 
			 	{
			 	     System.err.println(e);
			 	}
		}
		else
		{
			Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/default.jpg")).getImage();
			g.drawImage(bg,0,0,null);
		}
		
		  g.drawImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/inventory-lines.png")).getImage(),462,167,null);
	}

	private String imagePath;
	

	
	
}
