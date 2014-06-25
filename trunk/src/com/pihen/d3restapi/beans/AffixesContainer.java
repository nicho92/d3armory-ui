package com.pihen.d3restapi.beans;

import java.util.List;

public class AffixesContainer {

	private List<Affixes> oneOf;

	public List<Affixes> getOneOf() {
		return oneOf;
	}

	public void setOneOf(List<Affixes> oneOf) {
		this.oneOf = oneOf;
	}

	public String toString()
	{
		return oneOf.toString();
	}
}
