package me.recolance.crates.controller;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.recolance.crates.Crates;
import me.recolance.crates.crate.Crate;
import me.recolance.crates.crate.CratePrize;
import me.recolance.crates.data.DataHolder;
import me.recolance.crates.util.Util;

public class Controller{

	private static FileConfiguration config = Crates.getPlugin().getConfig();
	
	public static void loadCrates(){
		for(String crateSection : config.getConfigurationSection("crates").getKeys(false)){
			String crateName = config.getString("crates." + crateSection + ".name");
			int crateId = config.getInt("crates." + crateSection + ".id");
			String cratePermission = config.getString("crates." + crateSection + ".permission");
			ItemStack interactionItem = Util.getItemStack(config.getString("crates." + crateSection + ".interactItem.itemId"));
			Util.setName(interactionItem, config.getString("crates." + crateSection + ".interactItem.name"));
			Util.addLore(interactionItem, config.getStringList("crates." + crateSection + ".interactItem.lore"));
			List<CratePrize> cratePrizes = new ArrayList<>();
			for(String prizeSection : config.getConfigurationSection("crates." + crateSection + ".prizes").getKeys(false)){
				String displaySection = "crates." + crateSection + ".prizes." + prizeSection + ".displayItem";
				String prizeSectionTwo = "crates." + crateSection + ".prizes." + prizeSection;
				ItemStack displayItem = Util.getItemStack(config.getString(displaySection + ".itemId"));
				displayItem.setAmount(config.getInt(displaySection + ".amount"));
				Util.setName(displayItem, config.getString(displaySection + ".name"));
				Util.addLore(displayItem, config.getStringList(displaySection + ".lore"));
				for(String enchantmentString : config.getStringList(displaySection + ".enchants")) displayItem = Util.addEnchantment(displayItem, enchantmentString);
				double chance = config.getDouble(prizeSectionTwo + ".chance");
				String prizeName = config.getString(prizeSectionTwo + ".prizeName");
				List<String> commands = config.getStringList(prizeSectionTwo + ".commands");
				cratePrizes.add(new CratePrize(prizeName, displayItem, chance, commands.toArray(new String[commands.size()])));
			}
			Crate crate = new Crate(crateName, crateId, cratePermission, interactionItem, cratePrizes.toArray(new CratePrize[cratePrizes.size()]));
			for(CratePrize prize : cratePrizes) prize.setCrate(crate);
			DataHolder.loadedCrates.add(crate);
			System.out.println("LOADED " + DataHolder.loadedCrates.size());
		}
	}
	
	public static void reloadPlugin(){
		Crates.getPlugin().saveConfig();
		Crates.getPlugin().reloadConfig();
		new BukkitRunnable(){
			@Override
			public void run(){
				DataHolder.loadedCrates.clear();
				Controller.loadCrates();
			}
		}.runTaskLater(Crates.getPlugin(), 20);
	}
}
