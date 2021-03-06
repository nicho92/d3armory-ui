package org.armory.d3.ui.model;

import java.io.BufferedReader;
import java.io.FileReader;
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
		    	  
		    	  FileReader fr = new FileReader(getClass().getResource("/org/armory/d3/ui/resources/data/loot.csv").getFile());
		    	//  InputStreamReader fr = new InputStreamReader( new URL("https://raw.githubusercontent.com/nicho92/d3armory-ui/master/src/org/armory/d3/ui/data/loot.csv").openStream(),"ISO-8859-1");
	
		    	  
		    	  BufferedReader br = new BufferedReader(fr);
		    	  br.readLine(); //first line with title
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
		case 0 : return "Item";
		case 1 : return "Type";
		case 2 : return "Type Kadala";
		case 3 : return "demon-hunter";
		case 4 : return "monk";
		case 5 : return "barbarian";
		case 6 : return "crusader";
		case 7 : return "wizard";
		case 8 : return "witch-doctor";
		case 9 : return "necromancer";
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
		return 10;
	}

	
	public Class getColumnClass(int colonne) {
	       return String.class;
       
    }
	
	public boolean isCellEditable(int arg0, int arg1) {
			return false;
	}

}
