package me.Phil14052.ItemFrameClicker.Listeners;

import me.Phil14052.ItemFrameClicker.Lang;
import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityEvents implements Listener {

	private ItemFrameManager ifm = ItemFrameManager.getInstance();
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
		if(!(e.getEntity() instanceof ItemFrame)) return;
		
		ItemFrame itemf = (ItemFrame) e.getEntity();
		boolean valid = ifm.validItemFrame(itemf);
		if(!valid) return;
		if(!(e.getDamager() instanceof Player)){
			e.setDamage(0D);
			e.setCancelled(true);
			return;
		}
		Player p = (Player) e.getDamager();
		if(ifm.isDestoryingFrame(p)){
			ifm.removeItemFrame(itemf.getLocation());
			p.sendMessage(Lang.SUCCESFULLY_REMOVED_ITEM_FRAME.toString());
			return;
		}else{
			e.setCancelled(true);
			return;
		}
	}
	
}
