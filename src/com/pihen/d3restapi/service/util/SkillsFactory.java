package com.pihen.d3restapi.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pihen.d3restapi.beans.Gem;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.beans.Skill;
import com.pihen.d3restapi.beans.SkillRune;
import com.pihen.d3restapi.service.util.StuffCalculator.ELEMENTS;

public class SkillsFactory {
	public final static String PREFIX="_SKILLS_";
	private static Map<String,MinMaxBonus> buffs; 
	
	
	public static List<SkillRune> getSkillsFor(String clazz)
	{
		switch(clazz)
		{
			case "barbarian": return barbFactorySkills();
			case "monk": return monkFactorySkills();
			case "wizard": return wizardFactorySkills();
			case "crusader": return crusaderFactorySkills();
			case "witch-doctor": return witchdoctorFactorySkills();
			case "demon-hunter": return demonhunterFactorySkills();
			default : return null;
		}
		
		
	}
	
	private static List<SkillRune> demonhunterFactorySkills() {
		List<SkillRune> skills = new ArrayList<SkillRune>();
		
		SkillRune sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/thrill-of-the-hunt");
			sk.getSkill().setName("Thrill of the hunt");
			sk.getSkill().setIcon("demonhunter_passive_thrillofthehunt");
			sk.getSkill().setDescription("Every 6 seconds, your next skill that costs Hatred will immobilize all enemies hit for 2 seconds.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/tactical-advantage");
			sk.getSkill().setName("Tactical advantage");
			sk.getSkill().setIcon("demonhunter_passive_tacticaladvantage");
			sk.getSkill().setDescription("Whenever you use Vault, Smoke Screen, or backflip with Evasive Fire you gain 60% movement speed for 2 seconds.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/blood-vengeance");
			sk.getSkill().setName("Blood Vengeance");
			sk.getSkill().setIcon("demonhunter_passive_bloodvengeance");
			sk.getSkill().setDescription("Your maximum Hatred is increased by 25. In addition, gain 30 Hatred and 3 Discipline when you are healed by a health globe.");
			sk.getSkill().setLevel(13);
		skills.add(sk);
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/steady-aim");
			sk.getSkill().setName("Steady Aim");
			sk.getSkill().setIcon("demonhunter_passive_steadyaim");
			sk.getSkill().setDescription("As long as there are no enemies within 10 yards, all damage is increased by 20%.");
			sk.getSkill().setLevel(16);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/cull-the-weak");
			sk.getSkill().setName("Cull The Weak");
			sk.getSkill().setIcon("demonhunter_passive_culltheweak");
			sk.getSkill().setDescription("Increase damage against slowed enemies by 20%.");
			sk.getSkill().setLevel(20);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/night-stalker");
			sk.getSkill().setName("Night Stalker");
			sk.getSkill().setIcon("demonhunter_passive_nightstalker");
			sk.getSkill().setDescription("Critical Hits have a chance to restore 1 Discipline.\nDiscipline is used to fuel many of your tactical and defensive skills.");
			sk.getSkill().setLevel(20);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/brooding");
			sk.getSkill().setName("Brooding");
			sk.getSkill().setIcon("demonhunter_passive_brooding");
			sk.getSkill().setDescription("Gain 1.5% Life regeneration per second for every second you remain stationary, stacking up to 3 times. This bonus is reset 5 seconds after you move.");
			sk.getSkill().setLevel(25);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/hot-pursuit");
			sk.getSkill().setName("Hot Pursuit");
			sk.getSkill().setIcon("demonhunter_passive_hotpursuit");
			sk.getSkill().setDescription("Increase movement speed by 20% for 2 seconds when you hit an enemy");
			sk.getSkill().setLevel(27);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/archery");
			sk.getSkill().setName("Archery");
			sk.getSkill().setIcon("demonhunter_passive_archery");
			sk.getSkill().setDescription("Gain a bonus based on your weapon type:\nBow: 8% increased damage\nCrossbow: 50% Critical Hit Damage\nHand Crossbow: 5% Critical Hit Chance\n2nd Hand Crossbow: 1 Hatred per Second");
			sk.getSkill().setLevel(30);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/numbing-traps");
			sk.getSkill().setName("Numbing Traps");
			sk.getSkill().setIcon("demonhunter_passive_numbingtraps");
			sk.getSkill().setDescription("Enemies you Slow or hit with Fan of Knives, Spike Trap, Caltrops, Grenades, and Sentry fire have their damage reduced by 25% for 3 seconds.");
			sk.getSkill().setLevel(30);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/perfectionist");
			sk.getSkill().setName("Perfectionist");
			sk.getSkill().setIcon("demonhunter_passive_perfectionist");
			sk.getSkill().setDescription("Reduce the Discipline cost of all skills by 10%. Increase your Life, Armor, and resistance to all elements by 10%.\nDiscipline is used to fuel many of your tactical and defensive skills.");
			sk.getSkill().setLevel(35);
		skills.add(sk);
					
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/custom-engineering");
			sk.getSkill().setName("Custom Engineering");
			sk.getSkill().setIcon("demonhunter_passive_customengineering");
			sk.getSkill().setDescription("Increase the duration of your Caltrops, Marked for Death, Spike Trap, and Sentry by 100%.\nIncrease the maximum number of Sentries to 3 and Spike Traps to 6.");
			sk.getSkill().setLevel(40);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/grenadier");
			sk.getSkill().setName("Grenadier");
			sk.getSkill().setIcon("demonhunter_passive_grenadier");
			sk.getSkill().setDescription("Increase the damage of grenades by 10%.\nIncrease the explosion size of grenades by 20%.\nUpon death, you drop a giant grenade that explodes for 1000% weapon damage as Fire.");
			sk.getSkill().setLevel(45);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/sharpshooter");
			sk.getSkill().setName("Sharpshooter");
			sk.getSkill().setIcon("demonhunter_passive_sharpshooter");
			sk.getSkill().setDescription("Gain 4% Critical Hit Chance every second. This bonus is reset 1 seconds after you successfully critically hit.");
			sk.getSkill().setLevel(50);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/ballistics");
			sk.getSkill().setName("Ballistics");
			sk.getSkill().setIcon("demonhunter_passive_ballistics");
			sk.getSkill().setDescription("Increase damage of rockets by 100%.\nIn addition, you have a 20% chance to fire a homing rocket for 150% weapon damage when you attack.");
			sk.getSkill().setLevel(55);
		skills.add(sk);
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/ambush");
			sk.getSkill().setName("Ambush");
			sk.getSkill().setIcon("x1_demonhunter_passive_ambush");
			sk.getSkill().setDescription("You deal 40% additional damage to enemies above 75% health.");
			sk.getSkill().setLevel(64);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/awareness");
			sk.getSkill().setName("Awareness");
			sk.getSkill().setIcon("x1_demonhunter_passive_awareness");
			sk.getSkill().setDescription("Your Armor is increased by 30% of your Dexterity");
			sk.getSkill().setLevel(66);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/single-out");
			sk.getSkill().setName("Single Out");
			sk.getSkill().setIcon("x1_demonhunter_passive_singleout");
			sk.getSkill().setDescription("Gain 25% Critical Hit Chance against enemies who are more than 20 yards away from any other enemies.");
			sk.getSkill().setLevel(68);
		skills.add(sk);
		
		return skills;
	}

