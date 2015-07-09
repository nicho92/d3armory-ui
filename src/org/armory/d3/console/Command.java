package org.armory.d3.console;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;

public interface Command {

	CommandLineParser parser = new DefaultParser();
	
	
	public void run(String[] array) throws Exception ;
	
	public void usage();
	
	public void quit();

	


}
