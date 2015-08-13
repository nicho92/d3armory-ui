package com.pihen.d3restapi.test;

import java.util.List;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.Tag;

public class Main {

	public static void main(String[] args) throws Exception {
 
		
		Profile profile = D3ArmoryControler.getInstance().getProfil(new Tag("eu", "djae", new Long(2225)));
		
		List<Hero> heroes = profile.getHeroes();
		
		System.out.println(D3ArmoryControler.getInstance().getLastHeroPlayed());
		
		
		
		/*	new Thread(new Runnable() {
				
				public void run() {
					
				List<Tag> tags = D3ArmoryControler.getInstance().getListTags();
					
					
					for(Tag tag : tags)
					{
						try {
							Profile profile = D3ArmoryControler.getInstance().getProfil(tag);
							List<Hero> heroes = profile.getHeroes();
							
							for(Hero h: heroes)
							{
								h = D3ArmoryControler.getInstance().getHeroDetails(h);
								D3ArmoryControler.getInstance().initStuffHero(h);
								for(Item i : h.getItems().getItemsMap().values())
								{
									D3ArmoryControler.getInstance().getRecorder().saveItem(i);
								}
								
								
								
							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				}
			}).start();*/
		
	
		//System.out.println(new RSSReader().getRss("http://www.diablofans.com/news.rss"));
	
	}
	
	
}

