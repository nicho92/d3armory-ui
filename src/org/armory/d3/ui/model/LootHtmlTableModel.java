package org.armory.d3.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LootHtmlTableModel extends DefaultTableModel{
	
	public static String html = "http://diablo.incgamers.com/forums/threads/legendary-drop-rates-data-torment-drop.858143/";
	private List<String[]> list ;
	
	public void init() {
		      try {
		         Document doc = Jsoup.connect(html).get();
		         Elements tableElements = doc.select("table");
		         Elements tableRowElements = tableElements.select(":not(thead) tr");
		         list = new ArrayList<String[]>();
		         
		         
		         for (int i = 1; i<tableRowElements.size();i++) // on ne prend pas la premiere ligne en compte
		         {
		        	 Elements rowItems = tableRowElements.get(i).select("td");
		        	 String tab[] = new String[8];
		        	 for(int j=0;j<rowItems.size();j++)
		        	 {
		        		 if(!rowItems.get(0).text().startsWith("Item"))
			        			 if(rowItems.size()>1)
			        				 tab[j]=rowItems.get(j).text();
		        	 }
		        	 list.add(tab);
		        	 
		         }
		      } catch (Exception e) {
		    	  String[] s = new String[8];
		    	  list = new ArrayList<String[]>();
		    	  list.add(s);
		    	  
		         e.printStackTrace();
		      }
	  }
	
	@Override
	public String getColumnName(int column) {
		switch (column)
		{
		case 0: return "Item";
		case 1 : return "Torment Only";
		case 2 : return "demon-hunter";
		case 3 : return "barbare";
		case 4 : return "wizard";
		case 5 : return "witch-doctor";
		case 6 : return "monk";
		case 7 : return "crusader";
		default : return "";
		}
	}
	
	
	@Override
	public int getRowCount() {
		if(list==null)
			init();
		
		return list.size();
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		return list.get(row)[column];
	}
	
	
	@Override
	public int getColumnCount() {
		return 8;
	}

	
	public Class getColumnClass(int colonne) {
	       return String.class;
       
    }
	
	public boolean isCellEditable(int arg0, int arg1) {
			return false;
	}

}
