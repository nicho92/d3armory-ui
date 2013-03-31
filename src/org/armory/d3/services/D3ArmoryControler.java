package org.armory.d3.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.beans.Profile;

import com.sdfteam.d3armory.service.configuration.Configuration;
import com.sdfteam.d3armory.service.remote.RemoteService;
import com.sdfteam.d3armory.service.remote.SpringRemoteService;
import com.sdfteam.d3armory.service.remote.exception.D3ServerCommunicationException;

public class D3ArmoryControler {

	private static String CONF_FILE="conf/tags.d3armory";
	private static String LOCALE_FILE="conf/local.d3armory";
	
	private static D3ArmoryControler instance;
	public Configuration conf;
	private RemoteService<Profile> profileService ;
	private Profile profil ;
	private Hero selected;
	private StuffCalculator calculator;
	private String local;
	private Item selectedItem;
	
	public static D3ArmoryControler getInstance()
	{
		if(instance==null)
			instance = new D3ArmoryControler();
		
		return instance;
	}
	
	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	public Profile getProfil(String host, String tagName,Long tagID) throws D3ServerCommunicationException
	{
		  conf = new Configuration();
		  conf.setBattleTag(tagName);
		  conf.setBattleTagCode(tagID);
		  conf.setHost(host);
		  conf.setLocal(local);
		  if(local==null)
			  conf.setLocal("en_US");
		  profileService = new SpringRemoteService(Profile.class);
		  profil = profileService.receiveEntity(conf);
		  return profil;
	}
	
	public Profile getCurrentProfil()
	{
		return profil;
	}
	
	public Hero getHeroDetails(Long id) throws D3ServerCommunicationException
	{
		conf.setHeroId(id);
		RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
		selected = heroService.receiveEntity(conf);
		return selected;
	}
	
	public Hero getHeroDetails(Hero h) throws D3ServerCommunicationException
	{
		conf.setHeroId(h.getId());
		RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
		selected = heroService.receiveEntity(conf);
		return selected;
	}

	public void setSelectedHero(Hero h) {
			selected=h;
		
	}
	
	public Hero getSelectedHero(boolean reloading) throws D3ServerCommunicationException
	{
		if(reloading)
			selected = getHeroDetails(selected.getId().longValue());
		
		return selected;
	}
	
	public Item getItemDetails(Item i)
	{
		
		if(i==null)
			return null;
		
		RemoteService<Item> itemService = new SpringRemoteService(Item.class);
		conf.setItemId(i.getItemID());
		try {
			return itemService.receiveEntity(conf);
		} catch (D3ServerCommunicationException e) {
			return null;
		}
	}
	
	public List<String> getListTags()
	{
		//lecture du fichier texte	
		List<String> liste = new ArrayList<>();
				try{
					InputStream ips=new FileInputStream(CONF_FILE); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						liste.add(ligne);
					}
					br.close(); 
				}		
				catch (Exception e){
					e.printStackTrace();
				}
				return liste;
	}
	
	public void addTags(String code,String server)
	{
		try {
			FileWriter fw= new FileWriter(CONF_FILE,true);
			fw.write("\n"+code+"#"+server+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String loadLocal()
	{
		try {
			InputStream ips=new FileInputStream(LOCALE_FILE); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			local = br.readLine().trim();
			return local;
		} catch (IOException e) {
			return "en_US";
		}
	}




	
	
	public boolean initCalculator(List<Item> stuffs) {
		try {
			calculator = new StuffCalculator(stuffs, getSelectedHero(false));
			return true;
		} catch (D3ServerCommunicationException e) {
			e.printStackTrace();
			return false;
		}
		
	}




	
	public Item getSelectedItem() {
		return selectedItem;
	}

	public StuffCalculator getCalculator() {
		return calculator;
	}

	public void setSelectedItem(Item item) {
		this.selectedItem =item;
		
	}

	
	
}
