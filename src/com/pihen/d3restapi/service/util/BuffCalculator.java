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
		
		if(a.getSkill().getId().equals("vigilant"))
		{
				for(ELEMENTS e: ELEMENTS.values())
				{
					if(!e.equals(ELEMENTS.Physical))
						if(!e.equals(ELEMENTS.Holy))
							buffs.put("Damage_Percent_Reduction_From_Type#"+e+PREFIX+a,new MinMaxBonus(0.20));
				}
				buffs.put("Hitpoints_Regen_Per_Second"+PREFIX+a,new MinMaxBonus(2063));
				
		}
		
		if(a.getSkill().getId().equals("righteousness"))
		{
			buffs.put("Resource_Max_Bonus#Faith"+PREFIX+a,new MinMaxBonus(30));
			//buffs.put("Resource_Generation#Faith"+PREFIX+a,new MinMaxBonus(3));
		}
		
		if(a.getSkill().getId().equals("insurmontable"))
		{
			//buffs.put("Resource_Generation#Faith"+PREFIX+a,new MinMaxBonus(6));
		}
		
		if(a.getSkill().getId().equals("fanaticism"))
		{
			if(sc.getHero().getSkills().getActive().get(0).getSkill().getId().equals("punish") || 
					sc.getHero().getSkills().getActive().get(0).getSkill().getId().equals("slash") ||
						sc.getHero().getSkills().getActive().get(0).getSkill().getId().equals("smite"))
								buffs.put("Attacks_Per_Second_Percent"+PREFIX+a, new MinMaxBonus(0.15));
		}
		
		if(a.getSkill().getId().equals("indestructible"))
		{
			//buffs.put("Hitpoints_On_Kill"+PREFIX+a, new MinMaxBonus(82526));
			//buffs.put("Damage_Weapon_Percent_Bonus#Physical"+PREFIX+a, new MinMaxBonus(0.35));
		}
		
		if(a.getSkill().getId().equals("holy-cause"))
		{
			//buffs.put("Hitpoint_Regeneration_By_Type_Damage#Holy"+PREFIX+a, new MinMaxBonus(0.01));
			buffs.put("Damage_Weapon_Percent_Bonus#Physical"+PREFIX+a, new MinMaxBonus(0.10));
		}
		
		if(a.getSkill().getId().equals("wrathful"))
		{
			buffs.put("Spending_Resource_Heals_Percent#Faith"+PREFIX+a, new MinMaxBonus(1341));
			double healthGlobBonus=sc.filter("Health_Globe_Bonus_Health",null);
			buffs.put("Heal_Bonus"+PREFIX+a, new MinMaxBonus(1+(healthGlobBonus/100)));
		}
		
		if(a.getSkill().getId().equals("divine-fortress"))
		{
			double bonus=0;
			if(sc.getStuffs().get(EnumerationStuff.OFF_HAND)!=null)
				if(sc.getStuffs().get(EnumerationStuff.OFF_HAND).isShield())
					bonus=sc.getStuffs().get(EnumerationStuff.OFF_HAND).getRealBlockChance();
			double armor = sc.getArmor();
			buffs.put("Armor_Item"+PREFIX+a, new MinMaxBonus(armor*bonus));
			
		}
		if(a.getSkill().getId().equals("lord-commander"))
		{
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Crusader_SteedCharge"+PREFIX+a, new MinMaxBonus(0.25));
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Crusader_Bombardment"+PREFIX+a, new MinMaxBonus(0.35));
			buffs.put("Power_Damage_Percent_Bonus#X1_Crusader_Phalanx"+PREFIX+a, new MinMaxBonus(0.20));
		}
		if(a.getSkill().getId().equals("hold-your-ground"))
		{
			buffs.put("Increase_Dodge_Percent"+PREFIX+a, new MinMaxBonus(0-sc.getDodge()));
			buffs.put("Block_Chance_Bonus_Item"+PREFIX+a, new MinMaxBonus(0.15));
		}
		if(a.getSkill().getId().equals("long-arm-of-the-law"))
		{
		}
		if(a.getSkill().getId().equals("iron-maiden"))
		{
			double val=sc.getThorns()*0.50;
			buffs.put("Thorns_Fixed#Physical"+PREFIX+a,new MinMaxBonus(val));
		}
		if(a.getSkill().getId().equals("renewal"))
		{
			//buffs.put("Hitpoints_On_Block"+PREFIX+a, new MinMaxBonus(12379));
			
		}
		if(a.getSkill().getId().equals("blunt"))
		{
			buffs.put("Power_Damage_Percent_Bonus#X1_Crusader_justice"+PREFIX+a, new MinMaxBonus(0.20));
			buffs.put("Power_Damage_Percent_Bonus#X1_Crusader_blessedhammer"+PREFIX+a, new MinMaxBonus(0.20));
		}
		if(a.getSkill().getId().equals("towering-shield"))
		{
			buffs.put("Power_Damage_Percent_Bonus#X1_Crusader_punish"+PREFIX+a, new MinMaxBonus(0.20));
			buffs.put("Power_Damage_Percent_Bonus#X1_Crusader_shieldbash"+PREFIX+a, new MinMaxBonus(0.20));
			buffs.put("Power_Damage_Percent_Bonus#X1_Crusader_blessedshield"+PREFIX+a, new MinMaxBonus(0.20));
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Crusader_Shieldglare"+PREFIX+a, new MinMaxBonus(0.30));
			
		}

