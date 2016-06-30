package me.Phil14052.ItemFrameClicker.Managers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Phil14052.ItemFrameClicker.Lang;

public class PermissionManager {

	public static boolean hasPermission(Player p, String permission, boolean withMessage){
		if(p.isOp()) return true;
		else if(p.hasPermission("itemframeclicker.*")) return true;
		else if(p.hasPermission(permission)) return true;
		else {
			p.sendMessage(Lang.NO_PERMISSION.toString());
			return false;
		}
	}
	
	public static boolean hasPermission(CommandSender sender, String permission, boolean withMessage){
		if(sender instanceof Player){
			Player p = (Player) sender;
			return hasPermission(p, permission, withMessage);
		}else return true;

	}
}
