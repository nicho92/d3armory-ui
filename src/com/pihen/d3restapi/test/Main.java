package com.pihen.d3restapi.test;

import java.util.List;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.Tag;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
							Profile profile = D3ArmoryControler.getInstance().getProfil(new Tag("eu", "nicho92", new Long(2603)));
							List<Hero> heroes = profile.getHeroes();
							
							Hero h = D3ArmoryControler.getInstance().getHeroDetails(heroes.get(0));
							
							
							System.out.println(h.getLegendaryPowers()[0]);
//							for(Hero h: heroes)
//							{
//								h = D3ArmoryControler.getInstance().getHeroDetails(h);
//								D3ArmoryControler.getInstance().initStuffHero(h);
//								for(Item i : h.getItems().getItemsMap().values())
//								{
//									D3ArmoryControler.getInstance().getRecorder().saveItem(i);
//								}
//								
//								
//								
//							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
			
		//System.out.println(new RSSReader().getRss("http://www.diablofans.com/news.rss"));
	
	}


