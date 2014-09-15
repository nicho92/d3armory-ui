package com.pihen.d3restapi.test;

import java.io.File;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;


public class Main {

	
	public static void main(String[] args) throws Exception {
	
	Configuration conf = new Configuration();
		  conf.setBattleTag("nicho92");
		  conf.setBattleTagCode(new Long(2603));
		  conf.setHost("eu.battle.net");
		  conf.setLocal("fr_FR");
		  
		  RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
          RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
          Profile profile = profileService.receiveEntity(conf);
          
          
          //Hero hero = profile.getHeroes().get(0);
          
          
          HeroSkillContainer build = D3ArmoryControler.getInstance().loadBuild(new File("Sentry 2.1"));
          System.out.println(build.getActive());
          
//
//	Configuration conf2 = new Configuration();
//			conf2.setBattleTag("test");
//			conf2.setBattleTagCode(new Long(1234));
//			conf2.setHost("eu.battle.net");
//			conf2.setLocal("fr_FR");
//	
//	HeroComparator comp = new HeroComparator();
	
//			comp.initComparator(conf, conf2);
	
	
	}
	
	
}

