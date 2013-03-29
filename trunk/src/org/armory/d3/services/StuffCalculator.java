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

public class StuffCalculator {

	
	private List<Item> stuffs;
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
				piecesbyset.put(i.getSet(),LegendarySet.getNbItemSet(stuffs, i.getSet()));
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
					bonusItem.put(cleg +"_gem"+compteur, gems[g].getAttributesRaw().get(cleg));
				}
			}
			//fin des gems
			
			Iterator<String> keys = i.getAttributesRaw().keySet().iterator();
			while(keys.hasNext())
			{
				String cle = keys.next();
				compteur++;
				System.out.println(cle + "#"+i.getName()+" "+i.getAttributesRaw().get(cle));
				bonusItem.put(cle+"_"+i.getName().replaceAll(" ", "_")+"_"+compteur, i.getAttributesRaw().get(cle));
			}
			
		}//fin de boucle sur les items
		
		//on ajout les bonus de set
		Iterator<LegendarySet> it = piecesbyset.keySet().iterator();
		while(it.hasNext())
		{
			LegendarySet set = it.next();
			int nbpiece = piecesbyset.get(set);
			for(int i=0;i<nbpiece-1;i++)
			{
				Iterator<String> keys = set.getRanks().get(i).getAttributesRaw().keySet().iterator();
				String k = keys.next();
				compteur++;
				bonusItem.put(k+"_set"+compteur, set.getRanks().get(i).getAttributesRaw().get(k));
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
				if(elementfilter!=null && k.contains("#"))
				{
					if(k.contains(elementfilter))
					{
						if(debug)
							System.out.println(k + " " + bonusItem.get(k).getMoyenne());
						
						total=total+ bonusItem.get(k).getMoyenne();
					}
				}
				
				if(elementfilter==null)
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

		double bonus_min_arme=0;
		double bonus_max_arme=0;
		
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
		
		System.out.println("FINAL DPS :" + dps);
		return dps;
	}

	
}
