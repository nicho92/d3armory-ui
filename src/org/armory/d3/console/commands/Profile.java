package org.armory.d3.console.commands;

import org.armory.d3.console.Command;
import org.armory.d3.console.D3Console;
import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Tag;
	
public class Profile implements Command {

	@Override
	public void run(String[] args) {
		
		try {
			Object region = D3Console.env.get("region");
			
			if (region==null)
			{
				System.out.println("region unavailable, please call region function");
				return;
			}
			else {
				com.pihen.d3restapi.beans.Profile p = D3ArmoryControler.getInstance().getProfil(new Tag(args[0]+"#"+region.toString()));
				D3Console.env.put("profile", p);
				System.out.println("Chargement de " + p);
			}
		} catch (Exception e) {
			System.out.println("Error loading profile ");
		}
		
	}

	@Override
	public void usage() {
		System.out.println("profile tag#number");
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

}
