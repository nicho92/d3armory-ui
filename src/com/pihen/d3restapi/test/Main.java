package com.pihen.d3restapi.test;



public class Main {

	public static void main(String[] args) throws Exception {
 
		/*	new Thread(new Runnable() {
				
				public void run() {
					
				List<Tag> tags = D3ArmoryControler.getInstance().getListTags();
					
					
					for(Tag tag : tags)
					{
						try {
							Profile profile = D3ArmoryControler.getInstance().getProfil(tag);
							List<Hero> heroes = profile.getHeroes();
							
							for(Hero h: heroes)
							{
								h = D3ArmoryControler.getInstance().getHeroDetails(h);
								D3ArmoryControler.getInstance().initStuffHero(h);
								for(Item i : h.getItems().getItemsMap().values())
								{
									D3ArmoryControler.getInstance().getRecorder().saveItem(i);
								}
								
								
								
							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				}
			}).start();*/
		
	
		//System.out.println(new RSSReader().getRss("http://www.diablofans.com/news.rss"));
	
	}
	
	
}

