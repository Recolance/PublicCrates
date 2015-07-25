package me.recolance.crates.util;

import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;

public class Enchant{

	public static HashMap<String, Enchantment> enchantmentAliases = new HashMap<>();
	
	public static void loadEnchantmentAliases(){
		insertAliases(Enchantment.ARROW_DAMAGE, "power", "arrowpower", "arrowdamage", "arrow_damage");
		insertAliases(Enchantment.ARROW_FIRE, "flame", "arrowflame", "arrow_flame", "bowfire");
		insertAliases(Enchantment.ARROW_INFINITE, "infinity", "arrowinfinity", "arrow_infinite", "arrowinfinite");
		insertAliases(Enchantment.ARROW_KNOCKBACK, "punch", "arrowknockback", "arrow_knockback");
		insertAliases(Enchantment.DAMAGE_ALL, "sharpness", "damage_all", "damageall");
		insertAliases(Enchantment.DAMAGE_ARTHROPODS, "baneofarthropods", "bane", "bane of arthropods", "bane_of_arthropods", "damagearthropods", "damage_arthropods");
		insertAliases(Enchantment.DAMAGE_UNDEAD, "smite", "damageundead", "damage_undead");
		insertAliases(Enchantment.getByName("depth_strider"), "depthstrider", "strider", "depth_strider");
		insertAliases(Enchantment.DIG_SPEED, "efficiency", "dig_speed", "digspeed");
		insertAliases(Enchantment.FIRE_ASPECT, "fire", "fire_aspect", "fire aspect", "fireaspect");
		insertAliases(Enchantment.KNOCKBACK, "knockback");
		insertAliases(Enchantment.LOOT_BONUS_BLOCKS, "fortune", "loot_bonus_blocks");
		insertAliases(Enchantment.LOOT_BONUS_MOBS, "looting", "loot", "loot_bonus_mobs");
		insertAliases(Enchantment.LUCK, "luck", "luckofthesea", "luck of the sea", "luck_of_the_sea");
		insertAliases(Enchantment.LURE, "lure");
		insertAliases(Enchantment.OXYGEN, "respiration", "resperation", "oxygen");
		insertAliases(Enchantment.PROTECTION_ENVIRONMENTAL, "prot", "protection", "protection_environmental", "protectionenvironmental");
		insertAliases(Enchantment.PROTECTION_EXPLOSIONS, "blastprotection", "blast protection", "blastprot", "blast_protection", "blast");
		insertAliases(Enchantment.PROTECTION_FALL, "feather", "falling", "featherfalling", "feather falling", "feather_falling", "protection_fall");
		insertAliases(Enchantment.PROTECTION_FIRE, "fireprotection", "fire protection", "fireprot", "fire_protection", "protection_fire");
		insertAliases(Enchantment.PROTECTION_PROJECTILE, "projectileprotection", "projectile protection", "projectile_protection", "projectileprot", "protection_projectile");
		insertAliases(Enchantment.SILK_TOUCH, "silk", "silktouch", "silk touch", "silk_touch");
		insertAliases(Enchantment.THORNS, "thorns", "thorn");
		insertAliases(Enchantment.WATER_WORKER, "aquaaffinity", "affinity", "water_worker", "waterworker");
	}
	
	private static void insertAliases(Enchantment enchantment, String... aliases){
		for(String string : aliases){
			enchantmentAliases.put(string, enchantment);
		}
	}
	
	public static Enchantment getEnchantmentByName(String name){
		return enchantmentAliases.get(name.toLowerCase());
	}
}
