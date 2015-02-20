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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.HeroSkillContainer;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.beans.XP;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.StuffCalculator;

public class D3ArmoryControler {

	public static String CONF_DIR=System.getProperty("user.home")+"/d3conf/";
	public static String TAG_FILE=CONF_DIR+"tags.d3armory";
	public static String CONF_FILE=CONF_DIR+"local.d3armory";
	public static String SERIALISATION_DIR=CONF_DIR+"/items";
	public static String SERIALISATION_HERO_DIR=CONF_DIR+"/heroes";
	public static String SERIALISATION_BUILD_DIR=CONF_DIR+"/builds";
	
	
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

	public void updateStatUI()
	{
		//todo global update ui component with new calcul
	}
	
	public Profile getProfil(String profile) throws D3ServerCommunicationException
	{
		String[] val = profile.split("#");
		return getProfil(val[2], val[0], Long.parseLong(val[1]));
	}
	
	
	public Configuration getConfiguration(String host, String tagName,Long tagID)
	{
		Configuration conf = new Configuration();
		  conf.setBattleTag(tagName);
		  conf.setBattleTagCode(tagID);
		  conf.setHost(host);
		  conf.setLocal(local);
		  if(local==null)
			  conf.setLocal("en_US");
		  
		  return conf;
	}
	
	public XP getEndSeasonParangonLevelHC(int i) {
		XPCalculator xpc = new XPCalculator();
        
        long newXP =0;
        
        if(profil.getSeasonalProfiles().getSeason2()!=null)
        {
        	long xp0 = xpc.getXPByLevel(profil.getParagonLevelHardcore().intValue()).getTotalExp();
        	long xp1 = 0;
      
        	if(profil.getSeasonalProfiles().getSeason2().getParagonLevelHardcore()!=null)
        		xp1=xpc.getXPByLevel(profil.getSeasonalProfiles().getSeason2().getParagonLevelHardcore().intValue()).getTotalExp();
        	
        	newXP = xp0 + xp1;
        	return xpc.getXPByTotalXP(newXP);
        }
        
        return new XP(profil.getParagonLevelHardcore().intValue(), 0, 0);
	}
	
	public XP getEndSeasonParangonLevelSC(int season)
	{
		XPCalculator xpc = new XPCalculator();
         
        long newXP =0;
        
        if(profil.getSeasonalProfiles().getSeason2()!=null)
        {
        	long xp0 = xpc.getXPByLevel(profil.getParagonLevel().intValue()).getTotalExp();
        	long xp1 = 0;
      
        	if(profil.getSeasonalProfiles().getSeason2().getParagonLevel()!=null)
        		xp1=xpc.getXPByLevel(profil.getSeasonalProfiles().getSeason2().getParagonLevel().intValue()).getTotalExp();
        	
        	newXP = xp0 + xp1;
        	return xpc.getXPByTotalXP(newXP);
        }
        
        return new XP(profil.getParagonLevel().intValue(), 0, 0);
         	
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
		  Profile profil = profileService.receiveEntity(conf);
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
		return getHeroDetails(h.getId().longValue());
	}

	public void setSelectedHero(Hero h) {
			selected=h;
		
	}
	
	public Hero getSelectedHero(boolean reloading) 
	{
		if(reloading)
			try {
				selected = getHeroDetails(selected.getId().longValue());
			} catch (D3ServerCommunicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
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
			JOptionPane.showMessageDialog(null, e,"ERROR",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	
	public Map<EnumerationStuff,Item> initStuffHero(Hero hero)
	{
		Item head = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getHead());
		hero.getItems().setHead(head);
		
		Item foot = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getFeet());
		hero.getItems().setFeet(foot);
		
		Item shoulders = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getShoulders());
		hero.getItems().setShoulders(shoulders);
			
		Item gants = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getHands());
		hero.getItems().setHands(gants);
		
