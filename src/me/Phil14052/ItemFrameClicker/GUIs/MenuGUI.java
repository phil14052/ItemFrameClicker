package me.Phil14052.ItemFrameClicker.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import me.Phil14052.ItemFrameClicker.Utils.ItemLib;

public class MenuGUI extends InventoryGUI{

	public MenuGUI(){
		 Inventory inv = Bukkit.createInventory(null, 3*9, "§bItem frame editor");
		 ItemLib cooldownItem = new ItemLib(Material.WATCH);
		 cooldownItem.setDisplayName("§aEdit cooldown");
		 inv.setItem(4+9, cooldownItem.create());
		 this.setInv(inv);
	}
	
}
