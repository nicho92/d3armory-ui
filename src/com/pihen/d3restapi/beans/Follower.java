package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.List;

public class Follower implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4190827522169521184L;
	private String slug;
	private Number level;
	private FollowerItemSet items;
	private FollowersStats stats;
	private List<FollowersSkills> skills;
	
	
	
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

	public List<FollowersSkills> getSkills() {
		return skills;
	}

	public void setSkills(List<FollowersSkills> skills) {
		this.skills = skills;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}
