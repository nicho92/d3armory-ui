package org.armory.d3.ui.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.beans.MinMaxBonus;

public class ItemDetailsModel extends DefaultTableModel {

	private Map<String,MinMaxBonus> map;
	
	
	public ItemDetailsModel()
	{
		map = new TreeMap<String, MinMaxBonus>();
	}
	
	
	public String getColumnName(int c) {
		if(c==0)
			return "Bonus";
		else
			return "Valeur";
		
	}

	public String[] getAttributs()
	{
		Iterator<String> it = map.keySet().iterator();
		List<String> l = new ArrayList<String>();
		while(it.hasNext())
		{
			String att = it.next();
			att=att.replaceFirst("X", String.valueOf(map.get(att).getMoyenne()));
			l.add(att);
		}
		return l.toArray(new String[l.size()]);
	}
	
	public void addAttributes(String value,double valeur)
	{
		map.put(value, new MinMaxBonus(valeur));
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
		if(map==null)
			return 0;
		
		return map.size();
	}

	public Object getValueAt(int row, int column) {
		Object[] entries= map.entrySet().toArray();
	        Map.Entry<String, MinMaxBonus> entry=(Map.Entry)entries[row];
	        if (column==0) {
	            return entry.getKey();
	        } else if (column==1) { // column==1
	            return entry.getValue().getMoyenne();
	        } else {
	            throw new IndexOutOfBoundsException("MapTableModel provides a 2-column table, column-index "+column+" is illegal.");
	        } 
        
	}

	public void setValueAt(Object o, int row, int col) {
		List key = new ArrayList(map.keySet());
		map.get(key.get(row)).setValue(Double.parseDouble(o.toString()));
		
	}

	
	


}
