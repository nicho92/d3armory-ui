package com.pihen.d3restapi.service.util;

public class LegendaryGemEvolutionChance {

	public static int getChance(int lvlRift, int lvlgem)
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
}
