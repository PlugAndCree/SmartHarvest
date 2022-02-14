package it.plugandcree.smartharvest.config;

import it.plugandcree.smartharvest.SmartHarvest;

public class ConfigProcessor {
	public static boolean getHoeEnabled() {
		return SmartHarvest.getInstance().getMainConfig().getBoolean("harvest-with-hoe.enabled");
	}
	
	public static int getDurabilityLoss() {
		return SmartHarvest.getInstance().getMainConfig().getInt("harvest-with-hoe.durability-per-operation");
	}

	public static boolean getBrokeHoeEnabled() {
		return SmartHarvest.getInstance().getMainConfig().getBoolean("harvest-with-hoe.break-when-zero-durability");
	}
	
	public static boolean getParticlesEnabled() {
		return SmartHarvest.getInstance().getMainConfig().getBoolean("particles.enabled");
	}
	

	
}
