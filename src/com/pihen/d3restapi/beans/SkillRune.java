package com.pihen.d3restapi.beans;

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
	
	public String toString()
	{
		return skill.toString();
	}
	
	
	
}
