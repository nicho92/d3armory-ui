package org.armory.d3.ui.components;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;




/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class HeroPanel extends JPanel {

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Hero hero=null;
		
			hero = D3ArmoryControler.getInstance().getSelectedHero(false);
		
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
		
		// g.drawImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/inventory-lines.png")).getImage(),462,167,null);
	}
	
		
	public HeroPanel()
	{
		this.setLayout(null);
		this.setPreferredSize(new java.awt.Dimension(1055, 556));
	}
	
	
	private String imagePath;

	
}
