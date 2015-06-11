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
import org.armory.d3.services.impl.GSONRecorder;
import org.armory.d3.services.impl.SerializableRecorder;

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
	
	
	
	public static String SOURCE_REPOSITORY="https://github.com/nicho92/d3armory-ui/issues";
	public static String APP_VERSION="https://raw.githubusercontent.com/nicho92/d3armory-ui/master/src/version";
	public static String APP_DOWNLOAD="https://github.com/nicho92/d3armory-ui/tree/master/executable";
	public static String RSS="https://github.com/nicho92/d3armory-ui/commits/master.atom";
	
	
	private static D3ArmoryControler instance;
	public Configuration conf;
	private RemoteService<Profile> profileService ;
	private Profile profil ;
	private Hero selected;
	private StuffCalculator calculator;
	private String local;
	private Item selectedItem;
	
	static final Logger logger = LogManager.getLogger(D3ArmoryControler.class.getName());

	
	public D3ArmoryControler()
	{
		
	}
	
	
	public void initEnv()
	{
		 try{    
			 	logger.debug("Init environnement");
			    File repconf = new File(CONF_DIR);
			    logger.debug("Configuration directory " + repconf );
			    
			    if(!repconf.exists())
			    {
			    	logger.debug("Creating directory " + repconf );
			    	repconf.mkdir();
			    }
			    
		    	if(!new File(D3ObjectRecorder.SERIALISATION_BUILD_DIR).exists())
			    	new File(D3ObjectRecorder.SERIALISATION_BUILD_DIR).mkdir();

		    	if(!new File(D3ObjectRecorder.SERIALISATION_ITEM_DIR).exists())
		    	    new File(D3ObjectRecorder.SERIALISATION_ITEM_DIR).mkdir();
		    	
		    	if(!new File(D3ObjectRecorder.SERIALISATION_HERO_DIR).exists())	
		    		new File(D3ObjectRecorder.SERIALISATION_HERO_DIR).mkdir();
		    	
		    	if(!new File(CONF_FILE).exists())
		    	{
		    		new File(CONF_FILE).createNewFile();
		    		D3ArmoryControler.getInstance().setProperty("local","en_EN");
		    		D3ArmoryControler.getInstance().setProperty("recorder","org.armory.d3.services.impl.SerializableRecorder");
		    		D3ArmoryControler.getInstance().setProperty("cache","false");
		    	}
		    	
		    	if(!new File(D3ArmoryControler.TAG_FILE).exists())	
		    		new File(D3ArmoryControler.TAG_FILE).createNewFile();
			    }
			    catch(IOException e)
			    {
			    	logger.error(e.getStackTrace());
			    	JOptionPane.showMessageDialog(null, e);
			    }
	}
	
	
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
	
	public XP getEndSeasonParangonLevelHC(int i) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
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
	
	public XP getEndSeasonParangonLevelSC(int s) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
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
	
	public double getActualVersion()
	{
		try {
		InputStream in = getClass().getResourceAsStream("/version"); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		return Double.parseDouble(reader.readLine().trim());
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	
	public boolean hasUpdateVersionApp()
	{
		
		try{
		
			double actualversion = getActualVersion();
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
		  try
		  {
			  Profile profil = profileService.receiveEntity(conf);
			  return profil;
		  }
		  catch(Exception e)
		  {
			  JOptionPane.showMessageDialog(null, e);
			  return null;
		  }
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
		} catch (Exception e) {
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
						{	
							if(region==null)
							{
								liste.add(ligne);
							}
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
					logger.error(e.getStackTrace());
				}
				return liste;
	}
	
	public void addTags(String battletag)
	{
		try {
			FileWriter fw= new FileWriter(TAG_FILE,true);
			fw.write("\n"+battletag+"\n");
			logger.debug("Add Tag " + battletag);
			fw.close();
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}
		
	}
	
	public void addTags(String code,String server)
	{
			addTags("\n"+code+"#"+server+"\n");
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

	public String getProperty(String k,String defaut)
	{
		try {
			InputStream ips=new FileInputStream(CONF_FILE); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			Properties p = new Properties();
			p.load(ipsr);
			local = p.getProperty(k);
			logger.debug("Get "+k+" =" + local);
			return local;
		} catch (IOException e) {
			logger.error(e.getStackTrace());
			return defaut;
		}
	}

	public void setProperty(String key,String value)
	{
		try {
			PropertiesConfiguration prop = new PropertiesConfiguration();
			prop.setFile(new File(CONF_FILE));
			prop.load();
			prop.setProperty(key, value);
			prop.save();
			logger.debug("set "+key+"=" + value);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public D3ObjectRecorder getRecorder()
	{
		try {
			D3ObjectRecorder rec = (D3ObjectRecorder)Class.forName(getProperty("recorder", "org.armory.d3.services.impl.SerializableRecorder")).newInstance();
			logger.debug("loading recorder ="+ rec);
			return rec;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	private int season=-1;
	public int getSeason()
	{
		
		 try {
			 if(season==-1){
		    	  InputStreamReader fr = new InputStreamReader( new URL("https://raw.githubusercontent.com/nicho92/d3armory-ui/master/src/org/armory/d3/ui/resources/data/saison").openStream(),"ISO-8859-1");
		    	  BufferedReader br = new BufferedReader(fr);
		    	  String ligne= br.readLine();
		    	  logger.debug("Actual Season " + ligne);
		    	  season=Integer.parseInt(ligne.trim());
			 }
	    	 return season;

			 
	      } catch (Exception e) {
	    	
	    	 JOptionPane.showMessageDialog(null, e.getStackTrace(), "Erreur",JOptionPane.ERROR_MESSAGE);
	    	 logger.error(e.getStackTrace());
	    	 return 0;
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
	
	public void setProfile(Profile p) {
		profil=p;
		
	}

	public String refactorItem(String id) {
		return id.replaceAll("-","").replace(",", "").replace("'", "").replaceAll("\\.", "").replaceAll(" ", "-").trim().toLowerCase();
	}

	
	
	public void saveItem(Item item) throws Exception {
		getRecorder().saveItem(item);
	}


	public void saveBuild(HeroSkillContainer hsc) throws Exception {
		getRecorder().saveBuild(hsc);
	}

	public void saveHero(Hero hero) throws Exception {
		getRecorder().saveHero(hero);
	}


	public Item loadItem(File f) throws Exception {
			return getRecorder().loadItem(f);
	}


	public File[] getListeFileItem() {
		return getRecorder().getListeFileItem();
	}

	

	
	
}
