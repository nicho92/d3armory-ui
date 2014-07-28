package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class KillsInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3611613482562817012L;
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
	
	public String toString()
	{
		return "Elites:"+ elites + "/monsters: "+monsters+"/ hardcore Monsters: "+ hardcoreMonsters;
	}
	
}
