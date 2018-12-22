package pl.betacraft.hardcore;

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

import com.nijikokun.bukkit.Permissions.Permissions;

public class Hardcore {

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, new HardcoreList(), Priority.Normal, instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_TELEPORT, new HardcoreList(), Priority.Normal, instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, new HardcoreBlock(), Priority.Normal, instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, new HardcoreBlock(), Priority.Normal, instance);

		// CobbleX and StoneDrop are part of Hardcore
		Bukkit.getServer().getLogger().info(" [BetaCraft] Hardcore: wlaczony.");
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("hunban")) {
			if (!has(sender, "betacraft.hc.unban")) {
				sender.sendMessage("Brak dostepu.");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("Wybierz gracza");
				return true;
			}
			Hdb.unban(args[0]);
			sender.sendMessage("Gracz " + args[0] + " odbanowany");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("hban")) {
			if (!has(sender, "betacraft.hc.ban")) {
				sender.sendMessage("Brak dostepu.");
				return true;
			}
			if (args.length == 0 || args.length == 1) {
				sender.sendMessage("Wybierz gracza i date konca banu");
				return true;
			}
			String player = args[0];
			String date = args[1];
			boolean valid = DateVerifier.verify(date);
			if (valid) {
				Hdb.ban(player, date);
				sender.sendMessage("Zbanowano gracza " + player + " do " + date);
				return true;
			}
			else {
				sender.sendMessage("Zly format daty. Prawidlowy format: dd-MM-yyyy-HH-mm");
				return true;
			}
		}
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("cobblex")) {

			PlayerInventory pi = p.getInventory();
			ItemStack cobble = new ItemStack(Material.COBBLESTONE, 64);
			ItemStack cobblex = new ItemStack(Material.MOSSY_COBBLESTONE, 1);
			if (pi.contains(Material.COBBLESTONE, 576)) {
				for (int x = 0; x < 9; x++) {
					pi.removeItem(cobble);
				}
				pi.addItem(cobblex);
				p.sendMessage(ChatColor.GREEN + "Dostales cobblex!");
				Hdb.setPlayerCobbleX(p.getName(), true);
				return true;
			}
			p.sendMessage(ChatColor.RED + "Nie masz wystarczajacej ilosci cobble'a. Potrzeba 9 stakow.");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("itemy")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			p.sendMessage(ChatColor.LIGHT_PURPLE + "64 diamentowych blokow\n64 zelaznych blokow\n64 zlotych jablek\n64 obsidianu\n64 wegla");
		}
		return true;
	}

	public static boolean has(CommandSender cs, String permission) {
		if (cs instanceof Player) {
			Player p = (Player)cs;
			boolean perm = Permissions.Security.has(p, permission);
			if (p.isOp()) {
				return true;
			}
			else {
				return perm;
			}
		}
		else {
			return true;
		}
	}

	public static class DateVerifier {
		public static boolean verify(String date) {
			String[] d = date.split("-");
			if (d[0].length() != 2 || d[1].length() != 2 || d[2].length() != 4 || d[3].length() != 2 || d[4].length() != 2) {
				return false;
			}
			return true;
		}
	}
}