package com.pihen.d3restapi.service.util;

import java.util.Iterator;
import java.util.Map;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;


public class StuffCalculator2 
{
	
	private Map<EnumerationStuff, Item> stuff;
	private Hero h;
	
	/*S =	Primary Attribute Bonus
			C = Critical Hit and Critical Hit Damage
			R = Attacks per Second
			A = Average Hit
			M = Damage Modifiers (bonus)
			
			
			*/
	
	
	public StuffCalculator2(Map<EnumerationStuff, Item> stuff,Hero h) {
		this.stuff = stuff;
		this.h=h;
	}
	
	
	public double getPrimaryAttributeBonus_S()
	{
		return h.getPrimaryStatValue();
	}
	
	public double getCriticalHitChance()
	{
		return 0;
	}
	
	public double getCriticalHitDamage()
	{
		return 0;
	}

	public double getAS()
	{
		return 0;
	}

	
	
	
	

	
}
