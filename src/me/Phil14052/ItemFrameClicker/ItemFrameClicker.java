package me.Phil14052.ItemFrameClicker;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.Phil14052.ItemFrameClicker.Data.ConfigFileUpdater;
import me.Phil14052.ItemFrameClicker.Data.DataFileUpdater;
import me.Phil14052.ItemFrameClicker.Data.Files;
import me.Phil14052.ItemFrameClicker.Data.LangFileUpdater;
import me.Phil14052.ItemFrameClicker.Listeners.EntityEvents;
import me.Phil14052.ItemFrameClicker.Listeners.OnInteractEvent;
import me.Phil14052.ItemFrameClicker.Managers.CooldownManager;
import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemFrameClicker extends JavaPlugin{
	private static ItemFrameClicker plugin;
	private FileConfiguration dataConfig;
	public Files lang;
	private File dataConfigFile;
	private ItemFrameManager ifm;
	private CooldownManager cm;
	
	@Override
	public void onEnable(){
		plugin = this;
		new ConfigFileUpdater(plugin);
		ifm = ItemFrameManager.getInstance();
		registerEvents();
		registerCommands();
		dataConfig = null;
		dataConfigFile = null;
		new DataFileUpdater(plugin);
		ifm.loadItemFrames();
		lang = new Files(this, "lang.yml");
		new LangFileUpdater(plugin);
		Lang.setFile(lang);
		cm = CooldownManager.getInstance();
		cm.continueCooldowns();
		cm.startAutoSaveCooldowns();
		if(!this.getConfig().getBoolean("Enabled")){
			Bukkit.getConsoleSender().sendMessage("§cDisabling plugin.");
			Bukkit.getPluginManager().disablePlugin(plugin);
		}
	}

	@Override
	public void onDisable(){
		cm.stopAutoSaveCooldowns();
		plugin = null;
		ifm = null;
		return;
	}
	
	private void registerEvents() {
	    PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new OnInteractEvent(), this);
		pm.registerEvents(new EntityEvents(), this);
	}
	
	private void registerCommands() {
		getCommand("ifc").setExecutor(new MainCommand());
	}
	
	
	public void reloadDataConfig(){
		if(this.dataConfigFile == null){
			this.dataConfigFile = new File(new File(plugin.getDataFolder(), "Data"),"data.yml");
			this.dataConfig = YamlConfiguration.loadConfiguration(this.dataConfigFile);
			
		}
	}
	
	//Return the arena config
    public FileConfiguration getDataConfig() {
 
        if(this.dataConfigFile == null) this.reloadDataConfig();
 
        return this.dataConfig;
 
    }
 
    //Save the arena config
    public void saveDataConfig() {
 
        if(this.dataConfig == null || this.dataConfigFile == null) return;
 
        try {
            this.getDataConfig().save(this.dataConfigFile);
        } catch (IOException ex) {
            plugin.getServer().getLogger().log(Level.SEVERE, "Could not save data config to " + this.dataConfigFile +"!", ex);
        }
 
    }
	
	public static ItemFrameClicker getInstance(){
		return plugin;
	}
}
