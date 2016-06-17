package me.Phil14052.ItemFrameClicker.Instances;

import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;

public class IItemFrame {

	
	private UUID id = UUID.randomUUID();
	private Location loc;
	private int amount = 1;
	
	public IItemFrame(Location loc, int amount){
		Validate.notNull(loc);
		this.setLoc(loc);
		this.setAmount(amount);
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
	
	
}
