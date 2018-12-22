package pl.betacraft.other;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.util.config.Configuration;

public class ItemDB {
	private static Configuration db = new Configuration(new File("plugins/BetaCraft", "items.db"));
	private final static Map<Material, String> names = new HashMap<Material, String>();

	public static void load() {
		names.clear();
		db.load();
		if (db.getKeys() == null || db.getKeys().isEmpty()) {
			System.out.println(" [BetaCraft] ItemDB is empty. Creating new.");
			create();
			return;
		}
		for (String k: db.getKeys()) {
			Material mat = null;
			try {
				mat = Material.getMaterial(k);
			}
			catch (Exception ex) {
				System.out.println(" [BetaCraft] Could not parse argument '" + k + "' to any material.");
				return;
			}
			String name = (String) db.getProperty(k);
			names.put(mat, name);
		}
	}

	public static String getName(Material mat) {
		if (names.get(mat) == null) {
			load();
		}
		return names.get(mat);
	}

	private static void create() {
		db.load();
		for (Material m: Material.values()) {
			db.setProperty(m.name(), m.name());
		}
		db.save();
	}
}
