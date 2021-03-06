package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.armory.d3.services.D3ArmoryControler;

public class Season implements Serializable{

	private SeasonalProfile season0;
	private SeasonalProfile season1;
	private SeasonalProfile season2;
	private SeasonalProfile season3;
	private SeasonalProfile season4;
	private SeasonalProfile season5;
	private SeasonalProfile season6;
	private SeasonalProfile season7;
	private SeasonalProfile season8;
	private SeasonalProfile season9;
	private SeasonalProfile season10;
	private SeasonalProfile season11;
	private SeasonalProfile season12;
	private SeasonalProfile season13;
	
	public SeasonalProfile getSeason13() {
		return season13;
	}
	
	public void setSeason13(SeasonalProfile season13) {
		this.season13 = season13;
	}
	
	public SeasonalProfile getSeason11() {
		return season11;
	}


	public void setSeason11(SeasonalProfile season11) {
		this.season11 = season11;
	}


	public SeasonalProfile getSeason12() {
		return season12;
	}


	public void setSeason12(SeasonalProfile season12) {
		this.season12 = season12;
	}


	public SeasonalProfile getSeason9() {
		return season9;
	}


	public void setSeason9(SeasonalProfile season9) {
		this.season9 = season9;
	}


	public SeasonalProfile getSeason10() {
		return season10;
	}


	public void setSeason10(SeasonalProfile season10) {
		this.season10 = season10;
	}


	public SeasonalProfile getSeason6() {
		return season6;
	}


	public void setSeason6(SeasonalProfile season6) {
		this.season6 = season6;
	}


	public SeasonalProfile getSeason7() {
		return season7;
	}


	public void setSeason7(SeasonalProfile season7) {
		this.season7 = season7;
	}


	public SeasonalProfile getSeason8() {
		return season8;
	}


	public void setSeason8(SeasonalProfile season8) {
		this.season8 = season8;
	}


	public static Logger getLogger() {
		return logger;
	}


	public SeasonalProfile getSeason5() {
		return season5;
	}


	public void setSeason5(SeasonalProfile season5) {
		this.season5 = season5;
	}


	public SeasonalProfile getSeason4() {
		return season4;
	}


	public void setSeason4(SeasonalProfile season4) {
		this.season4 = season4;
	}

	static final Logger logger = LogManager.getLogger(Season.class.getName());
	
	
	public SeasonalProfile getSeason(int season) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		
    	Method m = this.getClass().getMethod("getSeason"+season, new Class[] {});
    	logger.debug("Call dynamic season method" + m);	
    	Object ret = m.invoke(D3ArmoryControler.getInstance().getCurrentProfil().getSeasonalProfiles());
    	return (SeasonalProfile)ret;
	}
	
	
	public SeasonalProfile getSeason3() {
		return season3;
	}

	public void setSeason3(SeasonalProfile season3) {
		this.season3 = season3;
	}

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

	public SeasonalProfile getSeason2() {
		return season2;
	}

	public void setSeason2(SeasonalProfile season2) {
		this.season2 = season2;
	}
}
