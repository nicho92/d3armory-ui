package com.pihen.d3restapi.beans;

public class Attributs implements Comparable<Attributs> {
	private String id;
	private String libelle;
	private MinMaxBonus value;
	private boolean displayable;
	
	
	public void setDisplayable(boolean displayable) {
		this.displayable = displayable;
	}
	public boolean isDisplayable()
	{
		return displayable;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public MinMaxBonus getValue() {
		return value;
	}
	public void setValue(MinMaxBonus value) {
		this.value = value;
	}
	
	public Attributs()
	{
		
	}
	
	public Attributs(String id,String libelle)
	{
		this.id=id;
		this.libelle=libelle;
		this.value= new MinMaxBonus(0);
		this.displayable=true;
	}
	
	public Attributs(String id,String libelle,boolean displayable)
	{
		this.id=id;
		this.libelle=libelle;
		this.value= new MinMaxBonus(0);
		this.displayable=displayable;
	}
	
	public String toString()
	{
		return libelle;
	}

	public int compareTo(Attributs o) {
		return libelle.compareTo(o.getLibelle());
	}

	public int hashCode() {
		return id.hashCode();
	}

	
	
	
	
}
