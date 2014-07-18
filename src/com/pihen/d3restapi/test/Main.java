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
			
		Hero hero = profile.getHeroes().get(0);
					conf.setHeroId(hero.getId());
		D3ArmoryControler.getInstance().setConf(conf);
		hero = heroService.receiveEntity(conf);

					StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().initStuffHero(hero),hero);
					calc.calculate();
					
					System.out.println("HERO : " + hero.getName() + " " + hero.getLevel() + " ("+ hero.getParagonLevel()+")");
					System.out.println("Profile DPS : " + hero.getStats().getDamage());
					
					System.out.println("CritC : " +calc.getCritChance()*100);
					System.out.println("CritD : " + calc.getCritDamage()*100);
					System.out.println(hero.getPrimaryStat() + " : " + calc.getPrimaryStatUnbuffedValue());
					System.out.println("Vita : " + calc.getVitality());
					System.out.println("HP : " + calc.getHP());
					System.out.println("Armor :" + calc.getStats().get("ARMOR"));
					System.out.println("RAW DPS : " + calc.calculate());
									
		}
}

