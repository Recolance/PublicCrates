package me.recolance.crates.crate;

import org.bukkit.inventory.ItemStack;

public class Crate{

	private String name;
	private int id;
	private String permission;
	private ItemStack interactItem;
	private CratePrize[] prizes;
	
	public Crate(String name, int id, String permission, ItemStack interactItem, CratePrize[] prizes){
		this.name = name;
		this.id = id;
		this.interactItem = interactItem;
		this.prizes = prizes;
		if(permission == null || permission.equalsIgnoreCase("")) this.permission = null;
		else this.permission = permission;
	}
	
	public ItemStack getInteractItem(){
		return interactItem;
	}
	
	public void setInteractItem(ItemStack interactItem){
		this.interactItem = interactItem;
	}
	
	public CratePrize[] getPrizes(){
		return prizes;
	}
	
	public void setPrizes(CratePrize[] prizes){
		this.prizes = prizes;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getPermission(){
		return permission;
	}

	public void setPermission(String permission){
		this.permission = permission;
	}
}
