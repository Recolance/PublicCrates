package me.recolance.crates.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.recolance.crates.crate.Crate;
import me.recolance.crates.data.DataHolder;

public class CratesUtil{

	public static Crate getCrateFromId(int id){
		for(Crate crate : DataHolder.loadedCrates){
			if(crate.getId() == id) return crate;
		}
		return null;
	}
	
	public static Crate getCrateFromItem(ItemStack item){
		for(Crate crate : DataHolder.loadedCrates){
			if(crate.getInteractItem().isSimilar(item)) return crate;
		}
		return null;
	}
	
	public static void removeCrate(Player player, ItemStack item){
		if(item.getAmount() > 1) item.setAmount(item.getAmount() - 1);
		else player.getInventory().remove(item);
		player.updateInventory();
	}
}
