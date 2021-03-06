package pl.betacraft.other;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

import pl.betacraft.moresteck.Betacraft;

// Small things go here
public class Other {
	public static final List<String> adminchat = new LinkedList<String>();

	public static void onEnable(JavaPlugin instance) {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new ZOO(), Priority.Normal, instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new OtherPlayer(), Priority.Lowest,
				instance);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, new OtherPlayer(), Priority.Normal,
				instance);

		Bukkit.getServer().getLogger().info(" [BetaCraft] SoundEffect: wlaczony.");
		Bukkit.getServer().getLogger().info(" [BetaCraft] ZoO!: wlaczone");
		Bukkit.getServer().getLogger().info(" [BetaCraft] AdminChat: wlaczony.");
		Bukkit.getServer().getLogger().info(" [BetaCraft] Other: wlaczony.");
	}

	// betacraft.zoo
	// betacraft.admin

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("zoo")) {
			if (args.length == 0) {
				sender.sendMessage("�cZoO! nie rozpoznalo komendy :(");
				sender.sendMessage(" �a/�ezoo �bpodglad");
				sender.sendMessage(" �a/�ezoo �bwsadz �8<�7gracz�8>");
				sender.sendMessage(" �a/�ezoo �bwysadz �8<�7gracz�8>");
				return true;
			}
			if (args[0].equalsIgnoreCase("podglad")) {
				if (sender instanceof Player) {
					Player pl = (Player)sender;
					if (ZOO.canSee(pl.getName())) {
						ZOO.denySee(pl.getName());
						sender.sendMessage(" �a[�eZoO�c!�a]: �cWylaczyles podglad.");
						return true;
					}
					ZOO.allowSee(pl.getName());
					sender.sendMessage(" �a[�eZoO�c!�a]: �eWlaczyles podglad :)");
					return true;
				}
				sender.sendMessage("�cZoO! twierdzi, ze nie mozesz tego zrobic :(");
				return true;
			}
			if (args[0].equalsIgnoreCase("wsadz")) {
				if (sender instanceof Player) {
					if (!Betacraft.permissions.getHandler().has((Player)sender, "betacraft.zoo")) {
						sender.sendMessage("�cZoO! twierdzi, ze nie mozesz tego zrobic :(");
						return true;
					}
				}
				if (args.length == 1) {
					sender.sendMessage("�cZoO! potrzebuje nicku gracza");
					sender.sendMessage(" �a/�ezoo �bwsadz �8<�7gracz�8>");
					return true;
				}
				String nick = args[1];
				Player p = Bukkit.getServer().getPlayer(nick);
				if (p == null) {
					sender.sendMessage("�cZoO! twierdzi, ze wybrany gracz nie jest online :(");
					return true;
				}
				ZOO.addToZoo(nick);
				Bukkit.getServer().broadcastMessage(" �a[�eZoO�c!�a]: �7" + nick + " �bzostal wsadzony do ZOO!");
				Bukkit.getServer().broadcastMessage(" �a[�eZoO�c!�a]: �ePodglad: ");
				Bukkit.getServer().broadcastMessage(" �a[�eZoO�c!�a]:  �a/�ezoo �bpodglad");
				return true;
			}
			if (args[0].equalsIgnoreCase("wysadz")) {
				if (sender instanceof Player) {
					if (!Betacraft.permissions.getHandler().has((Player)sender, "betacraft.zoo")) {
						sender.sendMessage("�cZoO! twierdzi, ze nie mozesz tego zrobic :(");
						return true;
					}
				}
				if (args.length == 1) {
					sender.sendMessage("�cZoO! potrzebuje nicku gracza");
					sender.sendMessage(" �a/�ezoo �bwysadz �8<�7gracz�8>");
					return true;
				}
				String nick = args[1];
				Player p = Bukkit.getServer().getPlayer(nick);
				if (p == null) {
					sender.sendMessage("�cZoO! twierdzi, ze wybrany gracz nie jest online :(");
					return true;
				}
				ZOO.removeFromZoo(nick);
				Bukkit.getServer().broadcastMessage(" �a[�eZoO�c!�a]: �7" + nick + " �bzostal uwolniony z ZOO!");
				return true;
			}
			else {
				sender.sendMessage("�cZoO! nie rozpoznalo komendy :(");
				sender.sendMessage(" �a/�ezoo �bpodglad");
				sender.sendMessage(" �a/�ezoo �bwsadz �8<�7gracz�8>");
				sender.sendMessage(" �a/�ezoo �bwysadz �8<�7gracz�8>");
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("save")) {
			if (!(sender instanceof Player)) {
				for (World w : Bukkit.getServer().getWorlds()) {
					w.save();
					sender.sendMessage(ChatColor.GREEN + "Zapisano swiat: " + ChatColor.RED + w.getName());
				}
				return true;
			}
			Player p = (Player)sender;
			if (!Betacraft.permissions.getHandler().has(p, "betacraft.admin")) {
				return true;
			}
			for (World w : Bukkit.getServer().getWorlds()) {
				w.save();
				sender.sendMessage(ChatColor.GREEN + "Zapisano swiat: " + ChatColor.RED + w.getName());
			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("adminchat")) {
			if (sender instanceof Player) {
				Player p = (Player)sender;
				if (!Betacraft.permissions.getHandler().has(p, "betacraft.admin")) {
					return true;
				}
				if (adminchat.contains(p.getName())) {
					adminchat.remove(p.getName());
					p.sendMessage(ChatColor.RED + "Opusciles adminchat");
					return true;
				}
				adminchat.add(p.getName());
				p.sendMessage(ChatColor.GREEN + "Dolaczyles do adminchatu");
				return true;
			}
			// Console messages
			String message = "";
			for (int i = 0; i < args.length; i++) {
				message = message + args[i];
			}
			sendAdminmsg("<CONSOLE> " + message);
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("clearitems")) {
			if (!sender.isOp()) {
				return true;
			}
			for (World w : Bukkit.getServer().getWorlds()) {
				int i = 0;
				for (Entity e: w.getEntities()) {
					e.remove();
					i++;
				}
				sender.sendMessage("Usunieto " + i + " entity.");
				i = 0;
				sender.sendMessage("Pozostalo: " + w.getEntities().size());
			}
			return true;
		}
		if (sender instanceof Player) {
			Player p = (Player)sender;
			if (cmd.getName().equalsIgnoreCase("seed")) {
				p.sendMessage(ChatColor.GREEN + "Seed: " + ChatColor.YELLOW + p.getWorld().getSeed());
				return true;
			}
			return true;
		}

		return true;
	}

	public static void sendAdminmsg(String msg) {
		Player[] online = Bukkit.getServer().getOnlinePlayers();
		for (Player p: online) {
			if (Betacraft.permissions.getHandler().has(p, "betacraft.adminchat")) {
				p.sendMessage(msg);
			}
		}
		// Console
		System.out.println("AdminChat: " + msg);
	}
}
