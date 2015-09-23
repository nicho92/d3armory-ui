package com.pihen.d3restapi.service.util;


import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;

public class KanaiCreator {

	public static Item createItemKanai(Item i)
	{
		
		i = D3ArmoryControler.getInstance().loadItemDetails(i);
			Item it = new Item();
			 it.setName(i.getName());
			 it.setTooltipParams(i.getTooltipParams());
			 it.setId(i.getId());
			 it.setType(null);
			 it.setIcon(i.getIcon());;
			 it.setDisplayColor(i.getDisplayColor());
			 it.setTypeName(i.getTypeName());
			 it.setArmor(null);
			
			 it.setRandomAffixes(null); 
		for(String k : i.getAttributesRaw().keySet())
		{
			if(k.startsWith("Item_Power_Passive#"))
			{
				it.addAttributesRaw(k, new MinMaxBonus(i.getAttributesRaw().get(k).getMax()));
			}
		}
		
		
		return it;
		
	}
}
