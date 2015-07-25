package me.recolance.crates.controller;

import me.recolance.crates.crate.Crate;
import me.recolance.crates.crate.CrateSpin;
import me.recolance.crates.crate.Message;
import me.recolance.crates.menu.MenuRenderer;
import me.recolance.crates.util.CratesUtil;
import me.recolance.crates.util.Util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getItem() == null || event.getItem().getType() == Material.AIR) return;
		Action action = event.getAction();
		ItemStack item = event.getItem();
		Player player = event.getPlayer();
		
		switch(action){
		case LEFT_CLICK_AIR:
		case LEFT_CLICK_BLOCK:
			Crate crate = CratesUtil.getCrateFromItem(item);
			if(crate == null) return;
			event.setCancelled(true);
			MenuRenderer.renderPrizes(player, crate);
			break;
			
		case RIGHT_CLICK_AIR:
		case RIGHT_CLICK_BLOCK:
			Crate crate2 = CratesUtil.getCrateFromItem(item);
			if(crate2 == null) return;
			event.setCancelled(true);
			if(crate2.getPermission() != null && !player.hasPermission(crate2.getPermission())){
				String noPermissionMessage = Message.NO_PERMISSION.getMessage();
				noPermissionMessage = Message.setMessage(noPermissionMessage, crate2);
				noPermissionMessage = Message.setMessage(noPermissionMessage, player);
				Util.message(player, noPermissionMessage);
				return;
			}
			new CrateSpin(player, crate2);
			CratesUtil.removeCrate(player, item);
			break;
			
		default: return;
		}
	}
}
