package pl.betacraft.logblock;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.command.ColouredConsoleSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijikokun.bukkit.Permissions.Permissions;

public class LogBlock {

	public static File log = new File("LogBlock/logi.log");
	public static Configuration conf = new Configuration(log);

	public static boolean consoleoutput = true;

	public void onDisable() {}

	public static void onEnable(JavaPlugin instance) {
		if (!log.exists()) {
			try {
				System.out.println(" [LogBlock] LogBlock: Tworzenie pliku Logu...");
				conf.load();
				conf.setProperty("LogBlock", "tak");
				conf.save();
				System.out.println(" [LogBlock] LogBlock: Stworzono log.");
			} catch (Exception e1) {
				System.out.println(" [BetaCraft] LogBlock: Niepowodzenie!");
				e1.printStackTrace();
			}
		}

		LBBlock b = new LBBlock();
		LBPlayer p = new LBPlayer();
		LBEntity e = new LBEntity();

		PluginManager pm = instance.getServer().getPluginManager();
		pm.registerEvent(Type.BLOCK_BREAK, b, Priority.Monitor, instance);
		pm.registerEvent(Type.BLOCK_BURN, b, Priority.Monitor, instance);
		pm.registerEvent(Type.BLOCK_IGNITE, b, Priority.Monitor, instance);
		pm.registerEvent(Type.BLOCK_PLACE, b, Priority.Monitor, instance);
		pm.registerEvent(Type.SIGN_CHANGE, b, Priority.Monitor, instance);

		pm.registerEvent(Type.PLAYER_INTERACT, p, Priority.Monitor, instance);
		pm.registerEvent(Type.PLAYER_COMMAND_PREPROCESS, p, Priority.Monitor, instance);
		
		pm.registerEvent(Type.ENTITY_DAMAGE, e, Priority.Monitor, instance);
		
		Bukkit.getServer().getLogger().info(" [BetaCraft] LogBlock: wlaczony.");
	}

	public static boolean onCommand(CommandSender s, Command c, String ss, String[] a) {
		if (c.getName().equalsIgnoreCase("lb")) {
			if (s instanceof ConsoleCommandSender || s instanceof ColouredConsoleSender) {
				if (a.length == 0) {
					s.sendMessage("/lb out  - wl/wyl output do konsoli (spam)");
					return true;
				}
				if (a[0].equalsIgnoreCase("out")) {
					if (consoleoutput == false) {
						consoleoutput = true;
						s.sendMessage("WLaczono output.");
						return true;
					}
					if (consoleoutput == true) {
						consoleoutput = false;
						s.sendMessage("WYLaczono output.");
						return true;
					}
				}
			}
			else {
				s.sendMessage("Komenda tylko dla konsoli :>");
				return true;
			}
		}
		if (c.getName().equalsIgnoreCase("rollback")) {
			if (!(s instanceof Player)) {
				s.sendMessage("Komenda tylko dla graczy online");
				return true;
			}
			Player p = (Player)s;
			if (!Permissions.Security.has(p, "logblock.rollback")) {
				p.sendMessage(ChatColor.RED + "Brak pozwolenia.");
				return true;
			}
			if (!LBPlayer.clicked.containsKey(p.getName())) {
				p.sendMessage(ChatColor.RED + "Zaznacz blok uzywajac skóry.");
				return true;
			}
			if (a.length == 0) {
				p.sendMessage(ChatColor.RED + "Wyznacz date.");
				return true;
			}
			Block clicked = LBPlayer.clicked.get(p.getName());
			boolean found = false;
			for (String check: DBLogger.get(clicked.getLocation())) {
				if (check.startsWith(a[0])) {
					found = true;
				}
			}
			if (!found) {
				p.sendMessage(ChatColor.RED + "Zly format daty.");
				p.sendMessage(ChatColor.BLUE + "dd_MM_yyyy-HH;mm;ss");
				return true;
			}
			else {
				Material mat = DBLogger.getBlockAsOf(clicked.getLocation(), a[0]);
				p.sendMessage(ChatColor.YELLOW + "Zmieniono typ bloku z " + clicked.getType().name() + " na " + mat.name());
				clicked.setType(mat);
				DBLogger.remove(clicked.getLocation(), a[0]);
			}
		}
		return true;
	}
}