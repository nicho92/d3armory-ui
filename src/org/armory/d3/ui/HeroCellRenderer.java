package org.armory.d3.ui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.armory.d3.beans.Hero;


public class HeroCellRenderer implements ListCellRenderer
{
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel lab = new JLabel();
		Hero hero = (Hero)value;
		lab.setText(hero.getName());
		System.out.println("/org/armory/d3/ui/resources/"+hero.getClazz()+"-"+hero.getSexe()+".png");
		lab.setIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/"+hero.getClazz()+"-"+hero.getSexe()+".png")));
		return lab;
	}

	
}
