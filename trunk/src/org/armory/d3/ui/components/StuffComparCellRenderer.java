package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class StuffComparCellRenderer extends JLabel implements TableCellRenderer {
	
	private DecimalFormat formatter;
	
	public StuffComparCellRenderer() {
	    formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		formatter.setDecimalFormatSymbols(symbols);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
		this.setForeground(Color.WHITE);
		this.setText(String.valueOf(value));
		
		if(column==1)
		{
			this.setForeground(Color.GRAY);
			setText(formatter.format(value).toString());
		}
		
		if(column==2)
		{
			this.setForeground(Color.LIGHT_GRAY);
			setText(formatter.format(value).toString());
		}
		if(column==3)
		{
			if(table.getValueAt(row, column).toString().startsWith("+")){
		        this.setForeground(Color.GREEN);
	        } 
			else
	        if(table.getValueAt(row, column).toString().startsWith("-")){
	        	 this.setForeground(Color.RED);
	        }
	        else
	        {
	        	this.setForeground(Color.white);
	        }
		}
        return this;
    }
}
