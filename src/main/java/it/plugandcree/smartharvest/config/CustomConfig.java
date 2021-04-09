package it.plugandcree.smartharvest.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig extends YamlConfiguration {
	public String getRawString(String path) {
		return ChatColor.translateAlternateColorCodes('&', super.getString(path));
	}
	
	public String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', super.getString("messages.prefix"));
	}
	
	@Override
	public String getString(String path) {
		return getPrefix() + getRawString(path);
	}
	
	public String noPerm() {
		return getString("messages.no-perm");
	}
}