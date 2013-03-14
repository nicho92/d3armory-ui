package org.armory.d3.beans;

public class Salvage {
	private Item item;
	private Number quantity;
	private Double chance;
	
	
	public Double getChance() {
		return chance;
	}

	public void setChance(Double chance) {
		this.chance = chance;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Number getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Number quantity) {
		this.quantity = quantity;
	}
}
