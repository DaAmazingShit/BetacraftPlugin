package pl.betacraft.wayback;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class Wayback {

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_DAMAGE, new WBlock(), Priority.Normal, instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.ENTITY_DAMAGE, new WEntity(), Priority.Normal, instance);
	}
}