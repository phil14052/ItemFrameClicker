package me.Phil14052.ItemFrameClicker.Data;

import java.util.ArrayList;

import me.Phil14052.ItemFrameClicker.ItemFrameClicker;
import me.Phil14052.ItemFrameClicker.Lang;

import org.bukkit.plugin.PluginDescriptionFile;

public class LangFileUpdater {
	public LangFileUpdater(ItemFrameClicker plugin){
		PluginDescriptionFile pluginYml = plugin.getDescription();
		Files lang = plugin.lang;
		lang.options().header(pluginYml.getName() + "! Version: " + pluginYml.getVersion() + 
				" By Phil14052");
		for(Lang s : Lang.values()){
			if(s.getDefault().startsWith("ARRAYLIST: ")){
				String def = s.getDefault();
				def = def.replaceFirst("ARRAYLIST: ", "");
				String[] def2 = def.split(" , ");
				ArrayList<String> lines = new ArrayList<String>();
				for(String string : def2){
					string.replaceFirst(" , ", "");
					lines.add(string);
				}
				lang.addDefault(s.getPath(), lines);
			}else{
				lang.addDefault(s.getPath(), s.getDefault());	
			}
		}
		lang.options().copyDefaults(true);
		lang.save();
	}

}
