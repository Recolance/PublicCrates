package me.recolance.crates.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MenuController implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent event){
		Player player = (Player)event.getWhoClicked();
		if(!MenuHelper.currentlyViewing.containsKey(player.getUniqueId())) return;
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event){
		MenuHelper.currentlyViewing.remove(event.getPlayer().getUniqueId());
	}
}
