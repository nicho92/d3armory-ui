package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jdesktop.application.Application;

public class FollowersPanel extends JPanel {
	
	public ItemLabel lblTemplarObject;
	public ItemLabel lblTemplarNeck;
	public ItemLabel lblTemplarRing1;
	public ItemLabel lblTemplarOH;
	public ItemLabel lblTemplarMH;
	public ItemLabel lblTemplarRing2;
	
	public ItemLabel lblScoundrelObject;
	public ItemLabel lblEnchanteressRing2;
	public ItemLabel lblEnchanteressRing1;
	public ItemLabel lblEnchanteressOH;
	public ItemLabel lblEnchanteressMH;
	public ItemLabel lblEnchanteressNeck;
	public ItemLabel lblScoundrelNeck;
	public ItemLabel lblScoundrelMH;
	public ItemLabel lblEnchanteressObject;
	public ItemLabel lblScoundrelOH;
	public ItemLabel lblScoundrelRing1;
	public ItemLabel lblScoundrelRing2;

	public FollowersPanel() {
		setBackground(new Color(35,33,29));
		this.setLayout(null);
		this.add(getLblTemplarObject());
		this.add(getLblTemplarNeck());
		this.add(getLblTemplarMH());
		this.add(getLblTemplarOH());
		this.add(getLblTemplarRing1());
		this.add(getLblTemplarRing2());
		this.add(getLblScoundrelRing2());
		this.add(getLblScoundrelRing1());
		this.add(getLblScoundrelOH());
		this.add(getLblScoundrelMH());
		this.add(getLblScoundrelNeck());
		this.add(getLblScoundrelObject());
		this.add(getLblEnchanteressObject());
		this.add(getLblEnchanteressNeck());
		this.add(getLblEnchanteressMH());
		this.add(getLblEnchanteressOH());
		this.add(getLblEnchanteressRing1());
		this.add(getLblEnchanteressRing2());

	}
	

    protected void paintComponent(Graphics g) {
    		Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/followers.jpg")).getImage();
			g.drawImage(bg,0,0,null);
			super.paintComponents(g);
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
    
    public ItemLabel getLblScoundrelRing2() {
    	if(lblScoundrelRing2 == null) {
    		lblScoundrelRing2 = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblScoundrelRing2.setBounds(520, 203, 39, 39);
    	}
    	return lblScoundrelRing2;
    }
    
    public ItemLabel getLblScoundrelRing1() {
    	if(lblScoundrelRing1 == null) {
    		lblScoundrelRing1 = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblScoundrelRing1.setBounds(520, 159, 39, 39);
    	}
    	return lblScoundrelRing1;
    }
    
    public ItemLabel getLblScoundrelOH() {
    	if(lblScoundrelOH == null) {
    		lblScoundrelOH = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblScoundrelOH.setBounds(463, 158, 51, 101);

   	}
    	return lblScoundrelOH;
    }
    
    public ItemLabel getLblScoundrelMH() {
    	if(lblScoundrelMH == null) {
    		lblScoundrelMH = new  ItemLabel(ItemLabel.SIZE_SMALL);
    		lblScoundrelMH.setBounds(410, 158, 51, 101);
    	}
    	return lblScoundrelMH;
    }
    
    public ItemLabel getLblScoundrelNeck() {
    	if(lblScoundrelNeck == null) {
    		lblScoundrelNeck = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblScoundrelNeck.setBounds(495, 113, 39, 39);
    	}
    	return lblScoundrelNeck;
    }
    
    public ItemLabel getLblScoundrelObject() {
    	if(lblScoundrelObject == null) {
    		lblScoundrelObject = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblScoundrelObject.setBounds(444, 113, 39, 39);
    	}
    	return lblScoundrelObject;
    }
    
    public ItemLabel getLblEnchanteressObject() {
    	if(lblEnchanteressObject == null) {
    		lblEnchanteressObject =new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblEnchanteressObject.setBounds(734, 113, 39, 39);
    	}
    	return lblEnchanteressObject;
    }
    
    public ItemLabel getLblEnchanteressNeck() {
    	if(lblEnchanteressNeck == null) {
    		lblEnchanteressNeck = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblEnchanteressNeck.setBounds(785, 113, 39, 39);
    	}
    	return lblEnchanteressNeck;
    }
    
    public ItemLabel getLblEnchanteressMH() {
    	if(lblEnchanteressMH == null) {
    		lblEnchanteressMH = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblEnchanteressMH.setBounds(700, 158, 51, 101);
    	}
    	return lblEnchanteressMH;
    }
    
    public ItemLabel getLblEnchanteressOH() {
    	if(lblEnchanteressOH == null) {
    		lblEnchanteressOH = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblEnchanteressOH.setBounds(753, 158, 51, 101);
    	}
    	return lblEnchanteressOH;
    }
    
    public ItemLabel getLblEnchanteressRing1() {
    	if(lblEnchanteressRing1 == null) {
    		lblEnchanteressRing1 = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblEnchanteressRing1.setBounds(810, 159, 39, 39);
    	}
    	return lblEnchanteressRing1;
    }
    
    public ItemLabel getLblEnchanteressRing2() {
    	if(lblEnchanteressRing2 == null) {
    		lblEnchanteressRing2 = new ItemLabel(ItemLabel.SIZE_SMALL);
    		lblEnchanteressRing2.setBounds(810, 203, 39, 39);
    	}
    	return lblEnchanteressRing2;
    }

}
