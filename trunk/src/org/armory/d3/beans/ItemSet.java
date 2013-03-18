package org.armory.d3.beans;

import org.armory.d3.services.D3ArmoryControler;

import com.sdfteam.d3armory.service.configuration.Configuration;
import com.sdfteam.d3armory.service.remote.RemoteService;
import com.sdfteam.d3armory.service.remote.SpringRemoteService;
import com.sdfteam.d3armory.service.util.EnumerationSet;



public class ItemSet {
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

	public Item get(EnumerationSet e)
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
