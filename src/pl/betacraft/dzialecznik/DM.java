package pl.betacraft.dzialecznik;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class DM {

	public static Configuration db = new Configuration(new File("plugins/BetaCraft/Dzialecznik", "dzialki.yml"));

	public static boolean hasPlayer(String p) {
		db.load();
		return db.getProperty("dzialki." + p) != null;
	}

	public static boolean hasBazar(String p) {
		db.load();
		return db.getProperty("bazary." + p) != null;
	}

	public static Location getLocation(String p, String dzielnia) {
		db.load();
		Location ret = null;
		String node = db.getString(dzielnia + "." + p, null);
		String[] coords = node.split(",");
		double x = Double.parseDouble(coords[0]);
		double y = Double.parseDouble(coords[1]);
		double z = Double.parseDouble(coords[2]);
		ret = new Location(Bukkit.getServer().getWorld("world"), x, y, z);
		return ret;
	}

	public static boolean setLocation(Player p, String who, String dzielnia) {
		db.load();
		String location = null;
		location = p.getLocation().getBlockX() + "," + 
				p.getLocation().getBlockY() + "," + 
				p.getLocation().getBlockZ();
		db.setProperty(dzielnia + "." + who, location);
		db.save();
		return true;
	}

	public static boolean delete(String p, String dzielnia) {
		db.load();
		db.removeProperty(dzielnia + "." + p);
		db.save();
		return true;
	}
}