package com.sdfteam.d3armory.service.util;

import java.util.HashMap;
import java.util.Map;

import org.armory.d3.beans.Item;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.beans.SkillRune;

public class BuffSkill {

	private static Map<String,MinMaxBonus> buffs; 
		
	public static Map<String,MinMaxBonus> getBuff(SkillRune a, Map<EnumerationStuff,Item> stuffs) {
		
		buffs = new HashMap<String, MinMaxBonus>();
		
		if(a==null)
			return buffs;
		
		if(a.getSkill()==null)
			return buffs;
		
// BARBARE
		if(a.getSkill().getId().equals("battle-rage"))
		{
			buffs.put("Crit_Percent_BUFF_"+a, new MinMaxBonus(0.03));
			buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.15));
		}
		if(a.getSkill().getId().equals("battle-rage-a"))
		{
			buffs.put("Crit_Percent_BUFF_"+a, new MinMaxBonus(0.03));
			buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.30));
		}

		if(a.getSkill().getId().equals("berserker-rage"))
		{
			buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.25));
		}
		
		if(a.getSkill().getId().equals("brawler"))
		{
			buffs.put("Damage_Weapon_Percent_Bonus#BUFF_"+a, new MinMaxBonus(0.30));
		}
		if(a.getSkill().getId().equals("ruthless"))
		{
			buffs.put("Crit_Damage_BUFF_"+a, new MinMaxBonus(0.5));
			buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.05));
		}
		if(a.getSkill().getId().equals("weapons-master"))
		{
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Sword")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Dagger"))
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.015));
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Mace")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Axe"))
				buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.1));
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Polearm")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Spear"))
				buffs.put("Attacks_Per_Second_Percent_BUFF_"+a, new MinMaxBonus(0.1));
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("MightyWeapon"))
				buffs.put("Attacks_Per_Second_Percent_BUFF_"+a, new MinMaxBonus(0.1));

		}
		if(a.getSkill().getId().equals("wrath-of-the-berserker"))
		{
			buffs.put("Attacks_Per_Second_Percent_BUFF_"+a, new MinMaxBonus(0.25));
			buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.1));
			buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.1));
			buffs.put("Movement_Scalar_BUFF_"+a, new MinMaxBonus(0.2));
			//TODO ADD dodge
		}
		
		if(a.getSkill().getId().equals("wrath-of-the-berserker-d"))//INSANITY
		{
			buffs.put("Attacks_Per_Second_Percent_BUFF_"+a, new MinMaxBonus(0.25));
			buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.1));
			buffs.put("Movement_Scalar_BUFF_"+a, new MinMaxBonus(0.2));
			buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(1));
			//TODO ADD dodge
		}
		
		if(a.getSkill().getId().equals("war-cry"))
		{
			buffs.put("Armor_BUFF_"+a, new MinMaxBonus(0.20));
			buffs.put("Resistance_All_BUFF_"+a, new MinMaxBonus(20));
		}


		if(a.getSkill().getId().equals("tough-as-nails"))
		{
			buffs.put("Armor_BUFF_"+a, new MinMaxBonus(0.25));
			//TODO ADD Epine damage
		}
		
		
		
		/*
		case "FOCUSEDMIND" : buffs.put("Attacks_Per_Second_Item_Bonus_BUFF"+skill, new MinMaxBonus(0.03));
			break;
		case ANATOMY: buffs.put("Crit_Percent_Bonus"+skill, new MinMaxBonus(0.03));
			break;
			
		case ARCHERY: {
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Crossbow"))
					buffs.put("Crit_Damage_BUFF_"+skill, new MinMaxBonus(0.5));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("HandXbow"))
					buffs.put("Crit_Percent_BUFF_"+skill, new MinMaxBonus(0.1));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Bow"))
					buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+skill, new MinMaxBonus(0.03));
			}
	*/
		return buffs;
	}
	
	
}