package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.armory.d3.beans.SkillRune;


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
public class RunePanel extends JPanel {

	private SkillRune skill;
	private SkillLabel skillLabel;
	private FormatedJLabel lblTextSkill;
	private FormatedJLabel lblUnLockedSkill;
	private JLabel lblNomSkill;

	public RunePanel(SkillRune skillRune) {
		this.skill=skillRune;
		this.setBackground(Color.BLACK);
		this.setSize(350, 100);
		this.setLayout(null);
		this.setPreferredSize(new java.awt.Dimension(350, 118));
					{
//						skillLabel = new SkillLabel();
//						skillLabel.setSkillRune(skill);
//						this.add(skillLabel);
//						skillLabel.setBounds(12, 51, 59, 58);
//						skillLabel.setSize(64, 64);
					}
					{
						lblTextSkill = new FormatedJLabel();
						lblTextSkill.setHtmlText(skill.getRune().getDescription(), "#C7B377", "green");//TODO gestion des . et , 
						this.add(lblTextSkill);
						lblTextSkill.setBounds(52, 39, 286, 58);
					}
					{
						lblNomSkill = new JLabel(skill.getRune().getName().toUpperCase());
						lblNomSkill.setForeground(new Color(199,179,119));
						lblNomSkill.setHorizontalAlignment(JLabel.CENTER);
						lblNomSkill.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
						this.add(lblNomSkill);
						lblNomSkill.setBounds(62, 8, 223, 25);
					}
					{
						lblUnLockedSkill = new FormatedJLabel();
						this.add(lblUnLockedSkill);
						lblUnLockedSkill.setHtmlText(String.valueOf("Level : " + skill.getRune().getLevel()), "#C7B377", "white"); 
						lblUnLockedSkill.setBounds(255, 97, 83, 16);
					}
	}
	
	public SkillRune getSkill() {
		return skill;
	}

	public void setSkill(SkillRune skill) {
		this.skill = skill;
	}
	
	

}
