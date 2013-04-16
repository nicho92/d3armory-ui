package org.armory.d3.beans;

public class Attributs implements Comparable<Attributs> {
	private String id;
	private String libelle;
	private MinMaxBonus value;
	
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
