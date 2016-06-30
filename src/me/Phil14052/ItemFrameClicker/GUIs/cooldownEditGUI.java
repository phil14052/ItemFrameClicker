package me.Phil14052.ItemFrameClicker.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Phil14052.ItemFrameClicker.Instances.ClickableItemFrame;
import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;
import me.Phil14052.ItemFrameClicker.Utils.ItemLib;

public class cooldownEditGUI extends InventoryGUI{

	private ItemFrameManager ifm = ItemFrameManager.getInstance();
	
	public cooldownEditGUI(Player p){
		 Inventory inv = Bukkit.createInventory(null, 3*9, "§c§d" + "§bItem frame editor");
		 ClickableItemFrame cif = ifm.getEditingGUI().get(p);
		 int current = cif.getCooldownInSec();
		 ItemLib cooldownItem = new ItemLib(Material.WATCH);
		 cooldownItem.setDisplayName("§c§u§r§r§e§n§t§3§l" + current);
		 ItemLib upItem = new ItemLib(Material.WOOL);
		 upItem.setDamageValue((short) 5);
		 upItem.setDisplayName("§a§l+1s");
		 ItemLib upItemMin = new ItemLib(Material.WOOL);
		 upItemMin.setDamageValue((short) 5);
		 upItemMin.setDisplayName("§a§l+1m");
		 ItemLib upItemHour = new ItemLib(Material.WOOL);
		 upItemHour.setDamageValue((short) 5);
		 upItemHour.setDisplayName("§a§l+1h");
		 ItemLib downItem = new ItemLib(Material.WOOL);
		 downItem.setDamageValue((short) 14);
		 downItem.setDisplayName("§c§l-1s");
		 ItemLib downItemMin = new ItemLib(Material.WOOL);
		 downItemMin.setDamageValue((short) 14);
		 downItemMin.setDisplayName("§c§l-1m");
		 ItemLib downItemHour = new ItemLib(Material.WOOL);
		 downItemHour.setDamageValue((short) 14);
		 downItemHour.setDisplayName("§c§l-1h");
		 inv.setItem(3, upItem.create());
		 inv.setItem(4, upItemMin.create());
		 inv.setItem(5, upItemHour.create());
		 inv.setItem(4+9, cooldownItem.create());
		 inv.setItem(3+18, downItem.create());
		 inv.setItem(4+18, downItemMin.create());
		 inv.setItem(5+18, downItemHour.create());
		 this.setInv(inv);
	}
	
}
