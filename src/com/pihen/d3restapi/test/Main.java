package com.pihen.d3restapi.test;

import java.io.File;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;


public class Main {

	public static void main(String[] args) throws Exception {
  
		
		for(File f : new File(D3ArmoryControler.SERIALISATION_HERO_DIR).listFiles())
		{
			Long number = Long.parseLong(f.getName().substring(0, f.getName().lastIndexOf(".")));
			Hero h = D3ArmoryControler.getInstance().loadHero(number);
			System.out.println("******"+h);
			for(Item i : h.getItems().getItems())
				if(i!=null)
					System.out.println(i);
		}
	}
	
	
}

