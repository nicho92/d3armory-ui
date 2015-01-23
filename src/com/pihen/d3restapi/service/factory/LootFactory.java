package com.pihen.d3restapi.service.factory;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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
	
	public String filterItemClazz() //filtre les items accessible pour la classe.
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
	
	public Map<String,Double> getGenerateItem(String type) // filtrer les item par type et > 0;
	{
		Map<String,Double> lootedItemTable=new HashMap<String,Double>();
		for(int i=0;i<tableLoot.getRowCount();i++)
		{
			if(tableLoot.getValueAt(i,columnType).toString().equalsIgnoreCase(type))
			{
				if(!tableLoot.getValueAt(i, columnPourcent).toString().equalsIgnoreCase("0.00%"))
					lootedItemTable.put(tableLoot.getValueAt(i,columnNameItem).toString(), Double.parseDouble(tableLoot.getValueAt(i, columnPourcent).toString().replaceAll("%", "").trim()));
			}
		}
		return lootedItemTable;
	}
	
	public Item generateItem()
	{
		//Map<String,Double> lootedItemTable= getGenerateItem(filterItemClazz());
		Map<String,Double> lootedItemTable= getGenerateItem("amulet");
		
		for(String k : lootedItemTable.keySet())
		{
			lootedItemTable.put(k, lootedItemTable.get(k)*new Random().nextInt(100));
		}
		
		Collections.max(
				lootedItemTable.entrySet(), 
                new Comparator<Entry<String,Double>>(){
                    public int compare(Entry<String,Double> o1, Entry<String,Double> o2) {
                        return o1.getValue() > o2.getValue()? 1:-1;
                    }
                }).getKey();
		
		String name=lootedItemTable.keySet().toArray()[(int)new Random().nextInt((int)lootedItemTable.keySet().size())].toString();
		Item i = new Item();
			i.setTooltipParams("item/"+name.replaceAll("-","").replace(",", "").replace("'", "").replaceAll(" ", "-").trim().toLowerCase());
			i = D3ArmoryControler.getInstance().getItemDetails(i);	
		
			
			
			
			
		return i;
	}
	
}
