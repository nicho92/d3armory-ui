package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.ItemCreatorFrame;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.LootFactory;

public class LootFactoryPanel extends JPanel {
	 Item lootedItem ;
	 
	public LootFactoryPanel() {
		///setLayout(new GridLayout(10, 5));
		setMinimumSize(new Dimension(0,0));
		setBackground(Color.black);
		setLayout(new GridLayout(6, 8, 0, 0));
	}
	
	
	public void init(final ItemPanelDetails itemPanelDetails)
	{
		final   LootFactory fact = new LootFactory(D3ArmoryControler.getInstance().getSelectedHero(false));
        
		this.removeAll();
		this.validate();
		
		for(String s : fact.getItemsType())
        {
     	   JButton b = new JButton(s);
     	   ImageIcon i=null;
     	   String uri="";
     	   
     	    switch (s){
     	    	case "Amulet" : uri="http://media.blizzard.com/d3/icons/items/small/unique_amulet_006_x1_demonhunter_male.png";break;
     	    	case "Ring" : uri="http://media.blizzard.com/d3/icons/items/small/unique_ring_019_x1_demonhunter_male.png";break;
     	    	case "2H" : uri="http://media.blizzard.com/d3/icons/items/small/unique_mace_2h_103_x1_demonhunter_male.png";break;
     	    	case "Boots" : uri="http://media.blizzard.com/d3/icons/items/small/unique_boots_013_x1_demonhunter_male.png";break;
     	    	case "Bracers" : uri="http://media.blizzard.com/d3/icons/items/small/unique_bracer_009_x1_demonhunter_male.png";break;
     	    	case "Dagger": uri="http://media.blizzard.com/d3/icons/items/small/unique_dagger_003_x1_demonhunter_male.png";break;
     	    	case "Helm":uri="http://media.blizzard.com/d3/icons/items/small/unique_helm_008_x1_demonhunter_male.png";break;
     	    	case "Shield":uri="http://media.blizzard.com/d3/icons/items/small/unique_shield_009_x1_demonhunter_male.png";break;
     	    	case "Axe" : uri="http://media.blizzard.com/d3/icons/items/small/unique_axe_1h_001_x1_demonhunter_male.png";break;
     	    	case "Belt" : uri="http://media.blizzard.com/d3/icons/items/small/unique_barbbelt_009_x1_demonhunter_male.png";break;
     	    	case "Shoulders":uri="http://media.blizzard.com/d3/icons/items/small/unique_shoulder_003_p1_demonhunter_male.png";break;
     	    	case "GeneralUtility" : uri="http://media.blizzard.com/d3/icons/items/small/consumable_add_sockets_1_demonhunter_male.png";break;
     	    	case "Quiver" : uri="http://media.blizzard.com/d3/icons/items/small/unique_quiver_102_x1_demonhunter_male.png";break;
     	    	case "1H":uri="http://media.blizzard.com/d3/icons/items/small/unique_sword_1h_102_x1_demonhunter_male.png";break;
     	    	case "HealthPotion":uri="http://media.blizzard.com/d3/icons/items/small/healthpotionlegendary_07_demonhunter_male.png";break;
     	    	
     	    }
     	    	try {
     				URL url = new URL(uri);
     				i=new ImageIcon(url);
     				i = new ImageIcon(GrayFilter.createDisabledImage(i.getImage()));
     			} catch (Exception e1) {
     				//e1.printStackTrace();
     			}
     	    	b.setIcon(i);
             b.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					 lootedItem = fact.generateItem(((JButton)e.getSource()).getText());	
	            	 itemPanelDetails.showItem(lootedItem);
	            	 
	            	 ItemLabel lab = new ItemLabel();
				        lab.setItem(lootedItem, null);
				        itemPanelDetails.getLblIcon().setIcon(lab.getIcon());
				        itemPanelDetails.getLblIcon().repaint();
				}
			});
           
            add(b);
            
           
        }
		 JButton tryButton = new JButton("Gear IT !");
         tryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ItemCreatorFrame itemBuilderFrame = new ItemCreatorFrame(lootedItem,EnumerationStuff.MAIN_HAND);
					 ItemLabel lab = new ItemLabel();
				        lab.setItem(lootedItem, null);
				        itemBuilderFrame.getItemPanelDetails().getLblIcon().setIcon(lab.getIcon());
				        itemBuilderFrame.getItemPanelDetails().getLblIcon().repaint();
				     
				}
			});
         
         add(tryButton);
	}


}

