package org.armory.d3.ui.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.beans.Attributs;
import org.armory.d3.beans.MinMaxBonus;

import com.sdfteam.d3armory.service.util.RawsAttributes;

public class ItemDetailsModel extends DefaultTableModel {

	private Set<Attributs> attSet;
	
	public void setAttributs(Attributs[] att)
	{
		for(Attributs a:att)
			attSet.add(a);
	}
	
	public ItemDetailsModel()
	{
		attSet = new HashSet<Attributs>();
	}
	
	public Set<Attributs> getAttSet() {
		return attSet;
	}

	public void setAttSet(Set<Attributs> attSet) {
		this.attSet = attSet;
	}

	public String getColumnName(int c) {
		if(c==0)
			return "Attributs";
		else
			return "Valeur";
		
	}

	public String[] getAttributs()
	{
		List<String> retour = new ArrayList<>();
		for(Attributs a : attSet)
		{
			String lib = a.getLibelle().replaceFirst("X", String.valueOf(a.getValue().getMoyenne()));
			retour.add(lib);
		}
		return retour.toArray(new String[retour.size()]);
	}
	
	
	public boolean isCellEditable(int row, int col) {
		if(col==1)
			return true;
		else
			return false;
		
	}

	public int getColumnCount() {
		return 2;
	}


	public int getRowCount() {
		if(attSet==null)
			return 0;
		
		return attSet.size();
	}

	public Object getValueAt(int row, int column) {
		    
		Attributs[] val = attSet.toArray(new Attributs[attSet.size()]);
			if (column==0) {
	            return val[row];
	        } else if (column==1) { 
	           return val[row].getValue();
	        }
		    return 0;
	}

	public void setValueAt(Object o, int row, int col) {
		MinMaxBonus val = new MinMaxBonus(Double.parseDouble(o.toString()));
		Attributs a = ((Attributs)getValueAt(row,0));
		a.setValue(val);
		attSet.add(a);
	}

	public void addAttributes(Attributs selectedItem) {
		attSet.add(selectedItem);
		
	}
	
	public void addAttributes(Map<String,MinMaxBonus> map) {
		
		Attributs a = new Attributs();
		Iterator<String> it = map.keySet().iterator();
		RawsAttributes att = new RawsAttributes();
		while(it.hasNext())
		{
			String id = it.next();
			a.setId(id);
			a.setLibelle(att.getAttribut(id).getLibelle().replaceFirst("X", String.valueOf(map.get(id).getMoyenne())));
			attSet.add(a);
		}
		
		
	}
	
}
