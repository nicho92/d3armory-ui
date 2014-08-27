package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Season implements Serializable{

	@SerializedName("0") private SeasonalProfile season0;

	public SeasonalProfile getSeason0() {
		return season0;
	}

	public void setSeason0(SeasonalProfile season0) {
		this.season0 = season0;
	}
}
