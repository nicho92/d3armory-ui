package com.pihen.d3restapi.test;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GriftLadderSniffer {

	public GriftLadderSniffer() throws IOException {
		String html = "http://eu.battle.net/d3/fr/rankings/era/1/rift-dh";
		//http://eu.battle.net/d3/fr/rankings/era/1/rift-crusader
		
		
		Document doc = Jsoup.connect(html).get();
		Elements tableElements = doc.getElementsByTag("tbody").select("tr");
		Iterator<Element> it = tableElements.listIterator();
        while(it.hasNext())
        {
        	Element e = it.next();
	        	System.out.println(e.select("td").html());
	        	System.out.println(e.select("td").select("strong").select("a").html());
	        	System.out.println(e.select("td").html());
	        	System.out.println(e.select("td").html());
        	
//        	if(!e.html().equals(""))
//        	{
//        		System.out.println(e.getElementsByClass("cell-Rank").html());
//        	   	System.out.println(e.getElementsByClass("cell-BattleTag").html());
//        	   	System.out.println(e.getElementsByClass("cell-RiftLevel").html());
//        	   	System.out.println(e.getElementsByClass("cell-RiftTime").html());
//        	}
        }
	}
	
	public static void main(String[] args) {
		try {
			new GriftLadderSniffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
