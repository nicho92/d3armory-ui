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
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
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
	public static String SOURCE_REPOSITORY="https://github.com/nicho92/d3armory-ui/issues";
	public static String APP_VERSION="https://raw.githubusercontent.com/nicho92/d3armory-ui/master/src/version";
	public static String APP_DOWNLOAD="https://github.com/nicho92/d3armory-ui/tree/master/executable";
	
	
	
	private static D3ArmoryControler instance;
	public Configuration conf;
	private RemoteService<Profile> profileService ;
	private Profile profil ;
	private Hero selected;
	private StuffCalculator calculator;
	private String local;
	private Item selectedItem;
	
	
	static final Logger logger = LogManager.getLogger(D3ArmoryControler.class.getName());

	

	public static D3ArmoryControler getInstance()
	{
		
		if(instance==null)
		{
			logger.debug("Initialisation Singleton Controler");
			instance = new D3ArmoryControler();
		}
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
	
	public XP getEndSeasonParangonLevelHC(String i) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		XPCalculator xpc = new XPCalculator();
        
        long newXP =0;
        
        if(profil.getSeasonalProfiles().getSeason(i)!=null)
        {
        	long xp0 = xpc.getXPByLevel(profil.getParagonLevelHardcore().intValue()).getTotalExp();
        	long xp1 = 0;
      
        	
        	if(profil.getSeasonalProfiles().getSeason(i).getParagonLevelHardcore()!=null)
        		xp1=xpc.getXPByLevel(profil.getSeasonalProfiles().getSeason(i).getParagonLevelHardcore().intValue()).getTotalExp();
        	
        	newXP = xp0 + xp1;
        	return xpc.getXPByTotalXP(newXP);
        }
        
        return new XP(profil.getParagonLevelHardcore().intValue(), 0, 0);
	}
	
	public XP getEndSeasonParangonLevelSC(String s) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		XPCalculator xpc = new XPCalculator();
        logger.debug("Calcul end season " + s + " parangon");
        long newXP =0;
        if(profil.getSeasonalProfiles().getSeason(s)!=null)
        {
        	long xp0 = xpc.getXPByLevel(profil.getParagonLevel().intValue()).getTotalExp();
        	long xp1 = 0;
        	
        	logger.debug("XP season0 = " + xp0);
        	
        	if(profil.getSeasonalProfiles().getSeason(s).getParagonLevel()!=null)
        		xp1=xpc.getXPByLevel(profil.getSeasonalProfiles().getSeason(s).getParagonLevel().intValue()).getTotalExp();
        	
        	logger.debug("XP season = "+s + xp1);
        	
        	newXP = xp0 + xp1;
        	return xpc.getXPByTotalXP(newXP);
        }
        
        return new XP(profil.getParagonLevel().intValue(), 0, 0);
         	
	}
	
	public boolean hasUpdateVersionApp()
	{
		try {
		
		InputStream in = getClass().getResourceAsStream("/version"); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			double actualversion = Double.parseDouble(reader.readLine().trim()); 
			logger.debug("Actual Version " + actualversion);
		 
	    	  InputStreamReader fr = new InputStreamReader( new URL(APP_VERSION).openStream(),"ISO-8859-1");
	    	  BufferedReader br = new BufferedReader(fr);
	    	 String version =  br.readLine();
	    	 logger.debug("Last commited Version " + version.trim());
	    	 
	    	 return actualversion<Double.parseDouble(version.trim());
		 }
		 catch(Exception e)
		 {
			 logger.error(e.getStackTrace());
			 return false;
		 }
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
				logger.error(e.getStackTrace());
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
			logger.error(e.getStackTrace());
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
					logger.debug("Tags "+region+ " :" + liste.size() +" tags");
					br.close(); 
				}		
				catch (Exception e){
					logger.error(e.getStackTrace());
				}
				return liste;
	}
	
	public void addTags(String code,String server)
	{
		try {
			FileWriter fw= new FileWriter(TAG_FILE,true);
			fw.write("\n"+code+"#"+server+"\n");
			logger.debug("Add Tag " + code+"#"+server);
			
			fw.close();
		} catch (IOException e) {
			logger.error(e.getStackTrace());
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
        	logger.error(e.getStackTrace());
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
			logger.debug("Get local =" + local);
			return local;
		} catch (IOException e) {
			logger.error(e.getStackTrace());
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
			logger.debug("Set local =" + local);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getSeason()
	{
		 try {
	    	  InputStreamReader fr = new InputStreamReader( new URL("https://raw.githubusercontent.com/nicho92/d3armory-ui/master/src/org/armory/d3/ui/resources/data/saison").openStream(),"ISO-8859-1");
	    	  BufferedReader br = new BufferedReader(fr);
	    	  String ligne= br.readLine();
	    	  logger.debug("Actual Season " + ligne);
	    	  return ligne.trim();
	      } catch (Exception e) {
	    	
	    	 JOptionPane.showMessageDialog(null, e.getStackTrace(), "Erreur",JOptionPane.ERROR_MESSAGE);
	    	 logger.error(e.getStackTrace());
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
			FileOutputStream fos = new FileOutputStream(SERIALISATION_HERO_DIR +"/"+i.getId()+".d3hero");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(i);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			logger.error(e.getStackTrace());
		}
	}
	
	public Hero loadHero(Number number)
	{
		try{
			FileInputStream fos = new FileInputStream(SERIALISATION_HERO_DIR +"/"+number+".d3hero");
			ObjectInputStream ois  = new ObjectInputStream(fos);
			return (Hero)ois.readObject();
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
			return null;
		}
	}
	
	
	
	
//	public void saveHero(Hero i)
//	{
//		try{
//			File f = new File(SERIALISATION_HERO_DIR +"/"+i.getId()+".d3hero");
//			logger.debug("save hero =" + SERIALISATION_HERO_DIR +"/"+i.getId()+".d3hero -> " + i  );
//			Gson GSON = new Gson();
//			FileWriter fw = new FileWriter(f.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(GSON.toJson(i));
//			bw.close();
//			
//		}
//		catch (java.io.IOException e) {
//			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
//			logger.error(e.getStackTrace());
//		}
//	}
//	
//	public Hero loadHero(Number number)
//	{
//		try{
//			
//			File f = new File(SERIALISATION_HERO_DIR +"/"+number+".d3hero");
//			InputStream fos = new FileInputStream(f);
//			String sHero = new BufferedReader(new InputStreamReader(fos)).readLine();
//			Gson GSON = new Gson();
//			Hero h = GSON.fromJson(sHero, Hero.class);
//			logger.debug("load hero =" + SERIALISATION_HERO_DIR +"/"+number+".d3hero -> " + h );
//			return h;
//		}
//		catch (Exception e) {
//			//JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
//			logger.error(e.getStackTrace());
//			e.printStackTrace();
//			return null;
//		}
//	}
	
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
			logger.error(e.getStackTrace());
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
			logger.error(e.getStackTrace());
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
			logger.error(e.getStackTrace());
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
			logger.error(e.getStackTrace());
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

	public void setLook(String look) {
		try {
			PropertiesConfiguration prop = new PropertiesConfiguration();
			prop.setFile(new File(CONF_FILE));
			prop.load();
			prop.setProperty("look", look);
			prop.save();
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public String getLook() {
		try {
			InputStream ips=new FileInputStream(CONF_FILE); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			Properties p = new Properties();
			p.load(ipsr);
			return p.getProperty("look");
		} catch (IOException e) {
			logger.error(e.getStackTrace());
			return null;
		}
	}

	

	
	
}
