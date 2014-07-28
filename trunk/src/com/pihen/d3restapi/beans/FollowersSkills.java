package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class FollowersSkills implements Serializable{


	private static final long serialVersionUID = -6745327194567651378L;
	private Skill skill;

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
