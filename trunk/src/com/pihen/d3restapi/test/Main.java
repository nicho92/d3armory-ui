package com.pihen.d3restapi.test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Profile;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.factory.LootFactory;
import com.pihen.d3restapi.service.remote.RemoteService;
import com.pihen.d3restapi.service.remote.SpringRemoteService;
import com.pihen.d3restapi.service.util.StuffCalculator;


public class Main {

	public static void main(String[] args) throws Exception {
	
	Configuration conf = new Configuration();
		  conf.setBattleTag("nicho92");
		  conf.setBattleTagCode(new Long(2603));
		  conf.setHost("eu.battle.net");
		  conf.setLocal("fr_FR");
		  
		  RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
          RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
          Profile profile = profileService.receiveEntity(conf);
          
          D3ArmoryControler.getInstance().setConf(conf);
//          for(Hero h : profile.getHeroes())
//          {
//        	  conf.setHeroId(h.getId());
//        	  h = heroService.receiveEntity(conf);
//        	  
//        	 for(Item i : h.getItems().getItems())
//        	 {
//        		 if(i!=null)
//        		 {
//        			 i=D3ArmoryControler.getInstance().getItemDetails(i);
//	        		 for(String k: i.getAttributesRaw().keySet())
//	        		 {
//	        			 if(k.startsWith("Item_Power_Passive"))
//	        			 {
//	        				 System.out.println(i.getName() + " " +k);
//	        			 }
//	        		 }
//        		 }
//        	 }
//          }
          
          Hero h = profile.getHeroes().get(2);
               conf.setHeroId(h.getId());
               h=heroService.receiveEntity(conf);
               
            final   LootFactory fact = new LootFactory(h);
         
               JFrame f = new JFrame("Kadala");
               f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               f.setSize(100,50);
               
               for(String s : fact.getItemsType())
               {
            	    JButton b = new JButton(s);
                   
                    b.addActionListener(new ActionListener() {
     				
     				public void actionPerformed(ActionEvent e) {
     					 Item i = fact.generateItem(((JButton)e.getSource()).getText());	
     	            	 System.out.println(i.getName());
     					
     				}
     			});
                    f.setSize(800, 600);
                    f.getContentPane().setLayout(new GridLayout(10, 5));
                    f.getContentPane().add(b);
               }
               
           
               f.setVisible(true);
               
//               StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().initStuffHero(h),h);
//               calc.calculate();
//               System.out.println(calc.getStatAttributs().get(StuffCalculator.KEY.DOT_DAMAGE));
               
              
               
               
               
               
               
          
          
          

	
	}
	
	
}

