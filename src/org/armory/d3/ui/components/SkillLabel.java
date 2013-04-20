package org.armory.d3.ui.components;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import org.armory.d3.beans.SkillRune;

public class SkillLabel extends JLabel {

	private SkillRune skill;
	
	public SkillRune getSkill() {
		return skill;
	}



	public void setSkill(SkillRune skill) {
		this.skill = skill;
	}



	public SkillLabel()
	{
		super();
	}
	
	
	
	public SkillLabel(SkillRune s)
	{
		this.skill=s;
		
	}
	
	

	public Icon getIcon(){
		if(skill != null)
			try {
				URL url = new URL("http://media.blizzard.com/d3/icons/skills/64/"+skill.getSkill().getIcon()+".png");
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
	
	
	
}
