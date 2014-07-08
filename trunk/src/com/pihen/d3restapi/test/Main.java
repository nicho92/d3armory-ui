package com.pihen.d3restapi.test;

import java.util.HashMap;
import java.util.Map;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.StuffCalculator;


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
			
		Hero hero = profile.getHeroes().get(2);
			conf.setHeroId(hero.getId());
			D3ArmoryControler.getInstance().setConf(conf);
			hero = heroService.receiveEntity(conf);

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
											  
					StuffCalculator calc = new StuffCalculator(stuffs,hero);
					
					System.out.println("HERO : " + hero.getName());
					System.out.println("CritC : " +calc.getCritChance()*100);
					System.out.println("CritD : " + calc.getCritDamage()*100);
					System.out.println(hero.getPrimaryStat() + " : " + calc.getPrimaryStatValue());
					System.out.println("%fireDamage : " + calc.filterStats("Damage_Dealt_Percent_Bonus", "Fire")*100);
					System.out.println("%ColdDamage : " + calc.filterStats("Damage_Dealt_Percent_Bonus", "Cold")*100);
					System.out.println("%LightningDamage : " + calc.filterStats("Damage_Dealt_Percent_Bonus", "Lightning")*100);
					System.out.println("%PoisonDamage : " + calc.filterStats("Damage_Dealt_Percent_Bonus", "Poison")*100);
					System.out.println("%HolyDamage : " + calc.filterStats("Damage_Dealt_Percent_Bonus", "Holy")*100);
					System.out.println("%PhysicalDamage : " + calc.filterStats("Damage_Dealt_Percent_Bonus", "Physical")*100);
					
									
		}
}
