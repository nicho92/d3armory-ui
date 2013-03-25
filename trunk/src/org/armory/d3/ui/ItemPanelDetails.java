package org.armory.d3.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ItemPanelDetails extends JPanel {

	 public void paint( Graphics g )
	  {
		 super.paint( g );
		 try {
			 Graphics2D g2d = ( Graphics2D )g;
			 Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/itemBackground.png")).getImage();
			 g2d.drawImage(i, 0, 0, null);
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
		 
	  }
	
}
