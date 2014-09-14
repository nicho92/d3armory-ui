package org.armory.d3.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**@deprecated*/
public class LootHtmlTableModel extends DefaultTableModel implements Serializable{
	
	public static String html = "http://diablo.incgamers.com/forums/threads/legendary-drop-rates-data-torment-drop.858143/";
	private List<String[]> list = new ArrayList<String[]>();
	
	public void init() {
		      try {
		         Document doc = Jsoup.connect(html).get();
		         Elements tableElements = doc.select("table");
		         Elements tableRowElements = tableElements.select(":not(thead) tr");
		         list = new ArrayList<String[]>();
		         boolean add=false;
		         String typeval = "";
	        	        for (int i = 1; i<tableRowElements.size();i++) // on ne prend pas la premiere ligne en compte
				         {
				        	 Elements rowItems = tableRowElements.get(i).select("td");
				        	 String tab[] = new String[9];
				        	 for(int j=0;j<rowItems.size();j++)
				        	 { 
				        		 if(!rowItems.get(0).text().startsWith("Item") && !rowItems.get(0).text().startsWith("[") && !rowItems.get(0).text().startsWith(" "))
				        		 {
			        				 if(rowItems.size()>1)
			        				 {
			        					 tab[j+1]=rowItems.get(j).text().replace("\\*", "");
			        					 tab[0]=typeval;
			        					 add=true;
		        				 	 }
			        				 else
			        				 {
			        					 typeval=rowItems.get(0).text();
			        				 }
			        			}
				        	 }
				        	 if(add)
				        		 list.add(tab);
				       
				        	 add=false;
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
		case 0: return "Type";
		case 1: return "Item";
		case 2 : return "Torment Only";
		case 3 : return "demon-hunter";
		case 4 : return "barbare";
		case 5 : return "wizard";
		case 6 : return "witch-doctor";
		case 7 : return "monk";
		case 8 : return "crusader";
		default : return "";
		}
	}
	
	
	@Override
	public int getRowCount() {
		if(list==null)
			return 0;
		
		return list.size();
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		return list.get(row)[column];
	}
	
	
	@Override
	public int getColumnCount() {
		return 9;
	}

	
	public Class getColumnClass(int colonne) {
	       return String.class;
       
    }
	
	public boolean isCellEditable(int arg0, int arg1) {
			return false;
	}

}
