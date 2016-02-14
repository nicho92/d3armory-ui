package com.pihen.d3restapi.service.configuration;

import java.util.LinkedHashMap;

import com.pihen.d3restapi.service.util.StuffCalculator.KEY;

/**
 * Represents a d3 server configuration with character identification.
 * 
 * @author NoTiCe
 * 
 */
@SuppressWarnings("serial")
public class Configuration extends LinkedHashMap<String, Object> {
	public static final String BATTLETAG_CODE_TAG = "<battletag-code>";
	public static final String BATTLETAG_NAME_TAG = "<battletag-name>";
	public static final String HOST_TAG = "<host>";
	public static final String HERO_ID = "<hero-id>";
	public static final String LOCAL="<local>";
	public static final String ITEM_ID="<item-id>";
	public static final String KEYAPI="<apikey>";
	/**
	 * Clones the mappings of the underlying map. This is not a deep copy.
	 * 
	 * @param configuration
	 *            The source configuration to clone.
	 */
	public Configuration(Configuration configuration) {
		super(configuration);
	}

	public String getItemId() {
		return (String) get(ITEM_ID);
	}

	public void setItemId(String itemID) {
		put(ITEM_ID,itemID);
	}
	
	public void setApiKEY(String api)
	{
		put(KEYAPI,api);
	}
	
	public String getKeyAPI()
	{
		return (String)get(KEYAPI);
	}
	
	public String getZone()
	{
		if(get(HOST_TAG)!=null)
			return ((String)get(HOST_TAG)).substring(0, get(HOST_TAG).toString().indexOf("."));
		else
			return "";
	}
	
	/**
	 * Default constructor, creates an empty configuration.
	 */
	public Configuration() {
	}

	public String getHost() {
		return (String) get(HOST_TAG);
	}

	public void setHost(String host) {
		put(HOST_TAG, host);
	}

	public String getBattleTag() {
		return (String) get(BATTLETAG_NAME_TAG);
	}

	public void setBattleTag(String battleTag) {
		put(BATTLETAG_NAME_TAG, battleTag);
	}

	public Long getBattleTagCode() {
		return (Long) get(BATTLETAG_CODE_TAG);
	}

	public void setBattleTagCode(Long battleTagCode) {
		put(BATTLETAG_CODE_TAG, battleTagCode);
	}

	public void setHeroId(Number id) {
		put(HERO_ID, id);
	}

	public Long getHeroId() {
		return (Long) get(HERO_ID);
	}

	public void setLocal(String local) {
		put(LOCAL, local);
	}
	
	public String getLocal() {
		return (String) get(LOCAL);
	}

	/**
	 * Clones the configuration using the Configuration(Configuration source)
	 * constructor.
	 */
	public Configuration clone() {
		return new Configuration(this);
	}
	
	public String toString()
	{
		return "http://"+getHost()+"/d3/profile/"+getBattleTag()+"-"+getBattleTagCode()+"/?locale="+getLocal()+"&apikey="+getKeyAPI();
	}
}
