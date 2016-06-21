package me.Phil14052.ItemFrameClicker;
 
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
 
/**
* An enum for requesting strings from the language file.
* Made by gomeow.
* Lang added by phil14052.
* @author gomeow
*/

public enum Lang {
	
    PREFIX("prefix", "&8[&3ItemFrameClicker&8]:"),
    USAGE_PREFIX("usage.prefix", "&4&lUsage:&c"),
    FAILED_PREFIX("failed.prefix", "&4&lFailed:&c"),
    INFO_PREFIX("info.prefix", "&3&lInfo:&8"),
    PLAYERS_ONLY("failed.players_only", "%failed_prefix% Only players can use this feature."),
    PUT_ITEM_FRAME("put_item_frame", "%prefix% Put an item in an item frame."),
    REMOVE_ITEM_FRAME("remove_item_frame", "%prefix% Left click the item frame you want to remove."),
    USAGE_COOLDOWN("usage.cooldown", "%usage_prefix% /ifc cooldown (time in seconds)"),
    INFO_DISABLE_COOLDOWN("info.disable_cooldown", "%info_prefix% Use &3&l0&8 to disable"),
    FAILED_NOT_A_NUMBER("failed.not-a-number", "%failed_prefix% %x% is not a number"),
    GIVE_COOLDOWN("give_cooldown", "%prefix% Right click on an item frame to give it a cooldown."),
    RELOAD_SUCCES("reload_succes", "%prefix% &aReload complete."),
    FAILED_AIR_NOT_ITEM("failed.cant_use_air", "%failed_prefix% You can not use air as an item."),
    SUCCESFULLY_REMOVED_ITEM_FRAME("removed_item_frame", "%prefix% &aSuccesfully removed item frame."),
    CREATED_ITEM_FRAME("created_item_frame", "%prefix% &aSuccesfully created clickable item frame"),
    FAILED_ITEM_FRAME_IN_USE("failed.already_in_use", "%failed% Item frame is already in use."),
    SET_COOLDOWN("set_cooldown", "%prefix% Set cooldown to &3&l%x%"),
    NEED_TO_COOLDOWN("need_to_cooldown", "&cYou need to wait &3%hours%&c hour/s, &3%minutes%&c minute/s and &3%seconds%&c second/s."),
    SEND_HELP("help", "ARRAYLIST: &8&l&m--------------------- ,   , &3ItemFrameClicker - Commands:"
    		+ " , &3/ifc create - Creates a new clickable item frame"
    		+ " , &3/ifc remove - Removes a clickable item frame"
    		+ " , &3/ifc cooldown (time in seconds) - Sets a cooldown on an item frame."
    		+ " ,  "
    		+ " , &8&l&m---------------------"),
    UNKNWON_ARGUMENT("unknown_argument", "&a%arg% &cis not a valid argument."),
    GAVE_ITEM("gave_item", "%prefix% You just got %amount% %item_display_name%");
    
    
    
    private String path;
    private String def;
    private static YamlConfiguration LANG;

    /**
    * Lang enum constructor.
    * @param path The string path.
    * @param start The default string.
    */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }
 
    /**
    * Set the {@code YamlConfiguration} to use.
    * @param config The config to set.
    */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }
 
    @Override
    public String toString() {
    	String s = LANG.getString(this.path);
    	s = s.replaceAll("%prefix%", LANG.getString(PREFIX.getPath()));
    	s = s.replaceAll("%usage_prefix%", LANG.getString(USAGE_PREFIX.getPath()));
    	s = s.replaceAll("%failed_prefix%", LANG.getString(FAILED_PREFIX.getPath()));
    	s = s.replaceAll("%info_prefix%", LANG.getString(INFO_PREFIX.getPath()));
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public List<String> toStringList(){
    	List<String> s = LANG.getStringList(this.path);
    	List<String> colored_s = new ArrayList<String>();
    	for(String string : s){
    		
        	string = string.replaceAll("%prefix%", LANG.getString(PREFIX.getPath()));
        	string = string.replaceAll("%usage_prefix%", LANG.getString(USAGE_PREFIX.getPath()));
        	string = string.replaceAll("%failed_prefix%", LANG.getString(FAILED_PREFIX.getPath()));
        	string = string.replaceAll("%info_prefix%", LANG.getString(INFO_PREFIX.getPath()));
    		colored_s.add(ChatColor.translateAlternateColorCodes('&', string));
    	}
    	return colored_s;
    }
    
    /**
    * Get the default value of the path.
    * @return The default value of the path.
    */
    public String getDefault() {
        return this.def;
    }
 
    /**
    * Get the path to the string.
    * @return The path to the string.
    */
    public String getPath() {
        return this.path;
    }
}