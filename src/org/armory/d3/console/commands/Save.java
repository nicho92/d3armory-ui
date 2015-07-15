package org.armory.d3.console.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.armory.d3.console.Command;
import org.armory.d3.console.D3Console;
import org.armory.d3.services.D3ArmoryControler;

public class Save implements Command {

	Options opts;
	
	public Save()
	{
		opts = new Options();
		opts.addOption("t","tag",true,"save tag");
		opts.addOption("l","local",true,"set local variable");
		opts.addOption("r","region",true,"set region variable");
		opts.addOption("?","help",false,"help for command");
	}
	
	
	@Override
	public void run(String[] args) throws Exception {
		CommandLine cl = parser.parse(opts, args);
		if(cl.hasOption("t"))
		{
			D3ArmoryControler.getInstance().addTags(cl.getOptionValue("t"), (String)D3Console.ENV.get("region"));
		}
		if(cl.hasOption("r"))
		{
			D3Console.ENV.put("region", cl.getOptionValue("r"));
			out.println("set region to " + cl.getOptionValue("r"));
		}
	}

	@Override
	public void usage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "save", opts );
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}
	
}

