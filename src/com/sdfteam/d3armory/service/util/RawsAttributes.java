package com.sdfteam.d3armory.service.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class RawsAttributes  {
	
	List<String> itemAttributes;
	Map<String, String> map;
	
	public RawsAttributes()
	{
		itemAttributes = new ArrayList<String>();
		map= new TreeMap<String,String>();
		initItemsAttributes();
		initMap();
	}
	
	private void initMap()
	{
		map.put("strength" ,"+X Strength");
		map.put("intelligence" ,"+X Intelligence");
		map.put("vitality" ,"+X Vitality");
		map.put("dexterity" ,"+X Dexterity");
		map.put("resist-all" ,"+X Resistance to All Elements");
		map.put("armor" ,"+X Armor");
		map.put("plus-life" ,"+ % Life");
		map.put("life-regen" ,"Regenerates  Life per Second");
		map.put("plus-block" ,"+% Chance to Block");
		map.put("cc-reduce" ,"Reduces the duration of control impairing effects by %");
		map.put("elite-reduce" ,"Reduces damage from elites by %");
		map.put("melee-reduce" ,"Reduces damage from melee attacks by %");
		map.put("range-reduce" ,"Reduces damage from ranged attacks by %");
		map.put("cold-reduce" ,"Reduces damage from Cold attacks by %.");
		map.put("arcane-resist" ,"Arcane Resistance");
		map.put("cold-resist" ,"Cold Resistance");
		map.put("fire-resist" ,"Fire Resistance");
		map.put("lightning-resist" ,"Lightning Resistance");
		map.put("physical-resist" ,"Physical Resistance");
		map.put("poison-resist" ,"Poison Resistance");
		map.put("thorns" ,"Melee attackers take  damage per hit");
		map.put("attack-speed" ,"Attack speed increased by %");
		map.put("plus-aps" ,"Attacks per Second");
		map.put("critical-hit" ,"Critical Hit Chance increased by %");
		map.put("critical-hit-damage" ,"Critical Hit Damage increased by %");
		map.put("plus-damage" ,"+% Damage");
		map.put("min-damage" ,"Minimum Damage");
		map.put("max-damage" ,"Maximum Damage");
		map.put("minmax-damage" ,"Damage (Min-Max)");
		map.put("ruby-damage" ,"Minimum and + Maximum Damage");
		map.put("plus-arcane-damage" ,"+% to Arcane Damage");
		map.put("plus-cold-damage" ,"+% to Cold Damage");
		map.put("plus-fire-damage" ,"+% to Fire Damage");
		map.put("plus-holy-damage" ,"+% to Holy Damage");
		map.put("plus-lightning-damage" ,"+% to Lightning Damage");
		map.put("plus-poison-damage" ,"+% to Poison Damage");
		map.put("arcane-damage" ,"Arcane Damage");
		map.put("cold-damage" ,"Cold Damage");
		map.put("fire-damage" ,"Fire Damage");
		map.put("holy-damage" ,"Holy Damage");
		map.put("lightning-damage" ,"Lightning Damage");
		map.put("poison-damage" ,"Poison Damage");
		map.put("elite-damage" ,"Increases Damage against Elites by %");
		map.put("demon-damage" ,"+% Damage to Demons");
		map.put("human-damage" ,"+% Damage to Humans");
		map.put("plus-arcane-damage-skills" ,"Arcane skills deal % more damage");
		map.put("plus-cold-damage-skills" ,"Cold skills deal % more damage");
		map.put("plus-fire-damage-skills" ,"Fire skills deal % more damage");
		map.put("plus-holy-damage-skills" ,"Holy skills deal % more damage");
		map.put("plus-lightning-damage-skills" ,"Lightning skills deal % more damage");
		map.put("plus-poison-damage-skills" ,"Poison skills deal % more damage");
		map.put("chance-bleed" ,"% chance to inflict Bleed for  damage over 5 seconds");
		map.put("chance-blind" ,"% chance to Blind on Hit");
		map.put("chance-chill" ,"% chance to Chill on Hit");
		map.put("chance-fear" ,"% chance to Fear on Hit");
		map.put("chance-freeze" ,"% chance to Freeze on Hit");
		map.put("chance-immobilize" ,"% chance to Immobilize on Hit");
		map.put("chance-knockback" ,"% chance to Knockback on Hit");
		map.put("chance-slow" ,"% chance to Slow on Hit");
		map.put("chance-stun" ,"% chance to Stun on Hit");
		map.put("chance-whirlwind" ,"Chance to occasionally Whirlwind furioulsy.");
		map.put("chance-ball-energy" ,"Chance to hurt a ball of pure energy when attacking.");
		map.put("chance-skeleton" ,"Summons a skeleton when attacked.");
		map.put("chance-reflect-projectiles" ,"Chance to reflect projectiles when hit.");
		map.put("effect-poison-cloud" ,"You are sourrounded by a deadly Posion Cloud.");
		map.put("plus-movement" ,"+% Movement Speed");
		map.put("plus-pickup-radius" ,"Increases Gold and Health pickup by  yards");
		map.put("plus-experience" ,"Monster kills grant + experience");
		map.put("plus-experience-percent" ,"Increased Experience Rewarded per Kill by %");
		map.put("plus-experience-bonus" ,"Increases Bonus Experience by %");
		map.put("plus-gold-find" ,"+% Extra Gold from Monsters");
		map.put("plus-magic-find" ,"% Better Chance of finding Magic Items");
		map.put("health-globes" ,"Health Globes and Potions Grant + Life");
		map.put("life-steal" ,"% of Damage Dealt is Converted to Life");
		map.put("life-kill" ,"Life after each Kill");
		map.put("life-hit" ,"Each hit adds + Life");
		map.put("level-reduce" ,"Level Requirement reduced by ");
		map.put("indestructible" ,"Ignores durability loss");
		map.put("bb-bash" ,"Increases bash damage by %");
		map.put("bb-cleave" ,"Increases cleave damage by %");
		map.put("bb-frenzy" ,"Increases frenzy damage by %");
		map.put("bb-rend" ,"Reduces resource cost of Rend by  Fury");
		map.put("bb-revenge" ,"Increases Critical Hit Chance of Revenge by %");
		map.put("bb-weapon-throw" ,"Reduces resource cost of Weapon Throw by  Fury");
		map.put("bb-hammer-of-the-ancients" ,"Reduces resource cost of Hammer of the Ancients by  Fury");
		map.put("bb-whirlwind" ,"Increases Critical Hit Chance of Whirlwind by %");
		map.put("bb-overpower" ,"Increases Critical Hit Chance of Overpower by %");
		map.put("bb-seismic-slam" ,"Increases Critical Hit Chance of Seismic Slam by %");
		map.put("bb-weapon-throw-dmg" ,"Increases Weapon Throw damage by %");
		map.put("bb-ancient-spear-dmg" ,"Increases Ancient Spear damage by %");
		map.put("fury-max" ,"Maximum Fury");
		map.put("hatred-regen" ,"Increases Hatred Regeneration by  per Second");
		map.put("max-discipline" ,"Maximum Discipline");
		map.put("dh-chakram" ,"Reduces resource cost of Chakram by  Hatred");
		map.put("dh-evasive-fire" ,"Increases Evasive Fire damage by %");
		map.put("dh-grenades" ,"Increases Grenades Damage by %");
		map.put("dh-impale" ,"Reduces resource cost of Impale by  Hatred");
		map.put("dh-spike-trap" ,"Increases Spike Trap damage by %");
		map.put("dh-bola-shot" ,"Increases Bola Shot damage by %");
		map.put("dh-elemental-arrow" ,"Increases Elemental Arrow damage by %");
		map.put("dh-entangling-shot" ,"Increases Entangling Shot damage by %");
		map.put("dh-hungering-arrow" ,"Increases Hungering Arrow damage by %");
		map.put("dh-multishot" ,"Increases Critical Hit Chance of Multishot by %");
		map.put("dh-rapid-fire" ,"Increases Critical Hit Chance of Rapid Fire by %");
		map.put("dh-cluster-arrow" ,"Reduces resource cost of Cluster Arrow by  Hatred");
		map.put("dh-strafe" ,"Increases Critical Hit Chance of Strafe by %");
		map.put("spirit-spent-life" ,"Gain  Life per Spirit Spent");
		map.put("spirit-regen" ,"Increases Spirit Regeneration by  per Second");
		map.put("mk-crippling-wave" ,"Increases Crippling Wave damage by %");
		map.put("mk-cyclone-strike" ,"Reduces resource cost of Cyclone Strike by  Spirit");
		map.put("mk-deadly-reach" ,"Increases Deadly Reach damage by %");
		map.put("mk-exploding-palm" ,"Increases Exploding Palm damage by %");
		map.put("mk-fists-of-thunder" ,"Increases Fist of Thunder damage by %");
		map.put("mk-sweeping-wind" ,"Increases Sweeping Wind damage by %");
		map.put("mk-sweeping-wind-cost" ,"Reduces resource cost of Sweeping Wind by  Spirit.");
		map.put("mk-way-of-the-hundred-fists" ,"Increases Way of the Hundred Fists damage by %");
		map.put("mk-lashing-tail-kick" ,"Reduces resource cost of Lashing Tail Kick by  Spirit");
		map.put("mk-tempest-rush" ,"Increases Critical Hit Chance of Tempest Rush by %");
		map.put("mk-wave-of-light" ,"Increases Critical Hit Chance of Wave of Light by %");
		map.put("mana-regen" ,"Increases Mana Regeneration by  per Second");
		map.put("mana-max" ,"Maximum Mana");
		map.put("wd-firebomb" ,"Reduces resource cost of Firebomb by  Mana");
		map.put("wd-haunt" ,"Increases Haunt Damage by %");
		map.put("wd-acid-cloud" ,"Increases Critical Hit Chance of Acid Cloud by %");
		map.put("wd-firebats" ,"Reduces resource cost of Firebats by  Mana");
		map.put("wd-zombie-dogs" ,"Reduces cooldown of Summon Zombie Dogs by  Seconds");
		map.put("wd-plague-of-toads" ,"Increases Plague of Toads damage by %");
		map.put("wd-poison-darts" ,"Increaeses Poison Darts damage by %");
		map.put("wd-spirit-barrage" ,"Increases Spirit Barrage damage by %");
		map.put("wd-wall-of-zombies" ,"Reduces cooldown of Wall of Zombies by  Seconds");
		map.put("wd-zombie-charger" ,"Reduces resource cost of Zombie Charger by  Mana");
		map.put("wd-gargantuan" ,"Reduces cooldown of Gargantuan by  seconds");
		map.put("ap-on-crit" ,"Critical Hits grant  Arcane Power");
		map.put("ap-max" ,"Maximum Arcane Power");
		map.put("ap-regen" ,"Increases Arcane Power regeneration by  per second.");
		map.put("wz-arcane-torrent" ,"Reduces resource cost of Arcane Torrent by  Arcane Power");
		map.put("wz-disintegrate" ,"Reduces resource cost of Disintegrate by  Arcane Power");
		map.put("wz-electrocute" ,"Increases Electrocute damage by %");
		map.put("wz-explosive-blast" ,"Increases Critical Hit Chance of Explosive Blast by %");
		map.put("wz-hydra" ,"Reduces resource cost of Hydra by  Arcane Power");
		map.put("wz-ray-of-frost" ,"Increases Critical Hit Chance of Ray of Frost by %");
		map.put("wz-energy-twister" ,"Increases Critical Hit Chance of Energy Twister by %");
		map.put("wz-magic-missle" ,"Increases Magic Missle damage by %");
		map.put("wz-arcane-orb" ,"Increases Critical Hit Chance of Arcane Orb by %");
		map.put("wz-blizzard" ,"Increases duration of Blizzard by  Seconds");
		map.put("wz-meteor" ,"Reduces resource cost of Meteor by  Arcane Power");
		map.put("wz-shock-pulse" ,"Increases Shock Pulse damage by %");
		map.put("wz-spectral-blade" ,"Increases Spectral Blade damage by %");
		map.put("pig-sticker" ,"Squeal!");
		map.put("leg-blood-magic-blade" ,"Blood oozes from you.");
		map.put("leg-wizardspike" ,"% chance to hurl a frozen orb when attacking.");
		map.put("leg-the-gidbinn" ,"Chance to summon a Fetish when attacking.");
		map.put("leg-last-breath" ,"Slain enemies rest in pieces.");
		map.put("leg-skycutter" ,"Chance to summon angelic assistance when attacking.");
		map.put("leg-sever" ,"Slain enemies rest in pieces.");
		map.put("leg-azurewrath" ,"This weapon will forcefully repel undead enemies.");
		map.put("leg-scourge" ,"20% chance to explode with demonic fury when attacking.");
		map.put("leg-maximus" ,"Chance to summon a Demonic Slave when attacking.");
		map.put("leg-genzaniku" ,"Chance to summon a ghostly Fallen Champion when attacking.");
		map.put("leg-the-butchers-sickle" ,"20% chance to drag enemies to you when attacking.");
		map.put("leg-the-burning-axe-of-sankis" ,"Chance to fight through the pain when enemies hit you.");
		map.put("leg-sky-splitter" ,"10% chance to Smite enemies when you hit them.");
		map.put("leg-butchers-carver" ,"The Butcher still inhabits his carver.");
		map.put("leg-fire-brand" ,"25% chance to cast a fireball when attacking.");
		map.put("leg-odyn-son" ,"20% chance to Chain Lightning enemies when you hit them.");
		map.put("leg-earthshatter" ,"20% chance to cause the ground to shudder when attacking.");
		map.put("leg-boneshatter" ,"Slain enemies rest in pieces.");
		map.put("leg-cataclysm" ,"25% chance to sunder the ground your enemies walk on when you attack.");
		map.put("leg-schaeferss-hammer" ,"25% chance to be protected by Lightning when you are hit.");
		map.put("leg-vigilance" ,"Chance to cast Inner Sanctuary when you are hit.");
		map.put("leg-the-ravens-wing" ,"Ravens flock to your side.");
		map.put("leg-cluckeye" ,"25% chance to cluck when attacking.");
		map.put("leg-demon-machine" ,"35% chance to shoot explosive bolts when attacking.");
		map.put("leg-buriza-do-kyanon" ,"40% chance for ranged projectiles to pierce enemies.");
		map.put("leg-pus-spitter" ,"25% chance to lob an acid blob when attacking.");
		map.put("leg-hellrack" ,"Chance to root enemies to the ground when you hit them.");
		map.put("leg-calamity" ,"20% chance to target enemies with Marked for Death when you hit them.");
		map.put("leg-fjord-cutter" ,"20% chance to be surrounded by a Chilling Aura when attacking.");
		map.put("leg-the-paddle" ,"Slap!");
		map.put("leg-flying-dragon" ,"Chance to double your attack speed when attacking.");
		map.put("leg-maloths-focus" ,"Enemies occasionally flee at the sight of this staff.");
		map.put("leg-the-tormentor" ,"Chance to charm enemies when you hit them.");
		map.put("leg-sloraks-madness" ,"This wand finds your death humorous.");
		map.put("leg-wall-of-bone" ,"20% chance to be protected by a shield of bones when you are hit.");
		map.put("leg-lidless-wall" ,"You have a chance to be shielded when hit by enemies.");
		map.put("leg-andariels-visage" ,"20% chance to cast a Poison Nova when you are hit.");
		map.put("leg-fire-walkers" ,"Burn the ground you walk on.");
		map.put("leg-goldskin" ,"Chance for enemies to drop gold when you hit them.");
		map.put("leg-pox-faulds" ,"These pants occasionally make you stink.");
		map.put("leg-death-watch-mantle" ,"15% chance to explode with knives when hit by enemies.");
		map.put("leg-the-grin-reaper" ,"Chance to summon horrific Mimics when attacking.");
		map.put("leg-storm-crow" ,"20% chance to cast a fiery ball when attacking.");
		map.put("leg-thunder-gods-vigor" ,"25% chance to cause Shock Pulse to erupt from your enemies when you hit them.");
		map.put("leg-moonlight-ward" ,"25% chance to be surrounded by balls of Arcane Power when attacking.");
		map.put("leg-puzzle-ring" ,"This ring sometimes calls forth a Treasure Goblin when you are hit.");
		map.put("leg-bul-kathoss-wedding-band" ,"You drain life from enemies around you.");
		map.put("leg-band-of-hollow-whispers" ,"This ring occasionally haunts nearby enemies.");
		map.put("leg-bul-kathoss-warrior-blood" ,"You occasionally Whirlwind furiously.");
		map.put("leg-shenlongs-relentless-assault" ,"Chance to hurl a ball of pure energy when attacking.");
		map.put("leg-manajumas-gory-fetch" ,"You are surrounded by a deadly Poison Cloud.");
		map.put("leg-litany-of-the-undaunted" ,"This ring sometimes summons a Skeleton when you attack.");
		map.put("leg-demons-flight" ,"Chance to reflect projectiles when you are hit by enemies.");
		map.put("leg-the-murlocket","Call forth a creature from the depths.");
	}
	
	
	private void initItemsAttributes()
	{
		itemAttributes.add("Armor_Bonus_Item");
		itemAttributes.add("Armor_Item_Percent" );
		itemAttributes.add("Attacks_Per_Second_Item_Bonus" );
		itemAttributes.add("Attacks_Per_Second_Item_Percent" );
		itemAttributes.add("Attacks_Per_Second_Percent" );
		itemAttributes.add("Block_Chance_Bonus_Item" );
		itemAttributes.add("Crit_Damage_Percent" );
		itemAttributes.add("Crit_Percent_Bonus_Capped" );
		itemAttributes.add("CrowdControl_Reduction" );
		itemAttributes.add("Damage_Bonus_Min#Physical" );
		itemAttributes.add("Damage_Delta#Physical" );
		itemAttributes.add("Damage_Min#Physical" );
		itemAttributes.add("Damage_Percent_Bonus_Vs_Elites" );
		itemAttributes.add("Damage_Percent_Reduction_From_Elites" );
		itemAttributes.add("Damage_Percent_Reduction_From_Melee" );
		itemAttributes.add("Damage_Percent_Reduction_From_Ranged" );
		itemAttributes.add("Damage_Type_Percent_Bonus#Arcane");
		itemAttributes.add("Damage_Type_Percent_Bonus#Lightning");
		itemAttributes.add("Damage_Type_Percent_Bonus#Cold");
		itemAttributes.add("Damage_Type_Percent_Bonus#Spirit");
		itemAttributes.add("Damage_Type_Percent_Bonus#Poison");
		itemAttributes.add("Damage_Type_Percent_Bonus#Physical");
		itemAttributes.add("Damage_Weapon_Delta#Arcane" );
		itemAttributes.add("Damage_Weapon_Delta#Lightning" );
		itemAttributes.add("Damage_Weapon_Delta#Cold" );
		itemAttributes.add("Damage_Weapon_Delta#Spirit" );
		itemAttributes.add("Damage_Weapon_Delta#Poison" );
		itemAttributes.add("Damage_Weapon_Delta#Physical" );
		itemAttributes.add("Damage_Weapon_Min#Arcane" );
		itemAttributes.add("Damage_Weapon_Min#Lightning" );
		itemAttributes.add("Damage_Weapon_Min#Cold" );
		itemAttributes.add("Damage_Weapon_Min#Spirit" );
		itemAttributes.add("Damage_Weapon_Min#Poison" );
		itemAttributes.add("Damage_Weapon_Min#Physical" );
		itemAttributes.add("Damage_Weapon_Percent_Bonus#Physical" );
		itemAttributes.add("Dexterity_Item" );
		itemAttributes.add("Experience_Bonus" );
		itemAttributes.add("Experience_Bonus_Percent" );
		itemAttributes.add("Gold_Find" );
		itemAttributes.add("Gold_PickUp_Radius" );
		itemAttributes.add("Hitpoints_Max_Bonus" );
		itemAttributes.add("Hitpoints_Max_Percent_Bonus_Item" );
		itemAttributes.add("Hitpoints_On_Hit" );
		itemAttributes.add("Hitpoints_On_Kill" );
		itemAttributes.add("Hitpoints_Regen_Per_Second" );
		itemAttributes.add("Intelligence_Item" );
		itemAttributes.add("Item_Indestructible" );
		itemAttributes.add("Item_Level_Requirement_Reduction" );
		itemAttributes.add("Magic_Find" );
		itemAttributes.add("Movement_Speed" );
		itemAttributes.add("On_Hit_Blind_Proc_Chance" );
		itemAttributes.add("On_Hit_Chill_Proc_Chance" );
		itemAttributes.add("On_Hit_Fear_Proc_Chance" );
		itemAttributes.add("On_Hit_Freeze_Proc_Chance" );
		itemAttributes.add("On_Hit_Immobilize_Proc_Chance" );
		itemAttributes.add("On_Hit_Knockback_Proc_Chance" );
		itemAttributes.add("On_Hit_Stun_Proc_Chance" );
		itemAttributes.add("Power_Damage_Percent_Bonus");
		itemAttributes.add("Resistance#Arcane" );
		itemAttributes.add("Resistance#Lightning" );
		itemAttributes.add("Resistance#Cold" );
		itemAttributes.add("Resistance#Spirit" );
		itemAttributes.add("Resistance#Poison" );
		itemAttributes.add("Resistance#Physical" );
		itemAttributes.add("Resistance_All" );
		itemAttributes.add("Resource_Max_Bonus#Arcanum" );
		itemAttributes.add("Resource_Max_Bonus#Discipline" );
		itemAttributes.add("Resource_Max_Bonus#Fury" );
		itemAttributes.add("Resource_Max_Bonus#Hatred" );
		itemAttributes.add("Resource_Max_Bonus#Mana" );
		itemAttributes.add("Resource_Max_Bonus#Spirit" );
		itemAttributes.add("Resource_On_Crit#ResType" );
		itemAttributes.add("Resource_On_Hit#ResType" );
		itemAttributes.add("Resource_On_Kill#ResType" );
		itemAttributes.add("Resource_Regen_Per_Second#Arcanum");
		itemAttributes.add("Resource_Regen_Per_Second#Discipline");
		itemAttributes.add("Resource_Regen_Per_Second#Fury");
		itemAttributes.add("Resource_Regen_Per_Second#Hatred");
		itemAttributes.add("Resource_Regen_Per_Second#Mana");
		itemAttributes.add("Resource_Regen_Per_Second#Spirit");
		itemAttributes.add("Sockets");
		itemAttributes.add("Spending_Resource_Heals_Percent#Arcanum");
		itemAttributes.add("Spending_Resource_Heals_Percent#Discipline");
		itemAttributes.add("Spending_Resource_Heals_Percent#Fury");
		itemAttributes.add("Spending_Resource_Heals_Percent#Hatred");
		itemAttributes.add("Spending_Resource_Heals_Percent#Mana");
		itemAttributes.add("Spending_Resource_Heals_Percent#Spirit");
		itemAttributes.add("Steal_Health_Percent");
		itemAttributes.add("Strength_Item" );
		itemAttributes.add("Thorns_Fixed#Physical");
		itemAttributes.add("Vitality_Item");
		itemAttributes.add("Weapon_On_Hit_Bleed_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Bleed_Proc_Damage_Base" );
		itemAttributes.add("Weapon_On_Hit_Bleed_Proc_Damage_Delta" );
		itemAttributes.add("Weapon_On_Hit_Blind_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Chill_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Fear_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Freeze_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Immobilize_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Knockback_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Slow_Proc_Chance" );
		itemAttributes.add("Weapon_On_Hit_Stun_Proc_Chance" );
//		itemAttributes.add("Power_Cooldown_Reduction#Character_Skill" );
//		itemAttributes.add("Power_Crit_Percent_Bonus#Character_Skill" );
//		itemAttributes.add("Power_Duration_Increase#Character_Skill" );
//		itemAttributes.add("Power_Resource_Reduction#Character_Skill" );

	}
	
	
	public String[] getAttributs()
	{
		List<String> l = new ArrayList();
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
		    l.add(entry.getValue());
		}
		Collections.sort(l);
		return l.toArray(new String[l.size()]);
	}

}
