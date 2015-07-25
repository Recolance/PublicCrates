package me.recolance.crates.crate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.recolance.crates.Crates;

public enum Message{

	INITIAL_SPIN("initialSpin"),
	WON_PRIZE("wonPrize"),
	NO_PERMISSION("noPermission"),
	NO_PERMISSION_COMMAND("noPermissionCommand");
	
	
	private static FileConfiguration config = Crates.getPlugin().getConfig();
	private String configSection;
	
	Message(String configSection){
		this.configSection = configSection;
	}
	
	public String getConfigSection(){
		return this.configSection;
	}
	
	public String getMessage(){
		return config.getString("messages." + getConfigSection());
	}
	
	public static String setMessage(String message, Crate crate){
		if(message == null) return message;
		String send = message.replace("%cratename%", crate.getName());
		return send;
	}
	
	public static String setMessage(String message, Player player){
		if(message == null) return message;
		String send = message.replace("%player%", player.getName());
		return send;
	}
	
	public static String setMessage(String message, CratePrize cratePrize){
		if(message == null) return message;
		String send = message.replace("%prizename%", cratePrize.getName());
		send = send.replace("%prizechance%", String.valueOf(cratePrize.getChance()));
		return send;
	}
}
