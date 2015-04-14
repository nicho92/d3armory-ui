package com.pihen.d3restapi.service.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.model.LootXlsTableModel;

import com.pihen.d3restapi.beans.Affixes;
import com.pihen.d3restapi.beans.AffixesContainer;
import com.pihen.d3restapi.beans.Attributs;
import com.pihen.d3restapi.beans.DisplayableItemAttributs;
import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;

public class LootFactory {

	private LootXlsTableModel tableLoot;
	private Hero h;
	
	static final Logger logger = LogManager.getLogger(LootFactory.class.getName());

	
	
	int columnPourcent;
	int columnNameItem=0;
	int columnType=1;
	int columngear=8;
	
	private void calculateColumnNumber()
	{
		if( h.getClazz().equals("demon-hunter")) 
			columnPourcent=6;
		if( h.getClazz().equals("barbarian")) 
			columnPourcent=8;
		if( h.getClazz().equals("wizard")) 
			columnPourcent=10;
		if( h.getClazz().equals( "witch-doctor")) 
			columnPourcent=11;
		if( h.getClazz().equals( "monk")) 
			columnPourcent=7;
		if( h.getClazz().equals( "crusader")) 
			columnPourcent=9;
		
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
			double pc = Double.parseDouble(tableLoot.getValueAt(i, columnPourcent).toString().replace(",", "."));
			if(pc>0)
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
			double pc = Double.parseDouble(tableLoot.getValueAt(i, columnPourcent).toString().replace(",", "."));
			if(pc>0)
			{
				typeItem.add((String)tableLoot.getValueAt(i, columnType));
			}
		}
		return typeItem;
	}
	
	
	public Map<String,Double> generateLootTable(String type) // filtrer les item par type et > 0;
	{
		Map<String,Double> lootedItemTable=new HashMap<String,Double>();
		for(int i=0;i<tableLoot.getRowCount();i++)
		{
			if(tableLoot.getValueAt(i,columnType).toString().equalsIgnoreCase(type))
			{
				double pc = Double.parseDouble(tableLoot.getValueAt(i, columnPourcent).toString().replace(",", "."));
				if(pc>0)
					lootedItemTable.put(tableLoot.getValueAt(i,columnNameItem).toString(), Double.parseDouble(tableLoot.getValueAt(i, columnPourcent).toString().replace(",", ".").trim())/100);
			}
		}
		return lootedItemTable;
	}
	
	public Item generateItem(String item)
	{
		Map<String,Double> lootedItemTable;
		if (item==null)
			lootedItemTable= generateLootTable(randomTypeItemClazz());
		else
			lootedItemTable= generateLootTable(item);
			
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
		
		logger.debug("Loot Table : " + lootedItemTable);
		String name=lootedItemTable.keySet().toArray()[(int)new Random().nextInt((int)lootedItemTable.keySet().size())].toString();
		
		Item i = getItemById(name);
			
		return i;
	}
	
	public Item getItemById(String tooltip)
	{
		
		//TODO bugfix on Bombardier's Rucksack name
		
		if(tooltip.equals("Bombadier's Rucksack"))
			tooltip="Bombardier's Rucksack";
		
		tooltip = D3ArmoryControler.getInstance().refactorItem(tooltip);
		Item i = new Item();
		i.setTooltipParams("item/"+tooltip);
		i = D3ArmoryControler.getInstance().getItemDetails(i);	
		
		Number level = D3ArmoryControler.getInstance().getSelectedHero(false).getLevel().intValue();
		int ancient = new Random().nextInt(100);
		if (ancient >= 90)//10% ancien droprate
		{
			i.addAttributesRaw("Ancient_Rank", new MinMaxBonus(1));
			i.setTypeName("Ancient " + i.getTypeName());
			
		}
		
		
		//for fixed attributes
		for(String key : i.getAttributesRaw().keySet())
		{
					Random rand = new Random();
					Double min = i.getAttributesRaw().get(key).getMin();
					Double max = i.getAttributesRaw().get(key).getMax();
					double val = min + (max - min) * rand.nextDouble();
					
					MinMaxBonus mmb = new MinMaxBonus(val);
					i.getAttributesRaw().put(key, mmb);
		}
		
		//for random attributes
		for(AffixesContainer ac : i.getRandomAffixes())
		{
			Affixes a = ac.getOneOf().get(new Random().nextInt(ac.getOneOf().size()));
			{
				for (String k : a.getAttributesRaw().keySet())
				{
					Random rand = new Random();
					Double min = a.getAttributesRaw().get(k).getMin();
					Double max = a.getAttributesRaw().get(k).getMax();
					double val = min + (max - min) * rand.nextDouble();

					MinMaxBonus mmb = new MinMaxBonus((int)val);
					mmb.setValue(val);
					i.getAttributesRaw().put(k, mmb);
				}
			}
		}
		
		RawsAttributeFactory facto = new RawsAttributeFactory();
		for(String key : i.getAttributesRaw().keySet())
		{
			//
			
			Attributs att = facto.getAttribut(key);
			
			if(att!=null)
			{
					if(key.endsWith("_Item")||key.startsWith("Resistance_")||key.startsWith("Damage_Weapon_Min")||key.startsWith("Damage_Dealt_Percent_Bonus"))
					{
						if(i.isAncientItem())
							i.getAttributesRaw().get(key).setValue(i.getAttributesRaw().get(key).getMoyenne()+(i.getAttributesRaw().get(key).getMoyenne()*30/100));
					}
			
				att.setValue(i.getAttributesRaw().get(key));
				i.addAttributs(att);
			}
		}
		
		i.setItemLevel(level);
		
		List<DisplayableItemAttributs> pass = i.getAttributes().getPassive();
		
		if(pass.size()>0){
		Pattern pat = Pattern.compile("(\\d+)–(\\d+)");
		Matcher m = pat.matcher(pass.get(0).getText());
		
		if (m.find()) {
			String val = m.group(0);
			int pour=1;
			if(pass.get(0).getText().contains("%"))
				pour=100;
			
			pass.get(0).setText(pass.get(0).getText().replaceAll(val, format(i.getAttributesRaw().get(i.getPowerPassive()).getMoyenne()*pour)));

		}
		
		}
			
		
		i.generateDisplayableAttributs();
		
		i.getAttributes().setPassive(pass);
		
		i.setRandomAffixes(new ArrayList<AffixesContainer>());
		
		
		return i;
	}
	
	public static String format(double val)
	{
		return new DecimalFormat("#").format(val);
		
	}
	
	public double getCoeff(double value,int level)
	{
		if(level == 70)
			return value;
		
		return value;
	}
	
	
}
