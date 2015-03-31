package com.pihen.d3restapi.test;

import java.io.File;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.RSSReader;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;


public class Main {

	public static void main(String[] args) throws Exception {
  
//TEST CACHED HERO FILES		
		//for(File f : new File(D3ArmoryControler.SERIALISATION_HERO_DIR).listFiles())
		{
			//Long number = Long.parseLong(f.getName().substring(0, f.getName().lastIndexOf(".")));
			Long number= (long) 8031600;
			Hero h = D3ArmoryControler.getInstance().loadHero(number);
			System.out.println("******"+h );
			//for(Item i : h.getItems().getItems())
			Item i = h.getItems().getOffHand();
			
			//	if(i!=null)
				System.out.println(i.getRandomAffixes());
		}
		
		
//		RSSReader read = new RSSReader();
//		System.out.println(read.getRss("http://www.diablofans.com/news.rss"));
//		System.out.println(read.getRss("http://eu.battle.net/d3/fr/feed/news"));
		
		
	}
	
	
}

