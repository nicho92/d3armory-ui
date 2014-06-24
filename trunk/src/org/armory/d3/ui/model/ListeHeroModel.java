package org.armory.d3.ui.model;

import javax.swing.DefaultComboBoxModel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;

public class ListeHeroModel extends DefaultComboBoxModel<Hero> {


	public Hero getElementAt(int i) {
		if (D3ArmoryControler.getInstance().getCurrentProfil() !=null)
		{
			return D3ArmoryControler.getInstance().getCurrentProfil().getHeroes().get(i);
		}
			
		return null; 
	}

	public int getSize() {
		if (D3ArmoryControler.getInstance().getCurrentProfil() !=null)
		{

			return D3ArmoryControler.getInstance().getCurrentProfil().getHeroes().size();
		}
			
		return 0;
	}

	public Hero get(int i) {
		if (D3ArmoryControler.getInstance().getCurrentProfil() !=null)
		{
			return D3ArmoryControler.getInstance().getCurrentProfil().getHeroes().get(i);
		}
		return null; 
	}

	

	
}
