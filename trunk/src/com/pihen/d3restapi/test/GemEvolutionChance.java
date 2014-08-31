package com.pihen.d3restapi.test;

public class GemEvolutionChance {

		private int[][] tableau;
		
		private int lvlMax=50;
		private int lvlRift=50;
		
		
		public GemEvolutionChance()
		{
			tableau = new int[lvlMax][lvlRift];
			
			for(int i=0;i<lvlMax;i++)
			{	for(int j=1;j<lvlRift;j++)
				{
				tableau[j][i]=(getChance(j, i));
				}

			
			}
		}
	
		
		private int getChance(int lvlRift, int lvlgem)
		{
			int result = lvlRift - lvlgem;
			if (result >= 10) result = 100;
			else if (result >=  9) result =  90;
			else if (result >=  8) result =  80;
			else if (result >=  7) result =  70;
			else if (result >=  0) result =  60;
			else if (result >= -1) result =  30;
			else if (result >= -2) result =  15;
			else if (result >= -3) result =   8;
			else if (result >= -4) result =   4;
			else if (result >= -5) result =   2;
			else if (result >= -6) result =   1;
			else result = 0;
			return result;
		}
		
		
		public static void main(String[] args) {
			new GemEvolutionChance();
		}
	
}
