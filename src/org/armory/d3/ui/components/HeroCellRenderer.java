package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Hero;


public class HeroCellRenderer implements ListCellRenderer
{
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel lab = new JLabel();
		Hero hero = (Hero)value;
		lab.setText("<html>"+ hero.getName() + " ("+hero.getLevel() +") <font color='#A591C2'>(" + hero.getParagonLevel()+")</font>");
		lab.setOpaque(true);
		lab.setBackground(Color.black);
		lab.setForeground(Color.white);
		if(hero.isHardcore())
			lab.setForeground(Color.red);
		
		lab.setBorder(new LineBorder(Color.gray));
		
		if(isSelected)
		{
			lab.setBackground(Color.DARK_GRAY);
		}
		Image i =new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/"+hero.getClazz()+"-"+hero.getSexe()+".png")).getImage();
		Image newimg = i.getScaledInstance(i.getWidth(null)/2, i.getHeight(null)/2,  java.awt.Image.SCALE_SMOOTH); 
		lab.setIcon(new ImageIcon(newimg));
		return lab;
	}

	
}
