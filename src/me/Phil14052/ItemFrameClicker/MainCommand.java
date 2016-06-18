package me.Phil14052.ItemFrameClicker;

import me.Phil14052.ItemFrameClicker.Managers.ItemFrameManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor{

		private ItemFrameManager ifm = ItemFrameManager.getInstance();
	
	   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		   if(!cmd.getName().equalsIgnoreCase("ifc")) return true;
		   if(args.length < 1){
			   sender.sendMessage("SEND HELP!");
			   return true;
		   }
		   if(args[0].equalsIgnoreCase("create")){
			   if(!(sender instanceof Player)){
				   sender.sendMessage("ONLY PLAYER");
				   return false;
			   }
			   Player p = (Player) sender;
			   ifm.setCreatingFrame(p);
			   p.sendMessage("Put item in item frame");
			   return true;
		   }else if(args[0].equalsIgnoreCase("destroy")){
			   if(!(sender instanceof Player)){
				   sender.sendMessage("ONLY PLAYER");
				   return false;
			   }
			   Player p = (Player) sender;
			   ifm.setDestoryingFrame(p);
			   p.sendMessage("Left click the item frame you want to destory.");
			   return true;
		   }else{
			   return false;
		   }
		   
	   }
	
}
