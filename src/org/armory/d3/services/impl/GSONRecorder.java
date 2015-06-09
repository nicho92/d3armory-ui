package org.armory.d3.services.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.armory.d3.services.D3ObjectRecorder;

import com.google.gson.Gson;
import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Item;

public class GSONRecorder implements D3ObjectRecorder {

	@Override
	public void saveItem(Item i) throws Exception {
		
		logger.debug("save item "+i.getName());
		File f = new File(SERIALISATION_ITEM_DIR +"/"+i.getName()+".d3itemG");
		Gson GSON = new Gson();
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(GSON.toJson(i));
		bw.close();

	}

	@Override
	public Item loadItem(File fi)  throws Exception {
		File f = new File(SERIALISATION_ITEM_DIR +"/"+fi.getName());
		InputStream fos = new FileInputStream(f);
		String sItem = new BufferedReader(new InputStreamReader(fos)).readLine();
		Gson GSON = new Gson();
		Item h = GSON.fromJson(sItem, Item.class);
		logger.debug("load item " +h );
		return h;
	}

	@Override
	public void saveBuild(HeroSkillContainer i)  throws Exception {
		File f = new File(SERIALISATION_BUILD_DIR +"/"+i.getNameBuild()+".d3buildG");
		logger.debug("save build =" +  i.getNameBuild()  );
		Gson GSON = new Gson();
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(GSON.toJson(i));
		bw.close();
	}

	@Override
	public HeroSkillContainer loadBuild(File f)   throws Exception {
		InputStream fos = new FileInputStream(f);
		String sHero = new BufferedReader(new InputStreamReader(fos)).readLine();
		Gson GSON = new Gson();
		HeroSkillContainer h = GSON.fromJson(sHero, HeroSkillContainer.class);
		logger.debug("load build " +  h );
		return h;
	}

	@Override
	public List<HeroSkillContainer> loadBuilds() throws Exception {
		File f = new File(SERIALISATION_BUILD_DIR);
		List<HeroSkillContainer> skills = new ArrayList<HeroSkillContainer>();
		
		for (File fb : f.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".d3buildGG");
			}
		}))
			skills.add(loadBuild(new File(fb.getAbsolutePath().substring(0, fb.getAbsolutePath().indexOf(".d3buildGG")))));
		
			return skills;
	}

	public File[] getListeFileItem() {
		File f = new File(SERIALISATION_ITEM_DIR);
		return f.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".d3itemG");
			}
		});
	}
	

	public void saveHero(Hero i)throws Exception
	{
			File f = new File(SERIALISATION_HERO_DIR +"/"+i.getId()+".d3heroG");
			logger.debug("save hero =" + SERIALISATION_HERO_DIR +"/"+i.getId()+".d3heroG -> " + i  );
			Gson GSON = new Gson();
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(GSON.toJson(i));
			bw.close();
	}
	
	public Hero loadHero(Number number)throws Exception
	{
			File f = new File(SERIALISATION_HERO_DIR +"/"+number+".d3heroG");
			InputStream fos = new FileInputStream(f);
			String sHero = new BufferedReader(new InputStreamReader(fos)).readLine();
			Gson GSON = new Gson();
			Hero h = GSON.fromJson(sHero, Hero.class);
			logger.debug("load hero =" + SERIALISATION_HERO_DIR +"/"+number+".d3heroG -> " + h );
			return h;
	}

	public String getRecorderName() {
		return "GSON Recorder";
	}
	

}
