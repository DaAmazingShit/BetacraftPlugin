package pl.betacraft.other;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.util.config.Configuration;

public class Otherdb {
	private static Configuration cobble = new Configuration(new File("plugins/BetaCraft/Other", "cobblex.data"));
	private static Configuration stone = new Configuration(new File("plugins/BetaCraft/Other", "stonedrop.yml"));

	public static class StoneDrop {
		public static Material getRandomDrop() {
			stone.load();
			return Material.AIR;
		}
	}
}