package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pihen.d3restapi.service.annotation.DataType;
import com.pihen.d3restapi.service.annotation.RemoteConfiguration;
import com.pihen.d3restapi.service.annotation.RemoteData;
import com.pihen.d3restapi.service.remote.RemoteEntity;
import com.pihen.d3restapi.service.util.RawsAttributes;

/**
 * Represents a single item, with links to the resources.
 * 
 * @author NoTiCe
 * 
 */
@RemoteConfiguration(url = "http://<host>/api/d3/data/item/<item-id>?locale=<local>")
public class Item  extends RemoteEntity implements Cloneable,Serializable {
	
	private static final long serialVersionUID = 5279622195128628056L;
	
	private String id;
	private String name;
	@RemoteData(type = DataType.IMAGE) private String icon;
	private String displayColor;
	@RemoteData(type = DataType.HTML) private String tooltipParams;
	private Number requiredLevel;
	private Number itemLevel;
	private Number bonusAffixes;
	private Number bonusAffixesMax;
	private Boolean accountBound;
	private String flavorText;
	private String typeName;
	private ItemType type;
	private MinMaxBonus armor;
	private AttributsContainer attributes;
	private Map<String, MinMaxBonus > attributesRaw;
	private List<AffixesContainer> randomAffixes;
	private List<Gem> gems;
	//todo socketEffects
	private Item transmogItem;
	
	
	private MinMaxBonus dps;
	private MinMaxBonus attacksPerSecond;
	private MinMaxBonus minDamage;
	private MinMaxBonus maxDamage;
	
	
	private LegendarySet set;
	
	
	public void addAttributs(Attributs a)
	{
		attributesRaw.put(a.getId(), a.getValue());
	}
	
	public Item()
	{
		super();
		attributesRaw=new HashMap<String, MinMaxBonus >();
	}

	public double getRealDPS()
	{
		
		double mindmg=getMinDamage().getMoyenne();
		double maxdmg=getMaxDamage().getMoyenne();
		
		String element = getEnchantedWeapon();
		double multiplicateur=1;
		
		if(getAttributesRaw().get("Damage_Weapon_Percent_Bonus#Physical")!=null)
			multiplicateur=multiplicateur+getAttributesRaw().get("Damage_Weapon_Percent_Bonus#Physical").getMoyenne();;
				
		if(!element.equals(""))
			{
				mindmg+=getAttributesRaw().get("Damage_Weapon_Min#"+element).getMoyenne()*multiplicateur;
				maxdmg+=(getAttributesRaw().get("Damage_Weapon_Min#"+element).getMoyenne()+getAttributesRaw().get("Damage_Weapon_Delta#"+element).getMoyenne())*multiplicateur;
				
			}
	
		return ((mindmg+maxdmg)/2)*getAttacksPerSecond().getMoyenne();
	}
	
	
	public int nbSockets(){
		if(attributesRaw.get("Sockets")==null)
			return 0;
		else
			return attributesRaw.get("Sockets").getMax().intValue();
	}
	
	public boolean equals(Object item) {
		if(item!=null)
		{
			return getName().equals(((Item)item).getName());
		}
		
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
	
	public boolean isTransmogrifiedObject()
	{
		return transmogItem !=null;
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

	public List<AffixesContainer> getRandomAffixes() {
		return randomAffixes;
	}

	public void setRandomAffixes(List<AffixesContainer> randomAffixes) {
		this.randomAffixes = randomAffixes;
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

	public List<Gem> getGems() {
		return gems;
	}

	public void setGems(List<Gem> gems) {
		this.gems = gems;
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

	
	
	public AttributsContainer getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributsContainer attributes) {
		this.attributes = attributes;
	}

	
	
	public Object clone() {
		try {
			Item i = (Item)super.clone();
			i.setAttributesRaw(new HashMap<String, MinMaxBonus>(this.getAttributesRaw()));
			i.setGems(i.getGems());
			return i;
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
				System.err.println("itemAttributes.add(new Attributs(\""+key+"\"));");
			}
		}
		//setAttributes(liste.toArray(new String[liste.size()]));
	}

	public String getRarity()
	{
			if (displayColor.equals("orange"))
				return "Legendary";
			if (displayColor.equals("yellow"))
				return "Rare";
			if (displayColor.equals("green"))
				return "Legendary Set";
			if (displayColor.equals("blue"))
				return "Magical";
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
		if (type.equalsIgnoreCase("Magical"))
			setDisplayColor("blue");
		if (type.equalsIgnoreCase("Normal"))
			setDisplayColor("white");
		if (type.equalsIgnoreCase("Damaged"))
			setDisplayColor("grey");
	}

	public Number getBonusAffixesMax() {
		return bonusAffixesMax;
	}

	public void setBonusAffixesMax(Number bonusAffixesMax) {
		this.bonusAffixesMax = bonusAffixesMax;
	}

	public Boolean getAccountBound() {
		return accountBound;
	}

	public void setAccountBound(Boolean accountBound) {
		this.accountBound = accountBound;
	}

	public Item getTransmogItem() {
		return transmogItem;
	}

	public void setTransmogItem(Item transmogItem) {
		this.transmogItem = transmogItem;
	}

	public String toString()
	{
		return getType() +" - " + getName();
	}
	
	public static Map<String,Double> getWeaponDefaultAS()
	{
		
		Map<String,Double>weaponDefaultAS=new HashMap<String,Double>();
			weaponDefaultAS.put("Axe", 1.30);
			weaponDefaultAS.put("HandXbow", 1.60);
			weaponDefaultAS.put("Dagger", 1.50);
			weaponDefaultAS.put("Mace", 1.20);
			weaponDefaultAS.put("FistWeapon", 1.40);
			weaponDefaultAS.put("MightyWeapon1H", 1.30);
			weaponDefaultAS.put("Spear", 1.20);
			weaponDefaultAS.put("Sword", 1.40);
			weaponDefaultAS.put("CeremonialDagger",1.4);
			weaponDefaultAS.put("Wand", 1.40);
			weaponDefaultAS.put("Axe2H", 1.00);
			weaponDefaultAS.put("Bow", 1.40);
			weaponDefaultAS.put("Daibo", 1.10);
			weaponDefaultAS.put("Crossbow", 1.10);
			weaponDefaultAS.put("Mace2H", 0.90);
			weaponDefaultAS.put("MightyWeapon2H", 1.00);
			weaponDefaultAS.put("Polearm", 0.95);
			weaponDefaultAS.put("Staff", 1.00);
			weaponDefaultAS.put("Sword2H", 1.1);
			weaponDefaultAS.put("Flail1H", 1.4);
			weaponDefaultAS.put("Flail2H", 1.15);
			weaponDefaultAS.put("None", 0.0);
		return weaponDefaultAS;
	}
	
}