	private static List<SkillRune> witchdoctorFactorySkills() {
		List<SkillRune> skills = new ArrayList<SkillRune>();
		SkillRune sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/jungle-fortitude");
			sk.getSkill().setName("Jungle Fortitude");
			sk.getSkill().setIcon("witchdoctor_passive_junglefortitude");
			sk.getSkill().setDescription("Reduce all damage taken by you and your pets by 15%.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		 sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/circle-of-life");
			sk.getSkill().setName("Circle of Life");
			sk.getSkill().setIcon("witchdoctor_passive_circleoflife");
			sk.getSkill().setDescription("When an enemy dies within 20 yards, there is a 30% chance that a Zombie Dog will automatically emerge.\nThe range of this effect is increased by your gold pickup radius.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		 sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/spiritual-attunement");
			sk.getSkill().setName("Spiritual Attunement");
			sk.getSkill().setIcon("witchdoctor_passive_spiritualattunement");
			sk.getSkill().setDescription("Maximum Mana is increased by 10%. Regenerate 1% of your maximum Mana per second.\nMana fuels your offensive and defensive skills.");
			sk.getSkill().setLevel(13);
		skills.add(sk);
		
		 sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/gruesome-feast");
			sk.getSkill().setName("Gruesome Feast");
			sk.getSkill().setIcon("witchdoctor_passive_gruesomefeast");
			sk.getSkill().setDescription("When you are healed by a health globe, gain 10% of your maximum Mana and 10% Intelligence for 15 seconds. The Intelligence bonus stacks up to 5 times.");
			sk.getSkill().setLevel(16);
		skills.add(sk);
				
		 sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/blood-ritual");
			sk.getSkill().setName("Blood Ritual");
			sk.getSkill().setIcon("witchdoctor_passive_bloodritual");
			sk.getSkill().setDescription("10% of Mana costs are paid with Life. In addition, you regenerate 1% of your maximum Life per second.");
			sk.getSkill().setLevel(20);
		skills.add(sk);
		
		 sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/bad-medicine");
			sk.getSkill().setName("Bad Medicine");
			sk.getSkill().setIcon("witchdoctor_passive_badmedicine");
			sk.getSkill().setDescription("When you deal Poison damage to an enemy, its damage is reduced by 20% for 3 seconds.");
			sk.getSkill().setLevel(20);
		skills.add(sk);		
		
		 sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/zombie-handler");
			sk.getSkill().setName("Zombie Handler");
			sk.getSkill().setIcon("witchdoctor_passive_zombiehandler");
			sk.getSkill().setDescription("You can have 1 additional Zombie Dog summoned at one time. The healths of you, your Zombie Dogs and Gargantuan are increased by 20%.");
			sk.getSkill().setLevel(24);
		skills.add(sk);		
		
		 sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/pierce-the-veil");
			sk.getSkill().setName("Pierce The Veil");
			sk.getSkill().setIcon("witchdoctor_passive_piercetheveil");
			sk.getSkill().setDescription("All of your damage is increased by 20%, but your Mana costs are increased by 30%.");
			sk.getSkill().setLevel(27);
		skills.add(sk);		
	   
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/spirit-vessel");
			sk.getSkill().setName("Spirit Vessel");
			sk.getSkill().setIcon("witchdoctor_passive_spiritvessel");
			sk.getSkill().setDescription("Reduce the cooldown of your Horrify, Spirit Walk, and Soul Harvest spells by 2 seconds.\nIn addition, the next time you receive fatal damage, you automatically enter the spirit realm for 2 seconds and heal to 15% of your maximum Life.\nThis effect may occur once every 90 seconds.");
			sk.getSkill().setLevel(30);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/fetish-sycophants");
			sk.getSkill().setName("Fetish Sycophants");
			sk.getSkill().setIcon("witchdoctor_passive_fetishsycophants");
			sk.getSkill().setDescription("When you hit enemies with your spells, you have up to a 10% chance to summon a dagger-wielding Fetish to fight by your side for 60 seconds.");
			sk.getSkill().setLevel(30);
		skills.add(sk);		

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/rush-of-essence");
			sk.getSkill().setName("Rush Of Essence");
			sk.getSkill().setIcon("witchdoctor_passive_rushofessence");
			sk.getSkill().setDescription("Spirit spells return 100 Mana over 10 seconds.\nSpirit spells are:\n Haunt\n Horrify\n Mass Confusion\n Soul Harvest\n Spirit Barrage\n Spirit Walk");
			sk.getSkill().setLevel(36);
		skills.add(sk);		
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/vision-quest");
			sk.getSkill().setName("Vision Quest");
			sk.getSkill().setIcon("witchdoctor_passive_visionquest");
			sk.getSkill().setDescription("When you deal damage with Corpse Spiders, Firebomb, Plague of Toads, or Poison Dart, your Mana regeneration is increased by 30% for 5 seconds.");
			sk.getSkill().setLevel(40);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/fierce-loyalty");
			sk.getSkill().setName("Fierce Loyalty");
			sk.getSkill().setIcon("witchdoctor_passive_fierceloyalty");
			sk.getSkill().setDescription("You can have 1 additional Zombie Dog summoned at one time. While you have a Zombie Dog, Gargantuan, or Fetish following you and not in combat, your movement speed is increased by 30%.");
			sk.getSkill().setLevel(45);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/grave-injustice");
			sk.getSkill().setName("Grave Injustice");
			sk.getSkill().setIcon("witchdoctor_passive_graveinjustice");
			sk.getSkill().setDescription("Gain 1% of your maximum Life and Mana and reduce the cooldown of all of your skills by 1 second when an enemy dies within 20 yards.\nThe range is extended by items that increase your gold pickup radius.");
			sk.getSkill().setLevel(50);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/tribal-rites");
			sk.getSkill().setName("Tribal Rites");
			sk.getSkill().setIcon("witchdoctor_passive_tribalrites");
			sk.getSkill().setDescription("Reduce the cooldowns of your Fetish Army, Big Bad Voodoo, Hex, Gargantuan, Summon Zombie Dogs and Mass Confusion skills by 25%.");
			sk.getSkill().setLevel(55);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/creeping-death");
			sk.getSkill().setName("Creeping Death");
			sk.getSkill().setIcon("x1_witchdoctor_passive_creepingdeath");
			sk.getSkill().setDescription("Your Haunt, Locust Swarm and the damage amplification from Piranhas last almost forever.");
			sk.getSkill().setLevel(64);
		skills.add(sk);		
	
		

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/physical-attunement");
			sk.getSkill().setName("Physical Attunement");
			sk.getSkill().setIcon("x1_witchdoctor_passive_physicalattunement");
			sk.getSkill().setDescription("You gain 70 Physical Resistance for every enemy within 20 yards.\nThe range of this effect is increased by your gold pickup radius.");
			sk.getSkill().setLevel(66);
		skills.add(sk);		

		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/midnight-feast");
			sk.getSkill().setName("Midnight Feast");
			sk.getSkill().setIcon("x1_witchdoctor_passive_midnightfeast");
			sk.getSkill().setDescription("You can have 1 additional Zombie Dog summoned at one time. The damage of your Zombie Dogs and Gargantuan is increased 50%.");
			sk.getSkill().setLevel(68);
		skills.add(sk);		

	
		
		
		return skills;
	}

