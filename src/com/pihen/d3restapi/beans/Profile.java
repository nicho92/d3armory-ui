package com.pihen.d3restapi.beans;

import java.util.List;

import com.pihen.d3restapi.service.annotation.RemoteConfiguration;
import com.pihen.d3restapi.service.remote.RemoteEntity;


@RemoteConfiguration(url = "http://<host>/api/d3/profile/<battletag-name>-<battletag-code>/?locale=<local>")
public class Profile extends RemoteEntity {

	private List<Hero> heroes;
	private Number lastHeroPlayed;
	private Number lastUpdated;
	private KillsInfo kills;
	private TimePlayed timePlayed;
	private List<Hero> fallenHeros;
	private Number paragonLevel;
	private Number paragonLevelHardcore;
	private String battleTag;
	private Number paragonLevelSeason;
	private Number paragonLevelSeasonHardcore;
	private Number highestDifficulty;
	private Number highestHardcoreLevel;
	private Season seasonalProfiles;
	
	public Number getHighestDifficulty() {
		return highestDifficulty;
	}

	public void setHighestDifficulty(Number highestDifficulty) {
		this.highestDifficulty = highestDifficulty;
	}

	public Season getSeasonalProfiles() {
		return seasonalProfiles;
	}

	public void setSeasonalProfiles(Season seasonalProfiles) {
		this.seasonalProfiles = seasonalProfiles;
	}

	public Number getHighestHardcoreLevel() {
		return highestHardcoreLevel;
	}

	public void setHighestHardcoreLevel(Number highestHardcoreLevel) {
		this.highestHardcoreLevel = highestHardcoreLevel;
	}


	public Number getParagonLevelSeason() {
		return paragonLevelSeason;
	}

	public void setParagonLevelSeason(Number paragonLevelSeason) {
		this.paragonLevelSeason = paragonLevelSeason;
	}

	public Number getParagonLevelSeasonHardcore() {
		return paragonLevelSeasonHardcore;
	}

	public void setParagonLevelSeasonHardcore(Number paragonLevelSeasonHardcore) {
		this.paragonLevelSeasonHardcore = paragonLevelSeasonHardcore;
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

	public List<Hero> getFallenHeros() {
		return fallenHeros;
	}

	public void setFallenHeros(List<Hero> fallenHeros) {
		this.fallenHeros = fallenHeros;
	}

	public String getBattleTag() {
		return this.battleTag;
	}

	public void setBattleTag(String battleTag) {
		this.battleTag = battleTag;
	}

	
	public KillsInfo getKills() {
		return kills;
	}

	public void setKills(KillsInfo kills) {
		this.kills = kills;
	}

	
	public List<Hero> getHeroes() {
		return this.heroes;
	}

	public void setHeroes(List<Hero> heroes) {
		this.heroes = heroes;
	}

	public Number getLastHeroPlayed() {
		return this.lastHeroPlayed;
	}

	public void setLastHeroPlayed(Number lastHeroPlayed) {
		this.lastHeroPlayed = lastHeroPlayed;
	}

	public Number getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Number lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	
	public TimePlayed getTimePlayed() {
		return this.timePlayed;
	}

	public void setTimePlayed(TimePlayed timePlayed) {
		this.timePlayed = timePlayed;
	}
	
	public String toString()
	{
		return getBattleTag(); 
	}
}
