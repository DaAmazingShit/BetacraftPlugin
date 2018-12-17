package pl.betacraft.dzialecznik;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.betacraft.moresteck.Betacraft;

public class Dzialecznik {

	public static void onEnable() {
		Bukkit.getServer().getLogger().info(" [BetaCraft] Dzialecznik: wlaczony.");
	}

	// betacraft.dzialecznik.admin

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("dzialka")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.GREEN + " [Dzialecznik] Wybierz dzielnice: klasyczna, sredniowieczna, modern");
				return true;
			}
			if (args[0].equalsIgnoreCase("ustaw")) {
				if (!Betacraft.permissions.getHandler().has(p, "betacraft.dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Brak dostepu.");
					return true;
				}
				if (args.length == 1 || args.length == 2) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Wybierz gracza, dla którego chcesz ustawic dzialke oraz dzielnie!");
					return true;
				}
				boolean done = DM.setLocation(p, args[1], args[2]);
				p.sendMessage(done == true ? ChatColor.BLUE + " [Dzialecznik] Ustawiono dzialke dla gracza " + args[1] + " w dzielni " + args[2] : ChatColor.RED + " [Dzialecznik] Cos poszlo nie tak!");
				return true;
			}
			if (args[0].equalsIgnoreCase("usun")) {
				if (!Betacraft.permissions.getHandler().has(p, "betacraft.dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Brak dostepu.");
					return true;
				}
				if (args.length == 1 || args.length == 2) {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Wybierz gracza, któremu chcesz usunac dzialke oraz dzielnie!");
					return true;
				}
				boolean done = DM.delete(args[1], args[2]);
				p.sendMessage(done == true ? ChatColor.BLUE + " [Dzialecznik] Usunieto dzialke graczowi " + args[1] + " z dzielni " + args[2] : ChatColor.RED + " [Dzialecznik] Cos poszlo nie tak!");
				return true;
			}
			if (args.length == 1) {
				if (DM.hasPlayer(p.getName())) {
					p.teleport(DM.getLocation(p.getName(), args[0]));
					p.sendMessage(ChatColor.GREEN + " [Dzialecznik] Teleportowano na dzialke w dzielni " + args[0]);
					return true;
				}
				else {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Nie masz dzialki w dzielnicy: " + args[0]);
					return true;
				}
			}
			if (args.length == 2) {
				if (DM.hasPlayer(args[0])) {
					p.teleport(DM.getLocation(args[0], args[1]));
					p.sendMessage(ChatColor.GREEN + " [Dzialecznik] Teleportowano na dzialke " + args[0]);
					return true;
				}
				else {
					p.sendMessage(ChatColor.RED + " [Dzialecznik] Gracz nie ma dzialki: " + args[0]);
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("bazar")) {
			if (args.length == 0) {
				if (DM.hasBazar(p.getName())) {
					Location loc = DM.getLocation(p.getName(), "bazary");
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
				if (!Betacraft.permissions.getHandler().has(p, "betacraft.dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Brak dostepu.");
					return true;
				}
				if (args.length == 1) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Wybierz gracza, dla którego chcesz ustawic bazar!");
					return true;
				}
				boolean done = DM.setLocation(p, args[1], "bazary");
				p.sendMessage(done == true ? ChatColor.BLUE + " [Bazarnik] Ustawiono bazar dla gracza " + args[1] : ChatColor.RED + " [Bazarnik] Cos poszlo nie tak!");
				return true;
			}
			if (args[0].equalsIgnoreCase("usun")) {
				if (!Betacraft.permissions.getHandler().has(p, "betacraft.dzialecznik.admin")) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Brak dostepu.");
					return true;
				}
				if (args.length == 1) {
					p.sendMessage(ChatColor.RED + " [Bazarnik] Wybierz gracza, któremu chcesz usunac bazar!");
					return true;
				}
				boolean done = DM.delete(args[1], "bazary");
				p.sendMessage(done == true ? ChatColor.BLUE + " [Bazarnik] Usunieto bazar graczowi " + args[1] : ChatColor.RED + " [Bazarnik] Cos poszlo nie tak!");
				return true;
			}
			else {
				if (DM.hasBazar(args[0])) {
					p.teleport(DM.getLocation(args[0], "bazary"));
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