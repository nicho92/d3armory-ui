package org.armory.d3.beans;

import java.util.List;

public class Follower {
	private FollowerItemSet items;
	private Number level;
	private List<Skill> skills;
	private String slug;
	private FollowersStats stats;
	
	
	public FollowerItemSet getItems() {
		return items;
	}

	public void setItems(FollowerItemSet items) {
		this.items = items;
	}

	public FollowersStats getStats() {
		return stats;
	}

	public void setStats(FollowersStats stats) {
		this.stats = stats;
	}

	public Number getLevel() {
		return level;
	}

	public void setLevel(Number level) {
		this.level = level;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}
