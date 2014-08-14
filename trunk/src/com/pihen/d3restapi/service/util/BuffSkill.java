package com.pihen.d3restapi.service.util;

import java.util.HashMap;
import java.util.Map;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.beans.SkillRune;
import com.pihen.d3restapi.service.util.StuffCalculator.ELEMENTS;

public class BuffSkill {
	public final static String PREFIX="_SKILLS_";
	private static Map<String,MinMaxBonus> buffs; 
		
	public static Map<String,MinMaxBonus> getBuff(SkillRune a, StuffCalculator sc) {
		
		buffs = new HashMap<String, MinMaxBonus>();
		
		if(a==null)
			return buffs;
		
		if(a.getSkill()==null)
			return buffs;
		
		
//CRUSADER
		if(a.getSkill().getId().equals("fervor"))
		{
			if(sc.getNbWeapon()<2)
			{
				buffs.put("Attacks_Per_Second_Percent"+PREFIX+a, new MinMaxBonus(0.15));
				buffs.put("Power_Cooldown_Reduction_Percent_All"+PREFIX+a, new MinMaxBonus(0.15));
			}
		}
		
		
		
		if(a.getSkill().getId().equals("finery"))
		{
			
			double gem = 0;
			double strength=sc.getPrimaryStatUnbuffedValue();
			for(Item i : sc.getAllItems())
			{
				if(i!=null)
				for(Gem g : i.getGems())
			
					if(g.getItem()!=null)
						gem=gem+1;
			}
			gem = gem*(1.5/100);
			buffs.put("Strength"+PREFIX+a, new MinMaxBonus(strength*gem));
		}
		
				
//WIZARD
		if(a.getSkill().getId().equals("evocation"))
		{
			buffs.put("Power_Cooldown_Reduction_Percent_All"+PREFIX+a, new MinMaxBonus(0.20));
		}
		if(a.getSkill().getId().equals("blur"))
		{
			buffs.put("Decrease_Damage_All"+PREFIX+a, new MinMaxBonus(0.17));
		}

		
		
//MONK
		
		if(a.getSkill().getId().equals("beacon-of-ytar"))
		{
			buffs.put("Power_Cooldown_Reduction_Percent_All"+PREFIX+a, new MinMaxBonus(0.20));
		}
		if(a.getSkill().getId().equals("sixth-sense"))
		{
			double critChance = sc.getCritChance()*100;
			double multi = 42.5;
			buffs.put("Increase_Dodge_Percent"+PREFIX+a, new MinMaxBonus((critChance*multi)/100));
		}
		if(a.getSkill().getId().equals("seize-the-initiative"))
		{
			double dext = sc.getPrimaryStatUnbuffedValue();
			double multi = 30;
			buffs.put("Armor"+PREFIX+a, new MinMaxBonus((dext*multi)/100));
		}
		if(a.getSkill().getId().equals("one-with-everything"))
		{
			double val=0;
			double max=val;
			ELEMENTS maxE=null;
			for(ELEMENTS e:ELEMENTS.values())
			{	
				val = sc.getResistance(e);
				if(val>max)
				{
					max=val;
					maxE=e;
				}
			}
			
			for(ELEMENTS e:ELEMENTS.values())
			{	
				if(e!=maxE)
					buffs.put("Resistance#"+e+PREFIX+a, new MinMaxBonus(sc.getResistance(maxE)-sc.getResistance(e)));
			}
			
			
		}
		
		
		/*		
// BARBARE
		/*
		if(a.getSkill().getId().equals("weapons-master"))
		{
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Sword")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Dagger"))
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.08));
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Mace")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Axe"))
				buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.05));
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Polearm")||stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Spear"))
				buffs.put("Attacks_Per_Second_Percent_BUFF_"+a, new MinMaxBonus(0.8));
		}
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
		
		//DEMON HUNTER 
		if(a.getSkill().getId().equals("archery"))
		{
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Crossbow"))
				buffs.put("Crit_Damage_Percent_BUFF_"+a, new MinMaxBonus(0.5));
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("HandXbow"))
				buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.1));
			if(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Bow"))
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.03));
		}
	
		/*
		 * DEMON HUNTER
		 *  if(a.getSkill().getId().equals("tactical-advantage")
			if(a.getSkill().getId().equals("perfectionist")
			if(a.getSkill().getId().equals("night-stalker")
			
		   WIZARD
		 *  if(a.getSkill().getId().equals("critical-mass)
			if(a.getSkill().getId().equals("paralysis)
			if(a.getSkill().getId().equals("cold-blooded)
		 * 
		 * 
		 *WITCH DOCTOR
		 * if(a.getSkill().getId().equals("circle-of-life)
		   if(a.getSkill().getId().equals("grave-injustice)
		   if(a.getSkill().getId().equals("gruesome-feast)

		 * MONK
		 *  if(a.getSkill().getId().equals(combination-strike)
			if(a.getSkill().getId().equals(one-with-everything)
			if(a.getSkill().getId().equals(seize-the-initiative)
		 * 
		 * */
		
		
		
		/*COMPANION
		case "FOCUSEDMIND" : buffs.put("Attacks_Per_Second_Item_Bonus_BUFF"+skill, new MinMaxBonus(0.03));
			break;
		case ANATOMY: buffs.put("Crit_Percent_Bonus"+skill, new MinMaxBonus(0.03));
			break;
			
		
	*/
		return buffs;
	}
	
	
}
