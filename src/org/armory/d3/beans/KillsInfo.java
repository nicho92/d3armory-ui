package org.armory.d3.beans;

public class KillsInfo {
	private Number elites;
	private Number monsters;
	private Number hardcoreMonsters;
	
	public Number getMonsters() {
		return monsters;
	}

	public void setMonsters(Number monsters) {
		this.monsters = monsters;
	}

	public Number getHardcoreMonsters() {
		return hardcoreMonsters;
	}

	public void setHardcoreMonsters(Number hardcoreMonsters) {
		this.hardcoreMonsters = hardcoreMonsters;
	}

	public Number getElites() {
		return this.elites;
	}

	public void setElites(Number elites) {
		this.elites = elites;
	}
}
