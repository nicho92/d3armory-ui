package org.armory.d3.console;

public interface Command {

	
	public void run(String[] args);
	
	public void usage();
	
	public void quit();
	
	
}
