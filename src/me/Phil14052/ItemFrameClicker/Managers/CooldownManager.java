package me.Phil14052.ItemFrameClicker.Managers;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.Phil14052.ItemFrameClicker.ItemFrameClicker;
import me.Phil14052.ItemFrameClicker.Instances.ClickableItemFrame;

public class CooldownManager {

	private static CooldownManager instance = null;
	private ItemFrameClicker plugin;
	private ItemFrameManager ifm;
	private DataManager dm;
	private BukkitTask autosave = null;
	private CooldownManager(){
		this.plugin = ItemFrameClicker.getInstance();
		ifm = ItemFrameManager.getInstance();
		dm = DataManager.getInstance();
	}
	
	public static CooldownManager getInstance() {
		if (instance == null) instance = new CooldownManager();
		return instance;
	}
	
	public void createCooldown(final Player p, final ClickableItemFrame cif){
		final UUID id = p.getUniqueId();
		if(cif.hasCooldown(id)){
			cif.removeFromCoolDown(id);
		}
		cif.addToCoolDown(p.getUniqueId());
		dm.saveItemFrame(cif);
		 new BukkitRunnable(){
			 int i = cif.getCooldownInSec();
			@Override
			public void run() {
				i--;
				cif.updatePlayerInCoolDown(id, i);
				if(i == 0){
					cif.removeFromCoolDown(id);
					this.cancel();
					dm.saveItemFrame(cif);
				}else{
					dm.updateCooldowns(cif);	
				}
			}
			
		}.runTaskTimer(plugin, 20, 20);
	}
	
	public void continueCooldowns(){
		for(final ClickableItemFrame cif : ifm.getItemframes()){
			for(final UUID p : cif.getCooldowns().keySet()){
				 new BukkitRunnable(){
					 int i = cif.getCooldowns().get(p);
					@Override
					public void run() {
						i--;
						cif.updatePlayerInCoolDown(p, i);
						if(i == 0){
							cif.removeFromCoolDown(p);
							this.cancel();
							dm.saveItemFrame(cif);
						}else{
							dm.updateCooldowns(cif);
						}
					}
					
				}.runTaskTimer(plugin, 20, 20);
			}
		}
	}
	
	public void startAutoSaveCooldowns(){
		autosave = new BukkitRunnable(){

			@Override
			public void run() {
				ifm.saveItemFrames();
			}
			
		}.runTaskTimerAsynchronously(plugin, 20*45, 20*45);
	}
	
	public void stopAutoSaveCooldowns(){
		if(autosave == null) return;
		this.autosave.cancel();
	}
	
}
