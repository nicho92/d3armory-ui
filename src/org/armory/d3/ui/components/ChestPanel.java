package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.SwingMainFrame;
import org.armory.d3.ui.components.transfert.DezItemLabelTargetListener;

import com.pihen.d3restapi.beans.Item;

public class ChestPanel extends JPanel{
	
	int index=0;
	GridBagConstraints c;
	JPanel contentChest;
	JPanel itemDezPanel;
	ItemLabel lblDezItem;
	
	public void init()
	{
		index=0;
		c = new GridBagConstraints();
		  c.weightx = 1;
		  c.weighty = 1;
		  c.gridx = 0;
		  c.gridy = 0;
		  c.insets = new Insets(2,2,2,2); 
		  c.anchor = GridBagConstraints.NORTHWEST;
		  
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				contentChest.removeAll();
				for(Item i : D3ArmoryControler.getInstance().getRecorder().listSavedItems())
				{
					
					ItemLabel lab = new ItemLabel();
							  lab.setItem(i);
							  lab.enabledDraggable(true);
							  addComponent(lab);
							 
				}
				repaint();
			}
		});
	}
	
	
	public ChestPanel() {
	
		contentChest=new JPanel();
		itemDezPanel=new JPanel();
		lblDezItem = new ItemLabel();
		
		lblDezItem.setIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/itemDez.png")));
		new DezItemLabelTargetListener(lblDezItem);
		
		
		setLayout(new BorderLayout());
		
		contentChest.setBackground(Color.BLACK);
		itemDezPanel.setBackground(Color.BLACK);
		
		contentChest.setLayout(new GridBagLayout());
		FlowLayout fl_itemDezPanel = new FlowLayout();
		fl_itemDezPanel.setAlignment(FlowLayout.LEFT);
		itemDezPanel.setLayout(fl_itemDezPanel);
		
		
		
		
		init();
		
		
		
		add(contentChest,BorderLayout.CENTER);
		add(itemDezPanel,BorderLayout.EAST);
		
		
		itemDezPanel.add(lblDezItem);
		
	}
	
	public void addComponent(ItemLabel i)
	{
		  i.setEnableRightClick(false);
		  i.setPreferredSize(new Dimension(64,128));
		if(index==10)
		{
			c.gridy=c.gridy+1;
			c.gridx=0;
			index=0;
		}
		
		c.gridx=c.gridx+1;
		contentChest.add(i,c);
		index++;
		
	}
}

