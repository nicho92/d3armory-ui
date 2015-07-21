package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Item;

public class ChestPanel extends JPanel {
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.getContentPane().add(new ChestPanel());
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	GridBagLayout  layout ;
	public ChestPanel() {
		
		setBackground(Color.BLACK);
		
		layout = new GridBagLayout();
		
		GridBagConstraints c = new GridBagConstraints();
		  c.weightx = 1;
		  c.weighty = 1;
		  c.gridx = 0;
		  c.gridy = 0;
		 // c.fill=GridBagConstraints.BOTH;
		  
		setLayout(layout);
		
		
		int index=0;
		for(Item i : D3ArmoryControler.getInstance().getRecorder().listSavedItems())
		{
			
			ItemLabel lab = new ItemLabel();
				
					  lab.setItem(i,null);
					 // lab.setEnabledClick(false);
					  lab.setPreferredSize(new Dimension(64,128));
					  lab.setMinimumSize(new Dimension(40,72));
					  lab.setMaximumSize(new Dimension(64,128));
					  lab.setTransferHandler(new TransferHandler("item"));
					  MouseListener listener = new DragMouseAdapter();
					  		lab.addMouseListener(listener);
			add(lab,c);
			
			c.gridx=c.gridx+1;
			index++;
			
			if(index==10)
			{
				c.gridy=c.gridy+1;
				c.gridx=0;
				
				index=0;
				
			}
		}
		
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

