package com.pihen.d3restapi.test;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.Tag;

public class Main {

	public static void main(String[] args) throws Exception {
 		
		Profile profile = D3ArmoryControler.getInstance().getProfil(new Tag("eu", "nicho92", new Long(2603)));
		
//		List<Hero> heroes = profile.getHeroes();
//		
//		System.out.println(D3ArmoryControler.getInstance().getLastHeroPlayed());
//		
		Hero h = D3ArmoryControler.getInstance().getHeroDetails(profile.getHeroes().get(1));


				for(Item it : h.getLegendaryPowers())
				{
					it = D3ArmoryControler.getInstance().loadItemDetails(it);
					String k = it.getPassiveKey();
					System.out.println(k);
					System.out.println(k+"_KANAI_"+it.getName()+ " "+ new MinMaxBonus(it.getAttributesRaw().get(k).getMax()));
				}
	
		//System.out.println(new RSSReader().getRss("http://www.diablofans.com/news.rss"));
	
	}
	
	
}

