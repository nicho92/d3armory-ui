package com.pihen.d3restapi.test;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.model.StuffComparaisonModel;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.Tag;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.KEY;

public class Main {

	public static void main(String[] args) throws Exception {

 		
		Profile profile = D3ArmoryControler.getInstance().getProfil(new Tag("eu", "nicho92", new Long(2603)));
		
//		List<Hero> heroes = profile.getHeroes();
//		
//		System.out.println(D3ArmoryControler.getInstance().getLastHeroPlayed());
//		
		Hero h = D3ArmoryControler.getInstance().getHeroDetails(profile.getHeroes().get(0));
		Hero h2 = D3ArmoryControler.getInstance().getHeroDetails(profile.getHeroes().get(1));
		
		
		D3ArmoryControler.getInstance().initStuffHero(h);
		
		Item weapon2 = D3ArmoryControler.getInstance().loadItemDetails(h2.getItems().get(EnumerationStuff.MAIN_HAND));
		
		D3ArmoryControler.getInstance().initCalculator(h.getItems().getItemsMap());
		
		
		System.out.println("========================");
		StuffCalculator c2 = D3ArmoryControler.getInstance().getCalculator().compareStuffWithItem(EnumerationStuff.MAIN_HAND, weapon2);
		
		
		StuffComparaisonModel mod = new StuffComparaisonModel();
		mod.setStuffCalc(D3ArmoryControler.getInstance().getCalculator(), c2);
		
		
		for(int i = 0;i<=mod.getRowCount();i++)
		{
			System.out.println("\n");
			for(int j =0;j<=mod.getColumnCount();j++)
				System.out.print(" " + mod.getValueAt(i, j));
		}
			
		
		
	}
}


