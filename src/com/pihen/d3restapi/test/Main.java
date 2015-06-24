package com.pihen.d3restapi.test;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Item;


public class Main {

	public static void main(String[] args) throws Exception {
 
			new Thread(new Runnable() {
				
				public void run() {
					
					
				
					for(Item i:  D3ArmoryControler.getInstance().getRecorder().listSavedItems())
					{
						System.out.println(i);
					}
				
				
				/*	List<Tag> tags = D3ArmoryControler.getInstance().getListTags();
					
					
					for(Tag tag : tags)
					{
						try {
							Profile profile = D3ArmoryControler.getInstance().getProfil(tag);
							List<Hero> heroes = profile.getHeroes();
							
							for(Hero h: heroes)
							{
								h = D3ArmoryControler.getInstance().getHeroDetails(h);
								D3ArmoryControler.getInstance().initCalculator(h.getItems().getItemsMap());
								
								
								System.out.println(h + " " + D3ArmoryControler.getInstance().getCalculator().calculate().get(StuffCalculator.KEY.DPS_ELITE));
								
							}
						} catch (D3ServerCommunicationException e) {
							e.printStackTrace();
						}
					}
				*/	
				}
			}).start();
	}
	
	
}

