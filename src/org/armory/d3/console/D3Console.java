package org.armory.d3.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class D3Console {

	public static Map<String,Object> env=new HashMap<String, Object>();
	
	ClassLoader classLoader = D3Console.class.getClassLoader();
	
	public static void main(String[] args) {
		new D3Console();
	}
	
	public D3Console()  {
		try {
			
			System.out.print("D3Console :\n$>");
			Scanner sc = new Scanner(System.in);
			while(sc.hasNextLine())
			{
				String[] commandeLine = sc.nextLine().split(" ");
				Command c = commandFactory(commandeLine[0]);
				
				if(commandeLine.length>1)
					c.run(new String[]{commandeLine[1]});
				else
					c.run(new String[]{null});
				System.out.print("$>");
			}
			
			
	    } catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	public Command commandFactory(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		Class myCommand = classLoader.loadClass("org.armory.d3.console.commands."+name);
        Command c = (Command)myCommand.newInstance();
        return c;
	}
	
	
}
