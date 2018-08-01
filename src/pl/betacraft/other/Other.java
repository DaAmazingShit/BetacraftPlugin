package pl.betacraft.other;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class Other {

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new OtherPlayer(), Priority.Normal,
				instance);
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (!(sender instanceof Player)) {
			// Support saving for console
			if (cmd.getName().equalsIgnoreCase("save")) {
				for (World w : Bukkit.getServer().getWorlds()) {
					w.save();
					sender.sendMessage(ChatColor.GREEN + "Zapisano swiat: " + ChatColor.RED + w.getName());
				}
			}
			return true;
		}
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("save")) {
			for (World w : Bukkit.getServer().getWorlds()) {
				w.save();
				p.sendMessage(ChatColor.GREEN + "Zapisano swiat: " + ChatColor.RED + w.getName());
			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("seed")) {
			p.sendMessage(ChatColor.GREEN + "Seed: " + ChatColor.YELLOW + p.getWorld().getSeed());
			return true;
		}
		return true;
	}
}
