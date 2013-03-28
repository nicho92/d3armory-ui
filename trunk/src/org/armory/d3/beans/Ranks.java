package org.armory.d3.beans;

import java.util.Map;

public class Ranks {

	private String required;
	private String[] attributes;
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

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}
	
	public String toString()
	{
		return ""+getAttributesRaw();
	}
	
}
