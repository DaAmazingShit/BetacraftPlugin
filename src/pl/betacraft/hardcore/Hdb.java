package pl.betacraft.hardcore;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class Hdb {

	private static Configuration config = new Configuration(new File("plugins/BetaCraft/Hardcore", "bans.yml"));

	public static List<String> getHardcoreWorlds() {
		config.load();
		List<String> l = config.getStringList("worlds", new LinkedList<String>());
		return l;
	}

	public static void ban(String player, String date) {
		config.load();
		List<String> l = new LinkedList<String>();
		l.addAll(getBannedPlayers());
		if (isBanned(player)) {
			return;
		}
		l.add(player + "_" + date);
		config.removeProperty("banned-players");
		config.setProperty("banned-players", l);
		config.save();
		Player p = Bukkit.getServer().getPlayer(player);
		if (p != null && getHardcoreWorlds().contains(p.getWorld().getName())) {
			p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
		}
	}

	public static void unban(String player) {
		if (!isBanned(player)) {
			return;
		}
		config.load();
		List<String> lols = new LinkedList<String>();
		lols.addAll(getBannedPlayers());
		if (lols == null || lols.isEmpty()) {
			System.out.println("to jes nul");
			return;
		}
		for (String s: lols) {
			String k = player + "_";
			if (s.startsWith(k)) {
				lols.remove(s);
			}
		}
		config.removeProperty("banned-players");
		config.setProperty("banned-players", lols);
		config.save();
	}

	public static String getExpirationDate(String player) {
		config.load();
		if (!isBanned(player)) {
			return null;
		}
		List<String> l = new LinkedList<String>();
		l.addAll(getBannedPlayers());
		for (String s: l) {
			if (s.startsWith(player + "_")) {
				String x = s.substring(player.length() + 1);
				return x;
			}
		}
		return null;
	}

	public static List<String> getBannedPlayers() {
		config.load();
		List<String> l = config.getStringList("banned-players", new LinkedList<String>());
		return l;
	}

	public static boolean isBanned(String player) {
		List<String> bp = getBannedPlayers();
		List<String> names = new LinkedList<String>();
		for (String k: bp) {
			String[] a = k.split("_");
			names.add(a[0]);
		}
		return names.contains(player);
	}

	public static int banDays() {
		config.load();
		return config.getInt("days", 7);
	}
}