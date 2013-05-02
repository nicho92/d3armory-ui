package com.sdfteam.d3armory.service.util;

import java.util.HashMap;
import java.util.Map;

import org.armory.d3.beans.Item;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.beans.SkillRune;

public class BuffSkill {

	private static Map<String,MinMaxBonus> buffs; 
		
	public static Map<String,MinMaxBonus> getBuff(EnumerationBuffs skill,Map<EnumerationStuff,Item> stuffs) {
		
		buffs = new HashMap<String, MinMaxBonus>();
		
		
		
		if(skill==null)
			return buffs;
			
		switch (skill) 
		{
		case FOCUSEDMIND : buffs.put("Attacks_Per_Second_Item_Bonus_BUFF"+skill, new MinMaxBonus(0.03));
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
			break;
		case BATTLERAGE:
				buffs.put("Crit_Percent_BUFF_"+skill, new MinMaxBonus(0.03));
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+skill, new MinMaxBonus(0.15));
			break;
		case BATTLERAGE_MARAUDER:
			break;
		case BERSERKERRAGE:
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+skill, new MinMaxBonus(0.25));
			break;
		case BLINDINGFLASH_FAITTHELIGHT:
			break;
		case BRAWLER:
			break;
		case BREATHTHEHEAVEN:
			break;
		case ENERGYARMOR:
			break;
		case FAMILIAR_SPARK:
			break;
		case GLASSECANNON:
			break;
		case MAGICWEAPON:
			break;
		case MAGICWEAPON_FORCEWEAPON:
			break;
		case MANTRAOFRETRIBUTION_TRANSGRESSION:
			break;
		case PIERCETHEVEIL:
			break;
		case PINPOINT:
			break;
		case RUTHLESS:
				buffs.put("Crit_Damage_BUFF_"+skill, new MinMaxBonus(0.5));
				buffs.put("Crit_Percent_Bonus_BUFF_"+skill, new MinMaxBonus(0.05));
			break;
		case SHARPSHOOTER:
			break;
		case STEADYAIM:
			break;
		case WEAPONMASTER:
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Sword")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Dagger"))
					buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+skill, new MinMaxBonus(0.015));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Mace")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Axe"))
					buffs.put("Crit_Percent_Bonus_BUFF_"+skill, new MinMaxBonus(0.1));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Polearm")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Spear"))
					buffs.put("Attacks_Per_Second_Percent_BUFF_"+skill, new MinMaxBonus(0.1));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("MightyWeapon"))
					buffs.put("Attacks_Per_Second_Percent_BUFF_"+skill, new MinMaxBonus(0.1));
			break;
		case WRATHOFTHEBERSERKER:
				buffs.put("Crit_Percent_Bonus_BUFF_"+skill, new MinMaxBonus(0.1));
				buffs.put("Movement_Scalar_BUFF_"+skill, new MinMaxBonus(0.2));
				//TODO ADD dodge
			break;
		case WRATHOFTHEBERSERKER_INSANITY:
				buffs.put("Crit_Percent_Bonus_BUFF_"+skill, new MinMaxBonus(0.1));
				buffs.put("Movement_Scalar_BUFF_"+skill, new MinMaxBonus(0.2));
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+skill, new MinMaxBonus(1));
				//TODO ADD dodge
			break;
			
		case WARCRY:
			buffs.put("Armor_BUFF_"+skill, new MinMaxBonus(0.20));
			buffs.put("Resistance_All_BUFF_"+skill, new MinMaxBonus(20));
			break;
		
	
			
		default:
			break;
		}
		
		return buffs;
	}
	
	public static Map<String,MinMaxBonus> getBuff(SkillRune s, Map<EnumerationStuff,Item> stuffs) {
		return getBuff(convert(s), stuffs);
	}
	
	
	public static EnumerationBuffs convert(SkillRune a)
	{
		System.out.println("CONVERT " + a);
		if(a.getSkill().getId().equals("ruthless"))
			return EnumerationBuffs.RUTHLESS;
		
		if(a.getSkill().getId().equals("weapons-master"))
			return EnumerationBuffs.WEAPONMASTER;
		
		if(a.getSkill().getId().equals("wrath-of-the-berserker"))
			return EnumerationBuffs.WRATHOFTHEBERSERKER;
		
		if(a.getSkill().getId().equals("battle-rage"))
			return EnumerationBuffs.BATTLERAGE;
		
		if(a.getSkill().getId().equals("war-cry"))
			return EnumerationBuffs.WARCRY;
		
		if(a.getSkill().getId().equals("berserker-rage"))
			return EnumerationBuffs.BERSERKERRAGE;
		
		return null;
			
	}
	
	
	
	
}
