package com.pihen.d3restapi.beans;

import java.io.Serializable;


public class SeasonalProfile implements Serializable {
	private Number seasonId;
	private Number paragonLevel;
	private Number paragonLevelHardcore;
	private KillsInfo kills;
	private TimePlayed timePlayed;
	private Number highestHardcoreLevel;
	//progression: {
	
	
	public String toString()
	{
		return "Season "+ seasonId;
	}
	
	
	
	public Number getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(Number seasonId) {
		this.seasonId = seasonId;
	}
	public Number getParagonLevel() {
		return paragonLevel;
	}
	public void setParagonLevel(Number paragonLevel) {
		this.paragonLevel = paragonLevel;
	}
	public Number getParagonLevelHardcore() {
		return paragonLevelHardcore;
	}
	public void setParagonLevelHardcore(Number paragonLevelHardcore) {
		this.paragonLevelHardcore = paragonLevelHardcore;
	}
	public KillsInfo getKills() {
		return kills;
	}
	public void setKills(KillsInfo kills) {
		this.kills = kills;
	}
	public TimePlayed getTimePlayed() {
		return timePlayed;
	}
	public void setTimePlayed(TimePlayed timePlayed) {
		this.timePlayed = timePlayed;
	}
	public Number getHighestHardcoreLevel() {
		return highestHardcoreLevel;
	}
	public void setHighestHardcoreLevel(Number highestHardcoreLevel) {
		this.highestHardcoreLevel = highestHardcoreLevel;
	}
	
}
