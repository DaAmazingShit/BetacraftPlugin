package pl.betacraft.betacommandblocks;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijikokun.bukkit.Permissions.Permissions;

public class BetaCommandBlocks {

	public static Configuration config;
	public static Set<Block> powered = new HashSet<Block>();

	public static void onEnable(JavaPlugin instance) {
		config = new Configuration(new File("plugins/BetaCraft/CommandBlocks", "config.yml"));
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, new PLIST(), Priority.Normal, instance);

		Bukkit.getServer().getLogger().info(" [BetaCraft] BetaCommandBlocks: wlaczony.");
	}

	// betacraft.commandblocks.use

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setblock")) {
			if (sender instanceof Player) {
				Player p = (Player)sender;
				if (!Permissions.Security.has(p, "betacraft.commandblocks.use")) {
					sender.sendMessage(ChatColor.RED + "Brak dostepu");
					return true;
				}
			}
			if (args.length == 0 || args.length == 1 || args.length == 2 || args.length == 3 || args.length == 4) {
				return true;
			}
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			int z = Integer.parseInt(args[2]);
			if (args[0].equalsIgnoreCase("~")) {
				// TODO: implement this
			}
			Material block = Material.getMaterial(args[3]);
			
			Bukkit.getServer().getWorld(args[4]).getBlockAt(x, y, z).setType(block);
			return true;
		}
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("cb")) {
			if (!Permissions.Security.has(p, "betacraft.commandblocks.use")) {
				p.sendMessage(ChatColor.RED + "Brak dostepu");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + " /cb komenda arg1 arg2 arg3 ...");
				p.sendMessage(ChatColor.RED + " /cb usun");
				return true;
			}
			if (args[0].equalsIgnoreCase("usun")) {
				config.load();
				if (PLIST.selected.get(p.getName()) == null) {
					p.sendMessage(ChatColor.RED + " [BetaCommandBlocks] Zaznacz commandblock.");
				}
			    Block block = PLIST.selected.get(p.getName());
			    config.removeProperty("commandblocks." + block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ());
			    config.save();
			    p.sendMessage(ChatColor.GREEN + " [BetaCommandBlocks] Usunieto komende z CommandBlocka.");
			    return true;
			}
			String komenda = null;
			for (int z = 0; args.length > z; z++) {
				if (komenda == null) {
					komenda = args[z];
				}
				else {
					komenda = komenda + " " + args[z];
				}
			}
			config.load();
			if (PLIST.selected.get(p.getName()) == null) {
				p.sendMessage(ChatColor.RED + " [BetaCommandBlocks] Zaznacz commandblock.");
			}
		    Block block = PLIST.selected.get(p.getName());
		    config.setProperty("commandblocks." + block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ(), komenda);
		    config.save();
		    p.sendMessage(ChatColor.GREEN + " [BetaCommandBlocks] Przypisano komende do CommandBlocka:   " + ChatColor.AQUA + "/" + komenda);
		}
		return true;
	}
}