	private static List<SkillRune> crusaderFactorySkills() {
		List<SkillRune> skills = new ArrayList<SkillRune>();
		SkillRune sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/heavenly-strength");
			sk.getSkill().setName("Heavenly Strength");
			sk.getSkill().setIcon("x1_crusader_passive_heavenlystrength");
			sk.getSkill().setDescription("You can wield a two-handed weapon in your main hand while bearing a shield in your off hand.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/fervor");
			sk.getSkill().setName("Fervor");
			sk.getSkill().setIcon("x1_crusader_passive_fervor");
			sk.getSkill().setDescription("While wielding a one-handed weapon, your attack speed is increased by 15% and all cooldowns are reduced by 15%.");
			sk.getSkill().setLevel(10);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/vigilant");
			sk.getSkill().setName("Vigilant");
			sk.getSkill().setIcon("x1_crusader_passive_vigilant");
			sk.getSkill().setDescription("Increase Life regeneration by 2063.\nReduce all non-Physical damage taken by 20%.");
			sk.getSkill().setLevel(13);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/righteousness");
			sk.getSkill().setName("Righteousness");
			sk.getSkill().setIcon("x1_crusader_passive_righteousness");
			sk.getSkill().setDescription("Your primary skills generate an additional 3 Wrath.\nIncrease maximum Wrath by 30.");
			sk.getSkill().setLevel(16);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/insurmountable");
			sk.getSkill().setName("Insurmountable");
			sk.getSkill().setIcon("x1_crusader_passive_insurmountable");
			sk.getSkill().setDescription("Blocking an attack generates 6 Wrath.");
			sk.getSkill().setLevel(20);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/fanaticism");
			sk.getSkill().setName("Fanaticism");
			sk.getSkill().setIcon("x1_crusader_passive_fanaticism");
			sk.getSkill().setDescription("Increase the attack speed of Punish, Slash, Smite and Justice by 15%.");
			sk.getSkill().setLevel(20);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/indestructible");
			sk.getSkill().setName("Indestructible");
			sk.getSkill().setIcon("x1_crusader_passive_indestructible");
			sk.getSkill().setDescription("When you receive fatal damage, you instead become immune to damage, gain 35% increased damage and gain 82526 Life per Kill for 5 seconds.\nThis effect may occur once every 60 seconds.");
			sk.getSkill().setLevel(25);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/holy-cause");
			sk.getSkill().setName("Holy Cause");
			sk.getSkill().setIcon("x1_crusader_passive_holycause");
			sk.getSkill().setDescription("The amount of damage dealt by your weapon is increased by 10%.\nWhenever you deal Holy damage, you heal 1% of your total Life.");
			sk.getSkill().setLevel(27);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/wrathful");
			sk.getSkill().setName("Wrathful");
			sk.getSkill().setIcon("x1_crusader_passive_wrathful");
			sk.getSkill().setDescription("Each point of Wrath spent heals you for 825 Life.\nHeal amount is increased by 1% of your Health Globe Healing Bonus.");
			sk.getSkill().setLevel(30);
		skills.add(sk);
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/divine-fortress");
			sk.getSkill().setName("Divine Fortress");
			sk.getSkill().setIcon("x1_crusader_passive_divinefortress");
			sk.getSkill().setDescription("Your Armor is increased by a percent equal to your shield's Block Chance.");
			sk.getSkill().setLevel(30);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/lord-commander");
			sk.getSkill().setName("Lord Commander");
			sk.getSkill().setIcon("x1_crusader_passive_lordcommander");
			sk.getSkill().setDescription("The cooldown of Steed Charge is reduced by 25% and Bombardment by 35%.\nDamage dealt by Phalanx is increased 20%.");
			sk.getSkill().setLevel(35);
		skills.add(sk);

		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/hold-your-ground");
			sk.getSkill().setName("Hold Your Ground");
			sk.getSkill().setIcon("x1_crusader_passive_holdyourground");
			sk.getSkill().setDescription("You can no longer Dodge, but your Block Chance is increased by 15%.");
			sk.getSkill().setLevel(40);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/long-arm-of-the-law");
			sk.getSkill().setName("Long Arm of the Law");
			sk.getSkill().setIcon("x1_crusader_passive_longarmofthelaw");
			sk.getSkill().setDescription("Increase the duration of the Active effect of all Laws by 5 seconds.");
			sk.getSkill().setLevel(45);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/iron-maiden");
			sk.getSkill().setName("Iron Maiden");
			sk.getSkill().setIcon("x1_crusader_passive_ironmaiden");
			sk.getSkill().setDescription("Your Thorns is increased by 50%.");
			sk.getSkill().setLevel(50);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/renewal");
			sk.getSkill().setName("Renewal");
			sk.getSkill().setIcon("x1_crusader_passive_renewal");
			sk.getSkill().setDescription("Whenever you successfully block, you gain 12379 Life.");
			sk.getSkill().setLevel(55);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/finery");
			sk.getSkill().setName("Finery");
			sk.getSkill().setIcon("x1_crusader_passive_finery");
			sk.getSkill().setDescription("Gain 1.5% Strength for every gem socketed into your gear.");
			sk.getSkill().setLevel(60);
		skills.add(sk);		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/blunt");
			sk.getSkill().setName("Blunt");
			sk.getSkill().setIcon("x1_crusader_passive_blunt");
			sk.getSkill().setDescription("Increase the damage of Justice and Blessed Hammer by 20%.");
			sk.getSkill().setLevel(65);
		skills.add(sk);		
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/towering-shield");
			sk.getSkill().setName("Towering Shield");
			sk.getSkill().setIcon("x1_crusader_passive_toweringshield");
			sk.getSkill().setDescription("Increase the damage of Punish, Shield Bash and Blessed Shield by 20%.\nReduce the cooldown of Shield Glare by 30%.");
			sk.getSkill().setLevel(70);
		skills.add(sk);		

		return skills;
	}

