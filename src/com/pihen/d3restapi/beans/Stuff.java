package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pihen.d3restapi.service.util.EnumerationStuff;



public class Stuff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7916630887795237313L;
	private Item bracers;
	private Item feet;
	private Item hands;
	private Item head;
	private Item legs;
	private Item mainHand;
	private Item offHand;
	private Item torso;
	private Item waist;
	private Item rightFinger;
	private Item leftFinger;
	private Item neck;
	private Item shoulders;
	
	
	public Item getShoulders() {
		
		return shoulders;
	}

	public void setShoulders(Item shoulders) {
		this.shoulders = shoulders;
	}

	public Item getBracers() {
		return bracers;
	}

	public void setBracers(Item bracers) {
		this.bracers = bracers;
	}

	public Item getFeet() {
		return feet;
	}

	public void setFeet(Item feet) {
		this.feet = feet;
	}

	public Item getHands() {
		return hands;
	}

	public void setHands(Item hands) {
		this.hands = hands;
	}

	public Item getHead() {
		return head;
	}

	public void setHead(Item head) {
		this.head = head;
	}

	public Item getLegs() {
		return legs;
	}

	public void setLegs(Item legs) {
		this.legs = legs;
	}

	public Item getMainHand() {
		return mainHand;
	}

	public void setMainHand(Item mainHand) {
		this.mainHand = mainHand;
	}

	public Item getOffHand() {
		return offHand;
	}

	public void setOffHand(Item offHand) {
		this.offHand = offHand;
	}

	public Item getTorso() {
		return torso;
	}

	public void setTorso(Item torso) {
		this.torso = torso;
	}

	public Item getWaist() {
		return waist;
	}

	public void setWaist(Item waist) {
		this.waist = waist;
	}

	public Item getRightFinger() {
		return rightFinger;
	}

	public void setRightFinger(Item rightFinger) {
		this.rightFinger = rightFinger;
	}

	public Item getLeftFinger() {
		return leftFinger;
	}

	public void setLeftFinger(Item leftFinger) {
		this.leftFinger = leftFinger;
	}

	public Item getNeck() {
		return neck;
	}

	public void setNeck(Item neck) {
		this.neck = neck;
	}

	public List<Item> getItems()
	{
		List<Item> list = new ArrayList<Item>();
		list.add(getNeck());
		list.add(getWaist());
		list.add(getBracers());
		list.add(getFeet());
		list.add(getHands());
		list.add(getHead());
		list.add(getLegs());
		list.add(getMainHand());
		list.add(getOffHand());
		list.add(getLeftFinger());
		list.add(getRightFinger());
		list.add(getShoulders());
		list.add(getTorso());
		return list;
		
	}
	
	
	
	public Item get(EnumerationStuff e)
	{
		switch (e) 
		{
			case NECK : return getNeck();	
			case BELT : return getWaist();
			case BRACER : return getBracers();
			case FEET : return getFeet();
			case GANT : return getHands();
			case HEAD : return getHead();
			case LEGS : return getLegs();
			case MAIN_HAND: return getMainHand();
			case OFF_HAND : return getOffHand();
			case RING_LEFT : return getLeftFinger();
			case RING_RIGHT : return getRightFinger();
			case SHOULDERS : return getShoulders();
			case TORSO : return getTorso();
			default : return null;
		}
		
	}
	
	
}
