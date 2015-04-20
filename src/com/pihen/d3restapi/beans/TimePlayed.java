package com.pihen.d3restapi.beans;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class TimePlayed {
	private Number barbarian;
	@SerializedName("demon-hunter")
	private Number demonHunter;
	private Number monk;
	@SerializedName("witch-doctor")
	private Number witchDoctor;
	private Number wizard;
	private Number crusader;
	
	
	public Number getCrusader() {
		return crusader;
	}

	public void setCrusader(Number crusader) {
		this.crusader = crusader;
	}

	public Number getBarbarian() {
		return barbarian;
	}

	public void setBarbarian(Number barbarian) {
		this.barbarian = barbarian;
	}

	public Number getDemonHunter() {
		return demonHunter;
	}

	public void setDemonHunter(Number demonHunter) {
		this.demonHunter = demonHunter;
	}

	public Number getMonk() {
		return monk;
	}

	public void setMonk(Number monk) {
		this.monk = monk;
	}

	public Number getWitchDoctor() {
		return witchDoctor;
	}

	public void setWitchDoctor(Number witchDoctor) {
		this.witchDoctor = witchDoctor;
	}

	public Number getWizard() {
		return wizard;
	}

	public void setWizard(Number wizard) {
		this.wizard = wizard;
	}

	
	public String toString()
	{
		return "barb :" + barbarian + " wd " + witchDoctor + " wz :" + wizard + " cru: " + crusader + " mk: "+ monk +" dh: "+ demonHunter;
	}
	
	
	public Map<String,Double> getPercentClazz()
	{
		double total = barbarian.doubleValue()+ witchDoctor.doubleValue()+wizard.doubleValue()+crusader.doubleValue()+monk.doubleValue()+demonHunter.doubleValue();
		Map<String,Double> list = new HashMap<String,Double>();
		
		list.put("barbarian",(getBarbarian().doubleValue()/total)*100);
		
		list.put("witch-doctor" ,(getWitchDoctor().doubleValue()/total)*100);
		list.put( "wizard" ,(getWizard().doubleValue()/total)*100);
		list.put( "crusader",(getCrusader().doubleValue()/total)*100);
		list.put( "monk", (getMonk().doubleValue()/total)*100);
		list.put( "demon-hunter", (getDemonHunter().doubleValue()/total)*100);
		
		return list;
	
	}
	
}
