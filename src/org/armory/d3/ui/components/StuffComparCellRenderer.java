package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class StuffComparCellRenderer extends JLabel implements TableCellRenderer {
	
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
		
		this.setForeground(Color.WHITE);
		this.setText(value.toString());
		
		if(column==1)
		{
			this.setForeground(Color.GRAY);
		}
		
		if(column==2)
		{
			this.setForeground(Color.LIGHT_GRAY);
		}
		if(column==3){
			if(table.getValueAt(row, column).toString().startsWith("+")){
		        this.setForeground(Color.GREEN);
	        } 
	        
	        if(table.getValueAt(row, column).toString().startsWith("-")){
	        	 this.setForeground(Color.RED);
	        }
		}
        return this;
    }
}
