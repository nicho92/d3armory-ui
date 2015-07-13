package org.armory.d3.console.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.armory.d3.console.Command;
import org.armory.d3.console.D3Console;
import org.armory.d3.console.table.ASCIITable;
import org.armory.d3.console.table.Test.Employee;
import org.armory.d3.console.table.impl.CollectionASCIITableAware;
import org.armory.d3.console.table.spec.IASCIITableAware;
import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Stuff;
import com.pihen.d3restapi.beans.Tag;
import com.pihen.d3restapi.service.util.EnumerationStuff;

public class Show implements Command {

	Options opts;
	
	public Show()
	{
		opts = new Options();
		opts.addOption("p","profil",false,"Show Profil detail");
		opts.addOption("hs","heroes",false,"show heroes");
		opts.addOption("?","help",false,"help for command");
		opts.addOption("i","items",false,"show item for selected hero");
		opts.addOption("r","region",false,"show region");
		opts.addOption("t","tags",false,"show saved tags");
	}
	
	public void run(String[] args) throws Exception {
		CommandLine cl = parser.parse(opts, args);
		
		if(cl.hasOption("p"))
		{
			System.out.println(D3ArmoryControler.getInstance().getCurrentProfil());
		}
		
		if(cl.hasOption("hs"))
		{
			IASCIITableAware asciiTableAware = new CollectionASCIITableAware<Hero>(D3ArmoryControler.getInstance().getCurrentProfil().getHeroes(), 
			    			"id","name", "clazz", "level", "hardcore", "seasonal");
			    ASCIITable.getInstance().printTable(asciiTableAware);
		}
		
		if(cl.hasOption("i"))
		{
			Stuff st = D3ArmoryControler.getInstance().getSelectedHero(false).getItems();
			
			
			
			
			EnumerationStuff[] gears = EnumerationStuff.values();
			
			for(int i = 0;i<gears.length;i++)
			{
				System.out.println(gears[i] + " " + st.get(gears[i]));
			}
			
		}
		if(cl.hasOption("r"))
		{
			System.out.println(D3Console.ENV.get("region"));
		}
		if(cl.hasOption("t"))
		{
			for(Tag t:D3ArmoryControler.getInstance().getListTags())
				System.out.println(t);
		}
		if(cl.hasOption("?"))
		{
			usage();
		}
		
	}

	@Override
	public void usage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "show", opts );

	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}

}
