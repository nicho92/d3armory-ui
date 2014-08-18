package org.armory.d3.ui.model;

import javax.swing.table.DefaultTableModel;

import com.pihen.d3restapi.service.util.StuffCalculator;

public class StuffComparaisonModel extends DefaultTableModel{

	private StuffCalculator a;
	private StuffCalculator b;

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return StuffCalculator.KEY.values().length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		try{
		if(column==0)
		{
			return StuffCalculator.KEY.values()[row];
		}
		if(column==1)
		{
			return  StuffCalculator.format(a.getStats().get(StuffCalculator.KEY.values()[row]));
		}
		if(column==2)
		{
			return StuffCalculator.format(b.getStats().get(StuffCalculator.KEY.values()[row]));
		}
		if(column==3)
		{
			double vala=a.getStats().get(StuffCalculator.KEY.values()[row]);
			double valb=b.getStats().get(StuffCalculator.KEY.values()[row]);
				
			if((Double)getValueAt(row, 2)>(Double)getValueAt(row, 1))
				return "+"+StuffCalculator.format(valb-vala)+ " ("+StuffCalculator.format(((valb - vala) / valb ) * 100)+"%)";
			
			if((Double)getValueAt(row, 2)<(Double)getValueAt(row, 1))
				return "-"+StuffCalculator.format(valb-vala)+ " ("+StuffCalculator.format(((valb - vala) / valb ) * 100)+"%)";
			
			return  StuffCalculator.format(valb-vala);
		}

		return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		switch (column)
		{
		case 0: return "ATTRIBUTS";
		case 1: return "CURRENT";
		case 2: return "WITH NEW ITEM";
		case 3: return "COMPARISON";
		default : return "";
		}
		
	}

	public void setStuffCalc(StuffCalculator a, StuffCalculator b) {
		this.a=a;
		this.b=b;
		
	}
	
	
	

}
