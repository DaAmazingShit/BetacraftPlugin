package pl.betacraft.moressentials;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class PDB {
	private static Configuration db = new Configuration(new File("BetaCraft/Mssentials/data", ""));

	public static String seen(String player) {
		db = new Configuration(new File("BetaCraft/Mssentials/data", player + ".dat"));
		db.load();
		if (db.getProperty("seen") == null) {
			return "o nieznanej porze";
		}
		return db.getString("seen");
	}

	public static void seen(String player, String date) {
		db = new Configuration(new File("BetaCraft/Mssentials/data", player + ".dat"));
		db.load();
		db.setProperty("seen", date);
		db.save();
	}

	public static void god(String player) {
		new Exception("Not supported yet.");
	}

	public static void invsee(Player kogo, Player widzi) {
		new Exception("Not supported yet.");
	}

	public static void back(Player p) {
		new Exception("Not supported yet.");
	}

	public static void ci(Player p) {
		p.getInventory().clear();
	}

	public static void ignore(Player p, String who) {
		new Exception("Not supported yet.");
	}

	public static void nick(Player p, String nick) {
		new Exception("Not supported yet.");
	}

	public static void socialspy(Player p, boolean on) {
		new Exception("Not supported yet.");
	}

	public static void tpa(Player p, String towho) {
		new Exception("Not supported yet.");
	}

	public static void tpaccept(Player p, String fromwho) {
		new Exception("Not supported yet.");
	}

	public static void tpahere(Player p, String who) {
		new Exception("Not supported yet.");
	}

	public static void tpall(Player p) {
		new Exception("Not supported yet.");
	}

	public static void tppos(Player p, Location loc) {
		new Exception("Not supported yet.");
	}

	public static void wi(Player p, String who) {
		new Exception("Not supported yet.");
	}
}
