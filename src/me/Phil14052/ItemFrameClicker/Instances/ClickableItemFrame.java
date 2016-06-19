package me.Phil14052.ItemFrameClicker.Instances;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;

public class ClickableItemFrame {

	
	private UUID id = UUID.randomUUID();
	private Location loc;
	private int amount = 1;
	private Map<UUID, Integer> cooldowns = new HashMap<UUID, Integer>();
	private int cooldownInSec = 0;
	
	public ClickableItemFrame(Location loc, int amount){
		Validate.notNull(loc);
		this.setLoc(loc);
		this.setAmount(amount);
	}
	public ClickableItemFrame(Location loc, int amount, UUID id, int cooldownInSec){
		Validate.notNull(loc);
		this.setLoc(loc);
		this.setAmount(amount);
		this.setId(id);
		this.setCooldownInSec(cooldownInSec);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	public void addToCoolDown(UUID p){
		this.cooldowns.put(p, this.getCooldownInSec());
	}
	
	public void updatePlayerInCoolDown(UUID p, int i){
		this.cooldowns.put(p, i);
	}
	
	public void removeFromCoolDown(UUID p){
		this.cooldowns.remove(p);
	}
	
	public boolean hasCooldown(UUID p){
		if(this.cooldowns.containsKey(p)) return true;
		else return false;
	}
	
	public int getSecondsLeftInCooldown(UUID p){
		if(!(this.hasCooldown(p))) return 0;
		else return this.cooldowns.get(p);
	}
	
	public int getCooldownInSec() {
		return cooldownInSec;
	}
	public void setCooldownInSec(int cooldownInSec) {
		this.cooldownInSec = cooldownInSec;
	}
	public Map<UUID, Integer> getCooldowns() {
		return cooldowns;
	}
	public void setCooldowns(Map<UUID, Integer> cooldowns) {
		this.cooldowns = cooldowns;
	}
}
