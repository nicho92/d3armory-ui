package org.armory.d3.ui.model;

import javax.swing.table.DefaultTableModel;

import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.ELEMENTS;
import com.pihen.d3restapi.service.util.StuffCalculator.SITUATIONAL;

public class EHPCalculatorModel extends DefaultTableModel {

	StuffCalculator calc;
	private boolean isElite;

	public EHPCalculatorModel(StuffCalculator mod) {
		calc=mod;
	
	}
	
	
	public StuffCalculator getCalculator() {
		return calc;
	}


	public void setCalculator(StuffCalculator calc) {
		this.calc = calc;
	}


	public String getColumnName(int column) {
		if(column==0)
			return "Elemental Damage";
		
		if(column==1)
			return "Resistance Reduction";
		
		if(column==2)
			return "Melee Damage Reduction";
		
		if(column==3)
			return "Ranged Damage Reduction";
		
		return "";
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	
	@Override
	public int getRowCount() {
		return ELEMENTS.values().length;
	}
	
	
	
	@Override
	public Object getValueAt(int row, int column) {
		if(column == 0)
		{
			return ELEMENTS.values()[row] + " Damage";
		}
		if(column==1)
		{
			calc.calculate();
			return calc.format(calc.getResistance(ELEMENTS.values()[row])/((5*calc.getMonsterLevel())+calc.getResistance(ELEMENTS.values()[row]))*100) +"%";
		}
		if(column==2)
		{
			calc.calculate();
			return calc.format(calc.getSituationalDR(ELEMENTS.values()[row],SITUATIONAL.Melee,isElite)*100) +"%";
			
		}
		if(column==3)
		{
			calc.calculate();
			return calc.format(calc.getSituationalDR(ELEMENTS.values()[row],SITUATIONAL.Ranged,isElite)*100) +"%";
		}
		
		
		
		return null;
	}


	public void setElite(boolean selected) {
		this.isElite=selected;
		
	}
	
	
}
