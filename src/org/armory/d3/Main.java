package org.armory.d3;

import java.util.HashMap;
import java.util.Map;

import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.Profile;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.StuffCalculator;

import com.sdfteam.d3armory.service.configuration.Configuration;
import com.sdfteam.d3armory.service.remote.RemoteService;
import com.sdfteam.d3armory.service.remote.SpringRemoteService;
import com.sdfteam.d3armory.service.util.EnumerationStuff;


public class Main {

	
	public static void main(String[] args) throws Exception  {
		Configuration conf = new Configuration();
					  conf.setBattleTag("djae");
					  conf.setBattleTagCode(new Long(2225));
					  conf.setHost("eu.battle.net");
					  conf.setLocal("fr_FR");
					  
		RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
		RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
//		RemoteService<Item> itemService = new SpringRemoteService(Item.class);
		
		Profile profile = profileService.receiveEntity(conf);
		
			
			Hero hero = profile.getHeroes().get(0);
				 conf.setHeroId(hero.getId());
				 D3ArmoryControler.getInstance().setConf(conf);
				 
				 hero = heroService.receiveEntity(conf);
				 System.out.println(hero.getName() + " "+ hero.getClazz() + " pg:"+ hero.getParagonLevel());
				 
				 System.out.println("============================================================================================");
					Item head = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getHead());
					Item foot = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getFeet());
					Item shoulders = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getShoulders());
					Item gants = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getHands());
					Item bracers = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getBracers());
					Item legs = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getLegs());
					Item neck = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getNeck());
					Item belt = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getWaist());
					Item ringright = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getLeftFinger());
					Item ringleft = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getRightFinger());
					Item mainHand = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getMainHand());
					if(mainHand!=null)
						mainHand.setMainHand(true);
					Item offhand = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getOffHand());
						
					Item torso = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getTorso());
					
					Map<EnumerationStuff,Item> stuffs = new HashMap<EnumerationStuff, Item>();
											  stuffs.put(EnumerationStuff.HEAD, head);
											  stuffs.put(EnumerationStuff.SHOULDERS, shoulders);
											  stuffs.put(EnumerationStuff.NECK, neck);
											  stuffs.put(EnumerationStuff.TORSO, torso);
											  stuffs.put(EnumerationStuff.GANT, gants);
											  stuffs.put(EnumerationStuff.BRACER, bracers);
											  stuffs.put(EnumerationStuff.BELT, belt);
											  stuffs.put(EnumerationStuff.LEGS, legs);
											  stuffs.put(EnumerationStuff.RING_RIGHT, ringright);
											  stuffs.put(EnumerationStuff.RING_LEFT, ringleft);
											  stuffs.put(EnumerationStuff.MAIN_HAND, mainHand);
											  stuffs.put(EnumerationStuff.OFF_HAND, offhand);
											  stuffs.put(EnumerationStuff.FEET, foot);
					  
					StuffCalculator calculator = new StuffCalculator(stuffs,hero);
					System.out.println("DPS UNBUFFED = " + calculator.calculateUnbuffedDPS());
					
						
					
					
						
				 		
		}
}
