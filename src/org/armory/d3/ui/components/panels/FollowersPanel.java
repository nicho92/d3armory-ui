package org.armory.d3.ui.components.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.armory.d3.ui.components.ItemLabel;
import org.armory.d3.ui.components.SkillLabel;


public class FollowersPanel extends JPanel{
	
	private ItemLabel lblTemplarObject;
	private ItemLabel lblTemplarNeck;
	private ItemLabel lblTemplarRing1;
	private ItemLabel lblTemplarOH;
	private ItemLabel lblTemplarMH;
	private ItemLabel lblTemplarRing2;
	
	private ItemLabel lblScoundrelObject;
	private ItemLabel lblEnchanteressRing2;
	private ItemLabel lblEnchanteressRing1;
	private ItemLabel lblEnchanteressOH;
	private ItemLabel lblEnchanteressMH;
	private ItemLabel lblEnchanteressNeck;
	private ItemLabel lblScoundrelNeck;
	private ItemLabel lblScoundrelMH;
	private ItemLabel lblEnchanteressObject;
	private ItemLabel lblScoundrelOH;
	private ItemLabel lblScoundrelRing1;
	private ItemLabel lblScoundrelRing2;

	private SkillLabel lblTemplarSkill1;
	private SkillLabel lblTemplarSkill2;
	private SkillLabel lblTemplarSkill3;
	private SkillLabel lblTemplarSkill4;
	
	private SkillLabel lblScoundrelSkill1;
	private SkillLabel lblScoundrelSkill2;
	private SkillLabel lblScoundrelSkill3;
	private SkillLabel lblScoundrelSkill4;
	
	private SkillLabel lblEnchanteressSkill1;
	private SkillLabel lblEnchanteressSkill2;
	private SkillLabel lblEnchanteressSkill3;
	private SkillLabel lblEnchanteressSkill4;
	
	
	public FollowersPanel() {
		
		this.setLayout(null);
		this.add(getLblTemplarObject());
		this.add(getLblTemplarNeck());
		this.add(getLblTemplarMH());
		this.add(getLblTemplarOH());
		this.add(getLblTemplarRing1());
		this.add(getLblTemplarRing2());
		this.add(getLblTemplarSkill1());
		this.add(getLblTemplarSkill2());
		this.add(getLblTemplarSkill3());
		this.add(getLblTemplarSkill4());
		
		this.add(getLblScoundrelRing2());
		this.add(getLblScoundrelRing1());
		this.add(getLblScoundrelOH());
		this.add(getLblScoundrelMH());
		this.add(getLblScoundrelNeck());
		this.add(getLblScoundrelObject());
		this.add(getLblScoundrelSkill1());
		this.add(getLblScoundrelSkill2());
		this.add(getLblScoundrelSkill3());
		this.add(getLblScoundrelSkill4());
		
		
		this.add(getLblEnchanteressObject());
		this.add(getLblEnchanteressNeck());
		this.add(getLblEnchanteressMH());
		this.add(getLblEnchanteressOH());
		this.add(getLblEnchanteressRing1());
		this.add(getLblEnchanteressRing2());
		this.add(getLblEnchanteressSkill1());
		this.add(getLblEnchanteressSkill2());
		this.add(getLblEnchanteressSkill3());
		this.add(getLblEnchanteressSkill4());
		
		this.setBackground(new Color(35,33,29));	
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
    

	public SkillLabel getLblTemplarSkill1() {
		if(lblTemplarSkill1==null)
		{
			lblTemplarSkill1= new SkillLabel(false);
			lblTemplarSkill1.setBounds(19,110, 39, 39);
		}
		return lblTemplarSkill1;
	}


	public SkillLabel getLblTemplarSkill2() {
		if(lblTemplarSkill2==null)
		{
			lblTemplarSkill2= new SkillLabel(false);
			lblTemplarSkill2.setBounds(19, 140, 39, 39);
		}
		return lblTemplarSkill2;
	}


	public SkillLabel getLblTemplarSkill3() {
		if(lblTemplarSkill3==null)
		{
			lblTemplarSkill3= new SkillLabel(false);
			lblTemplarSkill3.setBounds(19, 175, 39, 39);
		}
		return lblTemplarSkill3;
	}


	public SkillLabel getLblTemplarSkill4() {
		if(lblTemplarSkill4==null)
		{
			lblTemplarSkill4= new SkillLabel(false);
			lblTemplarSkill4.setBounds(19, 210, 39, 39);
		}
		return lblTemplarSkill4;
	}


	public SkillLabel getLblScoundrelSkill1() {
		if(lblScoundrelSkill1==null)
		{
			lblScoundrelSkill1= new SkillLabel(false);
			lblScoundrelSkill1.setBounds(305, 110, 39, 39);
		}
		return lblScoundrelSkill1;
	}


	public SkillLabel getLblScoundrelSkill2() {
		if(lblScoundrelSkill2==null)
		{
			lblScoundrelSkill2= new SkillLabel(false);
			lblScoundrelSkill2.setBounds(305, 140, 39, 39);
		}
		
		return lblScoundrelSkill2;
	}


	public SkillLabel getLblScoundrelSkill3() {
		if(lblScoundrelSkill3==null)
		{
			lblScoundrelSkill3= new SkillLabel(false);
			lblScoundrelSkill3.setBounds(305, 175, 39, 39);
		}
		
		return lblScoundrelSkill3;
	}


	public SkillLabel getLblScoundrelSkill4() {
		if(lblScoundrelSkill4==null)
		{
			lblScoundrelSkill4= new SkillLabel(false);
			lblScoundrelSkill4.setBounds(305, 210, 39, 39);
		}
		
		return lblScoundrelSkill4;
	}


	public SkillLabel getLblEnchanteressSkill1() {
		
		if(lblEnchanteressSkill1==null)
		{
			lblEnchanteressSkill1= new SkillLabel(false);
			lblEnchanteressSkill1.setBounds(592, 110, 39, 39);
		}
		return lblEnchanteressSkill1;
	}


	public SkillLabel getLblEnchanteressSkill2() {
		if(lblEnchanteressSkill2==null)
		{
			lblEnchanteressSkill2= new SkillLabel(false);
			lblEnchanteressSkill2.setBounds(592, 140, 39, 39);
		}
		
		return lblEnchanteressSkill2;
	}


	public SkillLabel getLblEnchanteressSkill3() {
		if(lblEnchanteressSkill3==null)
		{
			lblEnchanteressSkill3= new SkillLabel(false);
			lblEnchanteressSkill3.setBounds(592, 175, 39, 39);
		}
		
		return lblEnchanteressSkill3;
	}


	public SkillLabel getLblEnchanteressSkill4() {
		if(lblEnchanteressSkill4==null)
		{
			lblEnchanteressSkill4= new SkillLabel(false);
			lblEnchanteressSkill4.setBounds(592, 210, 39, 39);
		}
		
		return lblEnchanteressSkill4;
	}
}
