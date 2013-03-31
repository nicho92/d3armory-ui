package org.armory.d3.ui.model;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.services.D3ArmoryControler;

public class TableauDetailsModel extends DefaultTableModel {

	public String getColumnName(int c) {
		if(c==0)
			return "Bonus";
		else if(c==1)
			return "Min";
		else
			return "Max";
	}

	
	@Override
	public int getColumnCount() {
		return 3;
	}


	public int getRowCount() {
		int row=0;
		
		if(D3ArmoryControler.getInstance().getCalculator()!=null)
			row = D3ArmoryControler.getInstance().getCalculator().getBonusItem().size();
		
		return row;
	}

	 public Class getColumnClass(int colonne) {
		 System.out.println(colonne);
         if (colonne==0) {
           return String.class;
         } 
         if (colonne==1) {
             return Double.class;
          } 
         
         if (colonne==0) {
           return Double.class;
          } 
        return Object.class; 
     }

	
	
	@Override
	public Object getValueAt(int row, int column) {
		Map<String,MinMaxBonus> map = D3ArmoryControler.getInstance().getCalculator().getBonusItem();
		Object[] entries= map.entrySet().toArray();
	        Map.Entry<String, MinMaxBonus> entry=(Map.Entry)entries[row];
	        if (column==0) {
	            return entry.getKey();
	        } else if (column==1) { // column==1
	            return entry.getValue().getMin();
	        }else if (column==2) { // column==1
	            return entry.getValue().getMax();
	        } else {
	            throw new IndexOutOfBoundsException("MapTableModel provides a 2-column table, column-index "+column+" is illegal.");
	        } 
        
	}

	


}
