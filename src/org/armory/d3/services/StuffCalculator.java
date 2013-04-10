package org.armory.d3.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.armory.d3.Main;
import org.armory.d3.beans.Gem;
import org.armory.d3.beans.Hero;
import org.armory.d3.beans.HeroSkillContainer;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.LegendarySet;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.beans.Ranks;

import com.sdfteam.d3armory.service.util.EnumerationStuff;

public class StuffCalculator {
	private Map<EnumerationStuff,Item> stuffs;
	private HeroSkillContainer skills;
	private Map<String, MinMaxBonus> bonusItem;
	private Hero hero;
	private boolean twohanded;
	private Map<String,Double> weaponDefaultAS;
	int countweapon=0;
	
	public StuffCalculator(Map<EnumerationStuff,Item> stuff, Hero hero) {
		stuffs= new HashMap<EnumerationStuff,Item>();
		this.skills=hero.getSkills();
		this.hero= hero;
		Iterator<EnumerationStuff> keys = stuff.keySet().iterator();
		while(keys.hasNext())
		{
			EnumerationStuff key=keys.next();
			if(stuff.get(key)!=null)
				stuffs.put(key, stuff.get(key));
		
		}
		
		init();
		
	}

	public Map<String, MinMaxBonus> getBonusItem()
	{
		return bonusItem;
	}
	
	public void init()
	{
		bonusItem = new HashMap<>();
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
			
		Map<LegendarySet,Integer> piecesbyset=new HashMap<LegendarySet,Integer>();
		int compteur=0;
		
		
		for(Item i : stuffs.values())
		{
			if(i.isWeapon())
			{
				countweapon+=1;
				twohanded=i.getType().getTwoHanded();
			}
			
			if(i.isSetObjects())
			{
				piecesbyset.put(i.getSet(),LegendarySet.getStuffSetsNbPieces(stuffs.values(), i.getSet()));
			}
			
			//bonus de gems
			if(i.nbGems()>0)
			{
				Gem[] gems = i.getGems();
				for(int g=0;g<gems.length;g++)
				{
					Iterator<String> keysg = gems[g].getAttributesRaw().keySet().iterator();
					String cleg = keysg.next();
					compteur++;
					bonusItem.put(cleg +"_GEM_"+i.getName().replaceAll(" ", "-")+"_"+compteur, gems[g].getAttributesRaw().get(cleg));
				}
			}
			//fin des gems
			
			Iterator<String> keys = i.getAttributesRaw().keySet().iterator();
			while(keys.hasNext())
			{
				String cle = keys.next();
				compteur++;
				bonusItem.put(cle+"_"+i.getName().replaceAll(" ", "-"), i.getAttributesRaw().get(cle));
				String mainoff="";
				
				if(i.isWeapon())
				{
					if(i.isMainHand())
						mainoff="MAIN";
					else
						mainoff="OFF";
				
					bonusItem.put("Damage_DPS_Min_"+i.getName().replaceAll(" ", "-")+"_"+mainoff, i.getMinDamage());
					bonusItem.put("Damage_DPS_Max_"+i.getName().replaceAll(" ", "-")+"_"+mainoff, i.getMaxDamage());
					bonusItem.put("Damage_DPS_AttackSpeed_"+i.getName().replaceAll(" ", "-")+"_"+mainoff, i.getAttacksPerSecond());
				
				}
			}
			
		}//fin de boucle sur les items
		
		//on ajout les bonus de set
		Iterator<LegendarySet> it = piecesbyset.keySet().iterator();
		while(it.hasNext())
		{
			LegendarySet set = it.next();
			int nbpiece = piecesbyset.get(set);
			List<Ranks> actifsRanks = set.getRanksByPiece(nbpiece);
			for(Ranks r:actifsRanks)//pour chaque nbre piece du set (-1 car la premiere est inutile)
			{
				Iterator<String> keys = r.getAttributesRaw().keySet().iterator();
				while(keys.hasNext())
				{
					String k = keys.next();
					compteur++;
					bonusItem.put(k+"_SET_"+compteur, r.getAttributesRaw().get(k));
				}
			}
		}
		//fin des bonus de set
	}
	
	public List<Item> getArmor()

