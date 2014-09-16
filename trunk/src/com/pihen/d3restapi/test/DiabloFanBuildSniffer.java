package com.pihen.d3restapi.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DiabloFanBuildSniffer {

	
	
	public static void main(String[] args) throws IOException {
		
		String html = "http://www.diablofans.com/builds";
		String tablename="builds";
		
		 Document doc = Jsoup.connect(html).get();
         Elements tableElements = doc.getElementsByAttribute(tablename);
         
         System.out.println(tableElements);
	}
}
