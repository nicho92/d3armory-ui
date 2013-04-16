package org.armory.d3.beans;

public class MinMaxBonus {

	private Double min;
	private Double max;
	
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	
	public Double getMoyenne()
	{
		return (min+max)/2;
	}
	
	public String toString()
	{
		return "min:"+min+",max:"+max;
	}
	
	public void setValue(double val)
	{
		setMin(val);
		setMax(val);
	}
	
	public MinMaxBonus()
	{
		
	}
	
	public MinMaxBonus(double val)
	{
		setValue(val);
	}
	
	
}
