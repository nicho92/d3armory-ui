package org.armory.d3.beans;

import java.util.List;

public class LegendarySet {

	private String name;
	private String slug;
	private List<LegendarySetItem> ranks;
	private List<Item> items;
	
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public List<LegendarySetItem> getRanks() {
		return ranks;
	}
	public void setRanks(List<LegendarySetItem> ranks) {
		this.ranks = ranks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public String toString()
	{
		return name + " " + slug;
	}
	
	
}
