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
		for(Item i : stuffs)
		{
			
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
					bonusItem.put(cleg +"_GEM_"+i.getName().replaceAll(" ", "-"), gems[g].getAttributesRaw().get(cleg));
				}
			}
			//fin des gems
			
			Iterator<String> keys = i.getAttributesRaw().keySet().iterator();
			while(keys.hasNext())
			{
				String cle = keys.next();
				compteur++;
				bonusItem.put(cle+"_"+i.getName().replaceAll(" ", "-"), i.getAttributesRaw().get(cle));
				
				if(i.isWeapon())
				{
					bonusItem.put("Damage_DPS_Min_"+i.getName().replaceAll(" ", "-"), i.getMinDamage());
					bonusItem.put("Damage_DPS_Max_"+i.getName().replaceAll(" ", "-"), i.getMaxDamage());
					bonusItem.put("Damage_DPS_AttackSpeed_"+i.getName().replaceAll(" ", "-"), i.getAttacksPerSecond());
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
		double eff_comp_psv=0;

		double bonus_min_arme=getStat("Damage_Weapon_Min", "Bonus");
		double bonus_max_arme=getStat("Damage_Weapon_Min", "Bonus")+getStat("Damage_Weapon_Delta", "Bonus");
		
		double vit_attaque=getStat("Attacks_Per_Second",null);
		double degat_moy_arme= (((getStat("Damage_Weapon_Min", null)+getStat("Damage_Weapon_Delta", null)))/2)*(15/100)/vit_attaque;
		double chance_cc=0.05+getStat("Crit_Percent", null);
		double degat_cc=0.5+getStat("Crit_Damage", null);
		double stat_base=hero.getBaseStatPrimary()+getStat( hero.getPrimaryStat(),null);
		
		System.out.println("==========================================");
		System.out.println("vit_attaque_arme " + vit_attaque);
		System.out.println("eff_comp_psv " + eff_comp_psv );
		System.out.println("degat moyen de l'arme " + degat_moy_arme);
		System.out.println("bonus_min_arme " + bonus_min_arme);
		System.out.println("bonus_max_arme " + bonus_max_arme);
		System.out.println("chance_cc " + chance_cc*100);
		System.out.println("degat_cc " + degat_cc*100);
		System.out.println("stat Base :" + stat_base);
		double dps = (1 + eff_comp_psv) * (degat_moy_arme + (bonus_min_arme + bonus_max_arme) / 2) * vit_attaque * ( 1+(chance_cc * degat_cc)) * (1 + stat_base / 100);
		return dps;
	}

	
}
