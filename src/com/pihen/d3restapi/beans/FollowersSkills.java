package com.pihen.d3restapi.beans;

public class FollowersSkills {

	Skill skill;

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public String toString()
	{
		return skill.toString();
	}
}
