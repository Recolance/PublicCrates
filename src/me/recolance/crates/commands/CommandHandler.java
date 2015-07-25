package me.recolance.crates.commands;

import me.recolance.crates.controller.Controller;
import me.recolance.crates.crate.Crate;
import me.recolance.crates.crate.Message;
import me.recolance.crates.data.DataHolder;
import me.recolance.crates.util.CratesUtil;
import me.recolance.crates.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandHandler implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(!command.getName().equalsIgnoreCase("pcrates")) return false;
		boolean isPlayer = sender instanceof Player;
		if(isPlayer && !((Player)sender).hasPermission("pcrates.admin")){
			String noPermMessage = Message.NO_PERMISSION_COMMAND.getMessage();
			noPermMessage = Message.setMessage(noPermMessage, (Player)sender);
			Util.message((Player)sender,  noPermMessage);
			return true;
		}
		int length = args.length;
		
		if(length == 0) return handleCratesNoArgs(sender);
		switch(args[0].toLowerCase()){
		case "list":
			if(length != 1) return handleCrateListNoArgs(sender);
			else return handleCratesList(sender);
		
		case "give":
			if(length != 4) return handleCrateGiveNoArgs(sender);
			else return handleCrateGive(sender, args);
			
		case "reload":
			if(length != 1) return handleCrateReloadNoArgs(sender);
			else return handleCrateReload(sender);
		}
		return false;
	}
	
	public static boolean handleCratesNoArgs(CommandSender sender){
		if(sender instanceof Player){
			Player player = (Player)sender;
			Util.message(player, "&6##########################");
			Util.message(player, "&6/pcrates give <player> <crateID> <amount>");
			Util.message(player, "&6/pcrates reload");
			Util.message(player, "&6##########################");
		}else{
			Util.consoleMessage("&6##########################");
			Util.consoleMessage("&6/pcrates give <player> <crateID> <amount>");
			Util.consoleMessage("&6/pcrates reload");
			Util.consoleMessage("&6##########################");
		}
		return true;
	}
	
	public static boolean handleCrateGiveNoArgs(CommandSender sender){
		if(sender instanceof Player){
			Player player = (Player)sender;
			Util.message(player, "&6/pcrates give <player> <crateID> <amount>");
		}else{
			Util.consoleMessage("&6/pcrates give <player> <crateID> <amount>");
		}
		return true;
	}
	
	public static boolean handleCrateReloadNoArgs(CommandSender sender){
		if(sender instanceof Player){
			Player player = (Player)sender;
			Util.message(player, "&6/pcrates reload");
		}else{
			Util.consoleMessage("&6/pcrates reload");
		}
		return true;
	}
	
	public static boolean handleCrateListNoArgs(CommandSender sender){
		if(sender instanceof Player){
			Player player = (Player)sender;
			Util.message(player, "&6/pcrates list");
		}else{
			Util.consoleMessage("&6/pcrates list");
		}
		return true;
	}
	
	public static boolean handleCrateGive(CommandSender sender, String[] args){
		boolean isPlayer = sender instanceof Player;
		if(Bukkit.getPlayer(args[1]) == null || !Bukkit.getPlayer(args[1]).isOnline()){
			if(isPlayer) Util.message((Player)sender,  "&cThat player is not online.");
			else Util.consoleMessage("&cThat player is not online.");
		}else{
			Player player = Bukkit.getPlayer(args[1]);
			if(!Util.stringIsNumerical(args[2])){
				if(isPlayer) Util.message((Player)sender, "&cThat crate id is not numerical.");
				else Util.consoleMessage("&cThat crate id is not numerical.");
			}else{
				int id = Integer.parseInt(args[2]);
				if(!Util.stringIsNumerical(args[3])){
					if(isPlayer) Util.message((Player)sender, "&cThat amount is not numerical.");
					else Util.consoleMessage("&cThat amount is not numerical.");
				}else{
					int amount = Integer.parseInt(args[3]);
					Crate crate = CratesUtil.getCrateFromId(id);
					if(crate == null){
						if(isPlayer) Util.message((Player)sender, "&cA crate with the id " + id + " does not exist.");
						else Util.consoleMessage("&cA crate with the id " + id + " does not exist.");
					}else{
						ItemStack giveItem = new ItemStack(crate.getInteractItem());
						giveItem.setAmount(amount);
						player.getInventory().addItem(giveItem);
						if(isPlayer) Util.message((Player)sender, "&6" + player.getName() + " was given " + amount + " of " + crate.getName() + " crate!");
						else Util.consoleMessage("&6" + player.getName() + " was given " + amount + " of " + crate.getName() + " crate!");
					}
				}
			}
		}
		return true;
	}
	
	public static boolean handleCrateReload(CommandSender sender){
		if(sender instanceof Player) Util.message((Player)sender, "&6Config reloaded...");
		else Util.consoleMessage("&6Config reloaded...");
		Controller.reloadPlugin();
		return true;
	}
	
	public static boolean handleCratesList(CommandSender sender){
		if(sender instanceof Player) for(Crate crate : DataHolder.loadedCrates) Util.message((Player)sender, "&6" + crate.getId() + " " + crate.getName());
		else for(Crate crate : DataHolder.loadedCrates) Util.consoleMessage("&6" + crate.getId() + " " + crate.getName());
		return true;
	}

	
}