	private static List<SkillRune> wizardFactorySkills() {
		List<SkillRune> skills = new ArrayList<SkillRune>();
		
		
		SkillRune sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/power-hungry");
			sk.getSkill().setName("Power Hungry");
			sk.getSkill().setIcon("wizard_passive_powerhungry");
			sk.getSkill().setDescription("Being healed by a health globe causes the next Arcane Power Spender you cast to be cast for free.\nYou can have up to 10 charges of Power Hungry.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/blur");
			sk.getSkill().setName("Blur");
			sk.getSkill().setIcon("wizard_passive_blur");
			sk.getSkill().setDescription("Decrease damage taken by 17%.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/evocation");
			sk.getSkill().setName("Evocation");
			sk.getSkill().setIcon("wizard_passive_evocation");
			sk.getSkill().setDescription("Reduce all cooldowns by 20%.");
			sk.getSkill().setLevel(13);
		skills.add(sk);
			
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/glass-cannon");
			sk.getSkill().setName("Glass Cannon");
			sk.getSkill().setIcon("wizard_passive_glasscannon");
			sk.getSkill().setDescription("Increase all damage done by 15%, but decrease Armor and resistances by 10%.");
			sk.getSkill().setLevel(16);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/prodigy");
			sk.getSkill().setName("Prodigy");
			sk.getSkill().setIcon("wizard_passive_prodigy");
			sk.getSkill().setDescription("When you cast a Signature spell, you gain 5 Arcane Power.\nThe following skills are Signature spells:\n Magic Missile\n Shock Pulse\n Spectral Blade\n Electrocute");
			sk.getSkill().setLevel(20);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/astral-presence");
			sk.getSkill().setName("AstralPresence");
			sk.getSkill().setIcon("wizard_passive_astralpresence");
			sk.getSkill().setDescription("Increase your maximum Arcane Power by 20 and Arcane Power regeneration by 2 per second.");
			sk.getSkill().setLevel(24);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/illusionist");
			sk.getSkill().setName("Illusionist");
			sk.getSkill().setIcon("wizard_passive_illusionist");
			sk.getSkill().setDescription("When you take more than 15% of your maximum Life in damage within 1 second, the cooldowns on Mirror Image, Slow Time, and Teleport are reset.\nWhen you use Mirror Image, Slow Time, or Teleport, your movement speed is increased by 30% for 3 seconds.");
			sk.getSkill().setLevel(27);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/cold-blooded");
			sk.getSkill().setName("Cold Blooded");
			sk.getSkill().setIcon("wizard_passive_coldblooded");
			sk.getSkill().setDescription("Enemies chilled or frozen by you take 10% more damage from all sources for the duration of the chill or Freeze.");
			sk.getSkill().setLevel(30);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/conflagration");
			sk.getSkill().setName("Conflagration");
			sk.getSkill().setIcon("wizard_passive_conflagration");
			sk.getSkill().setDescription("Fire damage dealt to enemies applies a burning effect, increasing their chance to be critically hit by 6% for 3 seconds.");
			sk.getSkill().setLevel(34);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/paralysis");
			sk.getSkill().setName("Paralysis");
			sk.getSkill().setIcon("wizard_passive_paralysis");
			sk.getSkill().setDescription("Lightning spells have a 15% chance to Stun all targets hit for 1.5 seconds.");
			sk.getSkill().setLevel(37);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/galvanizing-ward");
			sk.getSkill().setName("Galvanizing Ward");
			sk.getSkill().setIcon("wizard_passive_galvanizingward");
			sk.getSkill().setDescription("As long as you have not taken damage in the last 5 seconds you gain a protective shield that absorbs the next 62720 damage.");
			sk.getSkill().setLevel(40);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/temporal-flux");
			sk.getSkill().setName("Temporal Flux");
			sk.getSkill().setIcon("wizard_passive_temporalflux");
			sk.getSkill().setDescription("Enemies that take Arcane damage are slowed by 80% for 2 seconds.");
			sk.getSkill().setLevel(45);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/dominance");
			sk.getSkill().setName("Dominance");
			sk.getSkill().setIcon("x1_wizard_passive_dominance");
			sk.getSkill().setDescription("Killing an enemy grants a shield that absorbs 12379 damage for 3 seconds. This effect can stack up to 10 times.\nRefreshing Dominance will set the shield to its maximum possible potency and each stack will increase its total duration by 0.5 seconds.");
			sk.getSkill().setLevel(50);
		skills.add(sk);	
			
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/arcane-dynamo");
			sk.getSkill().setName("Arcane Dynamo");
			sk.getSkill().setIcon("wizard_passive_arcanedynamo");
			sk.getSkill().setDescription("When you cast a Signature spell, you gain a Flash of Insight. After 5 Flashes of Insight, your next non-Signature spell deals 60% additional damage.\nThe following skills are Signature spells:\n Magic Missile\n Shock Pulse\n Spectral Blade\n Electrocute");
			sk.getSkill().setLevel(55);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/unstable-anomaly");
			sk.getSkill().setName("Unstable Anomaly");
			sk.getSkill().setIcon("wizard_passive_unstableanomaly");
			sk.getSkill().setDescription("When you receive fatal damage, you heal to 45% of your maximum Life and release a shockwave that knocks enemies back and slows them by 60% for 3 seconds.\nThis effect may occur once every 60 seconds.");
			sk.getSkill().setLevel(60);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/unwavering-will");
			sk.getSkill().setName("Unwavering Will");
			sk.getSkill().setIcon("x1_wizard_passive_unwaveringwill");
			sk.getSkill().setDescription("Standing still for 1.5 seconds increases the following attributes:\n Armor: 20%\n All Resistances: 20%\n Damage: 10%");
			sk.getSkill().setLevel(64);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/audacity");
			sk.getSkill().setName("Audacity");
			sk.getSkill().setIcon("x1_wizard_passive_audacity");
			sk.getSkill().setDescription("You deal 15% additional damage to enemies within 15 yards.");
			sk.getSkill().setLevel(66);
		skills.add(sk);	
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/elemental-exposure");
			sk.getSkill().setName("Elemental Exposure");
			sk.getSkill().setIcon("x1_wizard_passive_elementalexposure");
			sk.getSkill().setDescription("Damaging enemies with Arcane, Cold, Fire or Lightning will cause them to take 5% more damage from all sources for 5 seconds. Each different damage type applies a stack, stacking up to 4 times.\nElemental damage from your weapon contributes to Elemental Exposure.");
			sk.getSkill().setLevel(68);
		skills.add(sk);	
			
		return skills;
	}

