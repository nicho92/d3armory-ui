package org.armory.d3.beans;

public class SkillRune {

	private Skill skill;
	private Rune rune;
	
	
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Rune getRune() {
		return rune;
	}
	public void setRune(Rune rune) {
		this.rune = rune;
	}
	
	//TODO
	public String toString()
	{
		String s = getSkill().getId();
		String r = "";
		
		if(getRune()!=null)
		{
			r=getRune().getId();
			return r;
		}
		else
		{
			return s;
		}
		
		
		
	}
	
	
}
