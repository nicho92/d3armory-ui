package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class SkillRune implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3859658134581050046L;
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
