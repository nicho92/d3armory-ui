package org.armory.d3;

import java.util.List;

import org.armory.d3.beans.Gem;
import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.LegendarySetItem;
import org.armory.d3.beans.Profile;
import org.armory.d3.beans.SkillRune;

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
				 hero = heroService.receiveEntity(conf);
				 System.out.println(hero.getName() + " "+ hero.getClazz() + " pg:"+ hero.getParagonLevel());
				 System.out.println("f:" + hero.getStats().getStrength() + " v:" + hero.getStats().getVitality() + " d:" + hero.getStats().getDexterity() +" i:" + hero.getStats().getIntelligence());
				 System.out.println("DPS:" + hero.getStats().getDamage());
				 System.out.println("SKILL PASSIF: ");
				 List<SkillRune> actives = hero.getSkills().getActive();
				 for(SkillRune sr : actives)
				 {
					 System.out.println(sr.getSkill().getName() +" " + sr.getRune().getName());
				 }
				 
				 
				 
				 System.out.println("============================================================================================");
				 		conf.setItemId(hero.getItems().getMainHand().getItemID());
				 		
				 		Item item = itemService.receiveEntity(conf);
				 		System.out.println(item.getName() + " ( "+ item.getTypeName() + ")");
				 		System.out.println("Niveau nécessaire " + item.getRequiredLevel());
				 		System.out.println("Niveau objet " + item.getItemLevel());
				 		System.out.println("BonusAffixe " + item.getBonusAffixes());
				 		
				 		System.out.println("Echanté " + item.getEnchantedWeapon());
				 		
				 		
				 		if(item.isArmor())
				 			System.out.println("Armor " + item.getArmor());
				 		
				 		if(item.isWeapon()){
				 			System.out.println("DPS " + item.getDps());
				 			System.out.println("AttakSpeed " + item.getAttacksPerSecond());
				 			System.out.println("Min/Max damage " + item.getMinDamage().getMoyenne() + " " + item.getMaxDamage().getMoyenne());
				 		}
				 		for(int i=0;i<item.getAttributes().length;i++)
	 					{
	 						System.out.println(item.getAttributes()[i]);
	 					}
				 		
				 		System.out.println("Socket " + item.nbSockets());
				 		
				 		if(item.nbGems()>0)
				 		{
				 			for(int i=0;i<item.getGems().length;i++)
		 					{
		 						Gem gem = item.getGems()[i];
				 				System.out.print(gem.getItem().getName() + " ");
				 				for(int j=0;j<gem.getAttributes().length;j++)
			 					{
			 						System.out.println(gem.getAttributes()[j]);
			 					}
		 					}
				 		}
				 		
				 		if(item.isSetObjects()){
				 			System.out.println("====== "+item.getSet().getName()+" ====== ");
				 			
				 			for(Item i : item.getSet().getItems())
				 				System.out.println(i.getName());
				 			
				 			for(LegendarySetItem li : item.getSet().getRanks()){
				 					System.out.println(li.getRequire() +" =============");
				 					for(int i=0;i<li.getAttributes().length;i++)
				 					{
				 						System.out.println(li.getAttributes()[i]);
				 					}
				 			}
				 		}
				 		
				 		
		}
}
