package com.pihen.d3restapi.service.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.LegendarySet;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.beans.Ranks;
import com.pihen.d3restapi.beans.SkillRune;

public class StuffCalculator{
	
	public static enum KEY { PRIMARY_STAT, THORNS, BONUS_ATTACK_SPEED,ATTACK_PER_SECONDS,BLOCK_CHANCE, ATTACK_SPEED_PETS, ATTACK_SPEED_MAINHAND, ATTACK_SPEED_OFFHAND, HEALING, CRIT_CHANCE,CRIT_DAMAGE,DAMAGE_MAIN_HAND,DAMAGE_OFFHAND,VITALITY,TOUGHNESS, HP,ARMOR,BONUS_ELITE, DPS,DPS_ELEMENTAL, DODGECHANCE,BONUS_FIRE,BONUS_COLD,BONUS_POISON,BONUS_HOLY,BONUS_ARCANE,BONUS_LIGHTNING,BONUS_PHYSICAL, COOLDOWN_REDUCTION, DPS_ELITE, RESISTANCE_ALL, RESISTANCE_PHYSICAL, RESISTANCE_FIRE, RESISTANCE_POISON, RESISTANCE_COLD, RESISTANCE_LIGHTNING, RESISTANCE_ARCANE,XP_BONUS};
	public static enum ELEMENTS {Arcane,Cold,Fire,Holy,Lightning,Physical,Poison};
	public static enum SITUATIONAL { Ranged, Melee, Elites}
	
	private int monsterLevel;
	double armorReduction;
	double resistReduction;
	double dodgeReduction;
	double armorReductionPercent;
	double resistReductionPercent;
	double buffReduction;
	double totalReductionPercent;
	
	static final Logger logger = LogManager.getLogger(StuffCalculator.class.getName());

	public double getMindmgM() {
		return mindmgM;
	}

	public double getMaxdmgM() {
		return maxdmgM;
	}

	public double getTotalReductionPercent() {
		return totalReductionPercent;
	}

	public int getMonsterLevel() {
		return monsterLevel;
	}

