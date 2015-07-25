package org.armory.d3.services.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import org.armory.d3.services.D3ArmoryControler;
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
	private String era="1";
	
	private int max = Integer.parseInt(D3ArmoryControler.getInstance().getProperty("maxResultLadder", "1000"));
	
	public BNetLadderRetriever(String region,String clazz,boolean season,boolean hardcore,String era) throws IOException {
		this.region=region;
		this.clazz=clazz;
		this.season=season;
		this.hardcore=hardcore;
		this.era=era;
	}
	

	public void setMax(int max) {
		this.max = max;
	}


	public void retrieveLadder() throws IOException {
		String hc="";
		String sea="era";
		
		if(hardcore)
			hc="hardcore-";
		
		if(season)
			sea="season";
		
		if(clazz.equals("2")||clazz.equals("3")||clazz.equals("4"))
			clazz="team-"+clazz;
		
		
		String html = "http://"+region+".battle.net/d3/en/rankings/"+sea+"/"+era+"/rift-"+hc+clazz;
		Document doc = Jsoup.connect(html).get();
		Elements tableElements = doc.getElementsByTag("tbody").select("tr");
		Iterator<Element> it = tableElements.listIterator();
		ladders = new LinkedHashMap<Integer,Ladder>();
		int i=0;
		int rank=1;
        while(it.hasNext() && i<max)
        {
        	Element e = it.next();
        	
        	String rk=e.getElementsByClass("cell-Rank").text().trim();
        
        	if(rk.indexOf(".")!=-1)
        		rank =Integer.parseInt(rk.substring(0, rk.indexOf(".")));
        	
        	
	        String tag = e.getElementsByClass("cell-BattleTag").first().text().trim();
	        
	        String url=e.getElementsByClass("cell-BattleTag").select("a").attr("href");
	        String profile = url.substring("/d3/en/profile/".length(),url.length()-1);
	        
	        int levelRift= Integer.parseInt(e.getElementsByClass("cell-RiftLevel").html().trim());
	        
	        String time = e.getElementsByClass("cell-RiftTime").html().trim();
	        ImageIcon ic;
	        try {
				 ic = new ImageIcon(new URL(e.getElementsByClass("class-portrait").first().attr("src")));
			} catch (Exception e1) {
				ic = new ImageIcon();
			}
	        
	        String date = e.getElementsByClass("cell-CompletedTime").html().trim();
	        	Ladder l = new Ladder();
	        		l.setLevelRift(levelRift);
	        		l.setRank(rank);
	        		l.setTime(time);
	        		l.setDate(date);
	        		l.setName(tag);
	        		l.setProfile(profile.replaceAll("-", "#")+"#"+region);
	        		l.setIcon(ic);
        	ladders.put(i++, l);
        }
        
	}
	
	public Map<Integer, Ladder> getLadders() {
		return ladders;
	}

	
}
