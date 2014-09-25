package org.armory.d3.services;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pihen.d3restapi.beans.Ladder;

public class BNetLadderRetriever {

	Map<Integer,Ladder> ladders;
	private String region;
	private String clazz;
	private boolean season;
	private boolean hardcore;
	
	
	public BNetLadderRetriever(String region,String clazz,boolean season,boolean hardcore) throws IOException {
		this.region=region;
		this.clazz=clazz;
		this.season=season;
		this.hardcore=hardcore;
	}
	

	public void init() throws IOException {
		String hc="";
		String sea="era";
		
		if(hardcore)
			hc="hardcore-";
		
		if(season)
			sea="season";
		
		
		String html = "http://"+region+".battle.net/d3/en/rankings/"+sea+"/1/rift-"+hc+clazz;
		
		Document doc = Jsoup.connect(html).get();
		Elements tableElements = doc.getElementsByTag("tbody").select("tr");
		Iterator<Element> it = tableElements.listIterator();
		ladders = new LinkedHashMap<Integer,Ladder>();
		int i=0;
        while(it.hasNext() && i<500)
        {
        	Element e = it.next();

        	
        	String rk=e.getElementsByClass("cell-Rank").html().trim();
	        int rank =Integer.parseInt(rk.substring(0, rk.indexOf(".")));
	        
	        String tag = e.getElementsByClass("cell-BattleTag").first().text().trim();
	        
	        String url=e.getElementsByClass("cell-BattleTag").select("a").attr("href");
	        String profile = url.substring("/d3/en/profile/".length(),url.length()-1);
	        
	        int levelRift= Integer.parseInt(e.getElementsByClass("cell-RiftLevel").html().trim());
	        
	        String time = e.getElementsByClass("cell-CompletedTime").html().trim();
	        	Ladder l = new Ladder();
	        		l.setLevelRift(levelRift);
	        		l.setRank(rank);
	        		l.setTime(time);
	        		l.setName(tag);
	        		l.setProfile(profile.replaceAll("-", "#")+"#"+region);
        	ladders.put(i++, l);
        }
        
	}
	
	public static void main(String[] args) {
		try {
			new BNetLadderRetriever("us","crusader",false,true).init();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<Integer, Ladder> getLadders() {
		return ladders;
	}

	
}
