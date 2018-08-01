package pl.betacraft.dzialecznik;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.nijikokun.bukkit.Permissions.Permissions;

public class Dzialecznik {

	public static void onEnable() {
		Bukkit.getServer().getLogger().info(" [BetaCraft] Dzialecznik: wlaczony.");
	}
	public void onDisable() {}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("dzialka")) {
			if (args.length == 0) {
				if (DM.hasPlayer(p.getName(), "Kopytnik")) {
					Location loc = DM.getLocation(p.getName(), "dzialki", "Kopytnik");
					loc.setWorld(Bukkit.getServer().getWorld("world"));
					p.teleport(loc);
					p.sendMessage(ChatColor.GREEN + " [Dzialecznik] Teleportowano na dzialke.");
					return true;
				}
				else {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Nie masz dzialeczki!");
					p.sendMessage(ChatColor.RED + " /dzialka [gracz]");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("ustaw")) {
				if (!Permissions.Security.has(p, "dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Brak dostepu.");
					return true;
				}
				if (args.length == 1) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Wybierz gracza, dla którego chcesz ustawic dzialke!");
					return true;
				}
				boolean done = DM.setLocation(p, args[1], "dzialki", "Kopytnik");
				p.sendMessage(done == true ? ChatColor.BLUE + " [Dzialecznik] Ustawiono dzialke dla gracza " + args[1] + "." : ChatColor.RED + " [Dzialecznik] Cos poszlo nie tak!");
				return true;
			}
			if (args[0].equalsIgnoreCase("usun")) {
				if (!Permissions.Security.has(p, "dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Brak dostepu.");
					return true;
				}
				if (args.length == 1) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Wybierz gracza, któremu chcesz usunac dzialke!");
					return true;
				}
				boolean done = DM.delete(args[1], "dzialki", args[2]);
				p.sendMessage(done == true ? ChatColor.BLUE + " [Dzialecznik] Usunieto dzialke graczowi " + args[1] + "." : ChatColor.RED + " [Dzialecznik] Cos poszlo nie tak!");
				return true;
			}
			else {
				if (DM.hasPlayer(args[0], "Kopytnik")) {
					p.teleport(DM.getLocation(args[0], "dzialki", "Kopytnik"));
					p.sendMessage(ChatColor.GREEN + " [Dzialecznik] Teleportowano na dzialke " + args[0]);
				}
				else {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Gracz nie ma dzialki: " + args[0]);
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("bazar")) {
			if (args.length == 0) {
				if (DM.hasBazar(p.getName(), "Bazar")) {
					Location loc = DM.getLocation(p.getName(), "bazary", "Bazar");
					loc.setWorld(Bukkit.getServer().getWorld("world"));
					p.teleport(loc);
					p.sendMessage(ChatColor.GREEN + " [Bazarnik] Teleportowano na bazar.");
					return true;
				}
				else {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Nie masz bazaru!");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("ustaw")) {
				if (!Permissions.Security.has(p, "dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Brak dostepu.");
					return true;
				}
				if (args.length == 1) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Wybierz gracza, dla którego chcesz ustawic bazar!");
					return true;
				}
				boolean done = DM.setLocation(p, args[1], "bazary", "Bazar");
				p.sendMessage(done == true ? ChatColor.BLUE + " [Bazarnik] Ustawiono bazar dla gracza " + args[1] : ChatColor.RED + " [Bazarnik] Cos poszlo nie tak!");
				return true;
			}
			if (args[0].equalsIgnoreCase("usun")) {
				if (!Permissions.Security.has(p, "dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Brak dostepu.");
					return true;
				}
				if (args.length == 1) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Wybierz gracza, któremu chcesz usunac bazar!");
					return true;
				}
				boolean done = DM.delete(args[1], "bazary", "Bazar");
				p.sendMessage(done == true ? ChatColor.BLUE + " [Bazarnik] Usunieto bazar graczowi " + args[1] : ChatColor.RED + " [Bazarnik] Cos poszlo nie tak!");
				return true;
			}
			else {
				if (DM.hasBazar(args[0], "Bazar")) {
					p.teleport(DM.getLocation(args[0], "bazary", "Bazar"));
					p.sendMessage(ChatColor.GREEN + " [Dzialecznik] Teleportowano na bazar " + args[0]);
				}
				else {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Gracz nie ma bazaru: " + args[0]);
				}
			}
		}
		return true;
	}
}