package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Hero;


public class FollowersPanel extends JPanel {
	
	private ItemLabel lblTemplarObject;
	private ItemLabel lblTemplarNeck;

	public FollowersPanel() {
		setBackground(new Color(35,33,29));
		this.setPreferredSize(new java.awt.Dimension(845, 439));
		this.setLayout(null);
		this.add(getLblTemplarObject());
		this.add(getLblTemplarNeck());
	}
	

    protected void paintComponent(Graphics g) {
    		super.paintComponents(g);
			Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/followers.jpg")).getImage();
			g.drawImage(bg,0,0,null);	
	}
    
    public ItemLabel getLblTemplarObject() {
    	
    	if(lblTemplarObject ==null)
    	{
    		lblTemplarObject = new ItemLabel();
    	   	lblTemplarObject.setBounds(161, 113, 42, 39);
    	}
    	return lblTemplarObject;
    }
    
    public ItemLabel getLblTemplarNeck() {
    	if(lblTemplarNeck == null) {
    		lblTemplarNeck = new ItemLabel();
    		lblTemplarNeck.setBounds(209, 113, 42, 39);
    	}
    	return lblTemplarNeck;
    }

}
