package org.armory.d3.ui.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.factory.LootFactory;

public class LootFactoryPanel extends JPanel {
	
	public LootFactoryPanel() {
		setLayout(new GridLayout(10, 5));
	}
	
	
	public void init(final ItemPanelDetails itemPanelDetails)
	{
		final   LootFactory fact = new LootFactory(D3ArmoryControler.getInstance().getSelectedHero(false));
        
		this.removeAll();
		this.validate();
		
		for(String s : fact.getItemsType())
        {
     	    JButton b = new JButton(s);
            
             b.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					 Item i = fact.generateItem(((JButton)e.getSource()).getText());	
	            	 itemPanelDetails.showItem(i);
	            	 ItemLabel lab = new ItemLabel();
				        lab.setItem(i, null);
				        itemPanelDetails.getLblIcon().setIcon(lab.getIcon());
				        itemPanelDetails.getLblIcon().repaint();
					
				}
			});
           
            add(b);
        }
		
	}

	
}


