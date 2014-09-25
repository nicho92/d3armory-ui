package com.pihen.d3restapi.test;

import java.util.List;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.XPCalculator;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.SkillRune;
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
          
          XPCalculator xpc = new XPCalculator();
          long newXP = xpc.getXPByLevel(profile.getParagonLevel().intValue()).getTotalExp() + xpc.getXPByLevel(profile.getSeasonalProfiles().getSeason1().getParagonLevel().intValue()).getTotalExp();
          
          	System.out.println(xpc.getXPByTotalXP(newXP));
    
//          
//          List<HeroSkillContainer> builds = D3ArmoryControler.getInstance().loadBuilds();
//
//          for(HeroSkillContainer build: builds)
//          { 
//        	  System.out.println("--"+build.getNameBuild() +"-" + build.getClassBuild());
//        	  for(SkillRune r : build.getPassive())
//        		  System.out.println(r.getSkill() + " " + r.getRune());
//          }
//          

          
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

