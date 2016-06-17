package me.Phil14052.ItemFrameClicker.Listeners;

import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;

import org.bukkit.Location;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnInteractEvent implements Listener{

	private ItemFrameManager ifm = ItemFrameManager.getInstance();
	
	@EventHandler
	public void onInteractEntityEvent(PlayerInteractEntityEvent e){
		if (!(e.getRightClicked() instanceof ItemFrame)) return;
		ItemFrame itemframe = (ItemFrame) e.getRightClicked();
		Location loc = itemframe.getLocation();
		Player p = e.getPlayer();
		boolean isValid = ifm.validItemFrame(itemframe);
		if(isValid){
			ifm.giveItems(p, itemframe, loc);
		}else{
			
		}
	}
}
