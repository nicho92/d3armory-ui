package org.armory.d3.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class Gem implements Serializable{

	private Item item;
	private AttributsContainer attributes;
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
	public AttributsContainer getAttributes() {
		return attributes;
	}
	public void setAttributes(AttributsContainer attributes) {
		this.attributes = attributes;
	}
	
	
//	public final static String[] getType()
//	{
//		return new String[]{"Chipped","Flawed","Normal","Flawless","Perfect","Radiant","Square","Flawless Square","Perfect Square","Radiant Square","Star","Flawless Star","Perfect Star","Marquise"};
//	}
	
	
}
