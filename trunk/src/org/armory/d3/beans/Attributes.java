package org.armory.d3.beans;

public class Attributes {

	private ItemAttributs[] primary;
	private ItemAttributs[] secondary;
	private ItemAttributs[] passive;
	
	
	public ItemAttributs[] getPrimary() {
		return primary;
	}
	public void setPrimary(ItemAttributs[] primary) {
		this.primary = primary;
	}
	public ItemAttributs[] getSecondary() {
		return secondary;
	}
	public void setSecondary(ItemAttributs[] secondary) {
		this.secondary = secondary;
	}
	public ItemAttributs[] getPassive() {
		return passive;
	}
	public void setPassive(ItemAttributs[] passive) {
		this.passive = passive;
	}
	
}
