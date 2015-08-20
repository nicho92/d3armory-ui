package org.armory.d3.console;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.armory.d3.services.D3ArmoryControler;


public class D3Console {

	public static Map<String,Object> ENV=new HashMap<String, Object>();
	
	ClassLoader classLoader = D3Console.class.getClassLoader();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				 new D3Console();
				
			}
		}).start();
	}
	
	public D3Console()  {
		
		Console io = new Console();
				io.setTitle("D3 Console");
				io.setSize(1024,500);
				io.setLocationRelativeTo(null);
				io.setVisible(true);
				io.println("Welcome to D3 Console. Type help for commands",Color.CYAN);
		
		//Console.io.print(getPrompt());
		Command c = null;
		String line ="";
		while(line !="quit")
		{	
			try {
				io.print(getPrompt());
				line = io.nextLine();
				CommandLineParser parser = new DefaultParser();
				String[] commandeLine = line.split(" ");
					c = commandFactory(commandeLine[0]);
					c.run(commandeLine);
					c.quit();
		    } catch (Exception e) {
		    	handleException(e,c);
		    	io.print(getPrompt());
		    	line = io.nextLine();
		    } 
			
		}
			
	}
	
	private void handleException(Exception e, Command c) {
		if(c!=null)
		{
			e.printStackTrace();
			c.usage();
		}
	}
	
	private String getPrompt()
	{
		String prompt = "";
		if(D3ArmoryControler.getInstance().getCurrentProfil()!=null)
		{
			prompt=D3ArmoryControler.getInstance().getCurrentProfil().toString();
		}
		if(D3ArmoryControler.getInstance().getSelectedHero(false)!=null)
		{
			prompt+="/"+D3ArmoryControler.getInstance().getSelectedHero(false).getName();
		}
		return prompt + " $>";
	}
	
	
	

	public Command commandFactory(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String clazz = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		Class myCommand = classLoader.loadClass("org.armory.d3.console.commands."+clazz);
        Command c = (Command)myCommand.newInstance();
        
        return c;
	}
	
	
}
