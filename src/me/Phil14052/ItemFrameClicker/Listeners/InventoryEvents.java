package me.Phil14052.ItemFrameClicker.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Phil14052.ItemFrameClicker.Lang;
import me.Phil14052.ItemFrameClicker.GUIs.cooldownEditGUI;
import me.Phil14052.ItemFrameClicker.Instances.ClickableItemFrame;
import me.Phil14052.ItemFrameClicker.Managers.DataManager;
import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;
import me.Phil14052.ItemFrameClicker.Managers.PermissionManager;

public class InventoryEvents implements Listener{

	private DataManager dm = DataManager.getInstance();
	private ItemFrameManager ifm = ItemFrameManager.getInstance();
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e){
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getType() == Material.AIR) return;
		if(!(e.getWhoClicked() instanceof Player)) return;
		if(e.getInventory() == null) return;
		if(e.isCancelled()) return;
		Inventory inv = e.getInventory();
		String title = inv.getTitle();
		ItemStack is = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if(title.equals("§bItem frame editor")){
			e.setCancelled(true);
			if(!is.hasItemMeta() || !is.getItemMeta().hasDisplayName()) return;
			String display = is.getItemMeta().getDisplayName();
			if(display.equals("§aEdit cooldown")){
				if(!PermissionManager.hasPermission(p, "itemframeclicker.cooldown", true)){
					p.closeInventory();
					return;
				}
				new cooldownEditGUI(p).openInventory(p);
			}
			return;
		}else if(title.equals("§c§d" + "§bItem frame editor")){
			e.setCancelled(true);
			if(!is.hasItemMeta() || !is.getItemMeta().hasDisplayName()) return;
			String display = is.getItemMeta().getDisplayName();
			if(display.equals("§a§l+1s")){
				ItemMeta im = inv.getItem(4+9).getItemMeta();
				String idis = im.getDisplayName();
				idis = idis.replaceFirst("§c§u§r§r§e§n§t§3§l", "");
				int i = Integer.parseInt(idis);
				i++;
				im.setDisplayName("§c§u§r§r§e§n§t§3§l" + i);
				inv.getItem(4+9).setItemMeta(im);
			}else if(display.equals("§a§l+1m")){
				ItemMeta im = inv.getItem(4+9).getItemMeta();
				String idis = im.getDisplayName();
				idis = idis.replaceFirst("§c§u§r§r§e§n§t§3§l", "");
				int i = Integer.parseInt(idis);
				i = i+60;
				im.setDisplayName("§c§u§r§r§e§n§t§3§l" + i);
				inv.getItem(4+9).setItemMeta(im);
			}else if(display.equals("§a§l+1h")){
				ItemMeta im = inv.getItem(4+9).getItemMeta();
				String idis = im.getDisplayName();
				idis = idis.replaceFirst("§c§u§r§r§e§n§t§3§l", "");
				int i = Integer.parseInt(idis);
				i = i+(60*60);
				im.setDisplayName("§c§u§r§r§e§n§t§3§l" + i);
				inv.getItem(4+9).setItemMeta(im);
			}else if(display.equals("§c§l-1s")){
				ItemMeta im = inv.getItem(4+9).getItemMeta();
				String idis = im.getDisplayName();
				idis = idis.replaceFirst("§c§u§r§r§e§n§t§3§l", "");
				int i = Integer.parseInt(idis);
				if(i == 0) return;
				i--;
				im.setDisplayName("§c§u§r§r§e§n§t§3§l" + i);
				inv.getItem(4+9).setItemMeta(im);
			}else if(display.equals("§c§l-1m")){
				ItemMeta im = inv.getItem(4+9).getItemMeta();
				String idis = im.getDisplayName();
				idis = idis.replaceFirst("§c§u§r§r§e§n§t§3§l", "");
				int i = Integer.parseInt(idis);
				if(i == 0) return;
				i = i-60;
				if(i < 0) i = 0;
				im.setDisplayName("§c§u§r§r§e§n§t§3§l" + i);
				inv.getItem(4+9).setItemMeta(im);
			}else if(display.equals("§c§l-1h")){
				ItemMeta im = inv.getItem(4+9).getItemMeta();
				String idis = im.getDisplayName();
				idis = idis.replaceFirst("§c§u§r§r§e§n§t§3§l", "");
				int i = Integer.parseInt(idis);
				if(i == 0) return;
				i = i-(60*60);
				if(i < 0) i = 0;
				im.setDisplayName("§c§u§r§r§e§n§t§3§l" + i);
				inv.getItem(4+9).setItemMeta(im);
			}
		}
	}
	
	
	@EventHandler
	public void invCloseEvent(InventoryCloseEvent e){
		Inventory inv = e.getInventory();
		String title = inv.getTitle();
		if(!(e.getPlayer() instanceof Player)) return;
		Player p = (Player) e.getPlayer();
		if(title.equals("§c§d" + "§bItem frame editor")){
			ItemMeta im = inv.getItem(4+9).getItemMeta();
			String idis = im.getDisplayName();
			idis = idis.replaceFirst("§c§u§r§r§e§n§t§3§l", "");
			int i = Integer.parseInt(idis);
			ClickableItemFrame cif = ifm.getEditingGUI().get(p);
			if(cif.getCooldownInSec() == i) return;
			cif.setCooldownInSec(i);
			dm.saveItemFrame(cif);
			p.sendMessage(Lang.SET_COOLDOWN.toString().replaceAll("%x%", String.valueOf(i)));
			ifm.getEditingGUI().remove(p);
		}
	}
}
