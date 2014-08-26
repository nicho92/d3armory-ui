package com.pihen.d3restapi.service.util;

import java.util.ArrayList;
import java.util.List;

import com.pihen.d3restapi.beans.AttributsContainer;
import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Item;

public class GemsFactory {

	
	public static List<Gem> getGems(Gem.TYPES t, Item item)
	{
		List<Gem> list = new ArrayList<Gem>();
		
		for(int i=Gem.getQuality().length-1;i>=0;i--)
		{
			Gem g = new Gem();
			Item it = new Item();
			it.setName(Gem.getQuality()[i]);
			g.setItem(it);
			
			list.add(g);
		}
		
		
		
		
		return list;
	}

 public static void main(String[] args) {
	System.out.println(getGems(Gem.TYPES.Amethyst,null));
}
	
}
