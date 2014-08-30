package org.armory.d3.ui.model;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.StuffCalculator;

public class ItemsDetailModel extends DefaultTableModel {

	StuffCalculator calc;

	public ItemsDetailModel() {
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
			case 8: return "Cooldown Reduction";
			case 9: return calc.getElementalOrientation() +" Elemental Damage";
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
		return 10;
	}


	public int getRowCount() {
		
		return EnumerationStuff.values().length+2;
	}

	public Object getValueAt(int row, int column) {

		try {	
			
			if(row==EnumerationStuff.values().length)
			{
				if(column==0)
				{
					return "BASE";
				}
				if(column==1)
				{
					Hero h = D3ArmoryControler.getInstance().getSelectedHero(false);
					return h.getClazz() +"-"+ h.getLevel(); 
				}
				if(column==2)
				{
					return calc.getPrimaryBaseValue();
				}
				if(column==3)
				{
					return 5;
				}
				if(column==4)
				{
					return 50;
				}
				
			}
			if(row==EnumerationStuff.values().length+1) // POUR LES SET
			{
				if(column==0)
					return "SET BONUS";
				
				
				if(column==2)
					return calc.filter(calc.getHero().getPrimaryStat(), "SET");
				
				if(column==3)
					return StuffCalculator.format(calc.filter("Crit_Percent", "SET")*100);
				
				if(column==4)
				{
					return StuffCalculator.format(calc.filter("Crit_Damage", "SET")*100);
				}	
				if(column==5)
				{
					return StuffCalculator.format(calc.filter("Attacks_Per_Second_Percent", "SET")*100);
				}
				if(column==6)
				{
					return StuffCalculator.format(calc.filter("Vitality", "SET"));
				}
				if(column==7)
				{
					return StuffCalculator.format(calc.filter("Damage_Percent_Bonus_Vs_Elites", "SET")*100);
				}
				if(column==8)
				{
					return StuffCalculator.format(calc.filter("Power_Cooldown_Reduction_Percent_All","SET")*100);
				}
				if(column==9)
				{
					return StuffCalculator.format(calc.filter("Damage_Dealt_Percent_Bonus#"+calc.getElementalOrientation(),"SET")*100);
				}
			}
			else
			{
				Item i = calc.getStuffs().get(EnumerationStuff.values()[row]);
				
				if(column==0)
				{
					return EnumerationStuff.values()[row];
				}
				if(column==1)
				{
					return calc.getStuffs().get(EnumerationStuff.values()[row]).getName();
				}
				if(column==2)//stat de base, avec la récuperation des gems.
				{
					String statLabel = calc.getHero().getPrimaryStat();
					return calc.filter(i, statLabel, null) + calc.getGemValue(i, statLabel);
				}
				if(column==3)
				{
					return StuffCalculator.format((calc.filter(i, "Crit_Percent", null)+ calc.getGemValue(i, "Crit_Percent"))*100);
				}
				if(column==4)
				{
					double val=StuffCalculator.format((calc.filter(i, "Crit_Damage", null)+ calc.getGemValue(i, "Crit_Damage"))*100);
					return val;
				}	
				if(column==5)
				{
					
					if(i.isWeapon())
						return StuffCalculator.format(i.getAttacksPerSecond().getMoyenne());
					else
						return StuffCalculator.format(calc.filter(i, "Attacks_Per_Second_Percent", null)*100);
				}
				if(column==6)
				{
					return StuffCalculator.format(calc.filter(i, "Vitality", null)+ calc.getGemValue(i, "Vitality"));
				}
				if(column==7)
				{
					double val = StuffCalculator.format((calc.filter(i, "Damage_Percent_Bonus_Vs_Elites",null)+ calc.getGemValue(i, "Damage_Percent_Bonus_Vs_Elites"))*100);
					return val;
				}
				if(column==8)
				{
					double val = StuffCalculator.format((calc.filter(i, "Power_Cooldown_Reduction_Percent_All",null)+ calc.getGemValue(i, "Power_Cooldown_Reduction_Percent_All"))*100);
					return val;
				}
				if(column==9)
				{
					return StuffCalculator.format((calc.filter(i,"Damage_Dealt_Percent_Bonus", calc.getElementalOrientation().toString())+ calc.getGemValue(i, "Damage_Dealt_Percent_Bonus"))*100);
				}

			}
					return null;
	}catch(Exception e)
	{
		return null;
	}
	
	}

		
	
	
}
