package com.pihen.d3restapi.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IncGamerLootParser {
	
	public static String html = "http://diablo.incgamers.com/forums/threads/legendary-drop-rates-data-torment-drop.858143/";
	public static void main(String[] args) {
		new IncGamerLootParser().getLootToolTip();
	}
	public String getLootToolTip(){
		
	       try {
	    	      
	         Document doc = Jsoup.connect(html).get();
	         Elements tableElements = doc.select("table");

	         Elements tableRowElements = tableElements.select(":not(thead) tr");
	         String res="Loot Drop Rate : ";
	         
	         for (int i = 0; i < tableRowElements.size(); i++) {
	            Element row = tableRowElements.get(i);
	            Elements rowItems = row.select("td");
	            for(int j=0 ; j<rowItems.size();j++)
	            	System.out.println(rowItems.get(j));
	            
	            
	            
	         }
	        return res;

	      } catch (IOException e) {
	         e.printStackTrace();
	         return null;
	      }
	}
	
	
	
	
}
