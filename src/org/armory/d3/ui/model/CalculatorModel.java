package org.armory.d3.ui.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.KEY;

public class CalculatorModel extends DefaultTableModel {

	DecimalFormat formatter;
	
	public CalculatorModel() {
		 formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
			DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
			symbols.setGroupingSeparator(' ');
			formatter.setDecimalFormatSymbols(symbols);
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column==0)
				return "Bonus";
			else
				return "Value";
		
	}
	
	@Override
	public int getRowCount() {
		return StuffCalculator.KEY.values().length;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		 Object[] entries=D3ArmoryControler.getInstance().getCalculator().calculate().entrySet().toArray();
	        Map.Entry entry=(Map.Entry)entries[row];

	        if (column==0) {
	            return entry.getKey();
	        } else if (column==1) { 
	            return formatter.format((Double)entry.getValue());
	        } else {
	           return null;
	        }
	}
}
