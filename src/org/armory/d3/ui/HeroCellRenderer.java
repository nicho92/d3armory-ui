package org.armory.d3.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Hero;


public class HeroCellRenderer implements ListCellRenderer
{
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel lab = new JLabel();
		Hero hero = (Hero)value;
		lab.setText(hero.getName());
		lab.setBackground(Color.black);
		//lab.setForeground(Color.white);
		if(hero.isHardcore())
			lab.setForeground(Color.red);
		
		lab.setBorder(new LineBorder(Color.gray));
		
		if(cellHasFocus)
		{
			lab.setBackground(Color.gray);
		}
		lab.setIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/"+hero.getClazz()+"-"+hero.getSexe()+".png")));
		return lab;
	}

	
}
