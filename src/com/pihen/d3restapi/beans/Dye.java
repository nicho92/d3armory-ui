package com.pihen.d3restapi.beans;

public class Dye {

	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getName()
	{
		
		if(item!=null)
			return getItem().getName();
		else
			return null;
	}
	
	public String getId()
	{
		
		if(item!=null)
			return getItem().getId();
		else
			return null;
	}
	
	public String getIcon()
	{
		
		if(item!=null)
			return getItem().getIcon();
		else
			return null;
	}
	
	public String getDisplayColor()
	{
		
		if(item!=null)
			return getItem().getDisplayColor();
		else
			return null;
	}
	
	public String getTooltipParams()
	{
		if(item!=null)
			return getItem().getTooltipParams();
		else
			return null;
	}
	
}
