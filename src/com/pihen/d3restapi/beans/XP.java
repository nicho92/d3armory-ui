package com.pihen.d3restapi.beans;

public class XP
{
	private int level;
	private long expLevel;
	private long totalExp;
	
	public XP(int level,long xpLevel,long xpTotal)
	{
		this.level=level;
		this.expLevel=xpLevel;
		this.totalExp=xpTotal;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getExpLevel() {
		return expLevel;
	}
	public void setExpLevel(long expLevel) {
		expLevel = expLevel;
	}
	public long getTotalExp() {
		return totalExp;
	}
	public void setTotalExp(long totalExp) {
		totalExp = totalExp;
	}
	
	public String toString()
	{
		return level +" " + totalExp;
	}
}