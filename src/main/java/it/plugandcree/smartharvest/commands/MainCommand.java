package it.plugandcree.smartharvest.commands;

import org.bukkit.command.CommandSender;
import org.spigot.commons.commands.Command;
import org.spigot.commons.commands.ExecutionContext;

import net.md_5.bungee.api.ChatColor;

public class MainCommand extends Command {

	public MainCommand() {
		super("smartharvest");
		
		registerSubcommand(new Help());
		registerSubcommand(new Reload());
	}

	@Override
	public boolean execute(CommandSender sender, ExecutionContext context) {
		if(!context.isLastCommand())
			return false;
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lSMART HARVEST &7plugin made by &aPlug_And_Cree"));
		if(sender.hasPermission("smartharvest.help"))
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Type &a/smartharvest help &7for more info"));
		
		return true;
	}
	
}