	public void setMonsterLevel(int monsterLevel) {
		this.monsterLevel = monsterLevel;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	private Map<EnumerationStuff,Item> stuffs;
	
	private HeroSkillContainer skills;
	private Map<String, MinMaxBonus> statsCalculator;
	private Hero hero;
	private int countweapon=0;
	private Map<String,Double> weaponDefaultAS=new HashMap<String,Double>();
	private double attackSpeedMain;
	private double attackSpeedOff;

	
	private Map<KEY,Double> mapResultat ;
	private double classReduction;
	private double mindmgM;
	private double maxdmgM;
	

	public void setStatCalculator(Map<String, MinMaxBonus> bonusItem) {
		this.statsCalculator = bonusItem;
	}

	

	public Map<EnumerationStuff, Item> getStuffs() {
		return stuffs;
	}

	public void setStuffs(Map<EnumerationStuff, Item> stuffs) {
		this.stuffs = stuffs;
	}

	public StuffCalculator(Map<EnumerationStuff,Item> stuff, Hero hero) { //TODO ne recupere pas les skills
		stuffs= new HashMap<EnumerationStuff,Item>();
		this.hero= hero;
		this.skills=hero.getSkills();
		Iterator<EnumerationStuff> keys = stuff.keySet().iterator();
		mapResultat = new HashMap<KEY, Double>();
		statsCalculator = new HashMap<String,MinMaxBonus>();
		monsterLevel=hero.getLevel().intValue();
		while(keys.hasNext())
		{
			EnumerationStuff key=keys.next();
			if(stuff.get(key)!=null)
				stuffs.put(key, stuff.get(key));
		
		}
		init();
	}

	public double getAreaDamage(int nbMobs)
	{
		return (filter("Splash_Damage_Effect_Percent",null)*100)*(nbMobs-1)*0.2;
	}
	
	
	public Map<String, MinMaxBonus> getStatAttributs()
	{
		return statsCalculator;
	}
	
	public double getCritChance()
	{
		 return 0.05 + filter("Crit_Percent",null);
	}
	
	
	public double getCritDamage()
	{
		
		return 0.5 + filter("Crit_Damage",null);
	}
	
	public double getElementalDamageBonus(ELEMENTS element)
	{
		if(element!=null)
			return filter("Damage_Dealt_Percent_Bonus", element.toString());
		else
			return filter("Damage_Dealt_Percent_Bonus",null);
	}
	
	public double getBlockChance()
	{
		return filter("Block_Chance",null);
	}
	
	public double getPrimaryStatUnbuffedValue()
	{
		return getPrimaryBaseValue() + filter(hero.getPrimaryStat(), null);
	}
	
	public double getSecondaryBaseValue()
	{
		return 7+ (1*hero.getLevel().intValue());
	}
	
	public double getPrimaryBaseValue()
	{
		return 7+ (3*hero.getLevel().intValue());
	}
	
	public double getEliteDamageBonus()
	{
		return filter("Damage_Percent_Bonus_Vs_Elites", null);
	}
	
	public int getVitality()
	{
		return (int)(7+(2*hero.getLevel().intValue())+ filter("Vitality", null));
	}
	
	private double getResistanceAverage()
	{
		double val=0;
		for(ELEMENTS k : ELEMENTS.values())
		{
			if(!k.equals(ELEMENTS.Holy))
				val=val+getResistance(k);
		}
		return val/6;
	}
	

	public double getResistance(ELEMENTS e)
	{
		double baseValue=0;
		
		if(hero.getClazz().equals("witch-doctor")||hero.getClazz().equals("wizard"))
			baseValue=getPrimaryBaseValue();
		else
			baseValue=getSecondaryBaseValue();
	
		double intel = (baseValue + filter("Intelligence",null))/10;
		
		if(e!=null)
			return(baseValue + intel + filter("Resistance_All",null) + filter("Resistance#",e.toString()));
		else
			return(baseValue + intel + filter("Resistance_All",null));
	}
	
	public double getArmor()
	{
		double baseValue=0;
		if(hero.getClazz().equals("barbarian")||hero.getClazz().equals("crusader")||hero.getClazz().equals("monk")||hero.getClazz().equals("demon-hunter")) //2.1 -> hero.equals("demon-hunter")|| hero.equals("monk")
			baseValue=getPrimaryBaseValue();
		else
			baseValue=getSecondaryBaseValue();
	
		double strength = (baseValue + filter("Strength",null) + filter("Dexterity",null));
		return filter("Armor",null) + strength;
	}
	
	
	public double getDodge() // modify for 2.1 patch
	{
//		double dext = 0;
//		if(hero.equals("demon-hunter")|| hero.equals("monk"))
//			dext=getPrimaryBaseValue();
//		else
//			dext=getSecondaryBaseValue()+filter("Dexterity",null);
//		
//		return /*dext /*/ (0.00031*Math.pow(hero.getLevel().intValue(), 3) + 0.0186*Math.pow(hero.getLevel().intValue(),2) + 0.25*hero.getLevel().intValue() + 1.93) + filter("Increase_Dodge_Percent",BuffCalculator.PREFIX);
//	
		return 0;
	}
	
	private double getHealthPool()
	{
		int lvl = hero.getLevel().intValue();
		int vit = getVitality();
		double lifeB= filter("Hitpoints_Max_Percent_Bonus",null);
	
		double vitaMultiplier=0;
		
		if(lvl>65)
			vitaMultiplier=5*(lvl-65)+55;
		if(lvl<=65)
			vitaMultiplier=4*(lvl-60)+35;
		if(lvl<=60)
			vitaMultiplier=lvl-25;
		if(lvl<35)
			vitaMultiplier=10;
		
		double vitality = vit*vitaMultiplier;
		
		double hpLevel=36+(4*lvl);
	
		return (vitality+hpLevel)*(1+lifeB);
	}
	
	private double getHealing()//TODO
	{
		
		double lifePerHit=filter("Hitpoints_On_Hit",null)*(attackSpeedMain/(attackSpeedOff==0?1:attackSpeedOff));
		double healthGlobBonus=filter("Health_Globe_Bonus_Health",null);
		double lifePerSecond=filter("Hitpoints_Regen_Per_Second",null);
		double lifePerRessource=filter("Spending_Resource_Heals_Percent",null);//TODO
		double lifeOnKill=filter("Hitpoints_On_Kill",null)*0.16;
		//double lifeOnBuff=filter("Hitpoints_On_Skills",null);
		double lifeSteal=0; //not working on 70 level;
		
	return (lifePerHit+healthGlobBonus+lifePerSecond+lifeOnKill+lifeSteal+lifePerRessource)/5;
	}
	
	
	public double getToughness()
	{
		//1/(1-Armor Reduction%) * 1/(1-AllRes Reduction%) * Life
		armorReduction=getArmor();
		resistReduction=getResistanceAverage();
		//dodgeReduction=getDodge()/100;
		armorReductionPercent = armorReduction/((50*monsterLevel)+armorReduction);
		resistReductionPercent = resistReduction/((5*monsterLevel)+resistReduction);
		classReduction = hero.getClassReduction();
		buffReduction = filter("Damage_Percent_Reduction_From_All",null);
		totalReductionPercent =1-((1-armorReductionPercent)*(1-resistReductionPercent)*(1-classReduction)*(1-buffReduction));
		
		return (getHealthPool()/(1-totalReductionPercent));
	
	}

	public double getThorns() {
		return (getPrimaryStatUnbuffedValue()*1) + filter("Thorns_Fixed",null);//patch 2.3 thorns 100% primary damage
	}

	
	public double getSituationalDR(ELEMENTS e, SITUATIONAL s,boolean elite)
	{
		getToughness();
		double situationalReduction=0;
		situationalReduction=filter("Damage_Percent_Reduction_From_"+s,null);
		resistReduction=getResistance(e);
		armorReductionPercent = armorReduction/((50*monsterLevel)+armorReduction);
		resistReductionPercent = resistReduction/((5*monsterLevel)+resistReduction);
		buffReduction = filter("Damage_Percent_Reduction_From_All",null)+filter("Damage_Percent_Reduction_From_Type",e.toString());
		double elitReduction=0;
		
		if(elite)
			elitReduction=filter("Damage_Percent_Reduction_From_Elites",null);
		
		return 1-(/*(1-dodgeReduction)**/(1-armorReductionPercent)*(1-resistReductionPercent)*(1-classReduction)*(1-buffReduction)*(1-situationalReduction)*(1-elitReduction));
		
	}
	
	public void init()
	{
		
		weaponDefaultAS=Item.getWeaponDefaultAS();//init 
		
		Map<LegendarySet,Integer> piecesbyset=new HashMap<LegendarySet,Integer>();
		int compteur=0;
		
		
		for(Item i : stuffs.values())
		{
			if(i.isWeapon())
				countweapon+=1;
			
			if(i.isSetObjects())
			{
				piecesbyset.put(i.getSet(),new LegendarySet().getStuffSetsNbPieces(stuffs.values(), i.getSet()));
			}
			
			
			//bonus de gems
			if(i.getGems()!=null)
			if(i.getGems().size()>0)
			{
				List<Gem> gems = i.getGems();
				for(Gem g : gems)
				{
					Iterator<String> keysg = g.getAttributesRaw().keySet().iterator();
					String cleg = keysg.next();
					compteur++;
					statsCalculator.put(cleg +"_GEM_"+i.getName().replaceAll(" ", "-")+"-"+compteur, g.getAttributesRaw().get(cleg));
				}
			}
			//fin des gems
			
			Iterator<String> keys = i.getAttributesRaw().keySet().iterator();
			while(keys.hasNext())
			{
				String cle = keys.next();
				compteur++;
				statsCalculator.put(cle+"_"+i.getName().replaceAll(" ", "-"), i.getAttributesRaw().get(cle));
				
			}
			
			
			//BONUS LEG
			if(filter("Item_Power_Passive#ItemPassive_Unique_Ring_695_x1",null)>0 && stuffs.get(EnumerationStuff.MAIN_HAND).getType().getTwoHanded())//Hellskull shield
			{
				statsCalculator.put("Damage_Weapon_Percent_Bonus#Physical_LEG", new MinMaxBonus(0.10));
			}
			
			if(i.getAttributesRaw().get("Item_Power_Passive#ItemPassive_Unique_Ring_571_x1")!=null)//FURNACE
				statsCalculator.put("Damage_Percent_Bonus_Vs_Elites_LEG_", i.getAttributesRaw().get("Item_Power_Passive#ItemPassive_Unique_Ring_571_x1"));
		
			if(i.getAttributesRaw().get("Gem_Attributes_Multiplier")!=null)
				if(i.getGems().size()>0)
				{
						double bonus = i.getAttributesRaw().get("Gem_Attributes_Multiplier").getMoyenne();
						String k = i.getGems().get(0).getAttributesRaw().keySet().iterator().next();
						double value = i.getGems().get(0).getAttributesRaw().get(k).getMoyenne();
						statsCalculator.put(k+"_LEG_", new MinMaxBonus( value*bonus) );
					
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
	
	private void calculeBuff()
	{
		HeroSkillContainer cont = hero.getSkills();
		{
			List<SkillRune> sr = cont.getPassive();
			for(SkillRune s : sr)
			{
				statsCalculator.putAll(BuffCalculator.getBuff(s,this));
			}
		}
	}
	
	public double getPetAS() // Tasker and Theo
	{
		if(filter("Item_Power_Passive#ItemPassive_Unique_Ring_554_x1",null)>0)
			return 1+filter("Item_Power_Passive#ItemPassive_Unique_Ring_554_x1",null);
		else
			return 1;
	}
	
	public int getNbWeapon()
	{
		return countweapon;
	}
	
	
	public Map<KEY,Double> calculate()
	{
		double bonusDual=(countweapon==2)?0.15:0;
	
		double chance_cc=getCritChance();
		double degat_cc=getCritDamage();
		double stat_base=getPrimaryStatUnbuffedValue();

		
		double mainI=0;
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
			mainI=weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); //AS de base du type arme MAIN
		
		double offI=0;
		if(countweapon==2)
			offI=weaponDefaultAS.get(stuffs.get(EnumerationStuff.OFF_HAND).getType().getId()); //AS de base du type arme OFF
		
		double bonusAsArmor = filter("Attacks_Per_Second_Percent", null);
		double bonusWeapon = filter("Attacks_Per_Second_Item_Bonus", null);
		
		double compagnonBonus=0; // ou 0.3 pour l'enchanteresse
				
		attackSpeedMain=(1+bonusAsArmor+bonusDual)*(mainI*1+compagnonBonus+bonusWeapon);
		attackSpeedOff=0;

		if(countweapon==2)
			attackSpeedOff=(1+bonusAsArmor+bonusDual)*(offI*1+compagnonBonus+bonusWeapon);
		
		//CALCUL DAMAGE HIT
		double statDamage=1+(stat_base/100);
		double ccDamage=1+chance_cc*degat_cc;
		double minMaxDmg=getMinMaxArmorBonusDamage();
		double weaponDmgMain=0;
			
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
		{
			weaponDmgMain=minMaxDmg/2+(stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne())/2;
			weaponDmgMain = weaponDmgMain * (1+ filter("Damage_Weapon_Percent_Bonus#Physical",null));
		}
		double weaponDmgOff=0;
		
		if(countweapon==2)
		{
			weaponDmgOff=minMaxDmg/2+(stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne()+stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne())/2;
			weaponDmgOff = weaponDmgOff * (1+ filter("Damage_Weapon_Percent_Bonus#Physical",null));
		}
		
		double hitDmgMAIN=statDamage*ccDamage*weaponDmgMain;
		double hitDmgOFF=statDamage*ccDamage*weaponDmgOff;
		
		double dps=getDamage(stat_base,chance_cc,degat_cc,1+bonusAsArmor,minMaxDmg,0);
		double elementdps = getElemDamage(stat_base,chance_cc,degat_cc,1+bonusAsArmor,minMaxDmg,0);

		
		mapResultat.put(KEY.PRIMARY_STAT,stat_base);
		mapResultat.put(KEY.BONUS_ATTACK_SPEED,format(bonusAsArmor*100)); 
		mapResultat.put(KEY.ATTACK_SPEED_MAINHAND,format(attackSpeedMain));
		mapResultat.put(KEY.ATTACK_SPEED_OFFHAND,format(attackSpeedOff));
		mapResultat.put(KEY.CRIT_CHANCE, format(chance_cc*100));
		mapResultat.put(KEY.CRIT_DAMAGE,format(degat_cc*100));
		mapResultat.put(KEY.DAMAGE_MAIN_HAND,format(hitDmgMAIN));
		mapResultat.put(KEY.DAMAGE_OFFHAND,format(hitDmgOFF));
		mapResultat.put(KEY.VITALITY,format(getVitality()));
		mapResultat.put(KEY.HP,format(getHealthPool()));
		mapResultat.put(KEY.TOUGHNESS, format(getToughness()));
		mapResultat.put(KEY.HEALING, format(getHealing()));
		mapResultat.put(KEY.ARMOR,format(getArmor()));
		mapResultat.put(KEY.DODGECHANCE,format( getDodge()));
		mapResultat.put(KEY.BONUS_ELITE, format(getEliteDamageBonus()*100));
		mapResultat.put(KEY.BONUS_HOLY,format(getElementalDamageBonus(ELEMENTS.Holy)*100));
		mapResultat.put(KEY.BONUS_FIRE,format(getElementalDamageBonus(ELEMENTS.Fire)*100));
		mapResultat.put(KEY.BONUS_POISON,format(getElementalDamageBonus(ELEMENTS.Poison)*100));
		mapResultat.put(KEY.BONUS_COLD,format(getElementalDamageBonus(ELEMENTS.Cold)*100));
		mapResultat.put(KEY.BONUS_ARCANE,format(getElementalDamageBonus(ELEMENTS.Arcane)*100));
		mapResultat.put(KEY.BONUS_LIGHTNING, format(getElementalDamageBonus(ELEMENTS.Lightning)*100));
		mapResultat.put(KEY.BONUS_PHYSICAL,format(getElementalDamageBonus(ELEMENTS.Physical)*100));
		mapResultat.put(KEY.DPS,format(dps));
		mapResultat.put(KEY.DPS_ELEMENTAL,format(elementdps*(1+(getElementalDamageBonus(getElementalOrientation())))));
		mapResultat.put(KEY.DPS_ELITE,format(elementdps*(1+(getElementalDamageBonus(getElementalOrientation()))*(1+getEliteDamageBonus()))));
		mapResultat.put(KEY.COOLDOWN_REDUCTION, format(getCoolDownReduction()*100));
		mapResultat.put(KEY.RESISTANCE_ALL, format(getResistance(null)));
		mapResultat.put(KEY.RESISTANCE_PHYSICAL, format(getResistance(ELEMENTS.Physical)));
		mapResultat.put(KEY.RESISTANCE_FIRE, format(getResistance(ELEMENTS.Fire)));
		mapResultat.put(KEY.RESISTANCE_COLD, format(getResistance(ELEMENTS.Cold)));
		mapResultat.put(KEY.RESISTANCE_POISON, format(getResistance(ELEMENTS.Poison)));
		mapResultat.put(KEY.RESISTANCE_LIGHTNING, format(getResistance(ELEMENTS.Lightning)));
		mapResultat.put(KEY.RESISTANCE_ARCANE, format(getResistance(ELEMENTS.Arcane)));
		mapResultat.put(KEY.BLOCK_CHANCE, format(getBlockChance()*100));
		mapResultat.put(KEY.THORNS, format(getThorns()));
		mapResultat.put(KEY.ATTACK_SPEED_PETS, format(mapResultat.get(KEY.ATTACK_PER_SECONDS)*getPetAS()));
        mapResultat.put(KEY.XP_BONUS,format(getXPBonus()));
		
	return mapResultat;
	}
	
	
	private double getXPBonus() {
		return filter("Experience_Bonus_Percent", null)*100;
	}

	public Map<KEY,Double> getStats()
	{
		return mapResultat;
	}
	
	private double getCoolDownReduction()
	{
		return filter("Power_Cooldown_Reduction_Percent_All",null);
	}
	
	private double getMinMaxArmorBonusDamage() {
		
		double min = filter(getArmorsItem(), "Damage","Min");
		double max = filter(getArmorsItem(), "Damage_Min",null)+filter(getArmorsItem(), "Damage","Delta");
		return (min+max) ;
	}

	public ELEMENTS getElementalOrientation()
	{
		ELEMENTS ret=ELEMENTS.Physical;
		double temp=0;
		double max=0;
		for(ELEMENTS e:ELEMENTS.values())
		{
			temp = getElementalDamageBonus(e);
			
			if(temp>max)
			{
				max=temp;
				ret=e;
			}
		}
		return ret;
	}
	
	private double getElemDamage(double stat_base, double chance_cc,double ccDamage, double bonusAS, double minMaxDmg, int s)
	{
			double damage = 0; 
	    	double statBase = 1 + stat_base / 100; 
	    	double vitesseMoyenne = attackPerSecond(bonusAS); 
	    	double dommageMoyen = dommageMoyen(minMaxDmg);
	    	double critDamage = ccDamage; 
	    	double chanceCrit = chance_cc;
	    	double elementalDamage=filter("Damage_Dealt_Percent_Bonus",null);
	    	double mainWeaponMinDamage=0;
	    	double mainWeaponMaxDamage=0;
	    	
	    	if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
	    	{
	    		mainWeaponMinDamage =stuffs.get(EnumerationStuff.MAIN_HAND).getMinDamage().getMoyenne();
	    		mainWeaponMaxDamage =stuffs.get(EnumerationStuff.MAIN_HAND).getMaxDamage().getMoyenne();
	    	}
			
			if(countweapon<=1)
	    	{
				dommageMoyen += elementalDamage * (mainWeaponMinDamage + mainWeaponMaxDamage + minMaxDmg) / 2; 
				damage = (1 + s) * statBase * (1 + critDamage * chanceCrit) * dommageMoyen * vitesseMoyenne;
	    	}
	    	else
	    	{
	        	double offHandMinDamage =stuffs.get(EnumerationStuff.OFF_HAND).getMinDamage().getMoyenne();
				double offHandMaxDamage =stuffs.get(EnumerationStuff.OFF_HAND).getMaxDamage().getMoyenne();
				bonusAS += .15;//dual
	    		dommageMoyen += elementalDamage * (mainWeaponMinDamage + mainWeaponMaxDamage + offHandMinDamage + offHandMaxDamage + 2 * minMaxDmg) / 2;
	    		damage = (1 + s) * statBase * (1 + critDamage * chanceCrit) * bonusAS*dommageMoyen / vitesseMoyenne;
	    	}
	    	return damage * (1+ filter("Damage_Weapon_Percent_Bonus#Physical",null));	 
	}
	
	private double getDamage(double stat_base, double chance_cc,double ccDamage, double bonusAS, double minMaxDmg, int s) 
	{	
		
			double damage = 0; 
 	    	double statBase = 1 + stat_base / 100; 
 	    	double vitesseMoyenne = attackPerSecond(bonusAS); 
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
 		return damage* (1+ filter("Damage_Weapon_Percent_Bonus#Physical",null));
	}
	
	private double dommageMoyen(double minMaxDmg) {
		double n=0;
	
		ELEMENTS elementM;
		 mindmgM=0;
		 maxdmgM=0;
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
		{
			elementM = stuffs.get(EnumerationStuff.MAIN_HAND).getEnchantedWeapon();
			mindmgM=stuffs.get(EnumerationStuff.MAIN_HAND).getRealMin();
			maxdmgM=stuffs.get(EnumerationStuff.MAIN_HAND).getRealMax();

		}
		
		
//		if(!elementM.equals(""))
//		{
//			mindmgM+=stuffs.get(EnumerationStuff.MAIN_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementM).getMoyenne();
//			maxdmgM+=stuffs.get(EnumerationStuff.MAIN_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementM).getMoyenne()+stuffs.get(EnumerationStuff.MAIN_HAND).getAttributesRaw().get("Damage_Weapon_Delta#"+elementM).getMoyenne();
//		}
		
		if(countweapon==1)
		{
			n = minMaxDmg + mindmgM + maxdmgM;
			n/=2;
		}
		else if(stuffs.get(EnumerationStuff.OFF_HAND)!=null)
		{
			ELEMENTS elementO = stuffs.get(EnumerationStuff.OFF_HAND).getEnchantedWeapon();
			double mindmgO=stuffs.get(EnumerationStuff.OFF_HAND).getRealMin();
			double maxdmgO=stuffs.get(EnumerationStuff.OFF_HAND).getRealMax();
			
//			if(!elementO.equals(""))
//			{
//				mindmgO+=stuffs.get(EnumerationStuff.OFF_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementO).getMoyenne();
//				maxdmgO+=stuffs.get(EnumerationStuff.OFF_HAND).getAttributesRaw().get("Damage_Weapon_Min#"+elementO).getMoyenne()+stuffs.get(EnumerationStuff.OFF_HAND).getAttributesRaw().get("Damage_Weapon_Delta#"+elementO).getMoyenne();
//			}
			
			n = (2 * minMaxDmg + mindmgM + maxdmgM +mindmgO + maxdmgO)/ 2;
		}
		
		return n;
	}

	private double attackPerSecond(double bonusAS)
	{

		double n = 0; 
		double armorASBonus = 0;
		double defaultMHas =0;
		if(stuffs.get(EnumerationStuff.MAIN_HAND)!=null)
			defaultMHas = filter(stuffs.get(EnumerationStuff.MAIN_HAND),"Attacks_Per_Second_Item",null);
			//weaponDefaultAS.get(stuffs.get(EnumerationStuff.MAIN_HAND).getType().getId()); 

		double defaultOHas = 0;
		if(countweapon==2)
			defaultOHas=filter(stuffs.get(EnumerationStuff.OFF_HAND),"Attacks_Per_Second_Item",null);
			
			//weaponDefaultAS.get(stuffs.get(EnumerationStuff.OFF_HAND).getType().getId());;
		
		double bonusMHas = 1 + filter(stuffs.get(EnumerationStuff.MAIN_HAND), "Attacks_Per_Second_Item_Percent", null);
		double bonusOHas = 1;
		if(countweapon==2)
			bonusOHas=1 + filter(stuffs.get(EnumerationStuff.OFF_HAND), "Attacks_Per_Second_Item_Percent", null); //augmente la vitesse d'attaque de XX% sur la OH
		
		//armorASBonus=filter(getArmorsItem(), "Attacks_Per_Second_Item_Percent", null);//0.
		armorASBonus=filter("Attacks_Per_Second_Item_Percent", null);//0.
		
		double weaponASBonus = filter(getWeaponsItems(), "Attacks_Per_Second_Item_Bonus", null); 
		double attacks_Per_Second_Item_Bonus= filter(getWeaponsItems(), "Attacks_Per_Second_Item_Bonus", "OFF"); 
		
			
		if(countweapon==1)
			n = bonusAS * (defaultMHas * bonusMHas  + weaponASBonus + armorASBonus);
		else 
			n = 1 / (defaultMHas * bonusMHas  + weaponASBonus + attacks_Per_Second_Item_Bonus + armorASBonus) + 1 / (defaultOHas * bonusOHas  + weaponASBonus + attacks_Per_Second_Item_Bonus + armorASBonus);
	
		mapResultat.put(KEY.ATTACK_PER_SECONDS, format(n));
		
		return n;
	}
	
	public StuffCalculator compareStuffWithItem(EnumerationStuff g, Item i)
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
						calc2.calculate();
		
		return calc2;
	}
	
	
	public static double format(double val)
	{
		try{
			
			DecimalFormat format = new DecimalFormat("### ### ### ###.00");
			
			return Double.parseDouble(format.format(val).replaceAll(",", "."));
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
	
	public void addBonus(String att, MinMaxBonus val)
	{
		statsCalculator.put(att, val);
	}
	
	public void addBonus(Map<String,MinMaxBonus> bonus)
	{
		statsCalculator.putAll(bonus);
		
	}
	
	public List<Item> getArmorsItem()
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
	
	public List<Item> getWeaponsItems()
	{
			List<Item> items = new ArrayList<Item>();
			
			items.add(stuffs.get(EnumerationStuff.MAIN_HAND));
			if(countweapon==2)
				items.add(stuffs.get(EnumerationStuff.OFF_HAND));
			
		return items;
	}
	
	public List<Item> getAllItems()
	{
		List<Item> items = new ArrayList<Item>();
		for(EnumerationStuff e :EnumerationStuff.values())
			items.add(stuffs.get(e));

		return items;
	}
	
	public double filter(Map<String, MinMaxBonus> att,String stat,String elementfilter)
	{
		double total =0;
		for(String k : att.keySet())
		{
			if(k.startsWith(stat))
			total+=att.get(k).getMoyenne();
		}
		return total;
	}
	
	public double filter(List<Item> listes,String stat,String elementfilter)
	{
		
		double total =0;
		for(Item it:listes)
		{
			total+=filter(it, stat, elementfilter);
		}
		return total;
	}
	
	public double filter(Item i,String stat,String elementfilter) { // add gems
		
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
	
	public double filter(String stat,String elementfilter) {
		double total=0.0;
		Iterator<String> keyIt = statsCalculator.keySet().iterator();
		
		while(keyIt.hasNext())
		{
			String k = keyIt.next();
			
			if(k.startsWith(stat)||stat.equals(""))
			{
				if(elementfilter==null)
				{
					total=total+ statsCalculator.get(k).getMoyenne();
				}
				else 
				if(k.contains(elementfilter))
				{
					total=total+ statsCalculator.get(k).getMoyenne();
				}
				
				
			}
		}
		
		return total;	
	}
	

	public double getGemValue(Item i,String stat) { // add gems
		
		if(i==null)
			return 0;
		
		if(i.getGems().size()==0)
			return 0;
		
			double total=0.0;
			for(Gem g : i.getGems())
			{
				for(String k : g.getAttributesRaw().keySet())
				{
					if(k.startsWith(stat)||stat.equals(""))
					{
						total+=g.getAttributesRaw().get(k).getMoyenne();
					}
					
				}
			}	
		return total;
	}

	}