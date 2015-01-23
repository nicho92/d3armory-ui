package com.pihen.d3restapi.service.factory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.model.LootXlsTableModel;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;

public class LootFactory {

	private LootXlsTableModel tableLoot;
	private Hero h;
	
	int columnPourcent;
	int columnNameItem=1;
	int columnType=0;
	
	private void calculateColumnNumber()
	{
		
		String name = h.getClazz();
		switch (name) {
		case "demon-hunter": columnPourcent=2;break;
		case "barbare":columnPourcent=3;break;
		case "wizard" : columnPourcent=4;break;
		case "witch-doctor" : columnPourcent=5;break;
		case "monk": columnPourcent=6;break;
		case "crusader" : columnPourcent=7;break;
		default:break;
		}
	}
	
	
	public LootFactory(Hero h)
	{
		this.h=h;
		tableLoot = new LootXlsTableModel();
		tableLoot.init();
		calculateColumnNumber();
	}
	
	public String filterItemClazz()
	{
		Set<String> typeItem = new HashSet<String>();
		for(int i=0;i<tableLoot.getRowCount();i++)
		{
			String pc = (String)tableLoot.getValueAt(i, columnPourcent);
			if(!pc.equalsIgnoreCase("0.00%"))
			{
				typeItem.add((String)tableLoot.getValueAt(i, columnType));
			}
		}
		Object[] setArray = (Object[]) typeItem.toArray();
		return (String) setArray[(int)new Random().nextInt(typeItem.size())];
		
	}
	
	public Item generateItem()
	{
		System.out.println(filterItemClazz());

		
		int ligneItem  =(int)new Random().nextInt(tableLoot.getRowCount())+1;
		String name = (String)tableLoot.getValueAt(ligneItem, columnNameItem);
		
		Item i = new Item();
			i.setTooltipParams("item/"+name.replaceAll("-","").replace(",", "").replace("'", "").replaceAll(" ", "-").trim().toLowerCase());
			i = D3ArmoryControler.getInstance().getItemDetails(i);	
		
		return i;
	}
	
}
