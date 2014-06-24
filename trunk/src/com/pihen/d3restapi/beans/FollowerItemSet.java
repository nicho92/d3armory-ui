package com.pihen.d3restapi.beans;


public class FollowerItemSet {
	private Item leftFinger;
	private Item mainHand;
	private Item neck;
	private Item offHand;
	private Item rightFinger;
	private Item special;
	
	
	public String toString()
	{
		return leftFinger + " " + mainHand + " " + offHand + " " + neck + " " + special +" " + rightFinger;  
	}
	
	
	public Item getSpecial() {
		return special;
	}

	public void setSpecial(Item special) {
		this.special = special;
	}

	public Item getLeftFinger() {
		return leftFinger;
	}

	public void setLeftFinger(Item leftFinger) {
		this.leftFinger = leftFinger;
	}

	public Item getMainHand() {
		return mainHand;
	}

	public void setMainHand(Item mainHand) {
		this.mainHand = mainHand;
	}

	public Item getNeck() {
		return neck;
	}

	public void setNeck(Item neck) {
		this.neck = neck;
	}

	public Item getOffHand() {
		return offHand;
	}

	public void setOffHand(Item offHand) {
		this.offHand = offHand;
	}

	public Item getRightFinger() {
		return rightFinger;
	}

	public void setRightFinger(Item rightFinger) {
		this.rightFinger = rightFinger;
	}

}
