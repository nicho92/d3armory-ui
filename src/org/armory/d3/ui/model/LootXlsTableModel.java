package org.armory.d3.ui.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class LootXlsTableModel extends DefaultTableModel implements Serializable{
	
	private List<String[]> list = new ArrayList<String[]>();
	
	public static void main(String[] args) {
		new LootXlsTableModel().init();
	}
	
	
	public void init() {
		      try {
		    	  
		    	//  FileReader fr = new FileReader(getClass().getResource("/org/armory/d3/ui/resources/loot.csv").getFile());
		    	  InputStreamReader fr = new InputStreamReader( new URL("https://d3armory-ui.googlecode.com/svn/trunk/src/org/armory/d3/ui/resources/loot.csv").openStream(),"ISO-8859-1");
	
		    	  
		    	  BufferedReader br = new BufferedReader(fr);
		    	  String ligne= br.readLine();
		    	  while(ligne!=null)
		    	  {
		    		  list.add(ligne.split(";"));
		    		  ligne=br.readLine();
		    	  }
		    	  
		    	  
		      } catch (Exception e) {
		    	 e.printStackTrace();
		      }
	  }
	
	@Override
	public String getColumnName(int column) {
		switch (column)
		{
		case 0: return "Type";
		case 1: return "Item";
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
			return 0;
		
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
