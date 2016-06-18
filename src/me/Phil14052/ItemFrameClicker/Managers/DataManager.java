package me.Phil14052.ItemFrameClicker.Managers;

import java.util.UUID;

import me.Phil14052.ItemFrameClicker.ItemFrameClicker;
import me.Phil14052.ItemFrameClicker.Instances.ClickableItemFrame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class DataManager {
	private static DataManager instance = null;
	private ItemFrameClicker plugin;
	private DataManager(){
		this.plugin = ItemFrameClicker.getInstance();
	}
	
	public static DataManager getInstance() {
		if (instance == null) instance = new DataManager();
		return instance;
	}
	
	public String serializeLocation(Location loc){
		StringBuilder sb = new StringBuilder();
		sb.append(loc.getWorld().getName() + "_");
		sb.append(loc.getX() + "_");
		sb.append(loc.getY() + "_");
		sb.append(loc.getZ());
		return sb.toString();
	}
	
	public Location deserializeLocation(String string){
		String[] sloc = string.split("_");
		if(sloc[0] == null || sloc[1] == null || sloc[2] == null || sloc[3] == null) return null;
		World world = Bukkit.getWorld(sloc[0]);
		Double x = Double.parseDouble(sloc[1]);
		Double y = Double.parseDouble(sloc[2]);
		Double z = Double.parseDouble(sloc[3]);
		Location loc = new Location(world, x, y, z);
		return loc;
	}
	
	public void saveItemFrame(ClickableItemFrame cif){
		String suuid = cif.getId().toString();
		String loc = this.serializeLocation(cif.getLoc());
		int amount = cif.getAmount();
		plugin.getDataConfig().set("itemframes." + suuid + ".loc", loc);
		plugin.getDataConfig().set("itemframes." + suuid + ".amount", amount);
		plugin.saveDataConfig();
	}
	public void removeItemFrame(ClickableItemFrame cif){
		plugin.getDataConfig().set("itemframes." + cif.getId().toString(), null);
		plugin.saveDataConfig();
		return;
	}
	public ClickableItemFrame getItemFrame(String suuid){
		UUID uuid = UUID.fromString(suuid);
		String sloc = plugin.getDataConfig().getString("itemframes." + suuid + ".loc");
		Location loc = this.deserializeLocation(sloc);
		int amount = plugin.getDataConfig().getInt("itemframes." + suuid + ".amount");
		ClickableItemFrame cif = new ClickableItemFrame(loc, amount, uuid);
		return cif;
	}
	
}
