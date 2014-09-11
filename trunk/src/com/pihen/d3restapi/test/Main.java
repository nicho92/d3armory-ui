package com.pihen.d3restapi.test;

import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.util.HeroComparator;


public class Main {

	
	public static void main(String[] args) throws Exception {
	
	Configuration conf = new Configuration();
		  conf.setBattleTag("nicho92");
		  conf.setBattleTagCode(new Long(2603));
		  conf.setHost("eu.battle.net");
		  conf.setLocal("fr_FR");

	Configuration conf2 = new Configuration();
			conf2.setBattleTag("test");
			conf2.setBattleTagCode(new Long(1234));
			conf2.setHost("eu.battle.net");
			conf2.setLocal("fr_FR");
	
	HeroComparator comp = new HeroComparator();
	
			comp.initComparator(conf, conf2);
	
	
	}
	
	
}

