package com.pihen.d3restapi.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.pihen.d3restapi.beans.Attributs;
import com.pihen.d3restapi.service.util.StuffCalculator.ELEMENTS;

public class RawsAttributeFactory  {
	
	List<Attributs> itemAttributes;
	Map<String, String> map;
	
	public RawsAttributeFactory()
	{
		itemAttributes = new ArrayList<Attributs>();
		initMap();
	}
	
	private void initMap()
	{
		itemAttributes.add(new Attributs("Strength_Item" ,"+X Strength"));
		itemAttributes.add(new Attributs("Intelligence_Item" ,"+X Intelligence"));
		itemAttributes.add(new Attributs("Vitality_Item" ,"+X Vitality"));
		itemAttributes.add(new Attributs("Dexterity_Item" ,"+X Dexterity"));
		itemAttributes.add(new Attributs("Resistance_All" ,"+X Resistance to All Elements"));
		itemAttributes.add(new Attributs("Armor_Item" ,"+X Armor",false));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Percent" ,"Attack speed increased by X%"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item_Bonus" ,"+X Attacks per Second"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item_Percent" ,"Increases Attack Speed by X%"));
		itemAttributes.add(new Attributs("Crit_Percent_Bonus_Capped" ,"Critical Hit Chance increased by X%"));
		itemAttributes.add(new Attributs("Crit_Damage_Percent" ,"Critical Hit Damage increased by X%"));
		itemAttributes.add(new Attributs("Damage_Weapon_Percent_All" ,"+X% Damage"));
		itemAttributes.add(new Attributs("Damage_Percent_Bonus_Vs_Elites","Increases damage against elites by X%"));
		itemAttributes.add(new Attributs("Hitpoints_Max_Percent_Bonus_Item","+X% life"));
		itemAttributes.add(new Attributs("Power_Cooldown_Reduction_Percent_All","Reduce Cooldown of all skills by X%"));
		itemAttributes.add(new Attributs("Sockets" ,"X Sockets",false)); 
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Elites" ,"Reduces damage from elites by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Melee" ,"Reduces damage from melee attacks by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Ranged" ,"Reduces damage from ranged attacks by X%"));

		for(ELEMENTS e: ELEMENTS.values())
		{
			itemAttributes.add(new Attributs("Resistance#"+e ,"+X " +e+" Resistance"));
			itemAttributes.add(new Attributs("Damage_Dealt_Percent_Bonus#"+e,e+" Skills deal X% more damage"));
			itemAttributes.add(new Attributs("Damage_Weapon_Min#"+e ,"+X-X "+e+" Damage"));
		}
		itemAttributes.add(new Attributs("Damage_Min#Physical","+X-X Damage"));
	
	
	}
	
	public Attributs[] getAttributs()
	{
		Collections.sort(itemAttributes);
		return itemAttributes.toArray(new Attributs[itemAttributes.size()]);
	}
	
	public Attributs getAttribut(String key)
	{
		for(Attributs a : itemAttributes)
		{
			if(a.getId().equalsIgnoreCase(key))
				return a;
		}
		return null;
	}

}
