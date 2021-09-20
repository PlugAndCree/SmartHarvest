package it.plugandcree.smartharvest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import it.plugandcree.smartharvest.commands.MainCommand;
import it.plugandcree.smartharvest.config.ConfigProcessor;
import it.plugandcree.smartharvest.config.CustomConfig;
import it.plugandcree.smartharvest.events.InteractEvent;

public class SmartHarvest extends JavaPlugin {

	private static SmartHarvest instance;
	private static List<Material> harvestable;
	private CustomConfig mainConfig;
	private CustomConfig langConfig;
	private boolean hoeEnabled;
	private boolean particleEnabled;
	private int durabilityPerUse;
	
	@Override
	public void onEnable() {

		instance = this;

		reloadConfig();
		
		getServer().getPluginManager().registerEvents(new InteractEvent(), this);

		new MainCommand().register(this);
		
		setHarvestable(new ArrayList<Material>(Arrays.asList(new Material[] { Material.WHEAT, Material.BEETROOTS,
				Material.CARROTS, Material.POTATOES, Material.COCOA, Material.NETHER_WART })));

	}
	
	public void reloadConfig() {
		setMainConfig(createConfigFile("config.yml"));
		setLangConfig(createConfigFile("lang.yml"));
		
		setHoeEnabled(ConfigProcessor.getHoeEnabled());
		setDurabilityPerUse(ConfigProcessor.getDurabilityLoss());
		
		setParticleEnabled(ConfigProcessor.getParticlesEnabled());
	}

	private CustomConfig createConfigFile(String name) {
		File fc = new File(getDataFolder(), name);
		if (!fc.exists()) {
			fc.getParentFile().mkdirs();
			saveResource(name, false);
		}

		CustomConfig configFile = new CustomConfig();
		try {
			configFile.load(fc);
			return configFile;
		} catch (IOException | InvalidConfigurationException e) {
			throw new RuntimeException(e);
		}
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

	public CustomConfig getMainConfig() {
		return mainConfig;
	}

	public void setMainConfig(CustomConfig mainConfig) {
		this.mainConfig = mainConfig;
	}

	public CustomConfig getLangConfig() {
		return langConfig;
	}

	public void setLangConfig(CustomConfig langConfig) {
		this.langConfig = langConfig;
	}

	public boolean isHoeEnabled() {
		return hoeEnabled;
	}

	public void setHoeEnabled(boolean hoeEnabled) {
		this.hoeEnabled = hoeEnabled;
	}

	public int getDurabilityPerUse() {
		return durabilityPerUse;
	}

	public void setDurabilityPerUse(int durabilityPerUse) {
		this.durabilityPerUse = durabilityPerUse;
	}

	public boolean isParticleEnabled() {
		return particleEnabled;
	}

	public void setParticleEnabled(boolean particleEnabled) {
		this.particleEnabled = particleEnabled;
	}
	
}
