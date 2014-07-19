package org.armory.d3.ui.model;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.StuffCalculator;

public class StuffDetailsModel extends DefaultTableModel {

	StuffCalculator calc;

	public StuffDetailsModel() {
		calc=D3ArmoryControler.getInstance().getCalculator();
	}

	
	public Item getItemAt(int i)
	{
		return calc.getAllItems().get(i);
	}
	

	public String getColumnName(int c) {
		try{
			switch(c)
			{
			case 0: return "Item";
			case 1: return "Name";
			case 2: return calc.getHero().getPrimaryStat() ;
			case 3: return "Crit chance" ;
			case 4: return "Crit damage" ;
			case 5: return "Attack Speed" ;
			case 6: return "Vitality" ;
			case 7: return "Elite Damage" ;
			default : return "";
			}
		}
		catch(Exception e)
		{
			return "";
		}
	}

	
	public boolean isCellEditable(int row, int col) {
		return false;
		
	}

	public int getColumnCount() {
		return 8;
	}


	public int getRowCount() {
		
		return EnumerationStuff.values().length+1;
	}

	public Object getValueAt(int row, int column) {
	try {	
			if(column==0)
			{
				return EnumerationStuff.values()[row];
			}
			if(column==1){
				return calc.getStuffs().get(EnumerationStuff.getValueAt(row)).getName();
			}
			if(column==2)
			{
				return calc.filter(calc.getStuffs().get(EnumerationStuff.getValueAt(row)), calc.getHero().getPrimaryStat(), null);
			}
			if(column==3)
			{
				return StuffCalculator.format(calc.filter(calc.getStuffs().get(EnumerationStuff.getValueAt(row)), "Crit_Percent", null)*100);
			}
			if(column==4)
			{
				return StuffCalculator.format(calc.filter(calc.getStuffs().get(EnumerationStuff.getValueAt(row)), "Crit_Damage", null)*100);
			}	
			if(column==5)
			{
				return StuffCalculator.format(calc.filter(calc.getStuffs().get(EnumerationStuff.getValueAt(row)), "Attacks_Per_Second_Percent", null)*100);
			}
			if(column==6)
			{
				return StuffCalculator.format(calc.filter(calc.getStuffs().get(EnumerationStuff.getValueAt(row)), "Vitality", null));
			}
			if(column==7)
			{
				return StuffCalculator.format(calc.filter(calc.getStuffs().get(EnumerationStuff.getValueAt(row)),"Damage_Percent_Bonus_Vs_Elites", null)*100);
			}
		return null;
	}catch(Exception e)
	{
		return null;
	}
	
	}

		
	
	
}
