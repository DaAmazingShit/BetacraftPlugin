package pl.betacraft.moblimit;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class MobLimit {

	public static void onEnable(JavaPlugin instance) {
		instance.getServer().getPluginManager().registerEvent(Type.CREATURE_SPAWN, new Liste(), Priority.Normal, instance);
	}
}