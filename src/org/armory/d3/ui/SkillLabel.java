package org.armory.d3.ui;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import org.armory.d3.beans.Hero;
import org.armory.d3.beans.SkillRune;

public class SkillLabel extends JLabel {

	private SkillRune skill;
	
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
			this.setToolTipText("<html>"+ skill.getSkill().getDescription()+"</html>");
	}
}
