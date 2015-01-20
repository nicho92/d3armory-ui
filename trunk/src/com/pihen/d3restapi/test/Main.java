package com.pihen.d3restapi.test;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;
import com.pihen.d3restapi.service.util.StuffCalculator;


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
          
          D3ArmoryControler.getInstance().setConf(conf);
//          for(Hero h : profile.getHeroes())
//          {
//        	  conf.setHeroId(h.getId());
//        	  h = heroService.receiveEntity(conf);
//        	  
//        	 for(Item i : h.getItems().getItems())
//        	 {
//        		 if(i!=null)
//        		 {
//        			 i=D3ArmoryControler.getInstance().getItemDetails(i);
//	        		 for(String k: i.getAttributesRaw().keySet())
//	        		 {
//	        			 if(k.startsWith("Item_Power_Passive"))
//	        			 {
//	        				 System.out.println(i.getName() + " " +k);
//	        			 }
//	        		 }
//        		 }
//        	 }
//          }
          
          Hero h = profile.getHeroes().get(2);
               conf.setHeroId(h.getId());
               h=heroService.receiveEntity(conf);
               StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().initStuffHero(h),h);
               calc.calculate();
               System.out.println(calc.getStatAttributs().get(StuffCalculator.KEY.DOT_DAMAGE));
               
              
               
               
               
               
               
          
          
          

	
	}
	
	
}

