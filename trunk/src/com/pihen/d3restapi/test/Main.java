package com.pihen.d3restapi.test;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;


public class Main {

	
	public static void main(String[] args) throws Exception  {
		Configuration conf = new Configuration();
					  conf.setBattleTag("nicho92");
					  conf.setBattleTagCode(new Long(2603));
					  conf.setHost("eu.battle.net");
					  conf.setLocal("fr_FR");
		
		RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
		RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
		
		
		Profile profile = profileService.receiveEntity(conf);
			
		Hero h = profile.getHeroes().get(1);
			conf.setHeroId(h.getId());
			D3ArmoryControler.getInstance().setConf(conf);
			h = heroService.receiveEntity(conf);

			System.out.println(h.getSkills().getPassive().get(1).getSkill().getDescription());
			
				 /*	Item head = D3ArmoryControler.getInstance().getItemDetails(h.getItems().getHead());
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
					
	
					D3ArmoryControler.getInstance().setSelectedHero(hero);
					D3ArmoryControler.getInstance().initCalculator(stuffs);
					*/
									
		}
}
