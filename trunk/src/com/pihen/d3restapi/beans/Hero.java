package com.pihen.d3restapi.beans;

import com.google.gson.annotations.SerializedName;
import com.pihen.d3restapi.service.annotation.RemoteConfiguration;
import com.pihen.d3restapi.service.remote.RemoteEntity;

@RemoteConfiguration(url = "http://<host>/api/d3/profile/<battletag-name>-<battletag-code>/hero/<hero-id>?locale=<local>")
public class Hero extends RemoteEntity {
	
	private Number id;
	private String name;
	@SerializedName("class") private String clazz;
	private Number gender;	
	private Number level;
	private Long paragonLevel;
	private boolean hardcore;

	private HeroSkillContainer skills;
	@SerializedName("items") private Stuff stuff;
	private FollowersList followers;
	private Stats stats;
	private KillsInfo kills;
	
	private Progression progression;
	
	public Progression getProgression() {
		return progression;
	}


	public void setProgression(Progression progression) {
		this.progression = progression;
	}

	private boolean dead;
	@SerializedName("last-updated")	private Long lastUpdated;

	
	public String getPrimaryStat()
	{
			if("demon-hunter".equals(getClazz())) 
				return "Dexterity"; 
			if("barbarian".equals(getClazz()))
				return "Strength"; 
			if("monk".equals(getClazz()))
				return "Dexterity"; 
			if("wizard".equals(getClazz()))
				return "Intelligence"; 
			if("witch-doctor".equals(getClazz()))
				return "Intelligence"; 
			if("crusader".equals(getClazz()))
				return "Strength";
		
		return "";
	}
	

	public boolean isDead() {
		return dead;
	}


	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Long getParagonLevel() {
		return paragonLevel;
	}

	public void setParagonLevel(Long paragonLevel) {
		this.paragonLevel = paragonLevel;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public FollowersList getFollowers() {
		return followers;
	}

	public void setFollowers(FollowersList followers) {
		this.followers = followers;
	}

	public Number getGender() {
		return gender;
	}
	
	public String getSexe()
	{
		if(gender.intValue()==0)
			return "male";
		
		return "female";
	}
	
	public void setGender(Number gender) {
		this.gender = gender;
	}

	public boolean isHardcore() {
		return hardcore;
	}

	public void setHardcore(boolean hardcore) {
		this.hardcore = hardcore;
	}

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public Stuff getItems() {
		return stuff;
	}

	public void setItems(Stuff items) {
		this.stuff = items;
	}

	public KillsInfo getKills() {
		return kills;
	}

	public void setKills(KillsInfo kills) {
		this.kills = kills;
	}

	public Number getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Number getLevel() {
		return level;
	}

	public void setLevel(Number level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public HeroSkillContainer getSkills() {
		return skills;
	}

	public void setSkills(HeroSkillContainer skills) {
		this.skills = skills;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	public String toString()
	{
		return name;
	}

}