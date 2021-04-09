package it.plugandcree.smartharvest.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.spigot.libraries.commands.Command;

import it.plugandcree.smartharvest.SmartHarvest;
import it.plugandcree.smartharvest.config.CustomConfig;

public class Reload extends Command {
	
	public Reload() {
		super("reload", "smartharvest.reload", SmartHarvest.getInstance().getLangConfig().noPerm());
	}

	@Override
	public boolean execute(CommandSender sender, org.bukkit.command.Command cmd, List<String> args) {
		CustomConfig lang = SmartHarvest.getInstance().getLangConfig();
		
		SmartHarvest.getInstance().reloadConfig();
		sender.sendMessage(lang.getString("messages.reload-complete"));
		return true;
	}
}
