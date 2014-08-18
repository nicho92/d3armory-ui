package org.armory.d3.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Gem.TYPES;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.util.GemsFactory;

public class ListeGemsModel extends DefaultComboBoxModel<Gem>{
	Item it;
	
	public void setItem(Item i)
	{
		this.it=i;
	}
	public ListeGemsModel() {
		
	}
	
	public Gem getElementAt(int i) {
		return GemsFactory.getGems(TYPES.Amethyst,it).get(i);
	}

	public int getSize() {
		return GemsFactory.getGems(TYPES.Amethyst,it).size();
	}

	
}
