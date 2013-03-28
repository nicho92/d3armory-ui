package org.armory.d3.beans;

import java.util.List;

public class HeroSkillContainer {
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
