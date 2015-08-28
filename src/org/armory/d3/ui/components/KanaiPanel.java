package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class KanaiPanel extends JPanel {

	private ItemLabel armor;
	private ItemLabel weapon;
	private ItemLabel jewel;
	
	
	
	
	public KanaiPanel() {
		this.setBackground(new Color(35,33,29));	
		setLayout(null);
		add(getWeapon());
		add(getArmor());
		add(getJewel());
	}
	
	
	protected void paintComponent(Graphics g) {
		Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/kanai.png")).getImage();
		g.drawImage(bg,0,0,null);
		super.paintComponents(g);
	}
	
	public ItemLabel getArmor() {
    	if(armor == null) {
    		armor = new ItemLabel(ItemLabel.SIZE_LARGE);
    		armor.setBounds(157, 25, 93, 161);
    		armor.setTransparent(true);
    	}
    	return armor;
    }
	
	public ItemLabel getWeapon() {
    	if(weapon == null) {
    		weapon = new ItemLabel(ItemLabel.SIZE_LARGE);
    		weapon.setBounds(22, 25, 93, 161);
    		weapon.setTransparent(true);
    	}
    	return weapon;
    }
	
	public ItemLabel getJewel() {
    	if(jewel == null) {
    		jewel = new ItemLabel(ItemLabel.SIZE_LARGE);
    		jewel.setBounds(296, 25, 93, 161);
    		jewel.setTransparent(true);
    	}
    	return jewel;
    }
	
	
}
