package com.pihen.d3restapi.beans;

import java.util.Map;

public class Affixes {

	private AttributsContainer attributes;
	private Map<String, MinMaxBonus > attributesRaw;
	
	
	public AttributsContainer getAttributes() {
		return attributes;
	}
	public void setAttributes(AttributsContainer attributes) {
		this.attributes = attributes;
	}
	public Map<String, MinMaxBonus> getAttributesRaw() {
		return attributesRaw;
	}
	public void setAttributesRaw(Map<String, MinMaxBonus> attributesRaw) {
		this.attributesRaw = attributesRaw;
	}
	
	public String toString()
	{
		return attributes.toString();
		
	}
	
}
