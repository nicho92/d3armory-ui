package org.armory.d3.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.Profile;

import com.sdfteam.d3armory.service.configuration.Configuration;
import com.sdfteam.d3armory.service.remote.RemoteService;
import com.sdfteam.d3armory.service.remote.SpringRemoteService;
import com.sdfteam.d3armory.service.remote.exception.D3ServerCommunicationException;

public class D3ArmoryControler {

	private static D3ArmoryControler instance;
	public Configuration conf;
	RemoteService<Profile> profileService ;
	Profile profil ;
	Hero selected;
	
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




	public Profile getProfil(String host, String tagName,Long tagID,String local) throws D3ServerCommunicationException
	{
		  conf = new Configuration();
		  conf.setBattleTag(tagName);
		  conf.setBattleTagCode(tagID);
		  conf.setHost(host);
		  conf.setLocal(local);
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
					InputStream ips=new FileInputStream("conf/tags.d3armory"); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						System.out.println(ligne);
						liste.add(ligne);
					}
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
				return liste;
	}
	
	public void addTags(String code,String server)
	{
		
	}
	
	
}
