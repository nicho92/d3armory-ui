package org.armory.d3.beans;

import java.util.ArrayList;
import java.util.List;

public class AttributsContainer {

	private ArrayList<DisplayableItemAttributs> primary;
	private ArrayList<DisplayableItemAttributs> secondary;
	private ArrayList<DisplayableItemAttributs> passive;
	
	
	
	public ArrayList<DisplayableItemAttributs> getPrimary() {
		return primary;
	}
	public void setPrimary(ArrayList<DisplayableItemAttributs> primary) {
		this.primary = primary;
	}
	public ArrayList<DisplayableItemAttributs> getSecondary() {
		return secondary;
	}
	public void setSecondary(ArrayList<DisplayableItemAttributs> secondary) {
		this.secondary = secondary;
	}
	public ArrayList<DisplayableItemAttributs> getPassive() {
		return passive;
	}
	public void setPassive(ArrayList<DisplayableItemAttributs> passive) {
		this.passive = passive;
	}
	
	
	
	
	
	
}
