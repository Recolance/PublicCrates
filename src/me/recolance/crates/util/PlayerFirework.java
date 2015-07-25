package me.recolance.crates.util;

import me.recolance.crates.Crates;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerFirework{

private Color[] colors;
	
	public PlayerFirework(Color... colors){
		this.colors = colors;
	}
	
	public void send(Location loc){
		final Firework firework = (Firework)loc.getWorld().spawn(loc, Firework.class);
		FireworkEffect fireworkEffect = FireworkEffect.builder().trail(true).flicker(true).withColor(this.colors).build();
		FireworkMeta fireworkMeta = firework.getFireworkMeta();
		fireworkMeta.addEffect(fireworkEffect);
		fireworkMeta.setPower(0);
		firework.setFireworkMeta(fireworkMeta);
		new BukkitRunnable(){
			@Override
			public void run(){
				firework.detonate();
			}
		}.runTaskLater(Crates.getPlugin(), 2L);
	}
	
	public void send(Player player){
		send(player.getLocation());
	}
}
