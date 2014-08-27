package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.Map;


public class Gem implements Serializable{

	private Item item;
	private AttributsContainer attributes;
	private Map<String, MinMaxBonus > attributesRaw;
	private boolean isGem;
	private boolean isJewel;
	private Number jewelRank;
	private Number jewelSecondaryEffectUnlockRank;
	private String jewelSecondaryEffect;
	
	public Map<String, MinMaxBonus> getAttributesRaw() {
		return attributesRaw;
	}
	public void setAttributesRaw(Map<String, MinMaxBonus> attributesRaw) {
		this.attributesRaw = attributesRaw;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public AttributsContainer getAttributes() {
		return attributes;
	}
	public void setAttributes(AttributsContainer attributes) {
		this.attributes = attributes;
	}
	
	public boolean isGem() {
		return isGem;
	}
	public void setGem(boolean isGem) {
		this.isGem = isGem;
	}
	public boolean isJewel() {
		return isJewel;
	}
	public void setJewel(boolean isJewel) {
		this.isJewel = isJewel;
	}
	public Number getJewelRank() {
		return jewelRank;
	}
	public void setJewelRank(Number jewelRank) {
		this.jewelRank = jewelRank;
	}
	public Number getJewelSecondaryEffectUnlockRank() {
		return jewelSecondaryEffectUnlockRank;
	}
	public void setJewelSecondaryEffectUnlockRank(
			Number jewelSecondaryEffectUnlockRank) {
		this.jewelSecondaryEffectUnlockRank = jewelSecondaryEffectUnlockRank;
	}
	public String getJewelSecondaryEffect() {
		return jewelSecondaryEffect;
	}
	public void setJewelSecondaryEffect(String jewelSecondaryEffect) {
		this.jewelSecondaryEffect = jewelSecondaryEffect;
	}
	public String toString()
	{
		return item.toString();
	}

	public final static String[] QUALITIES()
	{
		return new String[]{"Chipped","Flawed","Normal","Flawless","Perfect","Radiant","Square","Flawless Square","Perfect Square","Radiant Square","Star","Flawless Star","Perfect Star","Marquise","Flawless Imperial","Royal","Flawless Royal"};
	}
	
	public static enum TYPES {Amethyst,Diamond,Emerald,Ruby,Topaz};
	
}
