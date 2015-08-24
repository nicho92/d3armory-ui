package org.armory.d3.console.commands;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.armory.d3.console.Command;
import org.armory.d3.console.table.ASCIITable;
import org.armory.d3.console.table.impl.CollectionASCIITableAware;
import org.armory.d3.console.table.spec.IASCIITableAware;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.impl.BNetLadderRetriever;

public class Ladder implements Command {

	Options opts;
	
	public Ladder() {
		opts = new Options();
		opts.addOption("r","region",true,"region param");
		opts.addOption("s","season",true,"Season param");
		opts.addOption("l","limit",true,"limit result");
		opts.addOption("h","hardcore",true,"hardcore");
		opts.addOption("e","era",true,"era");
		opts.addOption("c","class",true,"select class or group ");
		opts.addOption("?","help",false,"help for command");
	}
	
	
	@Override
	public void run(String[] args) throws Exception {
		
		CommandLine cl = parser.parse(opts, args);
		boolean season = false;
		boolean hardcore = false;
		int era = D3ArmoryControler.getInstance().getSeason();
		
		
		String clazz = ""; 
		
		if(D3ArmoryControler.getInstance().getSelectedHero(false)!=null)
			clazz=D3ArmoryControler.getInstance().getSelectedHero(false).getClazz();
		
		
		String region="eu";
		
		if(cl.hasOption("r"))
			region=cl.getOptionValue("r");
		
		season = cl.hasOption("s");
		hardcore= cl.hasOption("h");
		
		
		if(cl.hasOption("r"))
			region=cl.getOptionValue("r");
		
		if(cl.hasOption("r"))
			region=cl.getOptionValue("r");
		
		if(cl.hasOption("e"))
			era=Integer.parseInt(cl.getOptionValue("e"));
		
		if(cl.hasOption("c"))
			clazz=cl.getOptionValue("c");
		
		if(cl.hasOption("l"))
			D3ArmoryControler.getInstance().setProperty("maxResultLadder", cl.getOptionValue("l"));
			
		BNetLadderRetriever retriver = new BNetLadderRetriever(region, clazz, season, hardcore, String.valueOf(era));

		retriver.retrieveLadder();
		
		
		Map<Integer, com.pihen.d3restapi.beans.Ladder> lad = retriver.getLadders();

		IASCIITableAware asciiTableAware = new CollectionASCIITableAware<com.pihen.d3restapi.beans.Ladder>(new ArrayList<com.pihen.d3restapi.beans.Ladder>(lad.values()), 
																										   "rank","levelRift","profile","time","date","name");
		
		
		ASCIITable.getInstance(out).printTable(asciiTableAware);
		
	}

	@Override
	public void usage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "ladder", opts );
	}

	@Override
	public void quit() {
		

	}

}
