package org.armory.d3.console.commands;

import org.armory.d3.console.Command;
import org.armory.d3.console.D3Console;

import com.pihen.d3restapi.beans.Hero;
	
public class Heroes implements Command {

	@Override
	public void run(String[] args) {
		
		try {
			com.pihen.d3restapi.beans.Profile p = (com.pihen.d3restapi.beans.Profile)D3Console.env.get("profile");
			
			if (p==null)
			{
				System.out.println("profile unavailable, please call profile function");
				return;
			}
			else 
			{
				for(Hero h:p.getHeroes())
				{
					System.out.println(h);
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error loading heroes ");
		}
		
	}

	@Override
	public void usage() {
		System.out.println("heroes");
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

}
