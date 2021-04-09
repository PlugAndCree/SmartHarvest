package it.plugandcree.smartharvest.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import it.plugandcree.smartharvest.SmartHarvest;

public class InteractEvent implements Listener {

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().hasPermission("smartharvest.harvest")) {
			if (SmartHarvest.getHarvestable().contains(e.getClickedBlock().getType())) {
				Block block = e.getClickedBlock();
				Ageable crop = (Ageable) block.getBlockData();

				if (crop.getAge() == crop.getMaximumAge()) {

					if (SmartHarvest.getInstance().isHoeEnabled() && !e.getPlayer().getInventory().getItemInMainHand()
							.getType().name().toUpperCase().endsWith("HOE")) {
						return;
					}

					BlockBreakEvent bre = new BlockBreakEvent(block, e.getPlayer());
					Bukkit.getPluginManager().callEvent(bre);
					if (!bre.isCancelled()) {

						if (SmartHarvest.getInstance().isHoeEnabled()) {
							if (!(e.getPlayer().getInventory().getItemInMainHand().getItemMeta() instanceof Damageable))
								return;

							ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
							Damageable item = (Damageable) e.getPlayer().getInventory().getItemInMainHand()
									.getItemMeta();

							if ((hand.getType().getMaxDurability() - item.getDamage()) < SmartHarvest.getInstance()
									.getDurabilityPerUse())
								return;

							item.setDamage(item.getDamage() + SmartHarvest.getInstance().getDurabilityPerUse());
							e.getPlayer().getInventory().getItemInMainHand().setItemMeta((ItemMeta) item);

						}
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
