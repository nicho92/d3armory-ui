package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class FollowersList implements Serializable{

	private static final long serialVersionUID = 1762202995634306237L;
	private Follower enchantress;
	private Follower scoundrel;
	private Follower templar;

	public Follower getEnchantress() {
		return enchantress;
	}

	public void setEnchantress(Follower enchantress) {
		this.enchantress = enchantress;
	}

	public Follower getScoundrel() {
		return scoundrel;
	}

	public void setScoundrel(Follower scoundrel) {
		this.scoundrel = scoundrel;
	}

	public Follower getTemplar() {
		return templar;
	}

	public void setTemplar(Follower templar) {
		this.templar = templar;
	}
}
