package com.pihen.d3restapi.service.util;

import java.util.HashMap;
import java.util.Map;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.beans.SkillRune;
import com.pihen.d3restapi.service.util.StuffCalculator.ELEMENTS;

public class BuffCalculator {

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
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND)!=null)
				if(!sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getTwoHanded())
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
			buffs.put("Damage_Percent_Reduction_From_All"+PREFIX+a, new MinMaxBonus(0.17));
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
		
		if(a.getSkill().getId().equals("harmony")) //2.1 test
		{
			double multi=0.40;
			
			for(Item i : sc.getAllItems())
			{
				for(ELEMENTS e : ELEMENTS.values())
				{
					String k="Resistance#"+e;
					
					if(i.getAttributesRaw().containsKey(k))
					{
						double val = i.getAttributesRaw().get(k).getMoyenne();
						buffs.put("Resistance_all"+PREFIX+i.getName(),new MinMaxBonus(val*multi));
					}
				}
			}
			
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
		
		
			
// BARBARE
		
		if(a.getSkill().getId().equals("weapons-master"))
		{
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND)!=null){
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Sword")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Dagger"))
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.08));
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Mace")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Axe"))
				buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.05));
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Polearm")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Spear"))
				buffs.put("Attacks_Per_Second_Percent_BUFF_"+a, new MinMaxBonus(0.8));
			}
		}
		if(a.getSkill().getId().equals("tough-as-nails"))
		{
			buffs.put("Armor"+PREFIX+a, new MinMaxBonus(sc.getArmor()*.25));
		}
		if(a.getSkill().getId().equals("superstition"))
		{
			for(ELEMENTS e: ELEMENTS.values())
			{
				if(!e.equals(ELEMENTS.Physical))
					if(!e.equals(ELEMENTS.Holy))
						buffs.put("Damage_Percent_Reduction_From_Type#"+e+PREFIX+a,new MinMaxBonus(0.20));
			}
		}
		
		
		/*COMPANION
		case "FOCUSEDMIND" : buffs.put("Attacks_Per_Second_Item_Bonus_BUFF"+skill, new MinMaxBonus(0.03));
			break;
		case ANATOMY: buffs.put("Crit_Percent_Bonus"+skill, new MinMaxBonus(0.03));
			break;
			
		
	*/
		return buffs;
	}
	
}
