package pl.betacraft.hardcore;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class Hdb {

	private static Configuration config = new Configuration(new File("plugins/BetaCraft/Hardcore", "bans.yml"));
	private static Configuration sponges = new Configuration(new File("plugins/BetaCraft/Hardcore", "sponges.yml"));

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

	// Sponge

	public static void addSponge(Block block) {
		sponges.load();
		sponges.setProperty("sponges." + spongeToString(block), "present");
		sponges.save();
	}

	public static void removeSponge(Block block) {
		sponges.load();
		sponges.removeProperty("sponges." + spongeToString(block));
		sponges.save();
	}

	public static boolean isSpongeRegistered(Block block) {
		sponges.load();
		if (sponges.getString("sponges." + spongeToString(block), null) == null) {
			return false;
		}
		return true;
	}

	public static List<SpongeBlock> getAllSponges() {
		sponges.load();
		List<SpongeBlock> blocks = new LinkedList<SpongeBlock>();
		if (sponges.getKeys("sponges") == null || sponges.getKeys("sponges").isEmpty()) {
			return blocks;
		}
		for (String one: sponges.getKeys("sponges")) {
			String[] args = one.split(",");
			String world = args[0];
			int x = Integer.parseInt(args[1]);
			int y = Integer.parseInt(args[2]);
			int z = Integer.parseInt(args[3]);
			SpongeBlock s = SpongeBlock.getSpongeBlock(world, x, y, z);
			blocks.add(s);
		}
		return blocks;
	}

	public static String spongeToString(Block block) {
		return block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ();
	}
}