	private static List<SkillRune> monkFactorySkills() {
		List<SkillRune> skills = new ArrayList<SkillRune>();
		
		
		SkillRune sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/harmony");
			sk.getSkill().setName("Harmony 2.1 PTR");
			sk.getSkill().setIcon("monk_passive_onewitheverything");
			sk.getSkill().setDescription("40% of your single elemental resistances from items instead increases your resistance to all elements.");
			sk.getSkill().setLevel(45);
		skills.add(sk);
		
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/resolve");
			sk.getSkill().setName("Resolve");
			sk.getSkill().setIcon("monk_passive_resolve");
			sk.getSkill().setDescription("Damage you deal reduces enemy damage by 20% for 2.5 seconds.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/fleet-footed");
			sk.getSkill().setName("Fleet footed");
			sk.getSkill().setIcon("monk_passive_fleetfooted");
			sk.getSkill().setDescription("Increase movement speed by 10%.");
			sk.getSkill().setLevel(10);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/exalted-soul");
			sk.getSkill().setName("Exalted soul");
			sk.getSkill().setIcon("monk_passive_exaltedsoul");
			sk.getSkill().setDescription("Increase maximum Spirit by 100 and increase Spirit Regeneration by 2 per second.\nSpirit fuels your defensive and offensive abilities.");
			sk.getSkill().setLevel(13);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/transcendence");
			sk.getSkill().setName("Transcendence");
			sk.getSkill().setIcon("monk_passive_transcendence");
			sk.getSkill().setDescription("Every point of Spirit spent heals you for 248 Life.\nHeal amount is increased by 0.4% of your Health Globe Healing Bonus.");
			sk.getSkill().setLevel(16);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/chant-of-resonance");
			sk.getSkill().setName("Chant of resonance");
			sk.getSkill().setIcon("monk_passive_chantofresonance");
			sk.getSkill().setDescription("The Spirit costs of Mantra activation effects are reduced by 50% and you gain 2 Spirit every second when you have a Mantra learned.");
			sk.getSkill().setLevel(20);
		skills.add(sk);
		
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/seize-the-initiative");
			sk.getSkill().setName("Seize the Initiative");
			sk.getSkill().setIcon("monk_passive_seizetheinitiative");
			sk.getSkill().setDescription("Your Armor is increased by 30% of your Dexterity");
			sk.getSkill().setLevel(20);
		skills.add(sk);
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/the-guardians-path");
			sk.getSkill().setName("The Guardian's Path");
			sk.getSkill().setIcon("monk_passive_theguardianspath");
			sk.getSkill().setDescription("While dual-wielding, you gain a 15% chance to dodge incoming attacks. While using a two-handed weapon, all Spirit generation is increased by 35%.");
			sk.getSkill().setLevel(24);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/sixth-sense");
			sk.getSkill().setName("Sixth Sense");
			sk.getSkill().setIcon("monk_passive_sixthsense");
			sk.getSkill().setDescription("Your dodge chance is increased by an amount equal to 42.5% of your Critical Hit Chance.");
			sk.getSkill().setLevel(27);
		skills.add(sk);


		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/provocation");
			sk.getSkill().setName("Provocation");
			sk.getSkill().setIcon("monk_passive_provocation");
			sk.getSkill().setDescription("The duration of control-impairing effects on you are reduced by 25%.\nWhenever you are hit by a Stun, Freeze, Fear, Immobilize or Charm, you gain 15% increased damage for 10 seconds.");
			sk.getSkill().setLevel(30);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/beacon-of-ytar");
			sk.getSkill().setName("Beacon of ytar");
			sk.getSkill().setIcon("monk_passive_beaconofytar");
			sk.getSkill().setDescription("Reduce all cooldowns by 20%.");
			sk.getSkill().setLevel(35);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/guiding-light");
			sk.getSkill().setName("Guiding Light");
			sk.getSkill().setIcon("monk_passive_guidinglight");
			sk.getSkill().setDescription("Your heals and shields grant increased damage equal to the percentage of Life missing, up to a maximum of 30%, for 10 seconds.");
			sk.getSkill().setLevel(40);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/one-with-everything");
			sk.getSkill().setName("One With Everything");
			sk.getSkill().setIcon("monk_passive_onewitheverything");
			sk.getSkill().setDescription("Your resistance to all elements is equal to your highest elemental resistance.");
			sk.getSkill().setLevel(45);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/combination-strike");
			sk.getSkill().setName("Combination Strike");
			sk.getSkill().setIcon("monk_passive_combinationstrike");
			sk.getSkill().setDescription("Each different Spirit Generator you use increases your damage by 10% for 3 seconds.");
			sk.getSkill().setLevel(50);
		skills.add(sk);
	
		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/near-death-experience");
			sk.getSkill().setName("Near Death Experience");
			sk.getSkill().setIcon("monk_passive_neardeathexperience");
			sk.getSkill().setDescription("When receiving fatal damage, you are instead restored to 35% of maximum Life and 35% Spirit.\nThis effect may occur once every 60 seconds.\nWhen Near Death Experience is on cooldown, your Health Globe Healing Bonus, Life per Second and Life per Hit are increased by 35%.");
			sk.getSkill().setLevel(58);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/unity");
			sk.getSkill().setName("Unity");
			sk.getSkill().setIcon("x1_monk_passive_unity");
			sk.getSkill().setDescription("Each ally affected by your Mantras increases your damage by 5%, up to a maximum of 20%, and has 5% increased damage.");
			sk.getSkill().setLevel(64);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/momentum");
			sk.getSkill().setName("Momentum");
			sk.getSkill().setIcon("x1_monk_passive_momentum");
			sk.getSkill().setDescription("Moving 25 yards increases your damage by 20% for 6 seconds.");
			sk.getSkill().setLevel(66);
		skills.add(sk);

		sk = new SkillRune();
			sk.setSkill(new Skill());
			sk.getSkill().setTooltipUrl("skill/mythic-rhythm");
			sk.getSkill().setName("Mythic Rhythm");
			sk.getSkill().setIcon("x1_monk_passive_mythicrhythm");
			sk.getSkill().setDescription("Every third hit from a Spirit Generator increases the damage of your next damaging Spirit Spender by 40%.");
			sk.getSkill().setLevel(68);
		skills.add(sk);
		
		return skills;
	}

