package pl.betacraft.hardcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijikokun.bukkit.Permissions.Permissions;

public class Hardcore {

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, new HardcoreList(), Priority.Normal, instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_TELEPORT, new HardcoreList(), Priority.Normal, instance);

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
			public void run() {
				if (!Hdb.getBannedPlayers().isEmpty()) {
					for (String ep: Hdb.getBannedPlayers()) {
						String[] x = ep.split("_");
						String now = HardcoreList.getNowDate();
						if (x[1].equals(now)) {
							Hdb.unban(x[0]);
							if (Bukkit.getServer().getPlayer(x[0]) != null) {
								Bukkit.getServer().getPlayer(x[0]).sendMessage(ChatColor.RED + "Mozesz znowu grac na hardcore.");
							}
						}
					}
				}
			}
		}, 0L, 600L); // every 30s
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
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