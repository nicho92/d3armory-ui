package org.armory.d3.beans;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sdfteam.d3armory.service.annotation.DataType;
import com.sdfteam.d3armory.service.annotation.RemoteConfiguration;
import com.sdfteam.d3armory.service.annotation.RemoteData;
import com.sdfteam.d3armory.service.remote.RemoteEntity;
import com.sdfteam.d3armory.service.util.RawsAttributes;

/**
 * Represents a single item, with links to the resources.
 * 
 * @author NoTiCe
 * 
 */
@RemoteConfiguration(url = "http://<host>/api/d3/data/item/<item-id>?locale=<local>")
public class Item  extends RemoteEntity implements Cloneable,Serializable {
	private String id;
	private String displayColor;
	@RemoteData(type = DataType.IMAGE)
	private String icon;
	private String name;
	@RemoteData(type = DataType.HTML)
	private String tooltipParams;
	private Number requiredLevel;
	private String typeName;
	private Number bonusAffixes;
	private ItemType type;
	private String flavorText;
	private String[] attributes;
	private Gem[] gems;
	//private List<MinMaxBonus> socketEffects;
	private MinMaxBonus armor;
	private MinMaxBonus dps;
	private Map<String, MinMaxBonus > attributesRaw;
	private MinMaxBonus attacksPerSecond;
	private MinMaxBonus minDamage;
	private MinMaxBonus maxDamage;
	private List<Salvage> salvage ;
	private LegendarySet set;
	private Number itemLevel;
	private boolean isMainHand;
	
	public void setMainHand(boolean isMainHand) {
		this.isMainHand = isMainHand;
	}

	public boolean isMainHand()
	{
		return isMainHand;
	}
	
	public void addAttributs(Attributs a)
	{
		attributesRaw.put(a.getId(), a.getValue());
	}
	
	public Item()
	{
		attributesRaw=new HashMap<String, MinMaxBonus >();
	}
	
	
	public int nbSockets(){
		if(attributesRaw.get("Sockets")==null)
			return 0;
		else
			return attributesRaw.get("Sockets").getMax().intValue();
	}
	
	public boolean equals(Object item) {
		if(item!=null)
			return getId().equals(((Item)item).getId());
		
		return false;
	}

	public String getEnchantedWeapon()
	{
		if(!isWeapon())
			return "";
		
		if(attributesRaw==null)
			return "";
		
		Iterator<String> attributes = attributesRaw.keySet().iterator();
		while(attributes.hasNext())
		{
			String att= attributes.next();
			if(att.contains("Poison"))
				return "Poison";
			if(att.contains("Arcane"))
				return "Arcane";
			if(att.contains("Fire"))
				return "Fire";
			if(att.contains("Lightning"))
				return "Lightning";
			if(att.contains("Holy"))
				return "Holy";
			if(att.contains("Cold"))
				return "Cold";
		}
		return "";
		
	}
	
	
	public int nbGems(){
		if (gems!=null)
			return gems.length;
			
		return 0;
	}
	
	public boolean isSetObjects()
	{
		return set!=null;
	}
	

	public Number getItemLevel() {
		return itemLevel;
	}


	public void setItemLevel(Number itemLevel) {
		this.itemLevel = itemLevel;
	}
	
	public LegendarySet getSet() {
		return set;
	}

	public void setSet(LegendarySet set) {
		this.set = set;
	}

	public List<Salvage> getSalvage() {
		return salvage;
	}

	public void setSalvage(List<Salvage> salvage) {
		this.salvage = salvage;
	}

	public MinMaxBonus getAttacksPerSecond() {
		return attacksPerSecond;
	}

	public void setAttacksPerSecond(MinMaxBonus attacksPerSecond) {
		this.attacksPerSecond = attacksPerSecond;
	}

	public MinMaxBonus getMinDamage() {
		return minDamage;
	}

	public void setMinDamage(MinMaxBonus minDamage) {
		this.minDamage = minDamage;
	}

