package org.armory.d3.services;

import java.io.File;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Item;

public interface D3ObjectRecorder {

	static final Logger logger = LogManager.getLogger(D3ObjectRecorder.class.getName());

	public static String SERIALISATION_ITEM_DIR=D3ArmoryControler.CONF_DIR+"/items";
	public static String SERIALISATION_HERO_DIR=D3ArmoryControler.CONF_DIR+"/heroes";
	public static String SERIALISATION_BUILD_DIR=D3ArmoryControler.CONF_DIR+"/builds";

	
	public void saveItem(Item i)throws Exception;
	public Item loadItem(File f)throws Exception;
	
	public void saveBuild(HeroSkillContainer i)throws Exception;
	public HeroSkillContainer loadBuild(File f)throws Exception;
	public List<HeroSkillContainer> loadBuilds()throws Exception;
	
	
	public Hero loadHero(Number number) throws Exception;
	public void saveHero(Hero i)throws Exception;
	
	public List<Item> listSavedItems();
	public List<Hero> listSavedHeroes();
	
	
	public String getRecorderName();
}
