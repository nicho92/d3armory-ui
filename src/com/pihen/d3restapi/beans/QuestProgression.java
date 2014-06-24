package com.pihen.d3restapi.beans;

import java.util.List;

public class QuestProgression {

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
