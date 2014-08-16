package org.armory.d3.ui.model;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Item;

public class ListeGemsModel extends DefaultComboBoxModel<Gem>{


	private Item i;
	private List<Gem> gems;
	
	
	public ListeGemsModel() {
		gems=new ArrayList<Gem>();
	}
	
	public Gem getElementAt(int i) {
		return gems.get(i); 
	}

	public int getSize() {
		return gems.size();
	}

	public Gem get(int i) {
		return gems.get(i); 
	}

	public void setItem(Item item) {
		i=item;
		
	}
	
}
