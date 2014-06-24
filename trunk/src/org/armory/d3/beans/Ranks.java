package org.armory.d3.beans;

import java.io.Serializable;
import java.util.Map;

public class Ranks implements Serializable{

	private String required;
	private  AttributsContainer attributes;
	private Map<String, MinMaxBonus > attributesRaw;
	
	
	
	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public Map<String, MinMaxBonus> getAttributesRaw() {
		return attributesRaw;
	}

	public void setAttributesRaw(Map<String, MinMaxBonus> attributesRaw) {
		this.attributesRaw = attributesRaw;
	}

	
	public AttributsContainer getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributsContainer attributes) {
		this.attributes = attributes;
	}

	public String toString()
	{
		return ""+getAttributesRaw();
	}
	
}
