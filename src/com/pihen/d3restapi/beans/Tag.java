package com.pihen.d3restapi.beans;

public class Tag {

	String region;
	String name;
	Long number;
	
	
	/**
	 * 
	 * @param ligne : NAME#NUMBER#REGION
	 */
	public Tag(String ligne)
	{
		try{
		String values[] = ligne.split("#");
		setRegion(values[2]);
		setName(values[0]);
		setNumber(Long.parseLong(values[1]));
		}catch(Exception e)
		{
			//System.err.println("ERREUR " + ligne);
		}
	}
	
	public String toString()
	{
		return getName()+"#"+getNumber();
	}
	
	public Tag(String region,String name,Long number)
	{
		this.region=region;
		this.name=name;
		this.number=number;
	}
	
	
	public String getHost()
	{
		return getRegion()+".api.battle.net";
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
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
	
	
	
	
}
