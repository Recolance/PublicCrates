package me.recolance.crates.crate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.recolance.crates.Crates;
import me.recolance.crates.menu.MenuHelper;
import me.recolance.crates.menu.MenuType;
import me.recolance.crates.util.PlayerFirework;
import me.recolance.crates.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CrateSpin{

	private Inventory inventory;
	private Inventory endInventory;
	private Player player;
	private Crate crate;
	private HashMap<Integer, CratePrize> prizeHolder = new HashMap<>();
	
	public CrateSpin(Player player, Crate crate){
		this.inventory = Bukkit.createInventory(null, 27, "Spinning Crate Prizes...");
		setupSpinInventory();
		this.endInventory = Bukkit.createInventory(null, 45, "You've Won!");
		this.player = player;
		this.crate = crate;
		MenuHelper.currentlyViewing.put(player.getUniqueId(), MenuType.SPIN);
		player.openInventory(inventory);
		String spinMessage = Message.INITIAL_SPIN.getMessage();
		spinMessage = Message.setMessage(spinMessage, crate);
		spinMessage = Message.setMessage(spinMessage, this.player);
		Util.message(player, spinMessage);
		spin(1);
		new PlayerFirework(Color.ORANGE, Color.RED, Color.WHITE, Color.LIME, Color.AQUA).send(player);
	}
	
	public void spin(final int tickTime){
		if(tickTime == 0){
			new BukkitRunnable(){
				@Override
				public void run(){
					CratePrize prize = prizeHolder.get(Integer.valueOf(13));
					player.openInventory(endInventory);
					MenuHelper.currentlyViewing.put(player.getUniqueId(), MenuType.PRIZE_VIEW);
					runWonPrizeCosmetic(prize);
					givePrize(prize);
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				}
			}.runTaskLater(Crates.getPlugin(), 40);
			return;
		}
		new BukkitRunnable(){
			int i = 0;
			int x = getOftenTicks(tickTime);
			public void run(){
				if(i == x){
					this.cancel();
					spin(getNextTick(tickTime));
				}
				updatePrizes();
				player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
				i++;
			}
		}.runTaskTimer(Crates.getPlugin(), 0, tickTime);
	}
	
	public void runWonPrizeCosmetic(final CratePrize prize){
		new BukkitRunnable(){	
			int i = 1;
			@Override
			public void run(){
				boolean nobodyViewing = endInventory.getViewers().size() == 0;
				if(nobodyViewing){
					this.cancel();
					return;
				}
				setEndInventory(i, prize.getDisplayItem());
				i++;
				if(i == 7) i = 1;
			}
		}.runTaskTimer(Crates.getPlugin(), 0, 5);
	}
	
	public void setEndInventory(int stage, ItemStack middleItem){
		Color[] colors = getColorArray(stage);
		int i = 1;
		for(Color color : colors){
			for(int x = 0; x < getStageSlots(i).length; x++) endInventory.setItem(getStageSlots(i)[x], getColorItemStack(color));
			i++;
		}
		endInventory.setItem(22, middleItem);
	}
	
	public Color[] getColorArray(int stage){
		switch(stage){
		case 1: return new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE};
		case 2: return new Color[]{Color.PURPLE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
		case 3: return new Color[]{Color.BLUE, Color.PURPLE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN};
		case 4: return new Color[]{Color.GREEN, Color.BLUE, Color.PURPLE, Color.RED, Color.ORANGE, Color.YELLOW};
		case 5: return new Color[]{Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.RED, Color.ORANGE};
		case 6: return new Color[]{Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.RED};
		default: return new Color[]{};
		}
	}
	
	public int[] getStageSlots(int section){
		switch(section){
		case 1: return new int[]{0,8,36,44};
		case 2: return new int[]{1,9,7,17,35,43,27,37};
		case 3: return new int[]{2,10,18,28,38,6,16,26,34,42};
		case 4: return new int[]{5,15,25,33,41,3,11,19,29,39};
		case 5: return new int[]{4,14,24,32,40,30,20,12};
		case 6: return new int[]{23,13,21,31};
		default: return new int[]{};
		}
	}
	
	public ItemStack getColorItemStack(Color color){
		short s = 0;
		if(color == Color.RED) s = 14;
		else if(color == Color.ORANGE) s = 1;
		else if(color == Color.YELLOW) s = 4;
		else if(color == Color.GREEN) s = 5;
		else if(color == Color.BLUE) s = 11;
		else if(color == Color.PURPLE) s = 10;
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, s);
		Util.setName(item, " ");
		return item;
	}
	
	public int getNextTick(int currentTick){
		switch(currentTick){
		case 1: return 3;
		case 3: return 6;
		case 6: return 9;
		case 9: return 12;
		case 12: return 15;
		case 15: return 0;
		}
		return 0;
	}
	
	public int getOftenTicks(int tickTime){
		switch(tickTime){
		case 1:  return 25;
		case 3:  return 10;
		case 6: return 5;
		case 9: return 3;
		case 12: return 2;
		case 15: return 1;
		}
		return 1;
	}
	
	private void setupSpinInventory(){
		int[] greens = new int[]{4,22};
		int[] yellows = new int[]{3,5,21,23};
		int[] oranges = new int[]{2,6,20,24};
		int[] reds = new int[]{0,1,7,8,18,19,25,26};
		ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
		Util.setName(green, " ");
		ItemStack yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
		Util.setName(yellow, " ");
		ItemStack orange = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1);
		Util.setName(orange, " ");
		ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
		Util.setName(red, " ");
		for(int i = 0; i < greens.length; i++) inventory.setItem(greens[i], new ItemStack(green));
		for(int i = 0; i < yellows.length; i++) inventory.setItem(yellows[i], new ItemStack(yellow));
		for(int i = 0; i < oranges.length; i++) inventory.setItem(oranges[i], new ItemStack(orange));
		for(int i = 0; i < reds.length; i++) inventory.setItem(reds[i], new ItemStack(red));
	}
	
	
	
	public void updatePrizes(){
		int[] slots = new int[]{9,10,11,12,13,14,15,16,17};
		CratePrize newPrize = getRandomCratePrize();
		for(int i = 0; i < slots.length; i++){
			if(i - 1 < 0) continue;
			ItemStack prizeItem = inventory.getItem(slots[i]);
			if(prizeItem == null) continue;
			inventory.setItem(slots[i - 1], prizeItem);
			prizeHolder.put(Integer.valueOf(slots[i - 1]), prizeHolder.get(Integer.valueOf(slots[i])));
		}
		inventory.setItem(17, newPrize.getDisplayItem());
		prizeHolder.put(Integer.valueOf(17), newPrize);
	}
	
	private CratePrize getRandomCratePrize(){
		List<CratePrize> lootTable = new ArrayList<>();
		Random random = new Random();
		for(CratePrize prize : crate.getPrizes()){
			double randomDouble = random.nextDouble() * 100;
			if(randomDouble <= prize.getChance()) lootTable.add(prize);
		}
		if(lootTable.isEmpty()) return getRandomCratePrize();
		return lootTable.get(random.nextInt(lootTable.size()));
	}
	
	private void givePrize(CratePrize prize){
		for(String command : prize.getWinCommands()){
			String execute = command.replace("%player%", player.getName());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), execute);
		}
		String wonMessage = Message.WON_PRIZE.getMessage();
		wonMessage = Message.setMessage(wonMessage, prize);
		wonMessage = Message.setMessage(wonMessage, this.crate);
		wonMessage = Message.setMessage(wonMessage, this.player);
		Util.message(player, wonMessage);
	}
}
