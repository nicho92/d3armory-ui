package com.pihen.d3restapi.test;

import java.io.IOException;
import java.util.Iterator;

import org.armory.d3.services.D3ArmoryControler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;

public class D3ProgressLeaderBoard {

	public static void main(String[] args) throws IOException {
		
		String html = "http://www.diabloprogress.com/hero/";
		Configuration conf = new Configuration();
		  conf.setBattleTag("nicho92");
		  conf.setBattleTagCode(new Long(2603));
		  conf.setHost("eu.battle.net");
		  conf.setLocal("fr_FR");
		  
		 RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
        RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
        Profile profile = profileService.receiveEntity(conf);
        
        D3ArmoryControler.getInstance().setConf(conf);
        Hero h = profile.getHeroes().get(8);
        
       html= html+conf.getBattleTag()+"-"+conf.getBattleTagCode()+"/"+h.getName()+"/"+h.getId();
      Document doc = Jsoup.connect(html).get();
      //Elements tableElements = doc.getElementById("primary");
       System.out.println(doc.select("tbody").select("td"));
	}
	
	
	public String getWorldParangonLevel()
	{
	return "";	
	}
	
	public String getRegionalParangonLevel()
	{
		return "";
	}
	
}
