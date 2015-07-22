package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Item;

public class ChestPanel extends JPanel {
	
	LayoutManager  layout ;
	GridBagConstraints c;
	int index=0;
	
	public ChestPanel() {
		
		setBackground(Color.BLACK);
		
		layout = new GridBagLayout();
		
		c = new GridBagConstraints();
		  c.weightx = 1;
		  c.weighty = 1;
		  c.gridx = 0;
		  c.gridy = 0;
		  c.insets = new Insets(2,2,2,2); 
		  c.anchor = GridBagConstraints.NORTHWEST;
		 // c.fill=GridBagConstraints.BOTH;
		 
		setLayout(layout);
		
		
		for(Item i : D3ArmoryControler.getInstance().getRecorder().listSavedItems())
		{
			
			ItemLabel lab = new ItemLabel();
				
					  lab.setItem(i,null);
					
//					  lab.setTransferHandler(new TransferHandler("item"));
//					  MouseListener listener = new DragMouseAdapter();
//					  		lab.addMouseListener(listener);
			
					  addComponent(lab);
					 
		}
		
	}
	
	public void addComponent(ItemLabel i)
	{
		  i.setEnableRightClick(false);
		  i.setPreferredSize(new Dimension(64,128));
		if(index==12)
		{
			c.gridy=c.gridy+1;
			c.gridx=0;
			index=0;
		}
		
		c.gridx=c.gridx+1;
		add(i,c);
		index++;
		
	}
	
	private class DragMouseAdapter extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	      JComponent c = (JComponent) e.getSource();
	      TransferHandler handler = c.getTransferHandler();
	      System.out.println(handler.getCopyAction().getValue("item"));
	      handler.exportAsDrag(c, e, TransferHandler.COPY);
	    }
	  }
}

