package org.armory.d3.services;

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

public class StuffCalculator {

	
	private List<Item> stuffs;
	
	int countweapon;
	boolean twohanded;
	
	public List<Item> getStuffs() {
		return stuffs;
	}

	public void setStuffs(List<Item> stuffs) {
		this.stuffs = stuffs;
	}


	private HeroSkillContainer skills;
	private Map<String, MinMaxBonus> bonusItem;
	private Hero hero;
	
	public StuffCalculator(List<Item> stuff, Hero hero) {
		this.stuffs=stuff;
		this.skills=hero.getSkills();
		this.hero= hero;
		while(stuffs.remove(null));
		bonusItem = new HashMap<>();
		init();
	}

	public Map<String, MinMaxBonus> getBonusItem()
	{
		return bonusItem;
	}
	
	private void init()
	{
		Map<LegendarySet,Integer> piecesbyset=new HashMap<LegendarySet,Integer>();
		int compteur=0;
		countweapon=0;
		
		for(Item i : stuffs)
		{
			if(i.isWeapon())
			{
				countweapon=countweapon+1;
				twohanded = i.getType().getTwoHanded();
			}
			
			if(i.isSetObjects())
			{
				piecesbyset.put(i.getSet(),LegendarySet.getStuffSetsNbPieces(stuffs, i.getSet()));
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
	
	private double getStat(String stat,String elementfilter) {
		return getStat(stat, elementfilter,false);
	}
	
	
	/**
	 * @param stat : stat to filter (Critic,Strength,...)
	 * @param elementfilter : elemental filter : Fire, Cold,Physical,Lightning, Holy, Arcane
	 * @param debug : show result in console;
	 * */
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
		
		double as_bonusarmor=getStat("Attacks_Per_Second_Percent",null);
		double chance_cc;
		double degat_cc;
		double bonusDual;

		chance_cc=0.05+getStat("Crit_Percent", null);
		degat_cc=0.5+getStat("Crit_Damage", null);

		if(countweapon==2)
		{
			bonusDual=0.15;
		}
		else
		{
			bonusDual=0;
		}
		
		double stat_base=hero.getBaseStatPrimary()+getStat( hero.getPrimaryStat(),null);
		
		double damage_min =getStat("Damage_Min", null);
		double damage_max = damage_min+ getStat("Damage_Delta", null);
		
		double attackPerSecondBonus = getStat("Attacks_Per_Second_Item_Bonus",null);
		double attackPerSecondMain = getStat("Damage_DPS_Attack","MAIN");
		double attackPerSecondOff = getStat("Damage_DPS_Attack","OFF");
		
		double attackSpeedMain =attackPerSecondMain*(1+bonusDual+as_bonusarmor);
		double attackSpeedOff=(attackPerSecondBonus+attackPerSecondOff)*(1+bonusDual+as_bonusarmor);
		
		double dpsMain = ((getStat("Damage_DPS_Min","MAIN")+ getStat("Damage_DPS_Max","MAIN"))/2)*attackPerSecondMain;
		double dpsOff = ((getStat("Damage_DPS_Min","OFF")+ getStat("Damage_DPS_Max","OFF"))/2)*(attackPerSecondOff+attackPerSecondBonus);
		double damageIncrease=getStat("Damage_Weapon_Percent_Bonus", null);
		double weaponDPS=0;
		if(twohanded)
			weaponDPS=((dpsMain))*1.2;
		
		if(countweapon==2)
			weaponDPS=((dpsMain+dpsOff)/2)*1.15;
		
		if(countweapon==1)
			weaponDPS=dpsMain;
		
		System.out.println("NB Weapon : " + countweapon);
		System.out.println("Two Handed : " + twohanded);
		System.out.println("==========================================");
		System.out.println(hero.getPrimaryStat() +" : " + stat_base);
		
		System.out.println("Attack speed BONUS ITEM :  " + attackPerSecondBonus);
		System.out.println("Attack speed BONUS ARMOR :  " + as_bonusarmor);
		System.out.println("Attack Speed MAIN : " +  attackSpeedMain);
		System.out.println("Attack Speed OFF : " + attackSpeedOff);
		double attackSpeedFinal=attackSpeedMain;
		
		if(countweapon==2)
			attackSpeedFinal=(attackPerSecondMain+attackSpeedOff)/2;
		
		System.out.println("Attack Speed AVG : " + attackSpeedFinal);
		
		
		System.out.println("WEAPON DPS MAIN : " + dpsMain);
		System.out.println("WEAPON DPS OFF : " + dpsOff);
		
		System.out.println("Bonus dual weapon :  " + bonusDual);
		System.out.println("WEAPON DPS : " + weaponDPS);
		
		System.out.println("chance_cc : " + chance_cc*100);
		System.out.println("degat_cc : " + degat_cc*100);
		System.out.println("Damage min : " + damage_min);
		System.out.println("Damage max : " + damage_max);
		System.out.println("+% Damage  : " + damageIncrease*100);
		
		double dps= weaponDPS * (1 + stat_base / 100)* (1+attackSpeedFinal) *  (1 + (chance_cc * degat_cc));
		return dps;
	}

	
}
