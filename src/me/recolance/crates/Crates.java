package me.recolance.crates;

import me.recolance.crates.commands.CommandHandler;
import me.recolance.crates.controller.Controller;
import me.recolance.crates.controller.Events;
import me.recolance.crates.menu.MenuController;
import me.recolance.crates.util.Enchant;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Crates extends JavaPlugin{

	private static Plugin plugin;
	
	public void onEnable(){
		plugin = this;
		registration();
		Enchant.loadEnchantmentAliases();
		Controller.loadCrates();
	}
	
	public void onDisable(){
		plugin = null;
	}
	
	public static Plugin getPlugin(){
		return plugin;
	}
	
	public void registration(){
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new MenuController(), plugin);
		getServer().getPluginManager().registerEvents(new Events(), plugin);
		getCommand("pcrates").setExecutor(new CommandHandler());
	}
}
