package org.armory.d3.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.armory.d3.Main;
import org.armory.d3.beans.Gem;
import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.LegendarySet;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.beans.Ranks;

import com.sdfteam.d3armory.service.util.EnumerationStuff;

public class DPSCalculator {

	private Map<EnumerationStuff,Item> stuff;
	private Map<String,Double> weaponDefaultAS;
	private Hero hero;
	
	public DPSCalculator(Hero h) {
					weaponDefaultAS=new HashMap<String,Double>();
					weaponDefaultAS.put("Axe", 1.30);
					weaponDefaultAS.put("HandXbow", 1.60);
					weaponDefaultAS.put("Dagger", 1.50);
					weaponDefaultAS.put("Mace", 1.20);
					weaponDefaultAS.put("FistWeapon", 1.40);
					weaponDefaultAS.put("MightyWeapon1H", 1.30);
					weaponDefaultAS.put("Spear", 1.20);
					weaponDefaultAS.put("Sword", 1.40);
					weaponDefaultAS.put("CeremonialDagger",1.4);
					weaponDefaultAS.put("Wand", 1.40);
					weaponDefaultAS.put("Axe2H", 1.00);
					weaponDefaultAS.put("Bow", 1.40);
					weaponDefaultAS.put("Daibo", 1.10);
					weaponDefaultAS.put("Crossbow", 1.10);
					weaponDefaultAS.put("Mace2H", 0.90);
					weaponDefaultAS.put("MightyWeapon2H", 1.00);
					weaponDefaultAS.put("Polearm", 0.95);
					weaponDefaultAS.put("Staff", 1.00);
					weaponDefaultAS.put("Sword2H", 1.1);
					weaponDefaultAS.put("none", 0.0);
					stuff=new HashMap<EnumerationStuff,Item>();
					this.hero=h;
		}

	public void addItemStuff(EnumerationStuff cle,Item i)
	{
		stuff.put(cle, i);
	}
	
	public int nbWeapon()
	{
		int nbWeapon=0;
		
		if(stuff.get(EnumerationStuff.MAIN_HAND)!=null)
			nbWeapon=1;
		
		if(stuff.get(EnumerationStuff.OFF_HAND)!=null)
			if(stuff.get(EnumerationStuff.OFF_HAND).isWeapon())
				nbWeapon=nbWeapon+1;
		
		return nbWeapon;
	}
		
	public double getStat(List<Item> listes,String stat,String elementfilter)
	{
		double total =0;
		for(Item it:listes)
		{
			total+=getStat(it, stat, elementfilter);
		}
		return total;
	}
	
	public double getStat(Item i,String stat,String elementfilter) {
		double total=0.0;
		
		Map<String, MinMaxBonus> attributes = i.getAttributesRaw();
		Iterator<String> keyIt = attributes.keySet().iterator();
		while(keyIt.hasNext())
		{
			String k = keyIt.next();
			
			if(k.startsWith(stat)||stat.equals(""))
			{
				
				if(elementfilter==null)
				{
					total=total+ attributes.get(k).getMoyenne();
				}
				else if(k.contains(elementfilter))
				{
					total=total+ attributes.get(k).getMoyenne();
				}
			}
		}
		return total;
	}
	
	public List<Item> getArmor()

	{
		List<Item> items = new ArrayList<Item>();
		
		for(EnumerationStuff e :EnumerationStuff.values())
			items.add(stuff.get(e));
		
			items.remove(EnumerationStuff.MAIN_HAND);
		if(nbWeapon()==2)
			items.remove(EnumerationStuff.OFF_HAND);
		
		return items;
	}
	
	public List<Item> getWeapons()
	{
			List<Item> items = new ArrayList<Item>();
			
			items.add(stuff.get(EnumerationStuff.MAIN_HAND));
			if(nbWeapon()==2)
				items.add(stuff.get(EnumerationStuff.OFF_HAND));
			
		return items;
	}
	
	public List<Item> getAll()
	{
		List<Item> items = new ArrayList<Item>();
		for(EnumerationStuff e :EnumerationStuff.values())
			items.add(stuff.get(e));

		return items;
	}

	public double getDPS() {
		double dps=0;
		double bonusDual=0;
		double bonus=0;
		double stat_base = getStat(getAll(), hero.getPrimaryStat(), null);
		
		if(nbWeapon()==2)
			bonusDual=0.15;
			
		double bonusArmor = getStat(getArmor(), "Attacks_Per_Second_Percent", null);
		double bonusWeapon = getStat(getWeapons(), "Attacks_Per_Second_Item_Bonus", null);
		
		double mainBase= this.weaponDefaultAS.get(stuff.get(EnumerationStuff.MAIN_HAND).getType().getId());
		double offBase= this.weaponDefaultAS.get(stuff.get(EnumerationStuff.OFF_HAND).getType().getId());
		
		System.out.println(hero.getPrimaryStat() + " " + stat_base);
		System.out.println("BONUS ARMOR: " + bonusArmor*100 +"%");
		System.out.println("BONUS WEAPON: " + bonusWeapon);
		
		double attackSpeedMain=(1+bonusArmor+bonusDual)*(mainBase*1+bonus+bonusWeapon);
		double attackSpeedOff=(1+bonusArmor+bonusDual)*(offBase*1+bonus+bonusWeapon);
		
		System.out.println("ATTACKS PER SEC MH : " + attackSpeedMain);
		System.out.println("ATTACKS PER SEC OH " + attackSpeedOff);
		return dps;
	}
	
	}