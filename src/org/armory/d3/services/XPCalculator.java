package org.armory.d3.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.pihen.d3restapi.beans.XP;

public class XPCalculator {

	Map<Integer,XP> lvlList ;
	
	public XP getXPByLevel(int level)
	{
		if(level<1)
			return new XP(0, 0, 0);
		
		return lvlList.get(level);
	}
	
	public XP getXPByTotalXP(long xp)
	{
		XP lvl=null;
		
		for(int k : lvlList.keySet()) 
		{
			if(lvlList.get(k).getTotalExp()>xp)
				return lvl;
			
			lvl=lvlList.get(k);
		}
		return null;
		
	}
	
	
	public XPCalculator() {
		init();
	}
	
	public void init() {
	      try {
	    	  InputStreamReader fr = new InputStreamReader( new URL("https://raw.githubusercontent.com/nicho92/d3armory-ui/master/src/org/armory/d3/ui/resources/data/XP.csv").openStream(),"ISO-8859-1");
	    	  lvlList = new LinkedHashMap<Integer,XP>();
	    	  
	    	  BufferedReader br = new BufferedReader(fr);
	    	  br.readLine();
	    	  String ligne= br.readLine();
	    	  int k =1;
	    	  while(ligne!=null)
	    	  {
	    		  int level = Integer.parseInt(ligne.split(";")[0]);
	    		  long xpLvl = Long.parseLong(ligne.split(";")[1].replaceAll(" ", ""));
	    		  long xpTot=  Long.parseLong(ligne.split(";")[2].replaceAll(" ", ""));
	    		  lvlList.put(k++,new XP(level,xpLvl,xpTot));
	    		  ligne=br.readLine();
	    	  }
	    	  
	    	  
	      } catch (Exception e) {
	    	 e.printStackTrace();
	      }
	}
	
	
	
}
