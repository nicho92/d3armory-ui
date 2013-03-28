package org.armory.d3.beans;

import java.util.List;

public class LegendarySet {

	private String name;
	private String slug;
	private List<Ranks> ranks;
	private List<Item> items;
	
	
	public static int getNbItemSet(List<Item> stuff,LegendarySet set)
	{
		int nbPiece=0;
		for(Item z : stuff)
		{
			if(z!=null)
				for(Item s : set.getItems())
				{
					if(s.equals(z))
						nbPiece++;
				}
		}
		
		return nbPiece;
	}

	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public List<Ranks> getRanks() {
		return ranks;
	}
	public void setRanks(List<Ranks> ranks) {
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
		return slug;
	}

	public boolean equals(Object lgs) {
		return ((LegendarySet)lgs).getSlug().equals(this.getSlug());
	}
	
	public int hashCode()
	{
		return slug.hashCode();
	}
	
	
	
}
