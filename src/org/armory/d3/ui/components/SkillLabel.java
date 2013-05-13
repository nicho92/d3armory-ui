package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.SkillRune;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.SwingMainFrame;

import com.sdfteam.d3armory.service.util.BuffSkill;

public class SkillLabel extends JLabel implements MouseListener {

	private SkillRune skill;
	private boolean enabled=true;
	private String size;
	public static final String SMALL = "21";
	public static final String LARGE = "64";
	
	public SkillRune getSkill() {
		return skill;
	}

	public SkillLabel(boolean enabled)
	{
		super();
		size="64";
		this.enabled=enabled;
		addMouseListener(this);
	}
	
	public SkillLabel(SkillRune s)
	{
		this.skill=s;
		size=LARGE;
		addMouseListener(this);
	}
	
	public Border getBorder() {
		if(skill==null)
			return super.getBorder();
			
		if(skill.getSkill()==null)
			return super.getBorder();
		
		if(enabled)
			return new LineBorder(Color.red);
		else
			return super.getBorder(); 
	}



	public Icon getIcon(){
		if(skill != null)
			try {
				URL url = new URL("http://media.blizzard.com/d3/icons/skills/"+size+"/"+skill.getSkill().getIcon()+".png");
				return new ImageIcon(url);
			} catch (Exception e1) {
				return new ImageIcon();
			}
			return super.getIcon();
	}


	public void setSkillRune(SkillRune skillRune) {
		this.skill=skillRune;
		if(skill.getSkill()!=null)
		{
			this.setToolTipText(" ");
		}
	}

    public JToolTip createToolTip() {
		
    	int height=200;
    	int width=350;
    	
    	if(skill.getRune()!=null)
    	{
    		height=300;
    	}
    	
    	
    	JPanelToolTip t = new JPanelToolTip(width,height,this);
        			t.setComponent(this);
        return t;
    }



	public void mouseClicked(MouseEvent arg0) {
		if(enabled)
			enabled=false;
		else
			enabled=true;
		
		if(enabled)
			D3ArmoryControler.getInstance().getCalculator().addBonus(BuffSkill.getBuff(skill, D3ArmoryControler.getInstance().getCalculator().getStuffs()));
		else
			D3ArmoryControler.getInstance().getCalculator().removeBonus(BuffSkill.getBuff(skill, D3ArmoryControler.getInstance().getCalculator().getStuffs()).keySet());
		
		D3ArmoryControler.getInstance().getCalculator().calculate();
		((SwingMainFrame)this.getTopLevelAncestor()).refreshDPS(); 
		((SwingMainFrame)this.getTopLevelAncestor()).getTableauDetailsModel().fireTableDataChanged();
		repaint();
	}



	public void mouseEntered(MouseEvent arg0) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}



	public void mouseExited(MouseEvent arg0) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
	}



	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
	
}
