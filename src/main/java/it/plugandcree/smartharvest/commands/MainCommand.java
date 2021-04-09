package it.plugandcree.smartharvest.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.spigot.libraries.commands.Command;

import net.md_5.bungee.api.ChatColor;

public class MainCommand extends Command {

	public MainCommand() {
		super("smartharvest");
		
		registerSubcommand(new Help());
		registerSubcommand(new Reload());
	}

	@Override
	public boolean execute(CommandSender sender, org.bukkit.command.Command cmd, List<String> args) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lSMART HARVEST &7plugin made by &aPlug_And_Cree"));
		if(sender.hasPermission("smartharvest.help"))
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Type &a/smartharvest help &7for more info"));
		return false;
	}
	
}
