package me.recolance.crates.menu;

import me.recolance.crates.crate.Crate;
import me.recolance.crates.crate.CratePrize;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuRenderer{

	public static void renderPrizes(Player player, Crate crate){
		MenuHelper.currentlyViewing.put(player.getUniqueId(), MenuType.CRATE_PRIZES);
		Inventory inventory = Bukkit.createInventory(null, 54, "Possible Crate Prizes");
		int i = 0;
		for(CratePrize prize : crate.getPrizes()){
			inventory.setItem(i, prize.getDisplayItem());
			i++;
		}
		player.openInventory(inventory);
	}
}
