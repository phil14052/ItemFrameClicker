package me.Phil14052.ItemFrameClicker.Listeners;

import java.util.concurrent.TimeUnit;

import me.Phil14052.ItemFrameClicker.Lang;
import me.Phil14052.ItemFrameClicker.Instances.ClickableItemFrame;
import me.Phil14052.ItemFrameClicker.Managers.CooldownManager;
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
	private CooldownManager cm = CooldownManager.getInstance();
	
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
						p.sendMessage(Lang.FAILED_AIR_NOT_ITEM.toString());
						return;
					}
					ifm.createFrame(p, itemframe, loc);
					p.sendMessage(Lang.CREATED_ITEM_FRAME.toString());
					return;
				}
			}else{
				e.setCancelled(true);
				if(ifm.isCreatingFrame(p)){
					p.sendMessage(Lang.FAILED_ITEM_FRAME_IN_USE.toString());
					return;
				}else if(ifm.getSettingCooldown().containsKey(p)){
					e.setCancelled(true);
					int i = ifm.getSettingCooldown().get(p);
					ClickableItemFrame cif = ifm.getItemFrameInstance(loc);
					cif.setCooldownInSec(i);
					dm.saveItemFrame(cif);
					p.sendMessage(Lang.SET_COOLDOWN.toString().replaceAll("%x%", String.valueOf(i)));
					ifm.getSettingCooldown().remove(p);
					return;
				}
				ClickableItemFrame cif = ifm.getItemFrameInstance(loc);
				if(cif.getCooldownInSec() != 0 && cif.hasCooldown(p.getUniqueId())){					
					int timeleft = cif.getSecondsLeftInCooldown(p.getUniqueId());
					long hours = TimeUnit.SECONDS.toHours(timeleft);
					long minutes = TimeUnit.SECONDS.toMinutes(timeleft)-(TimeUnit.SECONDS.toHours(timeleft)*60);
					long seconds = timeleft-(minutes*60+(hours*60*60));
					String message = Lang.NEED_TO_COOLDOWN.toString();
					message = message.replaceAll("%hours%", String.valueOf(hours));
					message = message.replaceAll("%minutes%", String.valueOf(minutes));
					message = message.replaceAll("%seconds%", String.valueOf(seconds));
					p.sendMessage(message);
					return;
				}else if(cif.getCooldownInSec() != 0){
					cm.createCooldown(p, cif);
				}
				ifm.giveItems(p, itemframe, loc);
			}
			
		}else{
			if(ifm.isCreatingFrame(p)){
				e.setCancelled(true);
				if(p.getItemInHand().getType() == Material.AIR){
					p.sendMessage(Lang.FAILED_AIR_NOT_ITEM.toString());
					return;
				}
				ifm.createFrame(p, itemframe, loc);
				p.sendMessage(Lang.CREATED_ITEM_FRAME.toString());
			}
			
		}
	}
}
