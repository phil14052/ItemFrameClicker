package me.Phil14052.ItemFrameClicker.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Rotation;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Phil14052.ItemFrameClicker.ItemFrameClicker;
import me.Phil14052.ItemFrameClicker.Lang;
import me.Phil14052.ItemFrameClicker.Instances.ClickableItemFrame;

public class ItemFrameManager {
	
	private static ItemFrameManager instance = null;
	private List<ClickableItemFrame> itemframes;
	private List<Player> creating;
	private List<Player> destorying;
	private Map<Player, Integer> settingCooldown;
	private Map<Player, ClickableItemFrame> editingGUI;
	private ItemFrameClicker plugin;
	private DataManager dm;
	private ItemFrameManager(){
		this.dm = DataManager.getInstance();
		this.plugin = ItemFrameClicker.getInstance();
		this.setItemframes(new ArrayList<ClickableItemFrame>());
		this.creating = new ArrayList<Player>();
		this.destorying = new ArrayList<Player>();
		this.setSettingCooldown(new HashMap<Player, Integer>());
		this.setEditingGUI(new HashMap<Player, ClickableItemFrame>());
	}
	public static ItemFrameManager getInstance() {
		if (instance == null) instance = new ItemFrameManager();
		return instance;
	}
	public List<ClickableItemFrame> getItemframes() {
		return itemframes;
	}
	public void setItemframes(List<ClickableItemFrame> itemframes) {
		this.itemframes = itemframes;
	}
	
	public ClickableItemFrame addItemFrame(Location loc, int amount){
		ClickableItemFrame itemframe = new ClickableItemFrame(loc, amount);
		this.getItemframes().add(itemframe);
		return itemframe;
	}
	public void addItemFrame(ClickableItemFrame cif){
		this.getItemframes().add(cif);
	}
	public boolean validItemFrame(ItemFrame itemf){
		Location loc = itemf.getLocation();
		for(ClickableItemFrame itemframe : this.getItemframes()){
			if(itemframe.getLoc().getBlockX() == loc.getBlockX() && itemframe.getLoc().getBlockY() == loc.getBlockY() && itemframe.getLoc().getBlockZ() == loc.getBlockZ()){
				return true;
			}
		}
		return false;
	}
	
	public ClickableItemFrame getItemFrameInstance(Location loc){
		for(ClickableItemFrame itemframe : this.getItemframes()){
			if(itemframe.getLoc().getBlockX() == loc.getBlockX() && itemframe.getLoc().getBlockY() == loc.getBlockY() && itemframe.getLoc().getBlockZ() == loc.getBlockZ()){
				return itemframe;
			}
		}
		return null;
	}
	
	public boolean isCreatingFrame(Player p){
		if(this.creating.contains(p)) return true;
		else return false;
	}
	
	public void setCreatingFrame(Player p){
		if(!this.isCreatingFrame(p)){
			this.creating.add(p);
		}
	}
	
	public void giveItems(Player p, ItemFrame itemframe, Location loc){
		ClickableItemFrame itemframei = this.getItemFrameInstance(loc);
		int amount = itemframei.getAmount();
		ItemStack item = itemframe.getItem();
		int backupAmount = item.getAmount();
		if(plugin.getConfig().getBoolean("Multiple-Items")){
			item.setAmount(amount);
		}else{
			item.setAmount(1);
		}
		p.getInventory().addItem(item);
		item.setAmount(backupAmount);
		String display = item.getType().name().toLowerCase().replaceAll("_", " ");
		if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
			display = "§r"+item.getItemMeta().getDisplayName();
		}
		p.sendMessage(Lang.GAVE_ITEM.toString().replaceAll("%amount%", String.valueOf(amount)).replaceAll("%item_display_name%", display));
	}
	
	public void createFrame(Player p, ItemFrame frame, Location loc){
		ItemStack is = p.getItemInHand();
		int amount = is.getAmount();
		frame.setItem(is);
		frame.setRotation(Rotation.NONE);
		ClickableItemFrame cif = this.addItemFrame(loc, amount);
		dm.saveItemFrame(cif);
		this.creating.remove(p);
	}
	
	public void loadItemFrames(){
		for(String s : plugin.getDataConfig().getConfigurationSection("itemframes").getKeys(false)){
			ClickableItemFrame cif = dm.getItemFrame(s);
			this.addItemFrame(cif);
		}
	}
	public boolean isDestoryingFrame(Player p){
		if(this.destorying.contains(p)) return true;
		else return false;
	}
	
	public void setDestoryingFrame(Player p){
		if(!this.isDestoryingFrame(p)){
			this.destorying.add(p);
		}
	}
	
	public void removeItemFrame(Location loc){
		ClickableItemFrame cif = this.getItemFrameInstance(loc);
		this.getItemframes().remove(cif);
		dm.removeItemFrame(cif);
		return;
	}
	public Map<Player, Integer> getSettingCooldown() {
		return settingCooldown;
	}
	public void setSettingCooldown(Map<Player, Integer> settingCooldown) {
		this.settingCooldown = settingCooldown;
	}
	
	
	public void saveItemFrames(){
		for(ClickableItemFrame cif : this.getItemframes()){
			dm.saveItemFrame(cif);
		}
	}
	public Map<Player, ClickableItemFrame> getEditingGUI() {
		return editingGUI;
	}
	public void setEditingGUI(Map<Player, ClickableItemFrame> editingGUI) {
		this.editingGUI = editingGUI;
	}
	
}
