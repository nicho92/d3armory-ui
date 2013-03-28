package org.armory.d3.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.armory.d3.beans.Gem;
import org.armory.d3.beans.HeroSkillContainer;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.LegendarySet;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.beans.Ranks;

public class StuffCalculator {

	
	private List<Item> stuffs;
	private HeroSkillContainer skills;
	private Map<String, MinMaxBonus> bonusItem;
	
	public StuffCalculator(List<Item> stuff, HeroSkillContainer skill) {
		this.stuffs=stuff;
		this.skills=skill;
		stuffs.remove(null);
		bonusItem = new HashMap<>();
		init();
	}


	public Map<String, MinMaxBonus> getBonusItem()
	{
		return bonusItem;
	}
	
	
	
	public void init()
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
				bonusItem.put(cle+"_"+compteur, i.getAttributesRaw().get(cle));
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


	
	
	/**
	 * @param stat : stat to filter (Critic,Strengh,...)
	 * @param elementfilter : elemental filter : Fire, Cold,Physical,Lightning, Holy, Arcane
	 * */
	public double getStat(String stat,String elementfilter) {
		double total=0.0;
		Iterator<String> keyIt = bonusItem.keySet().iterator();
		
		while(keyIt.hasNext())
		{
			String k = keyIt.next();
			if(k.startsWith(stat)||stat.equals(""))
			{
				if(elementfilter!=null && k.contains("#"))
				{
					String[] el = k.split("#");
					if(el[1].startsWith(elementfilter))
					{
						System.out.println(k + " " + bonusItem.get(k).getMoyenne());
						total=total+ bonusItem.get(k).getMoyenne();
					}
				}
				
				if(elementfilter==null)
				{
					System.out.println(k + " " + bonusItem.get(k).getMoyenne());
					total=total+ bonusItem.get(k).getMoyenne();
				}
				
			}
		}
		
		return total;	
		}
	
	
}
