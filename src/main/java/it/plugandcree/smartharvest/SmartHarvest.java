package it.plugandcree.smartharvest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import it.plugandcree.smartharvest.events.InteractEvent;

public class SmartHarvest extends JavaPlugin {

	private static SmartHarvest instance;
	private static List<Material> harvestable;

	@Override
	public void onEnable() {

		instance = this;

		getServer().getPluginManager().registerEvents(new InteractEvent(), this);

		setHarvestable(new ArrayList<Material>(Arrays
				.asList(new Material[] { Material.WHEAT, Material.BEETROOTS, Material.CARROTS, Material.POTATOES, Material.COCOA, Material.NETHER_WART })));
	
	}

	public static SmartHarvest getInstance() {
		return instance;
	}

	public static List<Material> getHarvestable() {
		return harvestable;
	}

	public static void setHarvestable(List<Material> harvestable) {
		SmartHarvest.harvestable = harvestable;
	}
	
}
