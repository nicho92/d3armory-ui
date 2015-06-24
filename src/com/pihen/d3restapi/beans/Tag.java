package com.pihen.d3restapi.beans;

public class Tag {

	String region;
	String name;
	Integer number;
	
	
	/**
	 * 
	 * @param ligne : NAME#NUMBER#REGION
	 */
	public Tag(String ligne)
	{
		String values[] = ligne.split("#");
		setRegion(values[2]);
		setName(values[0]);
		setNumber(Integer.parseInt(values[1]));
	}
	
	public String toString()
	{
		return getName()+"#"+getNumber();
	}
	
	public Tag(String region,String name,int number)
	{
		this.region=region;
		this.name=name;
		this.number=number;
	}
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
	
	
}
