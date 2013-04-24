package com.sdfteam.d3armory.service.util;

import java.util.HashMap;
import java.util.Map;

import org.armory.d3.beans.Item;
import org.armory.d3.beans.MinMaxBonus;

public class BuffSkill {

	private static Map<String,MinMaxBonus> buffs; 
		
	public static Map<String,MinMaxBonus> getBuff(EnumerationBuffs b,Map<EnumerationStuff,Item> stuffs) {
		
		buffs = new HashMap<String, MinMaxBonus>();
		switch (b) {
		case FOCUSEDMIND : buffs.put("Attacks_Per_Second_Item_Bonus_"+b, new MinMaxBonus(0.03));
			break;
		case ANATOMY: buffs.put("Crit_Percent_Bonus"+b, new MinMaxBonus(0.03));
			break;
		case ARCHERY: {
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().equals("Crossbow"))
					buffs.put("Crit_Damage_"+b, new MinMaxBonus(0.5));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().equals("HandXbow"))
					buffs.put("Crit_Percent_"+b, new MinMaxBonus(0.1));
				if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().equals("Bow"))
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
			break;
		case SHARPSHOOTER:
			break;
		case STEADYAIM:
			break;
		case WEAPONMASTER:
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
	
	
	
	
	
	
}
