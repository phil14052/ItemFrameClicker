package me.Phil14052.ItemFrameClicker.Managers;

import java.util.ArrayList;
import java.util.List;

import me.Phil14052.ItemFrameClicker.Instances.IItemFrame;

import org.bukkit.Location;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemFrameManager {
	
	private static ItemFrameManager instance = null;
	private List<IItemFrame> itemframes;
	private ItemFrameManager(){
		this.setItemframes(new ArrayList<IItemFrame>());
	}
	public static ItemFrameManager getInstance() {
		if (instance == null) instance = new ItemFrameManager();
		return instance;
	}
	public List<IItemFrame> getItemframes() {
		return itemframes;
	}
	public void setItemframes(List<IItemFrame> itemframes) {
		this.itemframes = itemframes;
	}
	
	public void addItemFrame(Location loc, int amount){
		IItemFrame itemframe = new IItemFrame(loc, amount);
		this.getItemframes().add(itemframe);
	}
	
	public boolean validItemFrame(ItemFrame itemf){
		Location loc = itemf.getLocation();
		for(IItemFrame itemframe : this.getItemframes()){
			if(loc.equals(itemframe)){
				return true;
			}
		}
		return false;
	}
	
	public IItemFrame getItemFrameInstance(Location loc){
		for(IItemFrame itemframe : this.getItemframes()){
			if(loc.equals(itemframe)){
				return itemframe;
			}
		}
		return null;
	}
	
	public void giveItems(Player p, ItemFrame itemframe, Location loc){
		IItemFrame itemframei = this.getItemFrameInstance(loc);
		int amount = itemframei.getAmount();
		ItemStack item = itemframe.getItem();
		for(int i = 0; i<amount; i++){
			p.getInventory().addItem(item);
		}
	}
	
	
	
	
	
	
	
	
}