	{
		List<Item> items = new ArrayList<Item>();
		
		for(EnumerationStuff e :EnumerationStuff.values())
		{
			if(stuffs.get(e)!=null)
				items.add(stuffs.get(e));
		}
		
			items.remove(EnumerationStuff.MAIN_HAND);
		if(countweapon==2)
			items.remove(EnumerationStuff.OFF_HAND);
		
		return items;
	}
	
	public List<Item> getWeapons()
	{
			List<Item> items = new ArrayList<Item>();
			
			items.add(stuffs.get(EnumerationStuff.MAIN_HAND));
			if(countweapon==2)
				items.add(stuffs.get(EnumerationStuff.OFF_HAND));
			
		return items;
	}
	
	public List<Item> getAll()
	{
		List<Item> items = new ArrayList<Item>();
		for(EnumerationStuff e :EnumerationStuff.values())
			items.add(stuffs.get(e));

		return items;
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
	
	private double getStat(String stat,String elementfilter) {
		return getStat(stat, elementfilter,false);
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
	
	public double getStat(String stat,String elementfilter,boolean debug) {
		double total=0.0;
		Iterator<String> keyIt = bonusItem.keySet().iterator();
		
		while(keyIt.hasNext())
		{
			String k = keyIt.next();
			
			if(k.startsWith(stat)||stat.equals(""))
			{
				
				if(elementfilter==null)
				{
					if(debug)
						System.out.println(k + " " + bonusItem.get(k).getMoyenne());
					
					total=total+ bonusItem.get(k).getMoyenne();
				}
				else 
				if(k.contains(elementfilter))
				{
					if(debug)
						System.out.println(k + " " + bonusItem.get(k).getMoyenne());
					
					total=total+ bonusItem.get(k).getMoyenne();
				}
				
				
			}
		}
		
		return total;	
		}
	
	
	public double calculateUnbuffedDPS()
	{
		double bonusDual=(countweapon==2)?0.15:0;
		double chance_cc=0.05+getStat("Crit_Percent", null);
		double degat_cc=0.5+getStat("Crit_Damage", null);
		double stat_base=hero.getBaseStatPrimary()+getStat( hero.getPrimaryStat(),null);
		
		double damage_min =getStat("Damage_Min", null);
		double damage_max = damage_min+ getStat("Damage_Delta", null);
		
		System.out.println("NB Weapon : " + countweapon);
		System.out.println("Two Handed : " + twohanded);
		System.out.println("==========================================");
		System.out.println(hero.getPrimaryStat() +" : " + stat_base);
		
		
		//CALCUL attackSpeed 
		double attackPerSecondMain = stuffs.get(EnumerationStuff.MAIN_HAND).getAttacksPerSecond().getMoyenne();//getStat("Damage_DPS_Attack","MAIN");
		double attackPerSecondOff=0;
		if(countweapon==2)
			attackPerSecondOff = stuffs.get(EnumerationStuff.OFF_HAND).getAttacksPerSecond().getMoyenne();//getStat("Damage_DPS_Attack","OFF");
		
		double mainI=this.weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); //AS de base du type arme MAIN
		double offI=0;
		
		if(countweapon==2)
			offI=this.weaponDefaultAS.get(stuffs.get(EnumerationStuff.OFF_HAND).getType().getId()); //AS de base du type arme MAIN
		
		double bonusArmor = getStat(getArmor(), "Attacks_Per_Second_Percent", null);
		
		double bonusWeapon = getStat(getWeapons(), "Attacks_Per_Second_Item_Bonus", null);
		
		double compagnonBonus=0; // ou 0.3 pour l'enchanteresse
				
		double attackSpeedMain=(1+bonusArmor+bonusDual)*(mainI*1+compagnonBonus+bonusWeapon);
		
		double attackSpeedOff=0;
		if(countweapon==2)
			attackSpeedOff=(1+bonusArmor+bonusDual)*(offI*1+compagnonBonus+bonusWeapon);
		
		
		//CALCUL DAMAGE HIT
		double statDamage=1+(stat_base/100);
		double ccDamage=1+chance_cc*degat_cc;
		double minMaxDmg=damage_min+damage_max;
		
		double elementalDmgMin=getStat("Damage_Weapon_Min",null);
		double elementalDmgMax=getStat("Damage_Weapon_Min",null)+getStat("Damage_Weapon_Delta",null);
		
		//double weaponDmgMain=minMaxDmg/2+((getStat("Damage_DPS_Min","MAIN")+ getStat("Damage_DPS_Max","MAIN")))/2;
		double weaponDmgMain=minMaxDmg/2+(stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne())/2;
		
		
		double weaponDmgOff=0;
		
		if(countweapon==2)
			weaponDmgOff=minMaxDmg/2+(stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne())/2;
		
		double hitDmgMAIN=statDamage*ccDamage*weaponDmgMain;
		double hitDmgOFF=statDamage*ccDamage*weaponDmgOff;
		double hitDmg=0;

		double attackSpeedFinal=attackSpeedMain;
		if(countweapon==2)
			attackSpeedFinal=(attackSpeedMain + attackSpeedOff)/2;
		
		if(countweapon==2)
			hitDmg=(hitDmgMAIN+hitDmgOFF)/2;
		else
			hitDmg=hitDmgMAIN;
		
		
		
		System.out.println("+% Attack Speed : " + bonusArmor*100 +"%");
		System.out.println("Attack Speed MH "  + attackSpeedMain);
		
		if(countweapon==2)
			System.out.println("Attack Speed 0H "  + attackSpeedOff);
		
		System.out.println("Critical Hit Chance : " + chance_cc*100+"%");
		System.out.println("Critical Hit Damage : " + degat_cc*100+"%");
		System.out.println("MH Weapon Damage " + hitDmgMAIN);
		
		if(countweapon==2)
			System.out.println("OH Weapon Damage " + hitDmgOFF);
	
		
		double dps=getDamage(stat_base,chance_cc,degat_cc,1+bonusArmor,minMaxDmg,0);
		
		System.out.println(dps);
		
		
		return dps;
	}

	private double getDamage(double stat_base, double chance_cc,double ccDamage, double bonusAS, double minMaxDmg, int s) 
	{		//e, t, n, r, i, s
 			double u = 0; 
 	    	double a = 1 + stat_base / 100; 
 	    	double f = tempspd(bonusAS); //== 1.3333333
 	    	double l = tempdamageweaps(minMaxDmg); //1936.333333
 	    	double c = ccDamage; 
 	    	double h = chance_cc;
 	    	
 	    	System.out.println(a +" " + f +" " + l +" " +c +" " +h +" ");
 	    	
 	    	
 		if(this.countweapon==2)
 			u = (1 + s) * a * (1 + c * h) * l * f;
 		else
 		{
 			bonusAS += .15;
 			u = (1 + s) * a * (1 + c * h) * bonusAS * l / f;
 		}
 			return u;
	}
	
	private double tempdamageweaps(double minMaxDmg) {
		double n=0;
		if(countweapon==1)
		{
			n = minMaxDmg + stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne() + stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne();
			n/=2;
		}
		else
		{
			n = (2 * minMaxDmg + stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne() + stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne() +stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne() + stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne())/ 2;
		}
		
		return n;
	}

	private double tempspd(double e)
	{
		double n = 0; 
		double r = 0; 
		double i = weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); 
		double s = weaponDefaultAS.get(stuffs.get(EnumerationStuff.OFF_HAND).getType().getId());;
		double o = 1 + 0.25 / 100; 
		double u = 1 + 0 / 100;
		
		r=0;//getStat(getArmor(), "Attacks_Per_Second_Item_Bonus", null);
		double f = getStat(getWeapons(), "Attacks_Per_Second_Item_Bonus", null); 
		double l= getStat(getWeapons(), "Attacks_Per_Second_Item_Bonus", "OFF"); 
		double c = 0; //0.3 pour enchantersse
		
		System.out.println(n +" " + r +" " + i +" " + s +" " +o +" " + u +" " + f + " " + l);
		
		
		if(countweapon==1)
			n = e * (i * o + c + f + r);
		else 
			n = 1 / (i * o + c + f + l + r) + 1 / (s * u + c + f + l + r);
			
		return n;
	}
	


	}