package com.pihen.d3restapi.test;

import java.util.List;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Tag;


public class Main {

	public static void main(String[] args) throws Exception {
 
	
			
			new Thread(new Runnable() {
				
				public void run() {
					List<Tag> tags = D3ArmoryControler.getInstance().getListTags();
					
					
					for(Tag tag : tags)
					{
						System.out.println(tag);
					}
					
				}
			}).start();
			/*
			SwingUtilities.invokeLater(new Runnable() {
			      public void run() {

			    	  try{
			    	  
			    	  Configuration conf = new Configuration();
							  		  conf.setBattleTag("nicho92");
							  		  conf.setBattleTagCode(new Long(2603));
							  		  conf.setHost("eu.battle.net");
							  		  conf.setLocal("fr_FR");
			  		
			  		 RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
			  		 RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
			  			
			  		 Profile profile = profileService.receiveEntity(conf);
			  	
			  		
			  			
						Hero hero = profile.getHeroes().get(2);
						conf.setHeroId(hero.getId());	
						D3ArmoryControler.getInstance().setConf(conf);
						hero = heroService.receiveEntity(conf);
						D3ArmoryControler.getInstance().setSelectedHero(hero);
						D3ArmoryControler.getInstance().initStuffHero(hero);
						D3ArmoryControler.getInstance().initCalculator(hero.getItems().getItemsMap());
						
						Item i = hero.getItems().get(EnumerationStuff.TORSO);
		
			    	  }
			    	  catch(Exception e)
			    	  {
			    		  e.printStackTrace();
			    	  }
			      }
			});
			
			
			
//			LootFactory fact = new LootFactory(hero);
//			Item i = fact.getItemById("sunwukos crown");
			
			
			
			
			
			
			
			
//			D3ArmoryControler.getInstance().saveItem(i);
			
//			JFrame f = new JFrame();
//			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			f.setSize(400, 300);
//			LootFactoryPanel panel = new LootFactoryPanel();
//			ItemPanelDetails detail = new ItemPanelDetails();
//			detail.setSize(150, 300);
//			JPanel pane = new JPanel();
//			pane.setLayout(new BorderLayout());
//			pane.add(panel, BorderLayout.CENTER);
//			pane.add(detail,BorderLayout.EAST);
//			
//			panel.init(detail);
//			
//			
//			f.getContentPane().add(pane);
//			
//			
//			f.setVisible(true);
			
/*TEST CACHED HERO FILES		
		for(File f : new File(D3ArmoryControler.SERIALISATION_HERO_DIR).listFiles())
		{
			Long number = Long.parseLong(f.getName().substring(0, f.getName().lastIndexOf(".")));
			Hero h = D3ArmoryControler.getInstance().loadHero(number);
			System.out.println("******"+h );
			for(Item i : h.getItems().getItems())
			{
				if(i!=null)
				System.out.println(i.getRandomAffixes());
			}
*/		
		
//		RSSReader read = new RSSReader();
//		System.out.println(read.getRss("http://www.diablofans.com/news.rss"));
//		System.out.println(read.getRss("http://eu.battle.net/d3/fr/feed/news"));
		
		
		
		String pass = "Augmente les dégâts infligés aux élites de 40–50%";
	
		
	}
	
	
}

