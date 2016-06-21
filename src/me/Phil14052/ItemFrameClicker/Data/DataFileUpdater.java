package me.Phil14052.ItemFrameClicker.Data;

import me.Phil14052.ItemFrameClicker.ItemFrameClicker;

import org.bukkit.plugin.PluginDescriptionFile;

public class DataFileUpdater {
	public DataFileUpdater(ItemFrameClicker plugin){
		PluginDescriptionFile pluginYml = plugin.getDescription();
		plugin.getDataConfig().options().header(pluginYml.getName() + "! Version: " + pluginYml.getVersion() + 
				" By Phil14052"
				+ "\nIMPORTANT: ONLY EDIT THIS IF YOU KNOW WHAT YOU ARE DOING!!");
		if(plugin.getDataConfig().getConfigurationSection("itemframes") == null){
			plugin.getDataConfig().createSection("itemframes");
		}
		
		plugin.getDataConfig().options().copyDefaults(true);
		plugin.saveDataConfig();
	}

}
