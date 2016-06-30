package me.Phil14052.ItemFrameClicker.GUIs;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryGUI {

	private Inventory inv;

	public Inventory getInv() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	
	public void openInventory(Player p){
		p.openInventory(inv);
	}
	
}
