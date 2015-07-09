package org.armory.d3.console.commands;

import java.util.Enumeration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.armory.d3.console.Command;
import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Stuff;
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
	}
	
	public void run(String[] args) throws Exception {
		CommandLine cl = parser.parse(opts, args);
		
		if(cl.hasOption("p"))
		{
			System.out.println(D3ArmoryControler.getInstance().getCurrentProfil());
		}
		
		if(cl.hasOption("hs"))
		{
			for(Hero h: D3ArmoryControler.getInstance().getCurrentProfil().getHeroes())
			{
				System.out.println(h.getId() + " - "+ h.getName() +" " + h.getClazz() );
			}
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
