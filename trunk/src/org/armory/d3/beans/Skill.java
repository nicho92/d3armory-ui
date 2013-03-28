package org.armory.d3.beans;

public class Skill {
	
	private String description;
	private String icon;
	private String name;
	private String simpleDescription;
	private String slug;
	private String tooltipParams;
	private String skillCalcId;
	private Number level;
	private String categorySlug;
	private String tooltipUrl;
	private String flavor;
	
	
	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getTooltipUrl() {
		return tooltipUrl;
	}

	public void setTooltipUrl(String tooltipUrl) {
		this.tooltipUrl = tooltipUrl;
	}

	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public Number getLevel() {
		return level;
	}

	public void setLevel(Number level) {
		this.level = level;
	}

	public String getSkillCalcId() {
		return skillCalcId;
	}

	public void setSkillCalcId(String skillCalcId) {
		this.skillCalcId = skillCalcId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimpleDescription() {
		return this.simpleDescription;
	}

	public void setSimpleDescription(String simpleDescription) {
		this.simpleDescription = simpleDescription;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTooltipParams() {
		return this.tooltipParams;
	}

	public void setTooltipParams(String tooltipParams) {
		this.tooltipParams = tooltipParams;
	}
	
	public String toString()
	{
		return getName();
	}

}