	private static List<SkillRune> barbFactorySkills() {
		List<SkillRune> skills = new ArrayList<SkillRune>();
			SkillRune sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/pound-of-flesh");
				sk.getSkill().setName("Pound of Flesh");
				sk.getSkill().setIcon("barbarian_passive_poundofflesh");
				sk.getSkill().setDescription("Gain 50% additional Life from health globes.");
				sk.getSkill().setLevel(10);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/ruthless");
				sk.getSkill().setName("Ruthless");
				sk.getSkill().setIcon("barbarian_passive_ruthless");
				sk.getSkill().setDescription("You deal 40% additional damage to enemies below 30% health.");
				sk.getSkill().setLevel(10);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/nervesofsteel");
				sk.getSkill().setName("Nerves of Steel");
				sk.getSkill().setIcon("barbarian_passive_ruthless");
				sk.getSkill().setDescription("Increase your Armor by 50% of your Vitality.");
				sk.getSkill().setLevel(13);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/weaponsmaster");
				sk.getSkill().setName("Weapons Master");
				sk.getSkill().setIcon("barbarian_passive_weaponsmaster");
				sk.getSkill().setDescription("Gain a bonus based on the weapon type of your main hand weapon:\nSwords/Daggers: 8% increased damage\nMaces/Axes: 5% Critical Hit Chance\nPolearms/Spears: 8% attack speed,\nMighty Weapons: 1 Fury per hit");
				sk.getSkill().setLevel(16);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/inspiring-presence");
				sk.getSkill().setName("Inspiring Presence");
				sk.getSkill().setIcon("barbarian_passive_inspiringpresence");
				sk.getSkill().setDescription("The duration of your shouts is doubled. After using a shout you and all allies within 100 yards regenerate 1% of maximum Life per second for 60 seconds.\nYour shouts are:\n Battle Rage\n Threatening Shout\n War Cry");
				sk.getSkill().setLevel(16);
				skills.add(sk);

				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/berserker-rage");
				sk.getSkill().setName("Berserker Rage");
				sk.getSkill().setIcon("barbarian_passive_berserkerrage");
				sk.getSkill().setDescription("You deal 25% additional damage while at maximum Fury.");
				sk.getSkill().setLevel(20);
				skills.add(sk);
	
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/bloodthirst");
				sk.getSkill().setName("Bloodthirst");
				sk.getSkill().setIcon("barbarian_passive_bloodthirst");
				sk.getSkill().setDescription("Each point of Fury spent heals you for 578 Life.\nHeal amount is increased by 1% of your Health Globe Healing Bonus.");
				sk.getSkill().setLevel(24);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/animosity");
				sk.getSkill().setName("Animosity");
				sk.getSkill().setIcon("barbarian_passive_animosity");
				sk.getSkill().setDescription("Increase all Fury generation by 10%.\nIncrease maximum Fury by 20.\nFury is used to fuel your most powerful attacks.");
				sk.getSkill().setLevel(27);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/superstition");
				sk.getSkill().setName("Superstition");
				sk.getSkill().setIcon("barbarian_passive_superstition");
				sk.getSkill().setDescription("Reduce all non-Physical damage by 20%. When you take damage from a ranged or elemental attack, you have a chance to gain 2 Fury.");
				sk.getSkill().setLevel(30);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/tough-as-nails");
				sk.getSkill().setName("Tough as Nails");
				sk.getSkill().setIcon("barbarian_passive_toughasnails");
				sk.getSkill().setDescription("Increase Armor by 25%.\nIncrease Thorns damage dealt by 50%.");
				sk.getSkill().setLevel(30);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/no-escape");
				sk.getSkill().setName("No Escape");
				sk.getSkill().setIcon("barbarian_passive_noescape");
				sk.getSkill().setDescription("Increase the damage of Weapon Throw and Ancient Spear by 25% against enemies more than 20 yards away from you.");
				sk.getSkill().setLevel(35);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/relentless");
				sk.getSkill().setName("Relentless");
				sk.getSkill().setIcon("barbarian_passive_relentless");
				sk.getSkill().setDescription("While below 35% Life, all skills cost 75% less Fury and all damage taken is reduced by 50%.");
				sk.getSkill().setLevel(40);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/brawler");
				sk.getSkill().setName("Brawler");
				sk.getSkill().setIcon("barbarian_passive_brawler");
				sk.getSkill().setDescription("As long as there are 3 enemies within 12 yards, all of your damage is increased by 20%.");
				sk.getSkill().setLevel(45);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/juggernaut");
				sk.getSkill().setName("Juggernaut");
				sk.getSkill().setIcon("barbarian_passive_juggernaut");
				sk.getSkill().setDescription("The duration of control-impairing effects on you are reduced by 30%. In addition, whenever a Stun, Fear, Immobilize or Charm is cast on you, you have a chance to recover 20% of your maximum Life.");
				sk.getSkill().setLevel(50);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/unforgiving");
				sk.getSkill().setName("Unforgiving");
				sk.getSkill().setIcon("barbarian_passive_unforgiving");
				sk.getSkill().setDescription("You no longer degenerate Fury. Instead, you generate 2 Fury every 1 seconds.");
				sk.getSkill().setLevel(55);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/boon-of-bulkathos");
				sk.getSkill().setName("Boon of Bulkathos");
				sk.getSkill().setIcon("barbarian_passive_boonofbulkathos");
				sk.getSkill().setDescription("Reduce the cooldowns of your:\n Earthquake by 15 seconds.\n Call of the Ancients by 30 seconds.\n Wrath of the Berserker by 30 seconds.");
				sk.getSkill().setLevel(60);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/earthen-might");
				sk.getSkill().setName("Earthen Might");
				sk.getSkill().setIcon("X1_barbarian_passive_earthenmight");
				sk.getSkill().setDescription("Gain 30 Fury when activating Avalanche or Earthquake.");
				sk.getSkill().setLevel(64);
				skills.add(sk);
				
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/sword-and-board");
				sk.getSkill().setName("Sword and Board");
				sk.getSkill().setIcon("X1_barbarian_passive_swordandboard");
				sk.getSkill().setDescription("Blocking an attack generates 6 Fury.");
				sk.getSkill().setLevel(66);
				skills.add(sk);
				
				sk = new SkillRune();
				sk.setSkill(new Skill());
				sk.getSkill().setTooltipUrl("skill/rampage");
				sk.getSkill().setName("Rampage");
				sk.getSkill().setIcon("X1_barbarian_passive_rampage");
				sk.getSkill().setDescription("Increase Strength by 1% for 8 seconds after killing or assisting in killing an enemy. This effect stacks up to 25 times.");
				sk.getSkill().setLevel(68);
				skills.add(sk);
				
				
			return skills;
	}








