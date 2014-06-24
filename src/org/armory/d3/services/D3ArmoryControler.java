package org.armory.d3.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;
import com.pihen.d3restapi.service.util.EnumerationStuff;

public class D3ArmoryControler {

	private static String TAG_FILE="conf/tags.d3armory";
	private static String LOCALE_FILE="conf/local.d3armory";
	private static String SERIALISATION_DIR="conf/items";
	
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

	
	
	public Profile getProfil(String profile) throws D3ServerCommunicationException
	{
		String[] val = profile.split("#");
		return getProfil(val[2], val[0], Long.parseLong(val[1]));
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
		List<String> liste = new ArrayList<String>();
				try{
					InputStream ips=new FileInputStream(TAG_FILE); 
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
			FileWriter fw= new FileWriter(TAG_FILE,true);
			fw.write("\n"+code+"#"+server+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeTags(int pos)
	{
		try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(TAG_FILE)));
 
            StringBuffer sb = new StringBuffer(); 
            String line;    
            int nbLinesRead = 0;       
            while ((line = reader.readLine()) != null) {
                if (nbLinesRead != pos) {
                    sb.append(line + "\n");
                }
                nbLinesRead++;
            }
            reader.close();
            BufferedWriter out = new BufferedWriter(new FileWriter(TAG_FILE));
            out.write(sb.toString());
            out.close();
 
        } catch (Exception e) {
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

	public void setLocal(String local)
	{
		try {
			FileWriter fw= new FileWriter(LOCALE_FILE);
			fw.write(local);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean initCalculator(Map<EnumerationStuff, Item> stuff) {
		try {
			calculator = new StuffCalculator(stuff, getSelectedHero(false));
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
	
	public void saveItem(Item i)
	{
		try{
			FileOutputStream fos = new FileOutputStream(SERIALISATION_DIR +"/"+i.getName()+".d3armory");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(i);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	public Item loadItem(File f)
	{
		try{
			FileInputStream fos = new FileInputStream(SERIALISATION_DIR +"/"+f.getName());
			ObjectInputStream ois  = new ObjectInputStream(fos);
			return (Item)ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public File[] getListeFileItem()
	{
		File f = new File(SERIALISATION_DIR);
		return f.listFiles();
	}
	
	
	
	
	
}
