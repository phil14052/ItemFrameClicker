package me.Phil14052.ItemFrameClicker.Data;

import me.Phil14052.ItemFrameClicker.ItemFrameClicker;

import org.bukkit.plugin.PluginDescriptionFile;

public class ConfigFileUpdater {
	public ConfigFileUpdater(ItemFrameClicker plugin){
		PluginDescriptionFile pluginYml = plugin.getDescription();
		plugin.getConfig().options().header(pluginYml.getName() + "! Version: " + pluginYml.getVersion() + 
				" By Phil14052"
				+ "\nDefault config:");
		plugin.getConfig().addDefault("Enabled", true);
		plugin.getConfig().addDefault("Multiple-Items", true);
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();
		plugin.saveConfig();
	}

}
