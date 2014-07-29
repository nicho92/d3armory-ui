package com.pihen.d3restapi.test;

import java.io.File;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;
import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.KEY;


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
			
		Hero hero = profile.getHeroes().get(5);
					conf.setHeroId(
							hero.getId());
		D3ArmoryControler.getInstance().setConf(conf);
		hero = heroService.receiveEntity(conf);
		//hero= D3ArmoryControler.getInstance().loadHero(new File("Cynyda"));
					StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().initStuffHero(hero),hero);
					calc.calculate();
					
					
					D3ArmoryControler.getInstance().saveHero(hero);
					System.out.println("HERO : " + hero.getName() + " " + hero.getLevel() + " ("+ hero.getParagonLevel()+")");
					System.out.println("----------------------------------");
					
					for(KEY k : calc.getStatCalculator().keySet())
					{
						System.out.println(k + "--> " + calc.getStatCalculator().get(k));
					}
		}
}

