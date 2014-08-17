package org.armory.d3.ui.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LootHtmlTableModel extends DefaultTableModel{
	
	public static String html = "http://diablo.incgamers.com/forums/threads/legendary-drop-rates-data-torment-drop.858143/";
	private Elements tableRowElements;
	
	
	public void init() {
		      try {
		         Document doc = Jsoup.connect(html).get();
		         Elements tableElements = doc.select("table");
		         tableRowElements = tableElements.select(":not(thead) tr");
		         fireTableDataChanged();
		        
		      } catch (Exception e) {
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
		if(tableRowElements==null)
			init();
		return tableRowElements.size();
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		Elements rowItems = null ;
		try{
			rowItems = tableRowElements.get(row).select("td");
			
		if(rowItems.get(0).text().startsWith("Item"))
			 return "";
		 
		 if(rowItems.size()==1)
			return rowItems.get(0).text();
		 
	     return rowItems.get(column).text();
		}
		catch(IndexOutOfBoundsException iooe){
			System.err.println(row + " " + column + " " + rowItems);
			return null;
		}
	}
	
	
	@Override
	public int getColumnCount() {
		return 8;
	}

}
