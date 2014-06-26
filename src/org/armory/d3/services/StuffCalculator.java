package org.armory.d3.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.LegendarySet;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.beans.Ranks;
import com.pihen.d3restapi.beans.SkillRune;
import com.pihen.d3restapi.service.util.BuffSkill;
import com.pihen.d3restapi.service.util.EnumerationStuff;

public class StuffCalculator{
	private Map<EnumerationStuff,Item> stuffs;
	
	private HeroSkillContainer skills;
	private Map<String, MinMaxBonus> statsCalculator;
	private Hero hero;
	private boolean twohanded;
	int countweapon=0;
	private Map<String, MinMaxBonus> buffs;
	private Map<String,Double> weaponDefaultAS=new HashMap<String,Double>();
	
	private Map<String,Double> mapResultat ;
	

	public boolean isTwohanded() {
		return twohanded;
	}

	public void setStatCalculator(Map<String, MinMaxBonus> bonusItem) {
		this.statsCalculator = bonusItem;
	}

	
	public Map<String, Double> getStatCalculator() {
		return mapResultat;
	}

	public Map<EnumerationStuff, Item> getStuffs() {
		return stuffs;
	}

	public void setStuffs(Map<EnumerationStuff, Item> stuffs) {
		this.stuffs = stuffs;
	}

	public StuffCalculator(Map<EnumerationStuff,Item> stuff, Hero hero) {
		stuffs= new HashMap<EnumerationStuff,Item>();
		this.hero= hero;
		this.skills=hero.getSkills();
		Iterator<EnumerationStuff> keys = stuff.keySet().iterator();
		mapResultat = new HashMap<String, Double>();
		statsCalculator = new HashMap<String,MinMaxBonus>();
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
		return statsCalculator;
	}
	
	
	
	
	public void init()
	{
		
		weaponDefaultAS=Item.getWeaponDefaultAS();//init 
		
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
			if(i.getGems().size()>0)
			{
				List<Gem> gems = i.getGems();
				for(Gem g : gems)
				{
					Iterator<String> keysg = g.getAttributesRaw().keySet().iterator();
					String cleg = keysg.next();
					compteur++;
					statsCalculator.put(cleg +"_GEM_"+i.getName().replaceAll(" ", "-")+"_"+compteur, g.getAttributesRaw().get(cleg));
				}
			}
			//fin des gems
			
			Iterator<String> keys = i.getAttributesRaw().keySet().iterator();
			while(keys.hasNext())
			{
				String cle = keys.next();
				compteur++;
				statsCalculator.put(cle+"_"+i.getName().replaceAll(" ", "-"), i.getAttributesRaw().get(cle));
				String mainoff="";
			}
			
		}//fin de boucle sur les items
		
