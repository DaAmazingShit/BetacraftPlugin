package pl.betacraft.moneh;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Moneh {

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getLogger().info(" [BetaCraft] Moneh: wlaczony.");
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		return true;
	}
}