		Item bracers = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getBracers());
		hero.getItems().setBracers(bracers);
		
		Item legs = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getLegs());
		hero.getItems().setLegs(legs);
		
		Item neck = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getNeck());
		hero.getItems().setNeck(neck);
		
		Item belt = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getWaist());
		hero.getItems().setWaist(belt);
		
		Item ringright = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getRightFinger());
		hero.getItems().setRightFinger(ringright);
		
		Item ringleft = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getLeftFinger());
		hero.getItems().setLeftFinger(ringleft);
		
		Item mainHand = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getMainHand());
		hero.getItems().setMainHand(mainHand);
		
		Item offhand = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getOffHand());
		hero.getItems().setOffHand(offhand);
		
		Item torso = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getTorso());
		hero.getItems().setTorso(torso);
		
		
		Map<EnumerationStuff,Item>stuffs = new HashMap<EnumerationStuff, Item>();
								  stuffs.put(EnumerationStuff.HEAD, head);
								  stuffs.put(EnumerationStuff.SHOULDERS, shoulders);
								  stuffs.put(EnumerationStuff.NECK, neck);
								  stuffs.put(EnumerationStuff.TORSO, torso);
								  stuffs.put(EnumerationStuff.GANT, gants);
								  stuffs.put(EnumerationStuff.BRACER, bracers);
								  stuffs.put(EnumerationStuff.BELT, belt);
								  stuffs.put(EnumerationStuff.LEGS, legs);
								  stuffs.put(EnumerationStuff.RING_RIGHT, ringright);
								  stuffs.put(EnumerationStuff.RING_LEFT, ringleft);
								  stuffs.put(EnumerationStuff.MAIN_HAND, mainHand);
								  stuffs.put(EnumerationStuff.OFF_HAND, offhand);
								  stuffs.put(EnumerationStuff.FEET, foot);
								  
		return stuffs;
	}
	
	public List<String> getListTags()
	{
		return getListTags(null);
	}
	
	
	public List<String> getListTags(String region)
	{
		//lecture du fichier texte	
		List<String> liste = new ArrayList<String>();
				try{
					InputStream ips=new FileInputStream(TAG_FILE); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						if(ligne!=null)
						{	if(region==null)
								liste.add(ligne);
							else
							{
								if(ligne.endsWith(region))
									liste.add(ligne);
							}
						}
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
			InputStream ips=new FileInputStream(CONF_FILE); 
			
			InputStreamReader ipsr=new InputStreamReader(ips);
			Properties p = new Properties();
			p.load(ipsr);
			local = p.getProperty("local");
			return local;
		} catch (IOException e) {
			return "en_US";
		}
	}

	public void setLocal(String local)
	{
		try {
			PropertiesConfiguration prop = new PropertiesConfiguration();
			prop.setFile(new File(CONF_FILE));
			prop.load();
			prop.setProperty("local", local);
			prop.save();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setSeason(String s)
	{
		try {
			PropertiesConfiguration prop = new PropertiesConfiguration();
			prop.setFile(new File(CONF_FILE));
			prop.load();
			prop.setProperty("season", s);
			prop.save();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getSeason()
	{
		try {
			InputStream ips=new FileInputStream(CONF_FILE); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			Properties p = new Properties();
			p.load(ipsr);
			String season = p.getProperty("season");
			if(season==null)
				return "0";
			
			return season;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
			return "0";
		}
	}
	
	public boolean initCalculator(Map<EnumerationStuff, Item> stuff) {
	
			calculator = new StuffCalculator(stuff, getSelectedHero(false));
			return true;
		 
		
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
	
	public void saveHero(Hero i)
	{
		try{
			FileOutputStream fos = new FileOutputStream(SERIALISATION_HERO_DIR +"/"+i.getName()+".d3hero");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(i);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	public Hero loadHero(File f)
	{
		try{
			FileInputStream fos = new FileInputStream(SERIALISATION_HERO_DIR +"/"+f.getName()+".d3hero");
			ObjectInputStream ois  = new ObjectInputStream(fos);
			return (Hero)ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveItem(Item i)
	{
		try{
			FileOutputStream fos = new FileOutputStream(SERIALISATION_DIR +"/"+i.getName()+".d3item");
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

	public void setProfile(Profile p) {
		profil=p;
		
	}

	public void saveBuild(HeroSkillContainer i)
	{
		try{
			FileOutputStream fos = new FileOutputStream(SERIALISATION_BUILD_DIR +"/"+i.getNameBuild()+".d3build");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(i);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	public HeroSkillContainer loadBuild(File f)
	{
		try{
			FileInputStream fos = new FileInputStream(SERIALISATION_BUILD_DIR +"/"+f.getName()+".d3build");
			ObjectInputStream ois  = new ObjectInputStream(fos);
			return (HeroSkillContainer)ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<HeroSkillContainer> loadBuilds()
	{
		File f = new File(SERIALISATION_BUILD_DIR);
		List<HeroSkillContainer> skills = new ArrayList<HeroSkillContainer>();
		
		for (File fb : f.listFiles())
			skills.add(loadBuild(new File(fb.getAbsolutePath().substring(0, fb.getAbsolutePath().indexOf(".d3build")))));
		
			return skills;
	}

	

	
	
}
