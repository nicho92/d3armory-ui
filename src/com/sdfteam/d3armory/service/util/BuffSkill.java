package com.sdfteam.d3armory.service.util;

import java.util.HashMap;
import java.util.Map;

import org.armory.d3.beans.Item;
import org.armory.d3.beans.MinMaxBonus;

public class BuffSkill {

	private static Map<String,MinMaxBonus> buffs; 
		
	public static Map<String,MinMaxBonus> getBuff(EnumerationBuffs b,Map<EnumerationStuff,Item> stuffs) {
		
		buffs = new HashMap<String, MinMaxBonus>();
		
		if(b==null)
			return buffs;
			
		switch (b) {
		case FOCUSEDMIND : buffs.put("Attacks_Per_Second_Item_Bonus_"+b, new MinMaxBonus(0.03));
			break;
		case ANATOMY: buffs.put("Crit_Percent_Bonus"+b, new MinMaxBonus(0.03));
			break;
		case ARCHERY: {
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Crossbow"))
					buffs.put("Crit_Damage_"+b, new MinMaxBonus(0.5));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("HandXbow"))
					buffs.put("Crit_Percent_"+b, new MinMaxBonus(0.1));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Bow"))
					buffs.put("Damage_Weapon_Percent_Bonus#Physical_"+b, new MinMaxBonus(0.03));
			}
			break;
		case BATTLERAGE:
			break;
		case BATTLERAGE_MARAUDER:
			break;
		case BERSERKERRAGE:
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
				buffs.put("Crit_Damage_"+b, new MinMaxBonus(0.5));
				buffs.put("Crit_Percent_Bonus_"+b, new MinMaxBonus(0.05));
			break;
		case SHARPSHOOTER:
			break;
		case STEADYAIM:
			break;
		case WEAPONMASTER:
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Sword")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Dagger"))
					buffs.put("Damage_Weapon_Percent_Bonus#Physical_"+b, new MinMaxBonus(0.015));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Mace")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Axe"))
					buffs.put("Crit_Percent_Bonus_"+b, new MinMaxBonus(0.1));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Polearm")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Spear"))
					buffs.put("Attacks_Per_Second_Percent_"+b, new MinMaxBonus(0.1));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("MightyWeapon"))
					buffs.put("Attacks_Per_Second_Percent_"+b, new MinMaxBonus(0.1));
			break;
		case WRATHOFTHEBERSERKER:
			break;
		case WRATHOFTHEBERSERKER_INSANITY:
			break;
		default:
			break;
		}
		
		return buffs;
	}
	
	public static EnumerationBuffs convert(String a)
	{
		if(a.equals("ruthless"))
			return EnumerationBuffs.RUTHLESS;
		if(a.equals("weapons-master"))
			return EnumerationBuffs.WEAPONMASTER;
		
		return null;
			
	}
	
	
	
	
}
