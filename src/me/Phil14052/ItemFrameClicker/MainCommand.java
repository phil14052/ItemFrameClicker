package me.Phil14052.ItemFrameClicker;

import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor{

	private ItemFrameManager ifm = ItemFrameManager.getInstance();
	private ItemFrameClicker plugin = ItemFrameClicker.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("ifc")) return true;
		if(args.length < 1){
			for(String s : Lang.SEND_HELP.toStringList()){
				sender.sendMessage(s);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("create")){
			if(!(sender instanceof Player)){
				sender.sendMessage(Lang.PLAYERS_ONLY.toString());
				return false;
			}
			Player p = (Player) sender;
			ifm.setCreatingFrame(p);
			p.sendMessage(Lang.PUT_ITEM_FRAME.toString());
			return true;
		}else if(args[0].equalsIgnoreCase("remove")){
			if(!(sender instanceof Player)){
				sender.sendMessage(Lang.PLAYERS_ONLY.toString());
				return false;
			}
			Player p = (Player) sender;
			ifm.setDestoryingFrame(p);
			p.sendMessage(Lang.REMOVE_ITEM_FRAME.toString());
			return true;
		}else if(args[0].equalsIgnoreCase("cooldown")){
			if(!(sender instanceof Player)){
				sender.sendMessage(Lang.PLAYERS_ONLY.toString());
				return false;
			}
			if(args.length < 2){
				sender.sendMessage(Lang.USAGE_COOLDOWN.toString());
				sender.sendMessage(Lang.INFO_DISABLE_COOLDOWN.toString());
				return false;
			}
			Player p = (Player) sender;
			int i = 0;
			try{  
				i = Integer.parseInt(args[1]);  
			}  
			catch(NumberFormatException nfe){  
				p.sendMessage(Lang.FAILED_NOT_A_NUMBER.toString().replaceAll("%x%", args[1]));
				return false;  
			}  
			ifm.getSettingCooldown().put(p, i);
			p.sendMessage(Lang.GIVE_COOLDOWN.toString());
			return true;
		}else if(args[0].equalsIgnoreCase("reload")){
			plugin.reloadConfig();
			plugin.saveConfig();
			sender.sendMessage(Lang.RELOAD_SUCCES.toString());
			return true;
		}else{
			sender.sendMessage(Lang.UNKNWON_ARGUMENT.toString().replaceAll("%arg%", args[0]));
			return false;
		}
		   
	}
	
}
