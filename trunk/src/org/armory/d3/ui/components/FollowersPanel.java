package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jdesktop.application.Application;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class FollowersPanel extends JPanel {
	
	private ItemLabel lblTemplarObject;
	private ItemLabel lblTemplarNeck;
	private ItemLabel lblTemplarRing1;
	private ItemLabel lblTemplarOH;
	private ItemLabel lblTemplarMH;
	private ItemLabel lblTemplarRing2;

	public FollowersPanel() {
		setBackground(new Color(35,33,29));
		this.setPreferredSize(new java.awt.Dimension(845, 439));
		this.setLayout(null);
		this.add(getLblTemplarObject());
		this.add(getLblTemplarNeck());
		this.add(getLblTemplarMH());
		this.add(getLblTemplarOH());
		this.add(getLblTemplarRing1());
		this.add(getLblTemplarRing2());
		Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
	}
	

    protected void paintComponent(Graphics g) {
    		super.paintComponents(g);
			Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/followers.jpg")).getImage();
			g.drawImage(bg,0,0,null);	
	}
    
    public ItemLabel getLblTemplarObject() {
    	
    	if(lblTemplarObject ==null)
    	{
    		lblTemplarObject = new ItemLabel(ItemLabel.SIZE_SMALL);
    	   	lblTemplarObject.setBounds(161, 113, 39, 39);
    	}
    	return lblTemplarObject;
    }
    
    public ItemLabel getLblTemplarNeck() {
    	if(lblTemplarNeck == null) {
    		lblTemplarNeck = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblTemplarNeck.setBounds(209, 113, 39, 39);
    	}
    	return lblTemplarNeck;
    }
    
    public ItemLabel getLblTemplarMH() {
    	if(lblTemplarMH == null) {
    		lblTemplarMH = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblTemplarMH.setBounds(125, 158, 51, 101);
    	}
    	return lblTemplarMH;
    }
    
    public ItemLabel getLblTemplarOH() {
    	if(lblTemplarOH == null) {
    		lblTemplarOH = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblTemplarOH.setBounds(182, 158, 51, 101);
    	}
    	return lblTemplarOH;
    }
    
    public ItemLabel getLblTemplarRing1() {
    	if(lblTemplarRing1 == null) {
    		lblTemplarRing1 = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblTemplarRing1.setBounds(239, 158, 39, 39);

    	}
    	return lblTemplarRing1;
    }
    
    public ItemLabel getLblTemplarRing2() {
    	if(lblTemplarRing2 == null) {
    		lblTemplarRing2 = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblTemplarRing2.setBounds(239, 203, 39, 39);

    	}
    	return lblTemplarRing2;
    }

}
