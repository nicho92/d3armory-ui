package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.armory.d3.beans.Hero;

public class FollowersPanel extends JPanel {

	public FollowersPanel(Hero hero) {
		setBackground(new Color(35,33,29));
	}


	protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/followers.jpg")).getImage();
			g.drawImage(bg,0,0,null);	
			
	
	}
	
	
	

}
