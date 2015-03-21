package com.pihen.d3restapi.service.util;

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
		if( h.getClazz().equals("demon-hunter")) 
			columnPourcent=2;
		if( h.getClazz().equals("barbarian")) 
			columnPourcent=3;
		if( h.getClazz().equals("wizard")) 
			columnPourcent=4;
		if( h.getClazz().equals( "witch-doctor")) 
			columnPourcent=5;
		if( h.getClazz().equals( "monk")) 
			columnPourcent=6;
		if( h.getClazz().equals( "crusader")) 
			columnPourcent=7;
		
	}
	
	
	public LootFactory(Hero h)
	{
		this.h=h;
		tableLoot = new LootXlsTableModel();
		tableLoot.init();
		calculateColumnNumber();
	}
	
	public String randomTypeItemClazz() //filtre les items accessible pour la classe.
	{
		Set<String> typeItem = new HashSet<String>();
		for(int i=0;i<tableLoot.getRowCount();i++)
		{
			String pc = (String)tableLoot.getValueAt(i, columnPourcent);
			if(!pc.equalsIgnoreCase("0%"))
			{
				typeItem.add((String)tableLoot.getValueAt(i, columnType));
			}
		}
		Object[] setArray = (Object[]) typeItem.toArray();
		return (String) setArray[(int)new Random().nextInt(typeItem.size())];
		
	}
	
	public Set<String> getItemsType()
	{
		Set<String> typeItem = new HashSet<String>();
		for(int i=0;i<tableLoot.getRowCount();i++)
		{
			String pc = (String)tableLoot.getValueAt(i, columnPourcent);
			if(!pc.equalsIgnoreCase("0%"))
			{
				typeItem.add((String)tableLoot.getValueAt(i, columnType));
			}
		}
		return typeItem;
	}
	
	
	public Map<String,Double> getGenerateItem(String type) // filtrer les item par type et > 0;
	{
		Map<String,Double> lootedItemTable=new HashMap<String,Double>();
		for(int i=0;i<tableLoot.getRowCount();i++)
		{
			if(tableLoot.getValueAt(i,columnType).toString().equalsIgnoreCase(type))
			{
				if(!tableLoot.getValueAt(i, columnPourcent).toString().equalsIgnoreCase("0%"))
					lootedItemTable.put(tableLoot.getValueAt(i,columnNameItem).toString(), Double.parseDouble(tableLoot.getValueAt(i, columnPourcent).toString().replaceAll("%", "").trim()));
			}
		}
		return lootedItemTable;
	}
	
	public Item generateItem(String item)
	{
		Map<String,Double> lootedItemTable;
		if (item==null)
			lootedItemTable= getGenerateItem(randomTypeItemClazz());
		else
			lootedItemTable= getGenerateItem(item);
			
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
