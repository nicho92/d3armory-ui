package com.pihen.d3restapi.service.util;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;

public class LegendaryAffixe {

	
	
	
	public static void applyLegendaryAffixes(Item i)
	{
			if(i.getAttributesRaw().get("Item_Power_Passive#ItemPassive_Unique_Ring_571_x1")!=null)
				i.getAttributesRaw().put("Damage_Percent_Bonus_Vs_Elites_LEG_", i.getAttributesRaw().get("Item_Power_Passive#ItemPassive_Unique_Ring_571_x1"));
		
			if(i.getAttributesRaw().get("Gem_Attributes_Multiplier")!=null)
				if(i.getGems()!=null)
				{
					double bonus = i.getAttributesRaw().get("Gem_Attributes_Multiplier").getMoyenne();
					String k = i.getGems().get(0).getAttributesRaw().keySet().iterator().next();
					double value = i.getGems().get(0).getAttributesRaw().get(k).getMoyenne();
					
					i.getAttributesRaw().put(k+"_LEG_", new MinMaxBonus( value*bonus) );
				}
			
			
	}
}
