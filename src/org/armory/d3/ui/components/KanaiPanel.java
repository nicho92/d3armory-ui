package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class KanaiPanel extends JPanel {

	
	public KanaiPanel() {
		this.setBackground(new Color(35,33,29));	
	}
	
	
	protected void paintComponent(Graphics g) {
		Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/kanai.png")).getImage();
		g.drawImage(bg,0,0,null);
		super.paintComponents(g);
}
}
