package com.pihen.d3restapi.beans;

import java.io.Serializable;
import java.util.List;

public class QuestProgression implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8623958270945604532L;
	private boolean completed;
	private List<Quest> completedQuests;
	
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public List<Quest> getCompletedQuests() {
		return completedQuests;
	}
	public void setCompletedQuests(List<Quest> completedQuests) {
		this.completedQuests = completedQuests;
	}
	
	
}
