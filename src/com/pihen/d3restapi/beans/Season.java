package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Season implements Serializable{

	private SeasonalProfile season0;
	private SeasonalProfile season1;
	
	public SeasonalProfile getSeason1() {
		return season1;
	}

	public void setSeason1(SeasonalProfile season1) {
		this.season1 = season1;
	}

	public SeasonalProfile getSeason0() {
		return season0;
	}

	public void setSeason0(SeasonalProfile season0) {
		this.season0 = season0;
	}
}