// BARBARE
		
		if(a.getSkill().getId().equals("pound-of-flesh"))
		{
			double met = sc.filter("Health_Globe_Bonus_Health",null);
			buffs.put("Health_Globe_Bonus_Health"+PREFIX+a, new MinMaxBonus(met*0.50));
	
		}
		if(a.getSkill().getId().equals("ruthless"))
		{
			buffs.put("Damage_Weapon_Percent_Bonus#Physical"+PREFIX+a, new MinMaxBonus(0.40));
		}
		
		if(a.getSkill().getId().equals("nervesofsteel"))
		{
			double bonus=0.50;
			double vita = sc.getVitality();
			buffs.put("Armor_Item"+PREFIX+a, new MinMaxBonus(vita*bonus));
		}
		
		if(a.getSkill().getId().equals("weapons-master"))
		{
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND)!=null)
			{
				if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Sword")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Dagger"))
					buffs.put("Damage_Weapon_Percent_Bonus#Physical"+PREFIX+a, new MinMaxBonus(0.08));
				else if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Mace")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Axe"))
					buffs.put("Crit_Percent_Bonus"+PREFIX+a, new MinMaxBonus(0.05));
				else if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Polearm")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Spear"))
					buffs.put("Attacks_Per_Second_Percent"+PREFIX+a, new MinMaxBonus(0.8));
				else if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("MightyWeapon"))
					buffs.put("Resource_Heals_Percent_On_Hit",new MinMaxBonus(2));//mighty
			}
		}		
		if(a.getSkill().getId().equals("berserker-rage")){
			//buffs.put("Damage_Weapon_Percent_Bonus#Physical"+PREFIX+a, new MinMaxBonus(0.40));
		}
		if(a.getSkill().getId().equals("bloodthirst")){
			buffs.put("Spending_Resource_Heals_Percent#Fury"+PREFIX+a, new MinMaxBonus(578));
			double met = sc.filter("Health_Globe_Bonus_Health",null);
			buffs.put("Hitpoints_On_Skills"+PREFIX+a, new MinMaxBonus(met*0.01));
		}
		if(a.getSkill().getId().equals("animosity")){
			//fury generation
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
		if(a.getSkill().getId().equals("tough-as-nails"))
		{
			buffs.put("Armor"+PREFIX+a, new MinMaxBonus(sc.getArmor()*.25));
		}
		
		if(a.getSkill().getId().equals("no-escape"))
		{
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Barbarian_WeaponThrow"+PREFIX+a, new MinMaxBonus(0.25));
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Barbarian_AncientSpear"+PREFIX+a, new MinMaxBonus(0.25));
		}
		
		if(a.getSkill().getId().equals("relentless"))
		{
			//
		}
		
		if(a.getSkill().getId().equals("brawler"))
		{
			//buffs.put("Damage_Weapon_Percent_Bonus#Physical"+PREFIX+a, new MinMaxBonus(0.20));
		}
		
		if(a.getSkill().getId().equals("juggernaut"))
		{
			//
		}
		
		if(a.getSkill().getId().equals("unforgiving"))
		{
			//
		}
		
		if(a.getSkill().getId().equals("boon-of-bulkathos"))
		{
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Barbarian_Earthquake"+PREFIX+a, new MinMaxBonus(0.15));
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Barbarian_Calloftheancient"+PREFIX+a, new MinMaxBonus(0.30));
			buffs.put("Power_Cooldown_Reduction_Percent#X1_Barbarian_Wrathoftheberserker"+PREFIX+a, new MinMaxBonus(0.30));
		}
		if(a.getSkill().getId().equals("earthen-might"))
		{
		}
		if(a.getSkill().getId().equals("earthen-might"))
		{
		}
		if(a.getSkill().getId().equals("sword-and-board"))
		{
		}
		
		if(a.getSkill().getId().equals("rampage"))
		{
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
		if(a.getSkill().getId().equals("glass-cannon"))
		{
			buffs.put("Damage_Weapon_Percent_Bonus#Physical"+PREFIX+a, new MinMaxBonus(0.15));
			buffs.put("Armor_Item"+PREFIX+a, new MinMaxBonus(1- (sc.getArmor()*0.10)));
			for(ELEMENTS e: ELEMENTS.values())
			{
				if(!e.equals(ELEMENTS.Holy))
					buffs.put("Resistance#"+e+PREFIX+a,new MinMaxBonus(1- (sc.getResistance(e)*0.10)));
			}
			
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
			
		}
		
		if(a.getSkill().getId().equals("harmony"))
		{
			double multi=0.40;
			for(Item i : sc.getAllItems())
			{
				
				if(i!=null)
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
			
			

		
		/*COMPANION
		case "FOCUSEDMIND" : buffs.put("Attacks_Per_Second_Item_Bonus_BUFF"+skill, new MinMaxBonus(0.03));
			break;
		case ANATOMY: buffs.put("Crit_Percent_Bonus"+skill, new MinMaxBonus(0.03));
			break;
			
		
	*/
		
	//TODO DemonHunter / Witch Doctor / monk Skills
		
		
		return buffs;
	}
	
}

