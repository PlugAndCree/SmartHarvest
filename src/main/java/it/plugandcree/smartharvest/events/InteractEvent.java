package it.plugandcree.smartharvest.events;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.configuration.ConfigurationSection;
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
		if (e.getPlayer().getGameMode() != GameMode.SURVIVAL)
			return;

		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		if (!e.getPlayer().hasPermission("smartharvest.harvest"))
			return;

		if (!SmartHarvest.getHarvestable().contains(e.getClickedBlock().getType()))
			return;

		Block block = e.getClickedBlock();
		Ageable crop = (Ageable) block.getBlockData();

		if (crop.getAge() != crop.getMaximumAge())
			return;

		if (SmartHarvest.getInstance().isHoeEnabled()
				&& !e.getPlayer().getInventory().getItemInMainHand().getType().name().toUpperCase().endsWith("HOE")) {
			return;
		}

		BlockBreakEvent bre = new BlockBreakEvent(block, e.getPlayer());
		Bukkit.getPluginManager().callEvent(bre);

		if (bre.isCancelled())
			return;

		if (SmartHarvest.getInstance().isHoeEnabled()) {
			if (!(e.getPlayer().getInventory().getItemInMainHand().getItemMeta() instanceof Damageable))
				return;

			ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
			Damageable item = (Damageable) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();

			if ((hand.getType().getMaxDurability() - item.getDamage()) < SmartHarvest.getInstance()
					.getDurabilityPerUse()) {
				if(!SmartHarvest.getInstance().isBrokeHoeEnabled())
					return;
				
				e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				e.getPlayer().getLocation().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
			} else {
				item.setDamage(item.getDamage() + SmartHarvest.getInstance().getDurabilityPerUse());
				e.getPlayer().getInventory().getItemInMainHand().setItemMeta((ItemMeta) item);
			}
		}

		bre.setCancelled(true);
		e.getPlayer().swingMainHand();
		block.breakNaturally(e.getPlayer().getInventory().getItemInMainHand());
		block.setType(crop.getMaterial());
		crop.setAge(0);
		block.setBlockData(crop);

		if (!SmartHarvest.getInstance().isParticleEnabled())
			return;

		ConfigurationSection config = SmartHarvest.getInstance().getMainConfig().getConfigurationSection("particles");

		DustOptions dustOptions = new DustOptions(
				Color.fromRGB(config.getInt("color.red"), config.getInt("color.green"), config.getInt("color.blue")),
				(float) config.getDouble("size"));
		e.getClickedBlock().getLocation().getWorld().spawnParticle(Particle.REDSTONE,
				e.getClickedBlock().getLocation().add(0.5, 1, 0.5), config.getInt("count"), dustOptions);

	}
}
