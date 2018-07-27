package pl.betacraft.logblock;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.config.Configuration;

public class DBLogger {

	public static Configuration database = new Configuration(new File("plugins/LogBlock", "quick_database.db"));

	public static void write(Location l, String kto, String co, String efekt) {
		database = new Configuration(new File("Logging", toString(l)));
		database.load();

		String date = Data.current();
		database.setProperty(date + ".kto", kto);
		database.setProperty(date + ".cobylo", co);
		database.setProperty(date + ".cojest", efekt);
		database.save();
/*		database.load();
		List<String> block = new LinkedList<String>();
		if (database.getStringList(toString(l), null) != null) {
			block.addAll(database.getStringList(toString(l), null));
		}
		block.add(tresc);
		String filePath = "plugins/LogBlock/quick_database.db";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
		    out.write(toString(l) + ":" + block.toString());
		    out.newLine();
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		database.setProperty(toString(l), block);
		database.save();*/
	}

	public static List<String> get(Location l) {
		database = new Configuration(new File("Logging", toString(l)));
		database.load();
		List<String> actions = new LinkedList<String>();
		if (database.getKeys() == null || database.getKeys().isEmpty()) {
			return actions;
		}
		for (String date: database.getKeys()) {
			String kto = database.getString(date + ".kto");
			String przed = database.getString(date + ".cobylo");
			String po = database.getString(date + ".cojest");
			actions.add(date + " " + kto + " - " + przed + " --> " + po);
		}
		return actions;
	}

	public static Material getBlockAsOf(Location l, String date) {
		database = new Configuration(new File("Logging", toString(l)));
		database.load();
		List<String> ss = get(l);
		String rightstring = null;
		for (String s: ss) {
			if (s.startsWith(date)) {
				rightstring = s;
			}
		}
		if (rightstring == null) {
			return null;
		}
		String[] datee = rightstring.split(" ");
		if (database.getString(datee[0] + ".cobylo") == null) {
			return null;
		}
		String asof = database.getString(datee[0] + ".cobylo");
		Material ret = Material.getMaterial(asof);
		return ret;
	}

	public static void remove(Location l, String date) {
		database = new Configuration(new File("Logging", toString(l)));
		database.load();
		if (database.getString(date + ".kto") == null) {
			return;
		}
		database.removeProperty(date);
		database.save();
	}

	public static String toString(Location loc) {
		return loc.getWorld().getName() + "-" + "x" + loc.getBlockX() + "y" + loc.getBlockY() + "z" + loc.getBlockZ();
	}
}