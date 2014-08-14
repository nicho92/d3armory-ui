package com.pihen.d3restapi.test;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.Skill;
import com.pihen.d3restapi.beans.SkillRune;
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
		
		
		
	//	for(int i=0;i<6;i++)
		
		int i=5;
		{
		Hero hero = profile.getHeroes().get(i);
					conf.setHeroId(hero.getId());
		D3ArmoryControler.getInstance().setConf(conf);
		hero = heroService.receiveEntity(conf);
		
		SkillRune harmo = new SkillRune();
				  harmo.setSkill(new Skill());
				  harmo.getSkill().setTooltipUrl("skill/harmony");
				  harmo.getSkill().setName("Harmony PTR 2.1");
				  hero.getSkills().getPassive().set(1, harmo); //remplace old everything
				  
				  
		System.out.println(hero.getSkills().getPassive());
				  
		StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().initStuffHero(hero),hero);
						calc.calculate();
					
		System.out.println("HERO : " + hero.getName() + " " + hero.getLevel() + " ("+ hero.getParagonLevel()+")");
		for(KEY k : calc.getStats().keySet())
		{
				if(k.equals(KEY.TOUGHNESS))
					System.out.println(k + "--> " + calc.getStats().get(k));
		}
		System.out.println("----------------------------------");
		}
	}
}

