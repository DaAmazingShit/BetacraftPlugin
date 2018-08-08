package pl.betacraft.moressentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class Moressentials {

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new ZOO(), Priority.Normal, instance);
		Bukkit.getServer().getLogger().info(" [BetaCraft] Mssentials: wlaczone");
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("seen")) {
			if (args.length == 0) {
				sender.sendMessage("/seen <nick>");
				return true;
			}
			String player = args[0];
			sender.sendMessage("Gracz " + player + " byl ostatnio online " + PDB.seen(player));
		}
		return true;
	}
}