package org.armory.d3.beans;

public class Rune {
	private String slug;
	private String type;
	private String name;
	private Number level;
	private String description;
	private String simpleDescription;
	private String tooltipParams;
	private String skillCalcId;
	private Number order;
	
	public String getId()
	{
		return getSlug().substring(getSlug().lastIndexOf("/")+1,getSlug().length()).trim();
	}
	
	
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Number getLevel() {
		return level;
	}
	public void setLevel(Number level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSimpleDescription() {
		return simpleDescription;
	}
	public void setSimpleDescription(String simpleDescription) {
		this.simpleDescription = simpleDescription;
	}
	public String getTooltipParams() {
		return tooltipParams;
	}
	public void setTooltipParams(String tooltipParams) {
		this.tooltipParams = tooltipParams;
	}
	public String getSkillCalcId() {
		return skillCalcId;
	}
	public void setSkillCalcId(String skillCalcId) {
		this.skillCalcId = skillCalcId;
	}
	public Number getOrder() {
		return order;
	}
	public void setOrder(Number order) {
		this.order = order;
	}
	
	public String toString()
	{
		return getName();
	}



}
