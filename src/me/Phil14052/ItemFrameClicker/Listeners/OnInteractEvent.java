package me.Phil14052.ItemFrameClicker.Listeners;

import me.Phil14052.ItemFrameClicker.Managers.DataManager;
import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnInteractEvent implements Listener{

	private ItemFrameManager ifm = ItemFrameManager.getInstance();
	private DataManager dm = DataManager.getInstance();
	
	@EventHandler
	public void onInteractEntityEvent(PlayerInteractEntityEvent e){
		if (!(e.getRightClicked() instanceof ItemFrame)) return;
		ItemFrame itemframe = (ItemFrame) e.getRightClicked();
		Location loc = itemframe.getLocation();
		Player p = e.getPlayer();
		boolean isValid = ifm.validItemFrame(itemframe);
		if(isValid){
			if(itemframe.getItem().getType() == Material.AIR){
				Bukkit.getConsoleSender().sendMessage("§8[§4WARNING§8] §cClickableItemFrame at " + dm.serializeLocation(loc) + " found without item.");
				ifm.removeItemFrame(itemframe.getLocation());
				Bukkit.getConsoleSender().sendMessage("§8[§bINFO§8] §aRemoved ClickableItemFrame at " + dm.serializeLocation(loc) + " from config.");
				if(ifm.isCreatingFrame(p)){
					e.setCancelled(true);
					if(p.getItemInHand().getType() == Material.AIR){
						p.sendMessage("You can not use air as a item.");
						return;
					}
					ifm.createFrame(p, itemframe, loc);
					p.sendMessage("Created item frame.");
					return;
				}
			}else{
				e.setCancelled(true);
				if(ifm.isCreatingFrame(p)){
					p.sendMessage("That itemframe is already in use.");
					return;
				}
				ifm.giveItems(p, itemframe, loc);
			}
			
		}else{
			if(ifm.isCreatingFrame(p)){
				e.setCancelled(true);
				if(p.getItemInHand().getType() == Material.AIR){
					p.sendMessage("You can not use air as a item.");
					return;
				}
				ifm.createFrame(p, itemframe, loc);
				p.sendMessage("Created item frame.");
			}
			
		}
	}
}
