package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;


public class HeroCellRenderer implements ListCellRenderer<Hero>
{
	
	public Component getListCellRendererComponent(JList list, final Hero hero, int index, boolean isSelected, boolean cellHasFocus) {

		
		JLabel lab = new JLabel() {
			@Override
			public void  paint(Graphics g) {
				super.paint(g);
				if(hero.isDead())
				{
					Image dead =new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/dead.png")).getImage();
					g.drawImage(dead, getWidth()-60, 5, null);
				}
				if(hero.getSeasonal())
				{
					Image season=new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/season.png")).getImage();
					g.drawImage(season, getWidth()-25, 15, null);
					
				}
				
			}
		};
		
		String levels = hero.getLevel() +" ";
		
		if(hero.getSeasonal()==false)
		{		if(hero.getParagonLevel()>0)
					levels=levels+ "<font color='#A591C2'>(" + hero.getParagonLevel()+")</font>";
		}
		else
		{
			if(hero.getParagonLevel()>0)
				levels=levels+ "<font color='#A591C2'>(" + hero.getParagonLevel()+")</font>";
			
		}
		
		String lastPlayed ="";
		
		if(D3ArmoryControler.getInstance().getLastHeroPlayed().getId().intValue()==(hero.getId().intValue()))
			lastPlayed="   *";
		
		
		lab.setText("<html>"+ hero.getName() + " " + levels + lastPlayed);
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
		try{ 
			Image i =new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/"+hero.getClazz()+"-"+hero.getSexe()+".png")).getImage();
			Image newimg = i.getScaledInstance(i.getWidth(null)/2, i.getHeight(null)/2,  java.awt.Image.SCALE_SMOOTH); 
			lab.setIcon(new ImageIcon(newimg));
		}catch(Exception e)
		{
			
		}
		
		
		
		return lab;
	}

	
	
	
}
