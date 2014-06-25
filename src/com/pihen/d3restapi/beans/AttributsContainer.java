package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.List;

public class AttributsContainer  implements Serializable {

	private List<DisplayableItemAttributs> primary;
	private List<DisplayableItemAttributs> secondary;
	private List<DisplayableItemAttributs> passive;
	
	
	


	public List<DisplayableItemAttributs> getPrimary() {
		return primary;
	}





	public void setPrimary(List<DisplayableItemAttributs> primary) {
		this.primary = primary;
	}





	public List<DisplayableItemAttributs> getSecondary() {
		return secondary;
	}





	public void setSecondary(List<DisplayableItemAttributs> secondary) {
		this.secondary = secondary;
	}





	public List<DisplayableItemAttributs> getPassive() {
		return passive;
	}





	public void setPassive(List<DisplayableItemAttributs> passive) {
		this.passive = passive;
	}





	public String toString()
	{
		return "prim : " + getPrimary() + " \nsec: " + getSecondary() + " \npassive : " + getPassive();
	}
	
	
	
	
	
}
