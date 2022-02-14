package it.plugandcree.smartharvest.commands;

import org.bukkit.command.CommandSender;
import org.spigot.commons.commands.ExecutionContext;
import org.spigot.commons.commands.builtin.PermissibleCommand;

import it.plugandcree.smartharvest.SmartHarvest;
import it.plugandcree.smartharvest.config.CustomConfig;

public class Reload extends PermissibleCommand {

	public Reload() {
		super("reload", "smartharvest.reload", SmartHarvest.getInstance().getLangConfig().noPerm());
	}

	@Override
	public boolean execute(CommandSender sender, ExecutionContext context) {
		CustomConfig lang = SmartHarvest.getInstance().getLangConfig();

		SmartHarvest.getInstance().reloadConfig();
		sender.sendMessage(lang.getString("messages.reload-complete"));
		return true;
	}
}
