package pl.betacraft.moressentials;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import org.bukkit.util.config.Configuration;

public class PDB {
	private static Configuration db = new Configuration(new File("BetaCraft/MEssentials/data", ""));

	public static String seen(String player) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", player + ".dat"));
		db.load();
		if (db.getProperty("seen") == null) {
			return "o nieznanej porze";
		}
		return db.getString("seen");
	}

	public static void seen(String player, String date) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", player + ".dat"));
		db.load();
		db.setProperty("seen", date);
		db.save();
	}

	public static boolean god(String player) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", player + ".dat"));
		db.load();
		return db.getBoolean("god", false);
	}

	public static void god(String player, boolean on) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", player + ".dat"));
		db.load();
		db.setProperty("god", on);
		db.save();
	}

	public static Location back(Player p) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p.getName() + ".dat"));
		db.load();
		if (db.getProperty("back") == null) {
			return null;
		}
		String rawloc = db.getString("back");
		String[] split = rawloc.split(",");
		String world = split[0];
		int x = Integer.parseInt(split[1]);
		int y = Integer.parseInt(split[2]);
		int z = Integer.parseInt(split[3]);

		World w = Bukkit.getServer().getWorld(world);
		if (w == null) {
			return null;
		}
		Location loc = new Location(w, x, y, z);
		return loc;
	}

	public static void ci(Player p) {
		p.getInventory().clear();
	}

	public static void ignore(Player p, String who, boolean on) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p.getName() + ".dat"));
		db.load();
		List<String> ignored = new LinkedList<String>();
		ignored.addAll(db.getStringList("ignore", new LinkedList<String>()));
		if (on) {
			ignored.add(who);
		}
		else if (!on) {
			ignored.remove(who);
		}
		db.setProperty("ignore", ignored);
		db.save();
	}

	public static List<String> ignored(String p) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p + ".dat"));
		db.load();
		List<String> ignored = new LinkedList<String>();
		ignored.addAll(db.getStringList("ignore", new LinkedList<String>()));
		return ignored;
	}

	public static void back(Player p, Location loc) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p.getName() + ".dat"));
		db.load();
		db.setProperty("back", loc(loc));
		db.save();
	}

	public static void nick(String p, String nick) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p + ".dat"));
		db.load();
		db.setProperty("nick", nick);
		db.save();
	}

	public static String nick(String p) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p + ".dat"));
		db.load();
		return db.getString("nick", p);
	}

	public static boolean commandspy(String p) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p + ".dat"));
		db.load();
		return db.getBoolean("spy", false);
	}

	public static void commandspy(String p, boolean on) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", p + ".dat"));
		db.load();
		db.setProperty("spy", on);
		db.save();
	}

	public static void writeInventory(Player player) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", player.getName() + ".dat"));
		db.load();
		PlayerInventory pi = player.getInventory();
		StringBuilder inventory = new StringBuilder();
		for (int x = 0; x < pi.getSize(); x++) {
			ItemStack is = pi.getItem(x);
			if (inventory.toString() == null) {
				if (is.getData() != null) {
					inventory.append(is.getTypeId()+ ":" + 
				        is.getAmount() + ":" + 
						is.getDurability() + ":" + 
				        is.getData().getData());
				}
				else {
					inventory.append(is.getTypeId()+ ":" + 
					        is.getAmount() + ":" + 
							is.getDurability() + ":" + 
					        0);
				}
			}
			else {
				if (is.getData() != null) {
					inventory.append("," + is.getTypeId()+ ":" +
				        is.getAmount() + ":" +
						is.getDurability() + ":" +
				        is.getData().getData());
				}
				else {
					inventory.append("," + is.getTypeId()+ ":" +
					        is.getAmount() + ":" +
							is.getDurability() + ":" +
					        0);
				}
			}
		}
		db.setProperty("inventory", inventory.toString());
		db.save();
	}

	public static boolean readInventory(Player player) {
		db = new Configuration(new File("BetaCraft/MEssentials/data", player.getName() + ".dat"));
		db.load();
		String inv = db.getString("inventory", null);
		if (inv == null) {
			return false;
		}
		String[] split = inv.split(",");
		PlayerInventory pi = player.getInventory();
		for (int x = 0; x < split.length; x++) {
			System.out.println("SPLOT " + split[x]);
			String[] splyt = split[x].split(":");
			System.out.println("SPLOT 2 " + splyt[0]);
			int type = Integer.parseInt(splyt[0]);
			int amount = Integer.parseInt(splyt[1]);
			short durability = Short.parseShort(splyt[2]);
			byte data = Byte.parseByte(splyt[3]);
			CraftItemStack stack = (CraftItemStack) pi.getItem(x);
			stack.setTypeId(type);
			if (amount > 1) {
				stack.setAmount(amount);
			}
			stack.setDurability(durability);
			stack.setData(new MaterialData(type, data));
		}
		return true;
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

	public static void whois(Player p, String who) {
		new Exception("Not supported yet.");
	}

	public static void repair(Player p, ItemStack is) {
		new Exception("Not supported yet.");
	}

	public static String loc(Location loc) {
		return loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
	}
}
