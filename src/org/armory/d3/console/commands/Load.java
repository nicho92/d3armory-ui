package org.armory.d3.console.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.armory.d3.console.Command;
import org.armory.d3.console.D3Console;
import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.Tag;

public class Load implements Command {

	Options opts;
	
	public Load()
	{
		opts = new Options();
			opts.addOption("p","profil",true,"Loading Profile");
			opts.addOption("h","hero",true,"Loading Hero");
			opts.addOption("r","region",true,"set region var");
			opts.addOption("l","local",true,"set local variable");
			opts.addOption("?","help",false,"help for command");
	}
	
	
	public void run(String[] args) throws Exception 
	{
		CommandLine cl = parser.parse(opts, args);
		
		if(cl.hasOption("l"))
		{
			D3ArmoryControler.getInstance().setProperty("local", cl.getOptionValue("l"));
		}
		
		
		
		if(cl.hasOption("p"))
		{
			
			out.println("loading " + cl.getOptionValue("p") +"#"+D3Console.ENV.get("region"));
			Profile p = D3ArmoryControler.getInstance().getProfil(new Tag(cl.getOptionValue("p") +"#"+D3Console.ENV.get("region")));
			D3ArmoryControler.getInstance().setProfile(p);
		}
		
		if(cl.hasOption("h"))
		{
			Hero h = D3ArmoryControler.getInstance().getHeroDetails(Long.parseLong(cl.getOptionValue("h")));
			D3ArmoryControler.getInstance().setSelectedHero(h);
			out.println(h + " loaded");
		}
		
		if(cl.hasOption("?"))
		{
			usage();
		}
		
		

	}

	@Override
	public void usage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "load", opts );

	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}


}
