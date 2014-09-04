package com.pihen.d3restapi.test;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.KEY;


public class Main {

	
	public static void main(String[] args) throws Exception  {
		Configuration conf = new Configuration();
					  conf.setBattleTag("wacha");
					  conf.setBattleTagCode(new Long(2223));
					  conf.setHost("eu.battle.net");
					  conf.setLocal("fr_FR");
		
		RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
		RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
		Profile profile = profileService.receiveEntity(conf);
		
		
		int i=0;
		//for(int i=0;i<8;i++)
		{
		Hero hero = profile.getHeroes().get(i);
					conf.setHeroId(hero.getId());
		D3ArmoryControler.getInstance().setConf(conf);
		hero = heroService.receiveEntity(conf);
		StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().initStuffHero(hero),hero);
						calc.calculate();
						
		System.out.println("HERO : " + hero.getName() + " " + hero.getLevel() + " ("+ hero.getParagonLevel()+")");
		System.out.println(calc.getStuffs().get(EnumerationStuff.RING_RIGHT));		
		System.out.println("----------------------------------");
		System.out.println(D3ArmoryControler.getInstance().getListTags("us"));
		
		}
	}
}

