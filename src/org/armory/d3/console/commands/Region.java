package org.armory.d3.console.commands;

import org.armory.d3.console.Command;
import org.armory.d3.console.D3Console;
	
public class Region implements Command {

	@Override
	public void run(String[] args) {
		
		try {
			D3Console.env.put("region", args[0]);
		} catch (Exception e) {
			System.out.println("Error setting region ");
		}
		
	}

	@Override
	public void usage() {
		System.out.println("region <eu|us|tw|kr>");
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

}
