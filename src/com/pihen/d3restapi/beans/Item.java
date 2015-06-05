package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pihen.d3restapi.service.annotation.DataType;
import com.pihen.d3restapi.service.annotation.RemoteConfiguration;
import com.pihen.d3restapi.service.annotation.RemoteData;
import com.pihen.d3restapi.service.remote.RemoteEntity;
import com.pihen.d3restapi.service.util.RawsAttributeFactory;
import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.ELEMENTS;


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
	//TODO socketEffects
	//private String blockChance;
	private String damageRange;
	private String[] setItemsEquipped;
	private Item transmogItem;
	
	
	private String description;
	
	private MinMaxBonus dps;
	private MinMaxBonus attacksPerSecond;
	private MinMaxBonus minDamage;
	private MinMaxBonus maxDamage;
	private Number seasonRequiredToDrop;
	private boolean isSeasonRequiredToDrop;
	
	double maxdmg;
	double mindmg;
	
	
	public String[] getSetItemsEquipped() {
		return setItemsEquipped;
	}

	public void setSetItemsEquipped(String[] setItemsEquipped) {
		this.setItemsEquipped = setItemsEquipped;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRealMaxdmg(double maxdmg) {
		this.maxdmg = maxdmg;
	}

	public void setRealMindmg(double mindmg) {
		this.mindmg = mindmg;
	}

	public Number getSeasonRequiredToDrop() {
		return seasonRequiredToDrop;
	}

	public void setSeasonRequiredToDrop(Number seasonRequiredToDrop) {
		this.seasonRequiredToDrop = seasonRequiredToDrop;
	}

	public boolean isSeasonRequiredToDrop() {
		return isSeasonRequiredToDrop;
	}

	public void setSeasonRequiredToDrop(boolean isSeasonRequiredToDrop) {
		this.isSeasonRequiredToDrop = isSeasonRequiredToDrop;
	}

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

	public double getRealBlockChance()
	{
		double bonus=0;
		if(getAttributesRaw().get("Block_Chance_Bonus_Item")!=null)
			bonus=getAttributesRaw().get("Block_Chance_Bonus_Item").getMoyenne();
			
		return getAttributesRaw().get("Block_Chance_Item").getMoyenne()+bonus;
	}
	
	public boolean isAncientItem()
	{
		
		return (getAttributesRaw().get("Ancient_Rank")!=null);
	}
	
	public boolean isSocketAddedByGift()
	{
			return (isWeapon() && getAttributesRaw().get("ConsumableAddSockets")!=null);
		
	}
	
	public String getPowerPassive()
	{
		for(String k : getAttributesRaw().keySet())
			if(k.startsWith("Item_Power_Passive"))
				return k;
		
		return null;
	}
	
	public double getRealMin()
	{
		if(!isWeapon())
			return 0;
		
		boolean isCross= (getAttributesRaw().get("Damage_Weapon_Bonus_Min_X1#Physical")!=null);
		double multiplicateur=1;
		mindmg=0;
		ELEMENTS element = getEnchantedWeapon();
		
		boolean asRubySocket=false;
				if(getGems().size()>0)
					if(getGems().get(0).getAttributesRaw().get("Damage_Weapon_Bonus_Flat#Physical")!=null)
						asRubySocket=true;
		
		
		if(getAttributesRaw().get("Damage_Weapon_Percent_All")!=null)
			multiplicateur=multiplicateur+getAttributesRaw().get("Damage_Weapon_Percent_All").getMoyenne();
		
		if(asRubySocket)
			mindmg=getGems().get(0).getAttributesRaw().get("Damage_Weapon_Bonus_Flat#Physical").getMoyenne();
		
		if(isCross)
		{
			double bowbonus=0;
			if(getAttributesRaw().get("Damage_Weapon_Bonus_Min_X1#Physical")!=null)
				bowbonus=getAttributesRaw().get("Damage_Weapon_Bonus_Min_X1#Physical").getMoyenne();
			
			
			mindmg += (getAttributesRaw().get("Damage_Weapon_Min#Physical").getMoyenne() + bowbonus)*multiplicateur;
			
			if(element!=null)
				mindmg += getAttributesRaw().get("Damage_Weapon_Min#"+element).getMoyenne()*multiplicateur;
		}
		else
		{
			mindmg += getAttributesRaw().get("Damage_Weapon_Min#Physical").getMoyenne()*multiplicateur;
			if(element!=null)
				mindmg += getAttributesRaw().get("Damage_Weapon_Min#"+element).getMoyenne()*multiplicateur;
		}
		
		
		return mindmg;
	}
	
	public double getRealMax()
	{
		if(!isWeapon())
			return 0;
		
		boolean isCross= (getAttributesRaw().get("Damage_Weapon_Bonus_Min_X1#Physical")!=null);
	
		double multiplicateur=1;
		maxdmg=0;
		ELEMENTS element = getEnchantedWeapon();
		
		if(getAttributesRaw().get("Damage_Weapon_Percent_All")!=null)
			multiplicateur=multiplicateur+getAttributesRaw().get("Damage_Weapon_Percent_All").getMoyenne();

		if(isCross)
		{
			double bowbonus=0;
			if(getAttributesRaw().get("Damage_Weapon_Bonus_Delta_X1#Physical")!=null)
				bowbonus=getAttributesRaw().get("Damage_Weapon_Bonus_Delta_X1#Physical").getMoyenne();
	
			
			
			maxdmg+=getRealMin()+((getAttributesRaw().get("Damage_Weapon_Delta#Physical").getMoyenne() + bowbonus)*multiplicateur);
			if(element!=null)
				maxdmg += getAttributesRaw().get("Damage_Weapon_Delta#"+element).getMoyenne()*multiplicateur;
		}
		else
		{
			maxdmg+=getRealMin()+(getAttributesRaw().get("Damage_Weapon_Delta#Physical").getMoyenne()*multiplicateur);
			if(element!=null)
				maxdmg += getAttributesRaw().get("Damage_Weapon_Delta#"+element).getMoyenne()*multiplicateur;

		}
		return maxdmg;
	}
	
	public double getRealDPS()
	{
		double mindmg=getRealMin();
		double maxdmg=getRealMax();
		return ((mindmg+maxdmg)/2)*getRealAttacksPerSecond();
	}
	
	
	public int nbSockets(){
		if(getAttributesRaw().get("Sockets")!=null)
			return getAttributesRaw().get("Sockets").getMoyenne().intValue();
		else if(gems==null)
			return 0;
		else
			return gems.size();
		
	}
	
	public boolean equals(Object item) {
		if(item!=null)
		{
			return getName().equalsIgnoreCase(((Item)item).getName());
		}
		
		return false;
	}

	public ELEMENTS getEnchantedWeapon()
	{
		if(!isWeapon())
			return null;
		
		if(attributesRaw==null)
			return null;
		
		Iterator<String> attributes = attributesRaw.keySet().iterator();
		while(attributes.hasNext())
		{
		
			String att= attributes.next();
			
			if(att.contains("Damage_Weapon_Min#Poison"))
				return ELEMENTS.Poison;
			if(att.contains("Damage_Weapon_Min#Arcane"))
				return ELEMENTS.Arcane;
			if(att.contains("Damage_Weapon_Min#Fire"))
				return ELEMENTS.Fire;
			if(att.contains("Damage_Weapon_Min#Lightning"))
				return ELEMENTS.Lightning;
			if(att.contains("Damage_Weapon_Min#Holy"))
				return ELEMENTS.Holy;
			if(att.contains("Damage_Weapon_Min#Cold"))
				return ELEMENTS.Cold;
		}
		return null;
		
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
	
	public double getRealAttacksPerSecond() {
		
		double bonus = 1;
		if(getAttributesRaw().get("Attacks_Per_Second_Item_Percent")!=null)
			bonus = 1+getAttributesRaw().get("Attacks_Per_Second_Item_Percent").getMoyenne();
		
		return getAttributesRaw().get("Attacks_Per_Second_Item").getMoyenne()*bonus;
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

	public MinMaxBonus getRealArmor()
	{
		
		if(getAttributesRaw().get("Armor_Item")!=null)
			return getAttributesRaw().get("Armor_Item");
		return new MinMaxBonus(0);
	}
	
	public MinMaxBonus getArmor() {
		return armor;
	}

	public void setArmor(MinMaxBonus armor) {
		this.armor = armor;
	}

	public List<Gem> getGems() {
		if(gems!=null)
			return gems;
		else
			return new ArrayList<Gem>();
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
	
	public boolean isShield()
	{
		//return blockChance!=null;
		return getAttributesRaw().get("Block_Chance_Item")!=null;
	}
	
	public boolean isWeapon() {
		return dps!=null;
	}

	public boolean isArmor() {
		return getAttributesRaw().get("Armor_Item")!=null;
		//return dps==null;
	}
	

	public void generateDisplayableAttributs() {
		Iterator<String> keys = getAttributesRaw().keySet().iterator();
		RawsAttributeFactory r = new RawsAttributeFactory();
		List<DisplayableItemAttributs> liste = new ArrayList<DisplayableItemAttributs>();
		while(keys.hasNext())
		{
			
			String key = keys.next();
			if(r.getAttribut(key)!=null)
			{
				if(!r.getAttribut(key).getLibelle().equals(""))
				{
					DisplayableItemAttributs da = new DisplayableItemAttributs();
					
					
					double value=getAttributesRaw().get(key).getMoyenne();
				
					if(key.contains("Percent") || r.getAttribut(key).getLibelle().contains("%"))
						value= getAttributesRaw().get(key).getMoyenne()*100;
					
					if(r.getAttribut(key).getLibelle().contains("+X-X"))
					{
						
						String key_min="Damage_Weapon_Min";
						String key_delta="Damage_Weapon_Delta";
						String element=key.split("#")[1];
						
						if(r.getAttribut(key).getLibelle().equals("+X-X Damage"))
						{
							key_min="Damage_Min";
							key_delta="Damage_Delta";
						}
						
					double min = getAttributesRaw().get(key_min+"#"+element).getMoyenne();
					double max = getAttributesRaw().get(key_delta+"#"+element).getMoyenne();
					da.setText(r.getAttribut(key).getLibelle().replaceFirst("X",String.valueOf(min)).replaceFirst("X", String.valueOf(min+max)));
					
					}
					else
					{
						da.setText(r.getAttribut(key).getLibelle().replaceFirst("X",String.valueOf(new DecimalFormat("#0.0").format(value).replace(",", ".")) ));
					}
					
					
					da.setColor("blue");
					if(r.getAttribut(key).isDisplayable())
						if(value>0)
							liste.add(da);
				}
					
			}
			else
			{
				//System.err.println("itemAttributes.add(new Attributs(\""+key+"\"));");
			}
		}
		
		AttributsContainer att = new AttributsContainer();
		att.setPrimary(liste);
		setAttributes(att);
		
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
		if (type.equalsIgnoreCase("Ancient Legendary"))
			setDisplayColor("orange");
		if (type.equalsIgnoreCase("Ancient Legendary Set"))
			setDisplayColor("green");
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
		return getName();
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
			weaponDefaultAS.put("CombatStaff", 1.15);
			weaponDefaultAS.put(null, 0.0);
		return weaponDefaultAS;
	}

/*
	public String getBlockChance() {
		return blockChance;
	}

	public void setBlockChance(String blockChance) {
		this.blockChance = blockChance;
	}*/

	public String getDamageRange() {
		return damageRange;
	}

	public void setDamageRange(String damageRange) {
		this.damageRange = damageRange;
	}

	public double getRealBlockMax() {
		if(getAttributesRaw().get("Block_Amount_Item_Delta")!=null)
			return getRealBlockMin()+getAttributesRaw().get("Block_Amount_Item_Delta").getMoyenne();
		
		return 0;
	}
	
	public double getRealBlockMin() {
		if(getAttributesRaw().get("Block_Amount_Item_Min")!=null)
			return getAttributesRaw().get("Block_Amount_Item_Min").getMoyenne();
		
		return 0;
	}
	
	
	
}
