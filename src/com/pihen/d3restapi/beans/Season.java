package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
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

	static final Logger logger = LogManager.getLogger(Season.class.getName());
	
	
	public SeasonalProfile getSeason(String season) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
    	SeasonalProfile c = new SeasonalProfile();
    	Method m = this.getClass().getMethod("getSeason"+season, new Class[] {});
    	logger.debug("Call dynamic season method" + m);
    	Object ret = m.invoke(Season.class.newInstance());
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
