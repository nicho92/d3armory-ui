package org.armory.d3.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.service.configuration.Configuration;

public class D3ProgressLeaderBoard 
{

	Document doc ;
	

	
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
