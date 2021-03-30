package it.plugandcree.smartharvest.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import it.plugandcree.smartharvest.SmartHarvest;

public class InteractEvent implements Listener {

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (SmartHarvest.getHarvestable().contains(e.getClickedBlock().getType())) {
				Block block = e.getClickedBlock();
				Ageable crop = (Ageable) block.getBlockData();

				if (crop.getAge() == crop.getMaximumAge()) {
					BlockBreakEvent bre = new BlockBreakEvent(block, e.getPlayer());
					Bukkit.getPluginManager().callEvent(bre);
					if (!bre.isCancelled()) {
						bre.setCancelled(true);
						block.breakNaturally(e.getPlayer().getInventory().getItemInMainHand());
						block.setType(crop.getMaterial());
						crop.setAge(0);
						block.setBlockData(crop);
					}
				}
			}
		}
	}
}
