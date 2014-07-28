package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class Progression implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6637648015577173612L;
	private QuestProgression act1;
	private QuestProgression act2;
	private QuestProgression act3;
	private QuestProgression act4;
	private QuestProgression act5;
	
	
	public QuestProgression getAct1() {
		return act1;
	}


	public void setAct1(QuestProgression act1) {
		this.act1 = act1;
	}


	public QuestProgression getAct2() {
		return act2;
	}


	public void setAct2(QuestProgression act2) {
		this.act2 = act2;
	}


	public QuestProgression getAct3() {
		return act3;
	}


	public void setAct3(QuestProgression act3) {
		this.act3 = act3;
	}


	public QuestProgression getAct4() {
		return act4;
	}


	public void setAct4(QuestProgression act4) {
		this.act4 = act4;
	}


	public QuestProgression getAct5() {
		return act5;
	}


	public void setAct5(QuestProgression act5) {
		this.act5 = act5;
	}


	@Override
	public String toString() {
		return "Act 1: " + act1 + " Act 2 " + act2 + " Act 3 " + act3 + " Act 4 " + act4 + " Act 5 " + act5;
	}	
}
