package org.armory.d3.ui.model;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Hero;
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
			case 8: return "Cooldown Reduction";
			case 9: return "Elemental Damage";
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
			Item i = calc.getStuffs().get(EnumerationStuff.getValueAt(row));
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
			if(row==EnumerationStuff.values().length+1)
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
					return StuffCalculator.format(calc.filter("Damage_Percent_Bonus_Vs_Elites", null)*100);
				}
				if(column==8)
				{
					return StuffCalculator.format(calc.filter("Power_Cooldown_Reduction_Percent_All",null)*100);
				}
			}
			else
			{
				if(column==0)
				{
					return EnumerationStuff.values()[row];
				}
				if(column==1)
				{
					return calc.getStuffs().get(EnumerationStuff.getValueAt(row)).getName();
				}
				if(column==2)//stat de base, avec la récuperation des gems.
				{
					String statLabel = calc.getHero().getPrimaryStat();
					double gemstat =0;
					for(Gem g: i.getGems())
						gemstat+=calc.filter(g.getAttributesRaw(), statLabel, null);
					
					return calc.filter(i, statLabel, null)+gemstat;
				}
				if(column==3)
				{
					return StuffCalculator.format(calc.filter(i, "Crit_Percent", null)*100);
				}
				if(column==4)
				{
					return StuffCalculator.format(calc.filter(i, "Crit_Damage", null)*100);
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
					return StuffCalculator.format(calc.filter(i, "Vitality", null));
				}
				if(column==7)
				{
					return StuffCalculator.format(calc.filter(i,"Damage_Percent_Bonus_Vs_Elites", null)*100);
				}
				if(column==8)
				{
					double val = StuffCalculator.format(calc.filter(i, "Power_Cooldown_Reduction_Percent_All",null)*100);
					
					if( EnumerationStuff.values()[row].equals(EnumerationStuff.HEAD))
						if(i.getGems().size()>0)
							val=StuffCalculator.format(calc.filter(i.getGems().get(0).getAttributesRaw(), "Power_Cooldown_Reduction_Percent_All",null)*100);
					return val;
				}

			}
					return null;
	}catch(Exception e)
	{
		return null;
	}
	
	}

		
	
	
}
