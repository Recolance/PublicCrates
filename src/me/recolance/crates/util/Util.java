package me.recolance.crates.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

public class Util{

	@SuppressWarnings("deprecation")
	public static ItemStack getItemStack(String itemString){
		if(itemString == null || itemString.equals("")) return new ItemStack(Material.AIR);
		String[] splitItem = itemString.split(":");
		return new ItemStack(Material.getMaterial(Integer.valueOf(splitItem[0])), 1, Short.valueOf(splitItem.length > 1 ? Short.valueOf(splitItem[1]) : 0));
	}
	
	public static ItemStack setName(ItemStack item, String name){
		if(item == null) return null;
		if(name == null) name = "";
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack addlore(ItemStack item, String... lore){
		if(item == null) return null;
		if(lore == null || lore.length == 0) return item;
		ItemMeta meta = item.getItemMeta();
		List<String> setLore = new ArrayList<String>();
		if(meta.hasLore()) setLore = meta.getLore();
		for(String line : lore) setLore.add(ChatColor.translateAlternateColorCodes('&', line));
		meta.setLore(setLore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack addLore(ItemStack item, List<String> lore){
		for(String line : lore)addlore(item, line);
		return item;
	}
	
	public static ItemStack addEnchantment(ItemStack item, String enchantmentString){
		String[] enchantmentSplit = enchantmentString.split(":");
		if(enchantmentSplit.length != 2) return item;
		Enchantment enchantment = Enchant.getEnchantmentByName(enchantmentSplit[0]);
		if(enchantment == null){
			System.out.println(enchantmentSplit[0]);
				System.out.println("ITS FUCKING NULL");
			return item;
		}
		item.addUnsafeEnchantment(enchantment, Integer.valueOf(enchantmentSplit[1]));
		return item;
	}
	
	public static void message(Player player, String message){
		if(message == null) return;
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void consoleMessage(String message){
		if(message == null) return;
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static boolean stringIsNumerical(String string){
		Pattern pattern = Pattern.compile("[^0-9]");
		return pattern.matcher(string).find() ? false : true;
	}
}
