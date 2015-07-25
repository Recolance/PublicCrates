package me.recolance.crates.crate;

import org.bukkit.inventory.ItemStack;

public class CratePrize{

	private ItemStack displayItem;
	private double chance;
	private String name;
	private String[] winCommands;
	private Crate crate;
	
	public CratePrize(String name, ItemStack displayItem, double chance, String[] winCommands){
		this.name = name;
		this.displayItem = displayItem;;
		this.chance = chance;
		this.winCommands = winCommands;
	}

	public ItemStack getDisplayItem(){
		return displayItem;
	}

	public void setDisplayItem(ItemStack displayItem){
		this.displayItem = displayItem;
	}

	public double getChance(){
		return chance;
	}

	public void setChance(double chance){
		this.chance = chance;
	}

	public String[] getWinCommands(){
		return winCommands;
	}

	public void setWinCommands(String[] winCommands){
		this.winCommands = winCommands;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Crate getCrate(){
		return crate;
	}

	public void setCrate(Crate crate){
		this.crate = crate;
	}
}
