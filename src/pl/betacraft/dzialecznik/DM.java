package pl.betacraft.dzialecznik;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class DM {

	public static Configuration db = new Configuration(new File("plugins/DZIALKI", "graczy.yml"));

	public static boolean hasPlayer(String p, String miasto) {
		db.load();
		return db.getProperty("dzialki." + p + "." + miasto) != null;
	}

	public static boolean hasBazar(String p, String miasto) {
		db.load();
		return db.getProperty("bazary." + p + "." + miasto) != null;
	}

	public static Location getLocation(String p, String dzialkabazar, String miasto) {
		db.load();
		Location ret = null;
		String node = db.getString(dzialkabazar + "." + p + "." + miasto, null);
		String[] coords = node.split(",");
		double x = Double.parseDouble(coords[0]);
		double y = Double.parseDouble(coords[1]);
		double z = Double.parseDouble(coords[2]);
		ret = new Location(Bukkit.getServer().getWorld("world"), x, y, z);
		return ret;
	}

	public static boolean setLocation(Player p, String who, String dzialkabazar, String miasto) {
		db.load();
		String location = null;
		location = p.getLocation().getBlockX() + "," + 
				p.getLocation().getBlockY() + "," + 
				p.getLocation().getBlockZ();
		db.setProperty(dzialkabazar + "." + who + "." + miasto, location);
		db.save();
		return true;
	}

	public static boolean delete(String p, String dzialkabazar, String miasto) {
		db.load();
		db.removeProperty(dzialkabazar + "." + p + "." + miasto);
		db.save();
		return true;
	}
}