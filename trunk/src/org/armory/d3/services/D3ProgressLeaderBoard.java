package org.armory.d3.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;

public class D3ProgressLeaderBoard 
{

	Document doc ;
	
	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		  conf.setBattleTag("nicho92");
		  conf.setBattleTagCode(new Long(2603));
		  conf.setHost("eu.battle.net");
		  conf.setLocal("fr_FR");
		  
		  RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
          RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
          Profile profile = profileService.receiveEntity(conf);
          
          D3ArmoryControler.getInstance().setConf(conf);
          Hero h = profile.getHeroes().get(2);
               conf.setHeroId(h.getId());
               h=heroService.receiveEntity(conf);
		
		D3ProgressLeaderBoard classement = new D3ProgressLeaderBoard(conf,h);
		System.out.println(h.getName() +"  "+ classement.getRegionalDPS());
	}
	
	
	public D3ProgressLeaderBoard(Configuration conf,Hero h) throws IOException {
		
		String html = "http://www.diabloprogress.com/hero/";
		
    	html= html+conf.getBattleTag()+"-"+conf.getBattleTagCode()+"/"+h.getName()+"/"+h.getId();
    	doc = Jsoup.connect(html).get();
    	
	}
	
	
	public String getWorldParangonLevel()
	{
		return doc.select("tbody").select("td").select("table").select("td").get(1).text();	
	}
	
	public String getRegionalParangonLevel()
	{
		return doc.select("tbody").select("td").select("table").select("td").get(3).text();	
	}
	
	public String getWorldDPS()
	{
		return doc.select("tbody").select("td").select("table").get(2).select("td").get(1).text();
	}
	
	public String getRegionalDPS()
	{
		return doc.select("tbody").select("td").select("table").get(2).select("td").get(4).text();
	}
	
	
}
