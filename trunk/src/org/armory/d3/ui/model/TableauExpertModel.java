package org.armory.d3.ui.model;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.service.util.BuffCalculator;

public class TableauExpertModel extends DefaultTableModel {

	public String getColumnName(int c) {
		if(c==0)
			return "Bonus";
		if(c==1)
			return "Item/Skill";
		if(c==2)
			return "Valeur";
		
		return "";
	}

	
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}


	public int getColumnCount() {
		return 3;
	}


	public int getRowCount() {
		int row=0;
		
		if(D3ArmoryControler.getInstance().getCalculator()!=null)
			row = D3ArmoryControler.getInstance().getCalculator().getStatAttributs().size();
		
		return row;
	}

	 public Class getColumnClass(int colonne) {
	     if (colonne==0) {
           return String.class;
         }
	     if (colonne==1) {
	           return String.class;
	         } 
         if (colonne==2) {
             return Double.class;
          } 
         return Object.class; 
     }

	
	
	@Override
	public Object getValueAt(int row, int column) {
		Map<String,MinMaxBonus> map = D3ArmoryControler.getInstance().getCalculator().getStatAttributs();
		Object[] entries= map.entrySet().toArray();
	        Map.Entry<String, MinMaxBonus> entry=(Map.Entry)entries[row];
	        int pos = entry.getKey().lastIndexOf("_");
	        
	        if (column==0) {
	        	String ret = entry.getKey().replaceAll(BuffCalculator.PREFIX, "_").substring(0, pos);
	
	        	return ret;
	        } 
	        else if (column==1) { // column==1
	        	return entry.getKey().substring(pos+1, entry.getKey().length());
	        } 
	        else if (column==2) { // column==1
	            return entry.getValue().getMoyenne();
	        } 
	        
	        else {
	            throw new IndexOutOfBoundsException("MapTableModel provides a 2-column table, column-index "+column+" is illegal.");
	        } 
        
	}

	


}
