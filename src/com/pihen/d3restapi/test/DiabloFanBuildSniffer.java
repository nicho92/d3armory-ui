package com.pihen.d3restapi.test;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DiabloFanBuildSniffer {

	
	
	public static void main(String[] args) throws IOException {
		
		String html = "http://www.diablofans.com";
		String tablename="builds";
		
		Document doc = Jsoup.connect(html+"/builds").get();
        Elements tableElements = doc.getElementsByClass("listing");
         
        Iterator<Element> it = tableElements.listIterator();
        while(it.hasNext())
        {
        	Element e = it.next();
        	String name = e.getElementsByClass("d3build").html();
        	String url = (html+e.getElementsByClass("d3build").attr("href"));
        	System.out.println(name);
        }
        
        
       
	}
}
