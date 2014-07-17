package com.pihen.d3restapi.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.pihen.d3restapi.beans.Attributs;

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
		itemAttributes.add(new Attributs("Armor_Bonus_Item","+X Bonus Armor"));
		itemAttributes.add(new Attributs("Armor_Item_Percent","+X % Armor"));
		itemAttributes.add(new Attributs("Hitpoints_Max_Percent_Bonus_Item" ,"+X% Life"));
		itemAttributes.add(new Attributs("Hitpoints_Regen_Per_Second" ,"Regenerates X Life per Second"));
		itemAttributes.add(new Attributs("Block_Chance_Bonus_Item" ,"+X% Chance to Block"));
		itemAttributes.add(new Attributs("CrowdControl_Reduction_Hallowed" ,"Reduces the duration of control impairing effects by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Elites" ,"Reduces damage from elites by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Melee" ,"Reduces damage from melee attacks by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Range" ,"Reduces damage from ranged attacks by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Cold" ,"Reduces damage from Cold attacks by X%."));
		itemAttributes.add(new Attributs("Resistance#Arcane" ,"X Arcane Resistance"));
		itemAttributes.add(new Attributs("Resistance#Cold" ,"X Cold Resistance"));
		itemAttributes.add(new Attributs("Resistance#Fire" ,"X Fire Resistance"));
		itemAttributes.add(new Attributs("Resistance#Lightning" ,"X Lightning Resistance"));
		itemAttributes.add(new Attributs("Resistance#Physical" ,"X Physical Resistance"));
		itemAttributes.add(new Attributs("Resistance#Poison" ,"X Poison Resistance"));
		itemAttributes.add(new Attributs("Thorns_Fixed#Physical" ,"Melee attackers take X damage per hit"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Percent" ,"Attack speed increased by X%"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item_Bonus" ,"+X Attacks per Second"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item_Percent" ,"Increases Attack Speed by X%"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item" ,""));//attaque de base de l'arme
		itemAttributes.add(new Attributs("Crit_Percent_Bonus_Capped" ,"Critical Hit Chance increased by X%"));
		itemAttributes.add(new Attributs("Crit_Damage_Percent" ,"Critical Hit Damage increased by X%"));
		itemAttributes.add(new Attributs("Damage_Weapon_Percent_Bonus" ,"+X% Damage"));
		itemAttributes.add(new Attributs("Damage_Min" ,"+X Minimum Damage"));
		itemAttributes.add(new Attributs("Damage_Delta" ,"+X Maximum Damage"));
		itemAttributes.add(new Attributs("Damage_Min#Physical" ,"+X Minimum Damage"));
		itemAttributes.add(new Attributs("Damage_Delta#Physical" ,"+X Maximum Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Arcane" ,"+X% to Arcane Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Cold" ,"+X% to Cold Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Fire" ,"+X% to Fire Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Holy" ,"+X% to Holy Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Lightning" ,"+X% to Lightning Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Poison" ,"+X% to Poison Damage"));
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
		itemAttributes.add(new Attributs("Weapon_On_Hit_Blind_Proc_Chance" ,"X% chance to Blind on Hit"));
		itemAttributes.add(new Attributs("Weapon_On_Hit_Chill_Proc_Chance" ,"X% chance to Chill on Hit"));
		itemAttributes.add(new Attributs("Weapon_On_Hit_Fear_Proc_Chance" ,"X% chance to Fear on Hit"));
		itemAttributes.add(new Attributs("Weapon_On_Hit_Freeze_Proc_Chance" ,"X% chance to Freeze on Hit"));
		itemAttributes.add(new Attributs("Weapon_On_Hit_Immobilize_Proc_Chance" ,"X% chance to Immobilize on Hit"));
		itemAttributes.add(new Attributs("Weapon_On_Hit_Knockback_Proc_Chance" ,"X% chance to Knockback on Hit"));
		itemAttributes.add(new Attributs("Weapon_On_Hit_Slow_Proc_Chance" ,"X% chance to Slow on Hit"));
		itemAttributes.add(new Attributs("Weapon_On_Hit_Stun_Proc_Chance" ,"X% chance to Stun on Hit"));
		itemAttributes.add(new Attributs("Movement_Scalar" ,"+X% Movement Speed"));
		itemAttributes.add(new Attributs("Gold_PickUp_Radius" ,"Increases Gold and Health pickup by X yards"));
		itemAttributes.add(new Attributs("Health_Globe_Bonus_Health" ,"Health Globes and Potions Grant +X Life"));
		itemAttributes.add(new Attributs("Sockets" ,"X Sockets")); 
		itemAttributes.add(new Attributs("Durability_Max" ,""));//X Durabilitity MAX
		itemAttributes.add(new Attributs("Durability_Cur" ,""));//X Durabilitity CURRENT
		itemAttributes.add(new Attributs("Hitpoints_On_Hit" ,"Each hits adds +X Life"));
		itemAttributes.add(new Attributs("Hitpoints_On_Kill" ,"+X Life after each Kill"));
		itemAttributes.add(new Attributs("Hitpoints_Regen_Per_Second" ,"Regenerates X Life per Second"));
		itemAttributes.add(new Attributs("Magic_Find" ,"+X% Better Chance of Finding Magical Items"));
		itemAttributes.add(new Attributs("Item_Binding_Level_Override" ,"")); //lié au compte
		itemAttributes.add(new Attributs("Item_Level_Requirement_Reduction" ,"Level Requirement Reduced by X"));
		itemAttributes.add(new Attributs("Resource_Max_Bonus#Arcanum" ,"+X Maximum Arcane Power")); 
		itemAttributes.add(new Attributs("Resource_Max_Bonus#Discipline" ,"+X Maximum Discipline Power")); 
		itemAttributes.add(new Attributs("Resource_Max_Bonus#Fury" ,"+X Maximum Fury Power")); 
		itemAttributes.add(new Attributs("Resource_Max_Bonus#Hatred" ,"+X Maximum Hatred Power")); 
		itemAttributes.add(new Attributs("Resource_Max_Bonus#Mana" ,"+X Maximum Mana Power")); 
		itemAttributes.add(new Attributs("Resource_Max_Bonus#Spirit" ,"+X Maximum Spirit Power")); 
		itemAttributes.add(new Attributs("CrowdControl_Reduction","Reduce duration of control impairing effects by X%"));
		itemAttributes.add(new Attributs("Experience_Bonus","Monster kills grant +X experience"));
		itemAttributes.add(new Attributs("Experience_Bonus_Percent","Increases Bonus Experience by X%"));
		itemAttributes.add(new Attributs("Power_Resource_Reduction#Barbarian_Rend","Reduce Resource Cost of Rend by X Fury."));
		itemAttributes.add(new Attributs("Power_Resource_Reduction#Barbarian_WeaponThrow" ,"Reduces resource cost of Weapon Throw by X Fury"));
		itemAttributes.add(new Attributs("Power_Resource_Reduction#Barbarian_hammer-of-the-ancients" ,"Reduces resource cost of Hammer of the Ancients by X Fury"));
		itemAttributes.add(new Attributs("Power_Damage_Percent_Bonus#Barbarian_AncientSpear","Increase Ancient Spear Damage by X%"));
		itemAttributes.add(new Attributs("Power_Damage_Percent_Bonus#Barbarian_WeaponThrow","Increase Weapon Throw Damage by X%"));
		itemAttributes.add(new Attributs("Power_Damage_Percent_Bonus#Barbarian_Bash","Increase Bash Damage by X%"));
		itemAttributes.add(new Attributs("Power_Damage_Percent_Bonus#Barbarian_Cleave","Increase cleave Damage by X%"));
		itemAttributes.add(new Attributs("Power_Damage_Percent_Bonus#Barbarian_Frenzy","Increase frenzy Damage by X%"));
		itemAttributes.add(new Attributs("Power_Crit_Percent_Bonus#Barbarian_Whirlwind","Increases Critical Hit Chance of Whirlwind by X%"));
		itemAttributes.add(new Attributs("Power_Crit_Percent_Bonus#Barbarian_Revenge","Increases Critical Hit Chance of Revenge by X%"));
		itemAttributes.add(new Attributs("Power_Crit_Percent_Bonus#Barbarian_Overpower","Increases Critical Hit Chance of Overpower by X%"));
		itemAttributes.add(new Attributs("Power_Crit_Percent_Bonus#Barbarian_SeismicSlam","Increases Critical Hit Chance of Seismic Slam by X%"));
		itemAttributes.add(new Attributs("Item_Indestructible","Ignores Lost Durability"));
		itemAttributes.add(new Attributs("Gold_Find","+X% Extra Gold from Monsters"));
		itemAttributes.add(new Attributs("Damage_Percent_Bonus_Vs_Elites","Increases Damage against Elites by X%"));
		itemAttributes.add(new Attributs("Item_Power_Passive#ItemPassive_Unique_Ring_024","Chance to launch an explosive ball of Hellfire when you attack."));
		
		
		itemAttributes.add(new Attributs("Power_Cooldown_Reduction_Percent_All","X BLALALBLA"));
		itemAttributes.add(new Attributs("Season","Season X "));
		itemAttributes.add(new Attributs("Attribute_Set_Item_Discount","X BLALALBLA"));
		itemAttributes.add(new Attributs("Item_Legendary_Item_Base_Item","X BLALALBLA"));
		itemAttributes.add(new Attributs("Item_LegendaryItem_Level_Override","X BLALALBLA"));
		itemAttributes.add(new Attributs("Durability_Max_Before_Reforge","X BLALALBLA"));
		
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
