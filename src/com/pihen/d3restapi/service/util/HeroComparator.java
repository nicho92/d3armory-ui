package com.pihen.d3restapi.service.util;

import java.util.Map;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;

public class HeroComparator {

		private Hero hero1;
		private Hero hero2;
		
		private StuffCalculator calculator1;
		private StuffCalculator calculator2;
		
		public void recalculate()
		{
			calculator1.calculate();
			calculator2.calculate();
		}
		
		
		public void initComparatedHero(D3ArmoryControler arm,Hero two) throws D3ServerCommunicationException
		{
			
			hero1 = D3ArmoryControler.getInstance().getHeroDetails(D3ArmoryControler.getInstance().getSelectedHero(false));
			hero2 = arm.getHeroDetails(two);
			
			Map<EnumerationStuff,Item> map1=D3ArmoryControler.getInstance().initStuffHero(hero1);
			Map<EnumerationStuff,Item> map2=D3ArmoryControler.getInstance().initStuffHero(hero2);
			
			calculator1 = new StuffCalculator(map1, hero1);
			calculator2 = new StuffCalculator(map2, hero2);
		}
		
		public Hero getHero1() {
			return hero1;
		}



		public void setHero1(Hero hero1) {
			this.hero1 = hero1;
		}



		public Hero getHero2() {
			return hero2;
		}



		public void setHero2(Hero hero2) {
			this.hero2 = hero2;
		}



		public StuffCalculator getCalculator1() {
			return calculator1;
		}



		public void setCalculator1(StuffCalculator calculator1) {
			this.calculator1 = calculator1;
		}



		public StuffCalculator getCalculator2() {
			return calculator2;
		}



		public void setCalculator2(StuffCalculator calculator2) {
			this.calculator2 = calculator2;
		}


		public void initComparator(Configuration conf, Configuration conf2) {
			// TODO Auto-generated method stub
			
		}




	
	
}
