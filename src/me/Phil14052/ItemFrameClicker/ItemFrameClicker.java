package me.Phil14052.ItemFrameClicker;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemFrameClicker extends JavaPlugin{
	private static ItemFrameClicker plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
		//registerEvents();
		//registerCommands();
	}

	@Override
	public void onDisable(){
		plugin = null;
	}
	
//	private void registerEvents() {
//	    PluginManager pm = Bukkit.getPluginManager();
//		pm.registerEvents(new OnPlayerDeathEvent(), this);
//		pm.registerEvents(new OnItemPickupEvent(), this);
//	}
//	
//	private void registerCommands() {
//		getCommand("hunter").setExecutor(new HunterCommand());
//	}
	
	public static ItemFrameClicker getInstance(){
		return plugin;
	}
}
