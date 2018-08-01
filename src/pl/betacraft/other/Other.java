package pl.betacraft.other;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Other {
	public static List<String> players_cobblex = new LinkedList<String>();

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new OtherPlayer(), Priority.Normal,
				instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, new OtherBlock(), Priority.Normal,
				instance);
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cobblex")) {
			if (!(sender instanceof Player)) {
				// no support for console
				return true;

			}

			Player p = (Player) sender;
			PlayerInventory pi = p.getInventory();
			ItemStack cobble = new ItemStack(Material.COBBLESTONE, 64);
			ItemStack cobblex = new ItemStack(Material.MOSSY_COBBLESTONE, 1);
			if (pi.contains(Material.COBBLESTONE, 576)) {
				for (int x = 0; x < 9; x++) {
					pi.removeItem(cobble);
				}
				pi.addItem(cobblex);
				p.sendMessage(ChatColor.GREEN + "Dostales cobblex!");
				players_cobblex.add(p.getName());
				return true;
			}
			p.sendMessage(ChatColor.RED + "Nie masz wystarczajacej ilosci cobble'a. Potrzeba 9 stakow.");
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("itemy")) {
			if (!(sender instanceof Player)) {
				return true;
			}

			Player p = (Player) sender;
			p.sendMessage(ChatColor.LIGHT_PURPLE + "64 diamentowych blokow\n64 zelaznych blokow\n64 zlotych jablek\n64 obsidianu\n64 wegla");

		}
		return true;
	}
}
