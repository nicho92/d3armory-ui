package org.armory.d3.ui.model;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;

public class DetailsDPSModel extends DefaultTableModel {

	private Item item ;
	
	 
	 
	public DetailsDPSModel()
	{
		
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;

//		if(item!=null)
//		{
//			for(Gem g:item.getGems())
//				for(String k: g.getAttributesRaw().keySet())
//					item.getAttributesRaw().put(k+"_GEM_", g.getAttributesRaw().get(k));
//		}
			
		 	
		
	}

	
	public DetailsDPSModel(Item i)
	{
		this.item=i;
	}
	
	

	public String getColumnName(int c) {
		if(c==0)
			return "Attributs";
		else
			return "Value";
		
	}

	
	public boolean isCellEditable(int row, int col) {
		if(col==1)
			return true;
		else
			return false;
		
	}

	public int getColumnCount() {
		return 2;
	}


	public int getRowCount() {
		if(item==null)
			return 0;
		
		return item.getAttributesRaw().size();
	}

	public Object getValueAt(int row, int column) {
		
		   Object[] entries=item.getAttributesRaw().entrySet().toArray();
	       Map.Entry entry=(Map.Entry)entries[row];

	        if (column==0) {
	            return entry.getKey();
	        } else if (column==1) { 
	            return entry.getValue();
	        } else {
	           return null;
	        }
	        
	}

	public void setValueAt(Object o, int row, int column) {
		double l = Double.parseDouble(o.toString());
 
		Object[] entries=item.getAttributesRaw().entrySet().toArray();
        Map.Entry entry=(Map.Entry)entries[row];
          
        if (column==1) {
        	if(entry.toString().contains("Percent"))
        		l=l/100;
        		
        		entry.setValue(new MinMaxBonus(l));
        }
     
	}

	
	
	
}