		//on ajoute les bonus de set
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
					statsCalculator.put(k+"_SET_"+compteur, r.getAttributesRaw().get(k));
				}
			}
		}
		calculeBuff();
	}
	
	public void calculeBuff()
	{
		HeroSkillContainer cont = hero.getSkills();
		{
			List<SkillRune> sr = cont.getPassive();
			for(SkillRune s : sr)
			{
				statsCalculator.putAll(BuffSkill.getBuff(s,getStuffs()));
			}
		}
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
	
	private double filterStats(String stat,String elementfilter) {
		return filterStats(stat, elementfilter,false);
	}
	
	public double getStat(Item i,String stat,String elementfilter) {
		
		if(i==null)
			return 0;
		
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
						else 
							if(k.contains(elementfilter))
						{
							total=total+ attributes.get(k).getMoyenne();
						}
					}
				}
		return total;
	}
	
	public double filterStats(String stat,String elementfilter,boolean debug) {
		double total=0.0;
		Iterator<String> keyIt = statsCalculator.keySet().iterator();
		
		while(keyIt.hasNext())
		{
			String k = keyIt.next();
			
			if(k.startsWith(stat)||stat.equals(""))
			{
				
				if(elementfilter==null)
				{
					if(debug)
						System.out.println(k + " " + statsCalculator.get(k).getMoyenne());
					
					total=total+ statsCalculator.get(k).getMoyenne();
				}
				else 
				if(k.contains(elementfilter))
				{
					if(debug)
						System.out.println(k + " " + statsCalculator.get(k).getMoyenne());
					
					total=total+ statsCalculator.get(k).getMoyenne();
				}
				
				
			}
		}
		
		return total;	
	}
	
	public double calculate()
	{
		double bonusDual=(countweapon==2)?0.15:0;
		double chance_cc=0.05+filterStats("Crit_Percent", null);
		
		double degat_cc=0.5+filterStats("Crit_Damage", null);
		
		
		
		double stat_base=hero.getPrimaryStatValue()+filterStats( hero.getPrimaryStat(),null);
		double mainI=0;
		
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
			mainI=weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); //AS de base du type arme MAIN
		
		double offI=0;
		
		if(countweapon==2)
			offI=weaponDefaultAS.get(stuffs.get(EnumerationStuff.OFF_HAND).getType().getId()); //AS de base du type arme MAIN
		
		double bonusArmor = getStat(getArmor(), "Attacks_Per_Second_Percent", null) + filterStats("Attacks_Per_Second_Percent","BUFF");
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
		double weaponDmgMain=0;
			
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
		{
			weaponDmgMain=minMaxDmg/2+(stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne())/2;
			weaponDmgMain = weaponDmgMain * (1+ filterStats("Damage_Weapon_Percent_Bonus#Physical","BUFF"));
		}
		double weaponDmgOff=0;
		
		if(countweapon==2)
		{
			weaponDmgOff=minMaxDmg/2+(stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne())/2;
			weaponDmgOff = weaponDmgOff * (1+ filterStats("Damage_Weapon_Percent_Bonus#Physical","BUFF"));
		}
		
		double hitDmgMAIN=statDamage*ccDamage*weaponDmgMain;
		double hitDmgOFF=statDamage*ccDamage*weaponDmgOff;

		//CALCUL VITALITY
		double lifeB= filterStats("Hitpoints_Max_Percent_Bonus","",false);
		double lvl = hero.getLevel().doubleValue();
		double paran =  hero.getParagonLevel();
		double vitality = 9+ 2 *((lvl+paran)-1) + filterStats("Vitality_","",false);
		double life=0;
		
		if(lvl<35)
			life = (36 + (lvl * 4) + (vitality * 10)) * (1+lifeB);
		else
			life= (36 + 4* lvl  + (vitality * (lvl - 25))) * (1+lifeB);
		
		
		//ARMOR CALCUL
		double armorBonus= filterStats("Armor","",false);
		double armor = stat_base+armorBonus;
		
		
		
		mapResultat.put("STAT",stat_base);
		mapResultat.put("ATTACKSPEEDBONUS",format(bonusArmor*100));
		mapResultat.put("ATTACKSPEEDMH",format(attackSpeedMain));
		mapResultat.put("ATTACKSPEEDOH",format(attackSpeedOff));
		mapResultat.put("CRITCHANCE", format(chance_cc*100));
		mapResultat.put("CRITDAMAGE",format(degat_cc*100));
		mapResultat.put("MHDAMAGE",format(hitDmgMAIN));
		mapResultat.put("OHDAMAGE",format(hitDmgOFF));
		mapResultat.put("VITALITY",format(vitality));
		mapResultat.put("LIFE",format(life));
		mapResultat.put("ARMOR",format(armor));
		
		
		double dps=getDamage(stat_base,chance_cc,degat_cc,1+bonusArmor,minMaxDmg,0);
		double elementdps = getElemDamage(stat_base,chance_cc,degat_cc,1+bonusArmor,minMaxDmg,0);
		
		if(dps>=elementdps)
		{
			mapResultat.put("DPS",format(dps));
			return dps;
		}
		else
		{
			
			mapResultat.put("DPS",format(elementdps));
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
	
		return (min+max) ;
	}

	private double getElemDamage(double stat_base, double chance_cc,double ccDamage, double bonusAS, double minMaxDmg, int s)
	{
			double damage = 0; 
	    	double statBase = 1 + stat_base / 100; 
	    	double vitesseMoyenne = vitesseMoyenne(bonusAS); 
	    	double dommageMoyen = dommageMoyen(minMaxDmg);
	    	double critDamage = ccDamage; 
	    	double chanceCrit = chance_cc;
	    	double elementalDamage=filterStats("Damage_Type_Percent_Bonus", null);
	    	double damage_minM=0;
	    	double damage_maxM=0;
	    	
	    	if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
	    	{
	    		damage_minM =stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne();
	    		damage_maxM =stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne();
	    	}
			
			
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
	    		damage = (1 + s) * statBase * (1 + critDamage * chanceCrit) * bonusAS*dommageMoyen / vitesseMoyenne;
	    	}
	    	return damage* (1+ filterStats("Damage_Weapon_Percent_Bonus#Physical","BUFF"));	 
	}
	
	private double getDamage(double stat_base, double chance_cc,double ccDamage, double bonusAS, double minMaxDmg, int s) 
	{	
		
			double damage = 0; 
 	    	double statBase = 1 + stat_base / 100; 
 	    	double vitesseMoyenne = vitesseMoyenne(bonusAS); 
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
 		return damage* (1+ filterStats("Damage_Weapon_Percent_Bonus#Physical","BUFF"));
	}
	
	private double dommageMoyen(double minMaxDmg) {
		double n=0;
	
		String elementM="";
		double mindmgM=0;
		double maxdmgM=0;
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
		{
			elementM = stuffs.get(EnumerationStuff.MAIN_HAND).getEnchantedWeapon();
			mindmgM=stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne();
			maxdmgM=stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne();

		}
		
		
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
		else if(stuffs.get(EnumerationStuff.OFF_HAND)!=null)
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

	private double vitesseMoyenne(double bonusAS)
	{

		double n = 0; 
		double armorASBonus = 0;
		double defaultMHas =0;
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
			defaultMHas = weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); 

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
			n = bonusAS * (defaultMHas * bonusMHas  + weaponASBonus + armorASBonus);
		else 
			n = 1 / (defaultMHas * bonusMHas  + weaponASBonus + Attacks_Per_Second_Item_Bonus + armorASBonus) + 1 / (defaultOHas * bonusOHas  + weaponASBonus + Attacks_Per_Second_Item_Bonus + armorASBonus);
		return n;
	}
	
	public StuffCalculator compareStuffs(EnumerationStuff g, Item i)
	{
	   Map<EnumerationStuff,Item> stuffs2 = new HashMap<EnumerationStuff,Item>();
								   stuffs2.putAll(getStuffs());
								   stuffs2.put(g, i);
								   
	   if(i.isWeapon())
		   if(i.getType().getTwoHanded())
			   if(stuffs2.get(EnumerationStuff.OFF_HAND)!=null)
				   if(stuffs2.get(EnumerationStuff.OFF_HAND).isWeapon())
				   		stuffs2.put(EnumerationStuff.OFF_HAND, null);
				
		StuffCalculator calc2=new StuffCalculator(stuffs2, hero);
						calc2.calculeBuff();
						calc2.calculate();
						
		return calc2;
	}
	
	public static double format(double val)
	{
		try{
			return Double.parseDouble(new DecimalFormat("######.00").format(val).replaceAll(",", "."));
		}
		catch(NumberFormatException e)
		{
			return 0.0;
		}
	}

	public void removeBonus(Set<String> set) {
		for(String k: set)
			statsCalculator.remove(k);
	}
	
	public void addBonus(Map<String,MinMaxBonus> bonus)
	{
		statsCalculator.putAll(bonus);
		
	}
	


	}