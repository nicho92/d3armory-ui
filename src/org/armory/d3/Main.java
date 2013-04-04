package org.armory.d3;

import java.util.ArrayList;
import java.util.List;

import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.Profile;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.StuffCalculator;

import com.sdfteam.d3armory.service.configuration.Configuration;
import com.sdfteam.d3armory.service.remote.RemoteService;
import com.sdfteam.d3armory.service.remote.SpringRemoteService;


public class Main {

	
	public static void main(String[] args) throws Exception  {
		Configuration conf = new Configuration();
					  conf.setBattleTag("nicho92");
					  conf.setBattleTagCode(new Long(2603));
					  conf.setHost("eu.battle.net");
					  conf.setLocal("fr_FR");
					  
		RemoteService<Profile> profileService = new SpringRemoteService(Profile.class);
		RemoteService<Hero> heroService = new SpringRemoteService(Hero.class);
		RemoteService<Item> itemService = new SpringRemoteService(Item.class);
		
		Profile profile = profileService.receiveEntity(conf);
		
			
			Hero hero = profile.getHeroes().get(0);
				 conf.setHeroId(hero.getId());
				 D3ArmoryControler.getInstance().getInstance().setConf(conf);
				 
				 hero = heroService.receiveEntity(conf);
				 System.out.println(hero.getName() + " "+ hero.getClazz() + " pg:"+ hero.getParagonLevel());
				 System.out.println("poison:" + hero.getStats().getPoisonResist() + " fire:" + hero.getStats().getFireResist()+ " Arcane:" + hero.getStats().getArcaneResist() +" Cold:" + hero.getStats().getColdResist() + " physique:" + hero.getStats().getPhysicalResist());
				 
				 System.out.println("============================================================================================");
					Item head = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getHead());
					Item foot = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getFeet());
					Item shoulders = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getShoulders());
					Item gants = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getHands());
					Item bracers = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getBracers());
					Item legs = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getLegs());
					Item neck = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getNeck());
					Item belt = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getWaist());
					Item ringright = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getLeftFinger());
					Item ringleft = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getRightFinger());
					Item mainHand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getMainHand());
					if(mainHand!=null)
					mainHand.setMainHand(true);
					Item offhand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getOffHand());
					Item torso = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getTorso());
					
					List<Item> stuffs= new ArrayList<Item>();
						stuffs.add(head);
						stuffs.add(shoulders);
						stuffs.add(neck);
						stuffs.add(torso);
						stuffs.add(gants);
						stuffs.add(bracers);
						stuffs.add(belt);
						stuffs.add(legs);
						stuffs.add(ringright);
						stuffs.add(ringleft);
						stuffs.add(mainHand);
						stuffs.add(offhand);
						stuffs.add(foot);
						
					StuffCalculator calculator = new StuffCalculator(stuffs,hero);
								System.out.println(calculator.calculateUnbuffedDPS());
						
					
					
						
				 		
		}
}
