package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class FollowersStats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6758459890542133507L;
	private String goldFind;
	private String magicFind;
	private Number experienceBonus;
	
	
	public String getGoldFind() {
		return goldFind;
	}
	public void setGoldFind(String goldFind) {
		this.goldFind = goldFind;
	}
	public String getMagicFind() {
		return magicFind;
	}
	public void setMagicFind(String magicFind) {
		this.magicFind = magicFind;
	}
	public Number getExperienceBonus() {
		return experienceBonus;
	}
	public void setExperienceBonus(Number experienceBonus) {
		this.experienceBonus = experienceBonus;
	}
	
	
	public String toString()
	{
		return "MF:" + getMagicFind() + " GF:" + getGoldFind() + " XP:"+getExperienceBonus(); 
	}
	
	
}
