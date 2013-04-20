package org.armory.d3.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	private Map<String,Double> weaponDefaultAS=new HashMap<String,Double>();
	
	int countweapon=0;
	private Map<String,Double> mapResultat ;
	
	public Map<String, Double> getMapResultat() {
		return mapResultat;
	}

	public StuffCalculator(Map<EnumerationStuff,Item> stuff, Hero hero) {
		stuffs= new HashMap<EnumerationStuff,Item>();
		this.skills=hero.getSkills();
		this.hero= hero;
		Iterator<EnumerationStuff> keys = stuff.keySet().iterator();
		mapResultat = new HashMap<String, Double>();
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
	
	private void init()
	{
		bonusItem = new HashMap<String,MinMaxBonus>();
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
			{
				if(!stuffs.get(e).isWeapon())
				items.add(stuffs.get(e));
			}
		}
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
		
		//CALCUL attackSpeed 
		double attackPerSecondMain = stuffs.get(EnumerationStuff.MAIN_HAND).getAttacksPerSecond().getMoyenne();//getStat("Damage_DPS_Attack","MAIN");
		double attackPerSecondOff=0;
		
		if(countweapon==2)
			attackPerSecondOff = stuffs.get(EnumerationStuff.OFF_HAND).getAttacksPerSecond().getMoyenne();//getStat("Damage_DPS_Attack","OFF");
		
		double mainI=weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); //AS de base du type arme MAIN
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
		double minMaxDmg=tempDamage();
		
		double weaponDmgMain=minMaxDmg/2+(stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne())/2;
		
		
		double weaponDmgOff=0;
		
		if(countweapon==2)
			weaponDmgOff=minMaxDmg/2+(stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne())/2;
		
		double hitDmgMAIN=statDamage*ccDamage*weaponDmgMain;
		double hitDmgOFF=statDamage*ccDamage*weaponDmgOff;
		double hitDmg=0;

		if(countweapon==2)
			hitDmg=(hitDmgMAIN+hitDmgOFF)/2;
		else
			hitDmg=hitDmgMAIN;
		
		
		//System.out.println(hero.getPrimaryStat() +" " + stat_base);
		//System.out.println("+% Attack Speed : " + bonusArmor*100 +"%");
		//System.out.println("Attack Speed MH "  + attackSpeedMain);
		
		mapResultat.put("STAT",stat_base);
		mapResultat.put("ATTACKSPEEDBONUS",bonusArmor*100);
		mapResultat.put("ATTACKSPEEDMH",attackSpeedMain);
		mapResultat.put("ATTACKSPEEDOH",attackSpeedOff);
		mapResultat.put("CRITCHANCE", chance_cc*100);
		mapResultat.put("CRITDAMAGE",degat_cc*100);
		mapResultat.put("MHDAMAGE",hitDmgMAIN);
		mapResultat.put("OHDAMAGE",hitDmgOFF);
	
		
		double dps=getDamage(stat_base,chance_cc,degat_cc,1+bonusArmor,minMaxDmg,0);
		double elementdps = getElemDamage(stat_base,chance_cc,degat_cc,1+bonusArmor,minMaxDmg,0);
		
		if(dps>=elementdps)
		{
			mapResultat.put("DPS",dps);
			return dps;
		}
		else
		{
			mapResultat.put("DPS",elementdps);
			return elementdps;
		}
	}

	public Map<String,Double> getStats()
	{
		return mapResultat;
	}
	
	private double tempDamage() {
		
		double min = getStat(getArmor(), "Damage","Min");
		double max = getStat(getArmor(), "Damage_Min",null)+getStat(getArmor(), "Damage","Delta");
	
		return min+max;
	}

	private double getElemDamage(double stat_base, double chance_cc,double ccDamage, double bonusAS, double minMaxDmg, int s)
	{
			double damage = 0; 
	    	double statBase = 1 + stat_base / 100; 
	    	double vitesseMoyenne = vitesseMoyenne(bonusAS,0); 
	    	double dommageMoyen = dommageMoyen(minMaxDmg);
	    	double critDamage = ccDamage; 
	    	double chanceCrit = chance_cc;
	    	double elementalDamage=getStat("Damage_Type_Percent_Bonus", null);
	      	double damage_minM =stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne();
			double damage_maxM =stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne();
			
			if(countweapon<=1)
	    	{
				dommageMoyen += elementalDamage * (damage_minM + damage_maxM + minMaxDmg) / 2; 
				damage = (1 + s) * statBase * (1 + critDamage * chanceCrit) * dommageMoyen * vitesseMoyenne;
	    	}
	    	else
	    	{
	        	double damage_minO =stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne();
				double damage_maxO =stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne();
				bonusAS += .15;//dual
	    		dommageMoyen += elementalDamage * (damage_minM + damage_maxM + damage_minO + damage_maxO + 2 * minMaxDmg) / 2;
	    	}
	    	return damage;	 
	}
	
	private double getDamage(double stat_base, double chance_cc,double ccDamage, double bonusAS, double minMaxDmg, int s) 
	{	
		
			double damage = 0; 
 	    	double statBase = 1 + stat_base / 100; 
 	    	double vitesseMoyenne = vitesseMoyenne(bonusAS,0); 
 	    	double dommageMoyen = dommageMoyen(minMaxDmg);
 	    	double critDamage = ccDamage; 
 	    	double chanceCrit = chance_cc;
 	    	
 		if(countweapon<=1)
 		{
			damage = (1 + s) * statBase * (1 + critDamage * chanceCrit) * dommageMoyen * vitesseMoyenne;
 		}
 		else
 		{
 			bonusAS += .15;//dual
 			damage = (1 + s) * statBase * (1 + critDamage * chanceCrit) * bonusAS * dommageMoyen / vitesseMoyenne;
 		}
 		return damage;
	}
	
	private double dommageMoyen(double minMaxDmg) {
		double n=0;
	
		String elementM = stuffs.get(EnumerationStuff.MAIN_HAND).getEnchantedWeapon();
		double mindmgM=stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne();
		double maxdmgM=stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne();
		
		if(!elementM.equals(""))
		{
			mindmgM+=stuffs.get(EnumerationStuff.MAIN_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementM).getMoyenne();
			maxdmgM+=stuffs.get(EnumerationStuff.MAIN_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementM).getMoyenne()+stuffs.get(EnumerationStuff.MAIN_HAND).getAttributesRaw().get("Damage_Weapon_Delta#"+elementM).getMoyenne();
		}
		
		if(countweapon==1)
		{
			n = minMaxDmg + mindmgM + maxdmgM;
			n/=2;
		}
		else
		{
			String elementO = stuffs.get(EnumerationStuff.OFF_HAND).getEnchantedWeapon();
			double mindmgO=stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne();
			double maxdmgO=stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne();
			
			if(!elementO.equals(""))
			{
				mindmgO+=stuffs.get(EnumerationStuff.OFF_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementO).getMoyenne();
				maxdmgO+=stuffs.get(EnumerationStuff.OFF_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementO).getMoyenne()+stuffs.get(EnumerationStuff.OFF_HAND).getAttributesRaw().get("Damage_Weapon_Delta#"+elementO).getMoyenne();
			}
			
			n = (2 * minMaxDmg + mindmgM + maxdmgM +mindmgO + maxdmgO)/ 2;
		}
		
		return n;
	}

	private double vitesseMoyenne(double bonusAS,double enchanteresseBonus)
	{

		double n = 0; 
		double armorASBonus = 0; 
		double defaultMHas = weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); 
		double defaultOHas = 0;
		if(countweapon==2)
			defaultOHas=weaponDefaultAS.get(stuffs.get(EnumerationStuff.OFF_HAND).getType().getId());;
		
		double bonusMHas = 1 + getStat(stuffs.get(EnumerationStuff.MAIN_HAND), "Attacks_Per_Second_Item_Percent", null);
		double bonusOHas = 1;
		if(countweapon==2)
			bonusOHas=1 + getStat(stuffs.get(EnumerationStuff.OFF_HAND), "Attacks_Per_Second_Item_Percent", null); //augmente la vitesse d'attaque de XX% sur la OH
		
		armorASBonus=getStat(getArmor(), "Attacks_Per_Second_Item_Percent", null);//0.
		double weaponASBonus = getStat(getWeapons(), "Attacks_Per_Second_Item_Bonus", null); 
		double Attacks_Per_Second_Item_Bonus= getStat(getWeapons(), "Attacks_Per_Second_Item_Bonus", "OFF"); 
		
			
		if(countweapon==1)
			n = bonusAS * (defaultMHas * bonusMHas + enchanteresseBonus + weaponASBonus + armorASBonus);
		else 
			n = 1 / (defaultMHas * bonusMHas + enchanteresseBonus + weaponASBonus + Attacks_Per_Second_Item_Bonus + armorASBonus) + 1 / (defaultOHas * bonusOHas + enchanteresseBonus + weaponASBonus + Attacks_Per_Second_Item_Bonus + armorASBonus);
		
		return n;
	}

	public StuffCalculator compareStuffsDPS(EnumerationStuff g, Item i)
	{
		Map<EnumerationStuff,Item> stuffs2 = new HashMap<EnumerationStuff,Item>();
		stuffs2.putAll(stuffs);
		stuffs2.put(g, i);
		StuffCalculator calc2 = new StuffCalculator(stuffs2, hero);
		calc2.calculateUnbuffedDPS();
		return calc2;
		
		
	}
	


	}