	public MinMaxBonus getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(MinMaxBonus maxDamage) {
		this.maxDamage = maxDamage;
	}

	public MinMaxBonus getDps() {
		return dps;
	}

	public void setDps(MinMaxBonus dps) {
		this.dps = dps;
	}

	public Map<String, MinMaxBonus> getAttributesRaw() {
		return attributesRaw;
	}

	public void setAttributesRaw(Map<String, MinMaxBonus> attributesRaw) {
		this.attributesRaw = attributesRaw;
	}
	
	public void addAttributesRaw(String titre, MinMaxBonus b)
	{
		attributesRaw.put(titre, b);
	}

	public MinMaxBonus getArmor() {
		return armor;
	}

	public void setArmor(MinMaxBonus armor) {
		this.armor = armor;
	}

	public Gem[] getGems() {
		return gems;
	}

	public void setGems(Gem[] gems) {
		this.gems = gems;
	}

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Number getBonusAffixes() {
		return bonusAffixes;
	}

	public void setBonusAffixes(Number bonusAffixes) {
		this.bonusAffixes = bonusAffixes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayColor() {
		return this.displayColor;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTooltipParams() {
		return this.tooltipParams;
	}

	public void setTooltipParams(String tooltipParams) {
		this.tooltipParams = tooltipParams;
	}

	public Number getRequiredLevel() {
		return requiredLevel;
	}

	public Item clone() {
		try {
			return (Item)super.clone();
		} catch (CloneNotSupportedException e) {
			return new Item();
		}
	}

	public void setRequiredLevel(Number requiredLevel) {
		this.requiredLevel = requiredLevel;
	}
	
	public String getItemID()
	{
		return tooltipParams.split("/")[1];
	}
	
	public String toString()
	{
		return name;
	}

	public boolean isWeapon() {
		return dps!=null;
	}

	public boolean isArmor() {
		return armor!=null;
	}
	

	public void generateAttributsString() {
		Iterator<String> keys = getAttributesRaw().keySet().iterator();
		RawsAttributes r = new RawsAttributes();
		List<String> liste = new ArrayList<String>();
		while(keys.hasNext())
		{
			String key = keys.next();
			if(r.getAttribut(key)!=null)
			{
				if(!r.getAttribut(key).getLibelle().equals(""))
				{
					String value= String.valueOf(getAttributesRaw().get(key));
					if(key.contains("Percent") || r.getAttribut(key).getLibelle().contains("%"))
					{
						value= String.valueOf(getAttributesRaw().get(key).getMoyenne()*100);
					}
					liste.add(r.getAttribut(key).getLibelle().replaceFirst("X",value ));
				}
					
			}
			else
			{
				System.err.println("itemAttributes.add(new Attributs(\""+key+"\",\"X BLALALBLA\"));" );
			}
		}
		setAttributes(liste.toArray(new String[liste.size()]));
	}

	public String getTypeOfObject()
	{
			if (displayColor.equals("orange"))
				return "Legendary";
			if (displayColor.equals("yellow"))
				return "Rare";
			if (displayColor.equals("green"))
				return "Legendary Set";
			if (displayColor.equals("blue"))
				return "Magic";
			if (displayColor.equals("white"))
				return "Normal";
			if (displayColor.equals("grey"))
				return "Damaged";
			
			return "";
	}
	
	public void setTypeOfObject(String type)
	{
		if (type.equalsIgnoreCase("Legendary"))
			setDisplayColor("orange");
		if (type.equalsIgnoreCase("Rare"))
			setDisplayColor("yellow");
		if (type.equalsIgnoreCase("Legendary Set"))
			setDisplayColor("green");
		if (type.equalsIgnoreCase("Magic"))
			setDisplayColor("blue");
		if (type.equalsIgnoreCase("Normal"))
			setDisplayColor("white");
		if (type.equalsIgnoreCase("Damaged"))
			setDisplayColor("grey");
	}

	

	
}
