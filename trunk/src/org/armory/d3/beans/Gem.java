package org.armory.d3.beans;

import java.util.Map;


public class Gem {

	private Item item;
	private String[] attributes;
	private Map<String, MinMaxBonus > attributesRaw;
	
	
	public Map<String, MinMaxBonus> getAttributesRaw() {
		return attributesRaw;
	}
	public void setAttributesRaw(Map<String, MinMaxBonus> attributesRaw) {
		this.attributesRaw = attributesRaw;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String[] getAttributes() {
		return attributes;
	}
	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}
	
	
	
}
