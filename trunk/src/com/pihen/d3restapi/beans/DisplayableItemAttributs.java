package com.pihen.d3restapi.beans;

public class DisplayableItemAttributs {
	
	private String text;
	private String affixType;
	private String color;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAffixType() {
		return affixType;
	}
	public void setAffixType(String affixType) {
		this.affixType = affixType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString()
	{
		return getText();
	}
	
}
