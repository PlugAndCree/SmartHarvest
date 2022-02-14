package it.plugandcree.smartharvest.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.spigot.commons.commands.ExecutionContext;
import org.spigot.commons.commands.builtin.PermissibleCommand;

import it.plugandcree.smartharvest.SmartHarvest;
import it.plugandcree.smartharvest.config.CustomConfig;

public class Help extends PermissibleCommand {

	public Help() {
		super("help", "smartharvest.help", SmartHarvest.getInstance().getLangConfig().noPerm());
	}

	@Override
	public boolean execute(CommandSender sender, ExecutionContext context) {
		CustomConfig lang = SmartHarvest.getInstance().getLangConfig();
		
		sender.sendMessage(lang.getString("messages.help"));
		sender.sendMessage(ChatColor.GRAY + "Version: " + SmartHarvest.getInstance().getDescription().getVersion());
		sender.sendMessage(String.format(lang.getRawString("messages.help-format"), "/smartharvest", "Show the credits"));
		sender.sendMessage(String.format(lang.getRawString("messages.help-format"), "/smartharvest reload", "Reload the plugin"));
		
		return true;
	}
}
