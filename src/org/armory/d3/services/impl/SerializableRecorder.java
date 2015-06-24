package org.armory.d3.services.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.armory.d3.services.D3ObjectRecorder;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Item;

public class SerializableRecorder implements D3ObjectRecorder {
	
	
	public void saveItem(Item i) throws Exception
	{
			logger.debug("Save Item " +i );
			FileOutputStream fos = new FileOutputStream(SERIALISATION_ITEM_DIR +"/"+i.getName()+".d3item");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(i);
			oos.flush();
			oos.close();
	}
	
	public Item loadItem(File f)  throws Exception
	{
			logger.debug("Loading item from " +f );
			FileInputStream fos = new FileInputStream(SERIALISATION_ITEM_DIR +"/"+f.getName());
			ObjectInputStream ois  = new ObjectInputStream(fos);
			Item i = (Item)ois.readObject();
			ois.close();
			return i;
	}

	public void saveBuild(HeroSkillContainer i) throws Exception
	{
			logger.debug("Save Build " +i.getNameBuild() );
			FileOutputStream fos = new FileOutputStream(SERIALISATION_BUILD_DIR +"/"+i.getNameBuild()+".d3build");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(i);
			oos.flush();
			oos.close();
	}
	
	public HeroSkillContainer loadBuild(File f) throws Exception
	{
			logger.debug("Load Build from  " +f );
			FileInputStream fos = new FileInputStream(SERIALISATION_BUILD_DIR +"/"+f.getName()+".d3build");
			ObjectInputStream ois  = new ObjectInputStream(fos);
			HeroSkillContainer hsc= (HeroSkillContainer)ois.readObject();
			ois.close();
			return hsc;
		
	}
	
	public List<HeroSkillContainer> loadBuilds() throws Exception
	{
		File f = new File(SERIALISATION_BUILD_DIR);
		List<HeroSkillContainer> skills = new ArrayList<HeroSkillContainer>();
		
		for (File fb : f.listFiles())
			skills.add(loadBuild(new File(fb.getAbsolutePath().substring(0, fb.getAbsolutePath().indexOf(".d3build")))));
		
			return skills;
	}


	public Hero loadHero(Number number)throws Exception
	{
			logger.debug("Load hero id=  " +number );
			FileInputStream fos = new FileInputStream(SERIALISATION_HERO_DIR +"/"+number+".d3hero");
			ObjectInputStream ois  = new ObjectInputStream(fos);
			Hero h = (Hero)ois.readObject();
			ois.close();
			return h;
	}

	public void saveHero(Hero i)throws Exception
	{
			logger.debug("save hero " +i);
			FileOutputStream fos = new FileOutputStream(SERIALISATION_HERO_DIR +"/"+i.getId()+".d3hero");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(i);
			oos.flush();
			oos.close();
	}

	public List<Item> listSavedItems()
	{
		File f = new File(SERIALISATION_ITEM_DIR);
		List<Item> items = new ArrayList<Item>();
		
		for(File fItems : f.listFiles(new FileFilter() {
											public boolean accept(File pathname) {
													return pathname.getName().endsWith(".d3item");
											}
									})
			)
		{
			try{
				items.add(loadItem(fItems));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return items;
	}
	
	public String getRecorderName() {
		return "Serializer Recorder";
	}

	public List<Hero> listSavedHeroes() {
		File f = new File(SERIALISATION_HERO_DIR);
		List<Hero> heroes = new ArrayList<Hero>();
		
		for(File fHero : f.listFiles(new FileFilter() {
											public boolean accept(File pathname) {
													return pathname.getName().endsWith(".d3heroG");
											}
									})
			)
		{
			try{
				heroes.add(loadHero(Long.parseLong(fHero.getName().substring(0, fHero.getName().indexOf(".d3hero")))));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return heroes;
	}
}
