package org.armory.d3.services.impl;

import java.io.IOException;

import org.armory.d3.services.LeaderBord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.service.configuration.Configuration;

public class D3ProgressLeaderBoard implements LeaderBord 
{

	Document doc ;
	

	
	public D3ProgressLeaderBoard(Configuration conf,Hero h) throws IOException {
		
		String html = "https://www.diabloprogress.com/hero/";
		
    	html= html+conf.getBattleTag()+"-"+conf.getBattleTagCode()+"/"+h.getName()+"/"+h.getId();
    	doc = Jsoup.connect(html).get();
    	
	}
	
	
	/* (non-Javadoc)
	 * @see org.armory.d3.services.LeaderBord#getWorldParangonLevel()
	 */
	@Override
	public String getWorldParangonLevel()
	{
		try{
		return doc.select("tbody").select("td").select("table").select("td").get(1).text();
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	/* (non-Javadoc)
	 * @see org.armory.d3.services.LeaderBord#getRegionalParangonLevel()
	 */
	@Override
	public String getRegionalParangonLevel()
	{
		return doc.select("tbody").select("td").select("table").select("td").get(3).text();	
	}
	
	/* (non-Javadoc)
	 * @see org.armory.d3.services.LeaderBord#getWorldDPS()
	 */
	@Override
	public String getWorldDPS()
	{
		return doc.select("tbody").select("td").select("table").get(2).select("td").get(1).text();
	}
	
	/* (non-Javadoc)
	 * @see org.armory.d3.services.LeaderBord#getRegionalDPS()
	 */
	@Override
	public String getRegionalDPS()
	{
		return doc.select("tbody").select("td").select("table").get(2).select("td").get(4).text();
	}
	
	
}
