package com.pihen.d3restapi.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.pihen.d3restapi.beans.Attributs;
import com.pihen.d3restapi.service.util.StuffCalculator.ELEMENTS;

public class RawsAttributes  {
	
	List<Attributs> itemAttributes;
	Map<String, String> map;
	
	public RawsAttributes()
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
		itemAttributes.add(new Attributs("Armor_Item" ,"+X Armor"));
		for(ELEMENTS e: ELEMENTS.values())
			itemAttributes.add(new Attributs("Damage_Dealt_Percent_Bonus#"+e,e+" Skills deal X% more damage"));
		
		
		itemAttributes.add(new Attributs("Hitpoints_Regen_Per_Second" ,"Regenerates X Life per Second"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Percent" ,"Attack speed increased by X%"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item_Bonus" ,"+X Attacks per Second"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item_Percent" ,"Increases Attack Speed by X%"));
		itemAttributes.add(new Attributs("Crit_Percent_Bonus_Capped" ,"Critical Hit Chance increased by X%"));
		itemAttributes.add(new Attributs("Crit_Damage_Percent" ,"Critical Hit Damage increased by X%"));
		itemAttributes.add(new Attributs("Damage_Weapon_Percent_Bonus" ,"+X% Damage"));
		itemAttributes.add(new Attributs("Damage_Min" ,"+X Minimum Damage"));
		itemAttributes.add(new Attributs("Damage_Delta" ,"+X Maximum Damage"));
		itemAttributes.add(new Attributs("Damage_Min#Physical" ,"+X Minimum Damage"));
		itemAttributes.add(new Attributs("Damage_Delta#Physical" ,"+X Maximum Damage"));
//		for(ELEMENTS e: ELEMENTS.values())
//			itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#"+e ,"+X% to "+e+" Damage"));
//		
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Arcane" ,"+X Arcane Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Cold" ,"+X Cold Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Fire" ,"+X Fire Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Holy" ,"+X Holy Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Lightning" ,"+X Lightning Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Poison" ,"+X Poison Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Physical" ,"+X Degats Min"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Physical" ,"+X Degats Max"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Arcane" ,"+X Degats Max Arcanic"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Cold" ,"+X Degats Max Cold"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Holy" ,"+X Degats Max Holy"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Fire" ,"+X Degats Max Fire"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Poison" ,"+X Degats Max Poison"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Lightning" ,"+X Degats Max Lightning"));
		itemAttributes.add(new Attributs("Damage_Weapon_Percent_Bonus#Physical",""));
		itemAttributes.add(new Attributs("Movement_Scalar" ,"+X% Movement Speed"));
		itemAttributes.add(new Attributs("Gold_PickUp_Radius" ,"Increases Gold and Health pickup by X yards"));
		itemAttributes.add(new Attributs("Health_Globe_Bonus_Health" ,"Health Globes and Potions Grant +X Life"));
		itemAttributes.add(new Attributs("Sockets" ,"X Sockets")); 
		itemAttributes.add(new Attributs("Durability_Max" ,""));//X Durabilitity MAX
		itemAttributes.add(new Attributs("Durability_Cur" ,""));//X Durabilitity CURRENT
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
