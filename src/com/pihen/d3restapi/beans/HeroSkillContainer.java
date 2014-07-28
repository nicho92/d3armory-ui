package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.List;

public class HeroSkillContainer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2283076437248119519L;
	private List<SkillRune> active;
	private List<SkillRune> passive;

	public List<SkillRune> getActive() {
		return active;
	}

	public void setActive(List<SkillRune> active) {
		this.active = active;
	}

	public List<SkillRune> getPassive() {
		return passive;
	}

	public void setPassive(List<SkillRune> passive) {
		this.passive = passive;
	}

}
