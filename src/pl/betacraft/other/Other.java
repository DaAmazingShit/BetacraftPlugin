package pl.betacraft.other;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class Other {
	public static final List<String> adminchat = new LinkedList<String>();

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new OtherPlayer(), Priority.Normal,
				instance);

		Bukkit.getServer().getLogger().info(" [BetaCraft] SoundEffect: wlaczony.");
		Bukkit.getServer().getLogger().info(" [BetaCraft] Other: wlaczony.");
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("save")) {
			for (World w : Bukkit.getServer().getWorlds()) {
				w.save();
				sender.sendMessage(ChatColor.GREEN + "Zapisano swiat: " + ChatColor.RED + w.getName());
			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("adminchat")) {
			if (sender instanceof Player) {
				if (adminchat.contains(sender.getName())) {
					adminchat.remove(sender.getName());
					sender.sendMessage(ChatColor.RED + "Opusciles Adminchat");
					return true;
				}
				adminchat.add(sender.getName());
				sender.sendMessage(ChatColor.GREEN + "Dolaczyles do Adminchat");
				return true;
			}
			if (adminchat.contains("Console")) {
				adminchat.remove("Console");
				sender.sendMessage(ChatColor.RED + "Opusciles Adminchat");
				return true;
			}
			adminchat.add("Console");
			sender.sendMessage(ChatColor.GREEN + "Dolaczyles do Adminchat");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("clearitems")) {
			if (!sender.isOp()) {
				return true;
			}
			for (World w : Bukkit.getServer().getWorlds()) {
				int i = 0;
				for (Entity e: w.getEntities()) {
					e.remove();
					i++;
				}
				sender.sendMessage("Usunieto " + i + " entity.");
				i = 0;
				sender.sendMessage("Pozostalo: " + w.getEntities().size());
			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("save")) {
			for (World w : Bukkit.getServer().getWorlds()) {
				w.save();
				sender.sendMessage(ChatColor.GREEN + "Zapisano swiat: " + ChatColor.RED + w.getName());
			}
			return true;
		}
		if (sender instanceof Player) {
			Player p = (Player)sender;
			if (cmd.getName().equalsIgnoreCase("seed")) {
				p.sendMessage(ChatColor.GREEN + "Seed: " + ChatColor.YELLOW + p.getWorld().getSeed());
				return true;
			}
			return true;
		}

		return true;
	}
}