	public static Map<String,MinMaxBonus> getBuff(SkillRune a, StuffCalculator sc) {
		
		buffs = new HashMap<String, MinMaxBonus>();
		
		if(a==null)
			return buffs;
		
		if(a.getSkill()==null)
			return buffs;
		
		
//CRUSADER
		if(a.getSkill().getId().equals("fervor"))
		{
			if(sc.getNbWeapon()<2)
			{
				buffs.put("Attacks_Per_Second_Percent"+PREFIX+a, new MinMaxBonus(0.15));
				buffs.put("Power_Cooldown_Reduction_Percent_All"+PREFIX+a, new MinMaxBonus(0.15));
			}
		}
		
		
		
		if(a.getSkill().getId().equals("finery"))
		{
			
			double gem = 0;
			double strength=sc.getPrimaryStatUnbuffedValue();
			for(Item i : sc.getAllItems())
			{
				if(i!=null)
				for(Gem g : i.getGems())
			
					if(g.getItem()!=null)
						gem=gem+1;
			}
			gem = gem*(1.5/100);
			buffs.put("Strength"+PREFIX+a, new MinMaxBonus(strength*gem));
		}
		
				
//WIZARD
		if(a.getSkill().getId().equals("evocation"))
		{
			buffs.put("Power_Cooldown_Reduction_Percent_All"+PREFIX+a, new MinMaxBonus(0.20));
		}
		if(a.getSkill().getId().equals("blur"))
		{
			buffs.put("Damage_Percent_Reduction_From_All"+PREFIX+a, new MinMaxBonus(0.17));
		}

		
		
//MONK
		
		if(a.getSkill().getId().equals("beacon-of-ytar"))
		{
			buffs.put("Power_Cooldown_Reduction_Percent_All"+PREFIX+a, new MinMaxBonus(0.20));
		}
		if(a.getSkill().getId().equals("sixth-sense"))
		{
			double critChance = sc.getCritChance()*100;
			double multi = 42.5;
			buffs.put("Increase_Dodge_Percent"+PREFIX+a, new MinMaxBonus((critChance*multi)/100));
		}
		if(a.getSkill().getId().equals("seize-the-initiative"))
		{
			double dext = sc.getPrimaryStatUnbuffedValue();
			double multi = 30;
			buffs.put("Armor"+PREFIX+a, new MinMaxBonus((dext*multi)/100));
		}
		
		if(a.getSkill().getId().equals("harmony")) //2.1 test
		{
			double multi=0.40;
			
			for(Item i : sc.getAllItems())
			{
				for(ELEMENTS e : ELEMENTS.values())
				{
					String k="Resistance#"+e;
					
					if(i.getAttributesRaw().containsKey(k))
					{
						double val = i.getAttributesRaw().get(k).getMoyenne();
						buffs.put("Resistance_all"+PREFIX+i.getName(),new MinMaxBonus(val*multi));
					}
				}
			}
			
		}
		
		
		if(a.getSkill().getId().equals("one-with-everything"))
		{
			double val=0;
			double max=val;
			ELEMENTS maxE=null;
			for(ELEMENTS e:ELEMENTS.values())
			{	
				val = sc.getResistance(e);
				if(val>max)
				{
					max=val;
					maxE=e;
				}
			}
			
			for(ELEMENTS e:ELEMENTS.values())
			{	
				if(e!=maxE)
					buffs.put("Resistance#"+e+PREFIX+a, new MinMaxBonus(sc.getResistance(maxE)-sc.getResistance(e)));
			}
			
			
		}
		
		
			
// BARBARE
		
		if(a.getSkill().getId().equals("weapons-master"))
		{
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND)!=null){
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Sword")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Dagger"))
				buffs.put("Damage_Weapon_Percent_Bonus#Physical_BUFF_"+a, new MinMaxBonus(0.08));
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Mace")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Axe"))
				buffs.put("Crit_Percent_Bonus_BUFF_"+a, new MinMaxBonus(0.05));
			if(sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Polearm")||sc.getStuffs().get(EnumerationStuff.MAIN_HAND).getType().getId().startsWith("Spear"))
				buffs.put("Attacks_Per_Second_Percent_BUFF_"+a, new MinMaxBonus(0.8));
			}
		}
		if(a.getSkill().getId().equals("tough-as-nails"))
		{
			buffs.put("Armor"+PREFIX+a, new MinMaxBonus(sc.getArmor()*.25));
		}
		if(a.getSkill().getId().equals("superstition"))
		{
			for(ELEMENTS e: ELEMENTS.values())
			{
				if(!e.equals(ELEMENTS.Physical))
					if(!e.equals(ELEMENTS.Holy))
						buffs.put("Damage_Percent_Reduction_From_Type#"+e+PREFIX+a,new MinMaxBonus(0.20));
			}
		}
		
		
		/*COMPANION
		case "FOCUSEDMIND" : buffs.put("Attacks_Per_Second_Item_Bonus_BUFF"+skill, new MinMaxBonus(0.03));
			break;
		case ANATOMY: buffs.put("Crit_Percent_Bonus"+skill, new MinMaxBonus(0.03));
			break;
			
		
	*/
		return buffs;
	}
	
	
}
