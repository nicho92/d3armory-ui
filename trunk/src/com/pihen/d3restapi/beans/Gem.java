package com.pihen.d3restapi.beans;

import java.io.Serializable;
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
	
	public String toString()
	{
		return item.toString();
	}

	public final static String[] QUALITIES()
	{
		return new String[]{"Chipped","Flawed","Normal","Flawless","Perfect","Radiant","Square","Flawless Square","Perfect Square","Radiant Square","Star","Flawless Star","Perfect Star","Marquise","Flawless Imperial","Royal","Flawless Royal"};
	}
	
	public static enum TYPES {Amethyst,Diamond,Emerald,Ruby,Topaz};
	
}
