package com.sdfteam.d3armory.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.armory.d3.beans.Attributs;

public class RawsAttributes  {
	
	List<Attributs> itemAttributes;
	Map<String, String> map;
	
	public RawsAttributes()
	{
		itemAttributes = new ArrayList<Attributs>();
		initMap();
	}
	
	private void initMap()
	{
		itemAttributes.add(new Attributs("" ,""));
		itemAttributes.add(new Attributs("Strength_Item" ,"+X Strength"));
		itemAttributes.add(new Attributs("Intelligence_Item" ,"+X Intelligence"));
		itemAttributes.add(new Attributs("Vitality_Item" ,"+X Vitality"));
		itemAttributes.add(new Attributs("Dexterity_Item" ,"+X Dexterity"));
		itemAttributes.add(new Attributs("Resistance_All" ,"+X Resistance to All Elements"));
		itemAttributes.add(new Attributs("Armor_Item" ,"+X Armor"));
		itemAttributes.add(new Attributs("Hitpoints_Max_Percent_Bonus_Item" ,"+X% Life"));
		itemAttributes.add(new Attributs("Hitpoints_Regen_Per_Second" ,"Regenerates X Life per Second"));
		itemAttributes.add(new Attributs("Block_Chance_Bonus_Item" ,"+X% Chance to Block"));
		itemAttributes.add(new Attributs("CrowdControl_Reduction_Hallowed" ,"Reduces the duration of control impairing effects by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Elites" ,"Reduces damage from elites by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Melee" ,"Reduces damage from melee attacks by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Range" ,"Reduces damage from ranged attacks by X%"));
		itemAttributes.add(new Attributs("Damage_Percent_Reduction_From_Cold" ,"Reduces damage from Cold attacks by X%."));
		itemAttributes.add(new Attributs("Resistance#Arcane" ,"X Arcane Resistance"));
		itemAttributes.add(new Attributs("Resistance#Cold" ,"X Cold Resistance"));
		itemAttributes.add(new Attributs("Resistance#Fire" ,"X Fire Resistance"));
		itemAttributes.add(new Attributs("Resistance#Lightning" ,"X Lightning Resistance"));
		itemAttributes.add(new Attributs("Resistance#Physical" ,"X Physical Resistance"));
		itemAttributes.add(new Attributs("Resistance#Poison" ,"X Poison Resistance"));
		itemAttributes.add(new Attributs("Thorns_Fixed#Physical" ,"Melee attackers take X damage per hit"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Percent" ,"Attack speed increased by X%"));
		itemAttributes.add(new Attributs("Attacks_Per_Second_Item_Bonus" ,"+X Attacks per Second"));
		itemAttributes.add(new Attributs("Crit_Percent_Bonus" ,"Critical Hit Chance increased by X%"));
		itemAttributes.add(new Attributs("Crit_Damage_Percent" ,"Critical Hit Damage increased by X%"));
		itemAttributes.add(new Attributs("Damage_Weapon_Percent_Bonus" ,"+X% Damage"));
		itemAttributes.add(new Attributs("Damage_Min" ,"+X Minimum Damage"));
		itemAttributes.add(new Attributs("Damage_Delta" ,"+X Maximum Damage"));
		itemAttributes.add(new Attributs("ruby-damage" ,"Minimum and + Maximum Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Arcane" ,"+X% to Arcane Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Cold" ,"+X% to Cold Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Fire" ,"+X% to Fire Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Holy" ,"+X% to Holy Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Lightning" ,"+X% to Lightning Damage"));
		itemAttributes.add(new Attributs("Damage_Type_Percent_Bonus#Poison" ,"+X% to Poison Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Arcane" ,"+X Arcane Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Cold" ,"+X Cold Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Fire" ,"+X Fire Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Holy" ,"+X Holy Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Lightning" ,"+X Lightning Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Poison" ,"+X Poison Damage"));
		itemAttributes.add(new Attributs("Damage_Weapon_Min#Physical" ,"+X Degats Min"));
		itemAttributes.add(new Attributs("Damage_Weapon_Delta#Physical" ,"+X Degats Max"));
		itemAttributes.add(new Attributs("elite-damage" ,"Increases Damage against Elites by X%"));
		itemAttributes.add(new Attributs("demon-damage" ,"+X% Damage to Demons"));
		itemAttributes.add(new Attributs("human-damage" ,"+X% Damage to Humans"));
		itemAttributes.add(new Attributs("plus-arcane-damage-skills" ,"Arcane skills deal X% more damage"));
		itemAttributes.add(new Attributs("plus-cold-damage-skills" ,"Cold skills deal X% more damage"));
		itemAttributes.add(new Attributs("plus-fire-damage-skills" ,"Fire skills deal X% more damage"));
		itemAttributes.add(new Attributs("plus-holy-damage-skills" ,"Holy skills deal X% more damage"));
		itemAttributes.add(new Attributs("plus-lightning-damage-skills" ,"Lightning skills deal % more damage"));
		itemAttributes.add(new Attributs("plus-poison-damage-skills" ,"Poison skills deal % more damage"));
		itemAttributes.add(new Attributs("chance-bleed" ,"X% chance to inflict Bleed for  damage over 5 seconds"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Blind_Proc_Chance" ,"X% chance to Blind on Hit"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Chill_Proc_Chance" ,"X% chance to Chill on Hit"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Fear_Proc_Chance" ,"X% chance to Fear on Hit"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Freeze_Proc_Chance" ,"X% chance to Freeze on Hit"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Immobilize_Proc_Chance" ,"X% chance to Immobilize on Hit"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Knockback_Proc_Chance" ,"X% chance to Knockback on Hit"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Slow_Proc_Chance" ,"X% chance to Slow on Hit"));
		itemAttributes.add(new Attributs("Weapond_On_Hit_Stun_Proc_Chance" ,"X% chance to Stun on Hit"));
		itemAttributes.add(new Attributs("chance-whirlwind" ,"Chance to occasionally Whirlwind furioulsy."));
		itemAttributes.add(new Attributs("chance-ball-energy" ,"Chance to hurt a ball of pure energy when attacking."));
		itemAttributes.add(new Attributs("chance-skeleton" ,"Summons a skeleton when attacked."));
		itemAttributes.add(new Attributs("chance-reflect-projectiles" ,"Chance to reflect projectiles when hit."));
		itemAttributes.add(new Attributs("effect-poison-cloud" ,"You are sourrounded by a deadly Posion Cloud."));
		itemAttributes.add(new Attributs("Movement_Scalar" ,"+X% Movement Speed"));
		itemAttributes.add(new Attributs("plus-pickup-radius" ,"Increases Gold and Health pickup by X yards"));
		itemAttributes.add(new Attributs("plus-experience" ,"Monster kills grant + experience"));
		itemAttributes.add(new Attributs("plus-experience-percent" ,"Increased Experience Rewarded per Kill by X%"));
		itemAttributes.add(new Attributs("plus-experience-bonus" ,"Increases Bonus Experience by X%"));
		itemAttributes.add(new Attributs("plus-gold-find" ,"+X% Extra Gold from Monsters"));
		itemAttributes.add(new Attributs("plus-magic-find" ,"X% Better Chance of finding Magic Items"));
		itemAttributes.add(new Attributs("health-globes" ,"Health Globes and Potions Grant +X Life"));
		itemAttributes.add(new Attributs("life-steal" ,"X % of Damage Dealt is Converted to Life"));
		itemAttributes.add(new Attributs("life-kill" ,"X Life after each Kill"));
		itemAttributes.add(new Attributs("life-hit" ,"Each hit adds + X Life"));
		itemAttributes.add(new Attributs("level-reduce" ,"Level Requirement reduced by "));
		itemAttributes.add(new Attributs("indestructible" ,"Ignores durability loss"));
		itemAttributes.add(new Attributs("Sockets" ,"Sockets"));
		itemAttributes.add(new Attributs("Durability_Max" ,"X Durabilitity MAX"));
		itemAttributes.add(new Attributs("Durability_Cur" ,"X Durabilitity CURRENT"));
		
	/*	itemAttributes.add(new Attributs("bb-bash" ,"Increases bash damage by X%"));
		itemAttributes.add(new Attributs("bb-cleave" ,"Increases cleave damage by X%"));
		itemAttributes.add(new Attributs("bb-frenzy" ,"Increases frenzy damage by X%"));
		itemAttributes.add(new Attributs("bb-rend" ,"Reduces resource cost of Rend by X Fury"));
		itemAttributes.add(new Attributs("bb-revenge" ,"Increases Critical Hit Chance of Revenge by X%"));
		itemAttributes.add(new Attributs("bb-weapon-throw" ,"Reduces resource cost of Weapon Throw by X Fury"));
		itemAttributes.add(new Attributs("bb-hammer-of-the-ancients" ,"Reduces resource cost of Hammer of the Ancients by X Fury"));
		itemAttributes.add(new Attributs("bb-whirlwind" ,"Increases Critical Hit Chance of Whirlwind by X%"));
		itemAttributes.add(new Attributs("bb-overpower" ,"Increases Critical Hit Chance of Overpower by X%"));
		itemAttributes.add(new Attributs("bb-seismic-slam" ,"Increases Critical Hit Chance of Seismic Slam by X%"));
		itemAttributes.add(new Attributs("bb-weapon-throw-dmg" ,"Increases Weapon Throw damage by X%"));
		itemAttributes.add(new Attributs("bb-ancient-spear-dmg" ,"Increases Ancient Spear damage by X%"));
		itemAttributes.add(new Attributs("fury-max" ,"+X Maximum Fury"));
		itemAttributes.add(new Attributs("hatred-regen" ,"Increases Hatred Regeneration by X per Second"));
		itemAttributes.add(new Attributs("max-discipline" ,"Maximum Discipline"));
		itemAttributes.add(new Attributs("dh-chakram" ,"Reduces resource cost of Chakram by X Hatred"));
		itemAttributes.add(new Attributs("dh-evasive-fire" ,"Increases Evasive Fire damage by X%"));
		itemAttributes.add(new Attributs("dh-grenades" ,"Increases Grenades Damage by X%"));
		itemAttributes.add(new Attributs("dh-impale" ,"Reduces resource cost of Impale by X Hatred"));
		itemAttributes.add(new Attributs("dh-spike-trap" ,"Increases Spike Trap damage by X%"));
		itemAttributes.add(new Attributs("dh-bola-shot" ,"Increases Bola Shot damage by X%"));
		itemAttributes.add(new Attributs("dh-elemental-arrow" ,"Increases Elemental Arrow damage by X%"));
		itemAttributes.add(new Attributs("dh-entangling-shot" ,"Increases Entangling Shot damage by X%"));
		itemAttributes.add(new Attributs("dh-hungering-arrow" ,"Increases Hungering Arrow damage by X%"));
		itemAttributes.add(new Attributs("dh-multishot" ,"Increases Critical Hit Chance of Multishot by X%"));
		itemAttributes.add(new Attributs("dh-rapid-fire" ,"Increases Critical Hit Chance of Rapid Fire by X%"));
		itemAttributes.add(new Attributs("dh-cluster-arrow" ,"Reduces resource cost of Cluster Arrow by X Hatred"));
		itemAttributes.add(new Attributs("dh-strafe" ,"Increases Critical Hit Chance of Strafe by X%"));
		itemAttributes.add(new Attributs("spirit-spent-life" ,"Gain X Life per Spirit Spent"));
		itemAttributes.add(new Attributs("spirit-regen" ,"Increases Spirit Regeneration by X per Second"));
		itemAttributes.add(new Attributs("mk-crippling-wave" ,"Increases Crippling Wave damage by X%"));
		itemAttributes.add(new Attributs("mk-cyclone-strike" ,"Reduces resource cost of Cyclone Strike by X Spirit"));
		itemAttributes.add(new Attributs("mk-deadly-reach" ,"Increases Deadly Reach damage by X%"));
		itemAttributes.add(new Attributs("mk-exploding-palm" ,"Increases Exploding Palm damage by X%"));
		itemAttributes.add(new Attributs("mk-fists-of-thunder" ,"Increases Fist of Thunder damage by X%"));
		itemAttributes.add(new Attributs("mk-sweeping-wind" ,"Increases Sweeping Wind damage by X%"));
		itemAttributes.add(new Attributs("mk-sweeping-wind-cost" ,"Reduces resource cost of Sweeping Wind by X Spirit."));
		itemAttributes.add(new Attributs("mk-way-of-the-hundred-fists" ,"Increases Way of the Hundred Fists damage by X%"));
		itemAttributes.add(new Attributs("mk-lashing-tail-kick" ,"Reduces resource cost of Lashing Tail Kick by X Spirit"));
		itemAttributes.add(new Attributs("mk-tempest-rush" ,"Increases Critical Hit Chance of Tempest Rush by X%"));
		itemAttributes.add(new Attributs("mk-wave-of-light" ,"Increases Critical Hit Chance of Wave of Light by X%"));
		itemAttributes.add(new Attributs("mana-regen" ,"Increases Mana Regeneration by X per Second"));
		itemAttributes.add(new Attributs("mana-max" ,"Maximum Mana"));
		itemAttributes.add(new Attributs("wd-firebomb" ,"Reduces resource cost of Firebomb by X Mana"));
		itemAttributes.add(new Attributs("wd-haunt" ,"Increases Haunt Damage by X%"));
		itemAttributes.add(new Attributs("wd-acid-cloud" ,"Increases Critical Hit Chance of Acid Cloud by X%"));
		itemAttributes.add(new Attributs("wd-firebats" ,"Reduces resource cost of Firebats by X Mana"));
		itemAttributes.add(new Attributs("wd-zombie-dogs" ,"Reduces cooldown of Summon Zombie Dogs by X Seconds"));
		itemAttributes.add(new Attributs("wd-plague-of-toads" ,"Increases Plague of Toads damage by X%"));
		itemAttributes.add(new Attributs("wd-poison-darts" ,"Increaeses Poison Darts damage by X%"));
		itemAttributes.add(new Attributs("wd-spirit-barrage" ,"Increases Spirit Barrage damage by X%"));
		itemAttributes.add(new Attributs("wd-wall-of-zombies" ,"Reduces cooldown of Wall of Zombies by X Seconds"));
		itemAttributes.add(new Attributs("wd-zombie-charger" ,"Reduces resource cost of Zombie Charger by X Mana"));
		itemAttributes.add(new Attributs("wd-gargantuan" ,"Reduces cooldown of Gargantuan by X seconds"));
		itemAttributes.add(new Attributs("ap-on-crit" ,"Critical Hits grant  Arcane Power"));
		itemAttributes.add(new Attributs("ap-max" ,"Maximum Arcane Power"));
		itemAttributes.add(new Attributs("ap-regen" ,"Increases Arcane Power regeneration by X per second."));
		itemAttributes.add(new Attributs("wz-arcane-torrent" ,"Reduces resource cost of Arcane Torrent by X Arcane Power"));
		itemAttributes.add(new Attributs("wz-disintegrate" ,"Reduces resource cost of Disintegrate by X Arcane Power"));
		itemAttributes.add(new Attributs("wz-electrocute" ,"Increases Electrocute damage by X%"));
		itemAttributes.add(new Attributs("wz-explosive-blast" ,"Increases Critical Hit Chance of Explosive Blast by X%"));
		itemAttributes.add(new Attributs("wz-hydra" ,"Reduces resource cost of Hydra by X Arcane Power"));
		itemAttributes.add(new Attributs("wz-ray-of-frost" ,"Increases Critical Hit Chance of Ray of Frost by X%"));
		itemAttributes.add(new Attributs("wz-energy-twister" ,"Increases Critical Hit Chance of Energy Twister by X%"));
		itemAttributes.add(new Attributs("wz-magic-missle" ,"Increases Magic Missle damage by X%"));
		itemAttributes.add(new Attributs("wz-arcane-orb" ,"Increases Critical Hit Chance of Arcane Orb by X%"));
		itemAttributes.add(new Attributs("wz-blizzard" ,"Increases duration of Blizzard by X Seconds"));
		itemAttributes.add(new Attributs("wz-meteor" ,"Reduces resource cost of Meteor by X Arcane Power"));
		itemAttributes.add(new Attributs("wz-shock-pulse" ,"Increases Shock Pulse damage by X%"));
		itemAttributes.add(new Attributs("wz-spectral-blade" ,"Increases Spectral Blade damage by X%"));
		itemAttributes.add(new Attributs("pig-sticker" ,"Squeal!"));
		itemAttributes.add(new Attributs("leg-blood-magic-blade" ,"Blood oozes from you."));
		itemAttributes.add(new Attributs("leg-wizardspike" ,"% chance to hurl a frozen orb when attacking."));
		itemAttributes.add(new Attributs("leg-the-gidbinn" ,"Chance to summon a Fetish when attacking."));
		itemAttributes.add(new Attributs("leg-last-breath" ,"Slain enemies rest in pieces."));
		itemAttributes.add(new Attributs("leg-skycutter" ,"Chance to summon angelic assistance when attacking."));
		itemAttributes.add(new Attributs("leg-sever" ,"Slain enemies rest in pieces."));
		itemAttributes.add(new Attributs("leg-azurewrath" ,"This weapon will forcefully repel undead enemies."));
		itemAttributes.add(new Attributs("leg-scourge" ,"20% chance to explode with demonic fury when attacking."));
		itemAttributes.add(new Attributs("leg-maximus" ,"Chance to summon a Demonic Slave when attacking."));
		itemAttributes.add(new Attributs("leg-genzaniku" ,"Chance to summon a ghostly Fallen Champion when attacking."));
		itemAttributes.add(new Attributs("leg-the-butchers-sickle" ,"20% chance to drag enemies to you when attacking."));
		itemAttributes.add(new Attributs("leg-the-burning-axe-of-sankis" ,"Chance to fight through the pain when enemies hit you."));
		itemAttributes.add(new Attributs("leg-sky-splitter" ,"10% chance to Smite enemies when you hit them."));
		itemAttributes.add(new Attributs("leg-butchers-carver" ,"The Butcher still inhabits his carver."));
		itemAttributes.add(new Attributs("leg-fire-brand" ,"25% chance to cast a fireball when attacking."));
		itemAttributes.add(new Attributs("leg-odyn-son" ,"20% chance to Chain Lightning enemies when you hit them."));
		itemAttributes.add(new Attributs("leg-earthshatter" ,"20% chance to cause the ground to shudder when attacking."));
		itemAttributes.add(new Attributs("leg-boneshatter" ,"Slain enemies rest in pieces."));
		itemAttributes.add(new Attributs("leg-cataclysm" ,"25% chance to sunder the ground your enemies walk on when you attack."));
		itemAttributes.add(new Attributs("leg-schaeferss-hammer" ,"25% chance to be protected by Lightning when you are hit."));
		itemAttributes.add(new Attributs("leg-vigilance" ,"Chance to cast Inner Sanctuary when you are hit."));
		itemAttributes.add(new Attributs("leg-the-ravens-wing" ,"Ravens flock to your side."));
		itemAttributes.add(new Attributs("leg-cluckeye" ,"25% chance to cluck when attacking."));
		itemAttributes.add(new Attributs("leg-demon-machine" ,"35% chance to shoot explosive bolts when attacking."));
		itemAttributes.add(new Attributs("leg-buriza-do-kyanon" ,"40% chance for ranged projectiles to pierce enemies."));
		itemAttributes.add(new Attributs("leg-pus-spitter" ,"25% chance to lob an acid blob when attacking."));
		itemAttributes.add(new Attributs("leg-hellrack" ,"Chance to root enemies to the ground when you hit them."));
		itemAttributes.add(new Attributs("leg-calamity" ,"20% chance to target enemies with Marked for Death when you hit them."));
		itemAttributes.add(new Attributs("leg-fjord-cutter" ,"20% chance to be surrounded by a Chilling Aura when attacking."));
		itemAttributes.add(new Attributs("leg-the-paddle" ,"Slap!"));
		itemAttributes.add(new Attributs("leg-flying-dragon" ,"Chance to double your attack speed when attacking."));
		itemAttributes.add(new Attributs("leg-maloths-focus" ,"Enemies occasionally flee at the sight of this staff."));
		itemAttributes.add(new Attributs("leg-the-tormentor" ,"Chance to charm enemies when you hit them."));
		itemAttributes.add(new Attributs("leg-sloraks-madness" ,"This wand finds your death humorous."));
		itemAttributes.add(new Attributs("leg-wall-of-bone" ,"20% chance to be protected by a shield of bones when you are hit."));
		itemAttributes.add(new Attributs("leg-lidless-wall" ,"You have a chance to be shielded when hit by enemies."));
		itemAttributes.add(new Attributs("leg-andariels-visage" ,"20% chance to cast a Poison Nova when you are hit."));
		itemAttributes.add(new Attributs("leg-fire-walkers" ,"Burn the ground you walk on."));
		itemAttributes.add(new Attributs("leg-goldskin" ,"Chance for enemies to drop gold when you hit them."));
		itemAttributes.add(new Attributs("leg-pox-faulds" ,"These pants occasionally make you stink."));
		itemAttributes.add(new Attributs("leg-death-watch-mantle" ,"15% chance to explode with knives when hit by enemies."));
		itemAttributes.add(new Attributs("leg-the-grin-reaper" ,"Chance to summon horrific Mimics when attacking."));
		itemAttributes.add(new Attributs("leg-storm-crow" ,"20% chance to cast a fiery ball when attacking."));
		itemAttributes.add(new Attributs("leg-thunder-gods-vigor" ,"25% chance to cause Shock Pulse to erupt from your enemies when you hit them."));
		itemAttributes.add(new Attributs("leg-moonlight-ward" ,"25% chance to be surrounded by balls of Arcane Power when attacking."));
		itemAttributes.add(new Attributs("leg-puzzle-ring" ,"This ring sometimes calls forth a Treasure Goblin when you are hit."));
		itemAttributes.add(new Attributs("leg-bul-kathoss-wedding-band" ,"You drain life from enemies around you."));
		itemAttributes.add(new Attributs("leg-band-of-hollow-whispers" ,"This ring occasionally haunts nearby enemies."));
		itemAttributes.add(new Attributs("leg-bul-kathoss-warrior-blood" ,"You occasionally Whirlwind furiously."));
		itemAttributes.add(new Attributs("leg-shenlongs-relentless-assault" ,"Chance to hurl a ball of pure energy when attacking."));
		itemAttributes.add(new Attributs("leg-manajumas-gory-fetch" ,"You are surrounded by a deadly Poison Cloud."));
		itemAttributes.add(new Attributs("leg-litany-of-the-undaunted" ,"This ring sometimes summons a Skeleton when you attack."));
		itemAttributes.add(new Attributs("leg-demons-flight" ,"Chance to reflect projectiles when you are hit by enemies."));
		itemAttributes.add(new Attributs("leg-the-murlocket","Call forth a creature from the depths."));*/
	}
	
//		itemAttributes.add("Armor_Bonus_Item");
//		itemAttributes.add("Armor_Item_Percent" );
//		itemAttributes.add("Attacks_Per_Second_Item_Bonus" );
//		itemAttributes.add("Attacks_Per_Second_Item_Percent" );
//		itemAttributes.add("Attacks_Per_Second_Percent" );
//		itemAttributes.add("Block_Chance_Bonus_Item" );
//		itemAttributes.add("Crit_Damage_Percent" );
//		itemAttributes.add("Crit_Percent_Bonus_Capped" );
//		itemAttributes.add("CrowdControl_Reduction" );
//		itemAttributes.add("Damage_Bonus_Min#Physical" );
//		itemAttributes.add("Damage_Delta#Physical" );
//		itemAttributes.add("Damage_Min#Physical" );
//		itemAttributes.add("Damage_Percent_Bonus_Vs_Elites" );
//		itemAttributes.add("Damage_Percent_Reduction_From_Elites" );
//		itemAttributes.add("Damage_Percent_Reduction_From_Melee" );
//		itemAttributes.add("Damage_Percent_Reduction_From_Ranged" );
//		itemAttributes.add("Damage_Type_Percent_Bonus#Arcane");
//		itemAttributes.add("Damage_Type_Percent_Bonus#Lightning");
//		itemAttributes.add("Damage_Type_Percent_Bonus#Cold");
//		itemAttributes.add("Damage_Type_Percent_Bonus#Spirit");
//		itemAttributes.add("Damage_Type_Percent_Bonus#Poison");
//		itemAttributes.add("Damage_Type_Percent_Bonus#Physical");
//		itemAttributes.add("Damage_Weapon_Delta#Arcane" );
//		itemAttributes.add("Damage_Weapon_Delta#Lightning" );
//		itemAttributes.add("Damage_Weapon_Delta#Cold" );
//		itemAttributes.add("Damage_Weapon_Delta#Spirit" );
//		itemAttributes.add("Damage_Weapon_Delta#Poison" );
//		itemAttributes.add("Damage_Weapon_Delta#Physical" );
//		itemAttributes.add("Damage_Weapon_Min#Arcane" );
//		itemAttributes.add("Damage_Weapon_Min#Lightning" );
//		itemAttributes.add("Damage_Weapon_Min#Cold" );
//		itemAttributes.add("Damage_Weapon_Min#Spirit" );
//		itemAttributes.add("Damage_Weapon_Min#Poison" );
//		itemAttributes.add("Damage_Weapon_Min#Physical" );
//		itemAttributes.add("Damage_Weapon_Percent_Bonus#Physical" );
//		itemAttributes.add("Dexterity_Item" );
//		itemAttributes.add("Experience_Bonus" );
//		itemAttributes.add("Experience_Bonus_Percent" );
//		itemAttributes.add("Gold_Find" );
//		itemAttributes.add("Gold_PickUp_Radius" );
//		itemAttributes.add("Hitpoints_Max_Bonus" );
//		itemAttributes.add("Hitpoints_Max_Percent_Bonus_Item" );
//		itemAttributes.add("Hitpoints_On_Hit" );
//		itemAttributes.add("Hitpoints_On_Kill" );
//		itemAttributes.add("Hitpoints_Regen_Per_Second" );
//		itemAttributes.add("Intelligence_Item" );
//		itemAttributes.add("Item_Indestructible" );
//		itemAttributes.add("Item_Level_Requirement_Reduction" );
//		itemAttributes.add("Magic_Find" );
//		itemAttributes.add("Movement_Speed" );
//		itemAttributes.add("On_Hit_Blind_Proc_Chance" );
//		itemAttributes.add("On_Hit_Chill_Proc_Chance" );
//		itemAttributes.add("On_Hit_Fear_Proc_Chance" );
//		itemAttributes.add("On_Hit_Freeze_Proc_Chance" );
//		itemAttributes.add("On_Hit_Immobilize_Proc_Chance" );
//		itemAttributes.add("On_Hit_Knockback_Proc_Chance" );
//		itemAttributes.add("On_Hit_Stun_Proc_Chance" );
//		itemAttributes.add("Power_Damage_Percent_Bonus");
//		itemAttributes.add("Resistance#Arcane" );
//		itemAttributes.add("Resistance#Lightning" );
//		itemAttributes.add("Resistance#Cold" );
//		itemAttributes.add("Resistance#Spirit" );
//		itemAttributes.add("Resistance#Poison" );
//		itemAttributes.add("Resistance#Physical" );
//		itemAttributes.add("Resistance_All" );
//		itemAttributes.add("Resource_Max_Bonus#Arcanum" );
//		itemAttributes.add("Resource_Max_Bonus#Discipline" );
//		itemAttributes.add("Resource_Max_Bonus#Fury" );
//		itemAttributes.add("Resource_Max_Bonus#Hatred" );
//		itemAttributes.add("Resource_Max_Bonus#Mana" );
//		itemAttributes.add("Resource_Max_Bonus#Spirit" );
//		itemAttributes.add("Resource_On_Crit#ResType" );
//		itemAttributes.add("Resource_On_Hit#ResType" );
//		itemAttributes.add("Resource_On_Kill#ResType" );
//		itemAttributes.add("Resource_Regen_Per_Second#Arcanum");
//		itemAttributes.add("Resource_Regen_Per_Second#Discipline");
//		itemAttributes.add("Resource_Regen_Per_Second#Fury");
//		itemAttributes.add("Resource_Regen_Per_Second#Hatred");
//		itemAttributes.add("Resource_Regen_Per_Second#Mana");
//		itemAttributes.add("Resource_Regen_Per_Second#Spirit");

//		itemAttributes.add("Spending_Resource_Heals_Percent#Arcanum");
//		itemAttributes.add("Spending_Resource_Heals_Percent#Discipline");
//		itemAttributes.add("Spending_Resource_Heals_Percent#Fury");
//		itemAttributes.add("Spending_Resource_Heals_Percent#Hatred");
//		itemAttributes.add("Spending_Resource_Heals_Percent#Mana");
//		itemAttributes.add("Spending_Resource_Heals_Percent#Spirit");
//		itemAttributes.add("Steal_Health_Percent");
//		itemAttributes.add("Strength_Item" );
//		itemAttributes.add("Thorns_Fixed#Physical");
//		itemAttributes.add("Vitality_Item");
//		itemAttributes.add("Weapon_On_Hit_Bleed_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Bleed_Proc_Damage_Base" );
//		itemAttributes.add("Weapon_On_Hit_Bleed_Proc_Damage_Delta" );
//		itemAttributes.add("Weapon_On_Hit_Blind_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Chill_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Fear_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Freeze_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Immobilize_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Knockback_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Slow_Proc_Chance" );
//		itemAttributes.add("Weapon_On_Hit_Stun_Proc_Chance" );
//		itemAttributes.add("Power_Cooldown_Reduction#Character_Skill" );
//		itemAttributes.add("Power_Crit_Percent_Bonus#Character_Skill" );
//		itemAttributes.add("Power_Duration_Increase#Character_Skill" );
//		itemAttributes.add("Power_Resource_Reduction#Character_Skill" );
	
	public Attributs[] getAttributs()
	{
		Collections.sort(itemAttributes);
		return itemAttributes.toArray(new Attributs[itemAttributes.size()]);
	}
	
	public Attributs getAttribut(String key)
	{
		for(Attributs a : itemAttributes)
		{
			if(a.getId().equalsIgnoreCase(key))
				return a;
		}
		return null;
	}

}
