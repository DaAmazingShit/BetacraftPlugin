package pl.betacraft.hardcore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class HardcoreList extends PlayerListener {

	@Override
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		boolean banned = Hdb.isBanned(p.getName());
		List<String> worlds = Hdb.getHardcoreWorlds();
		if (!banned) {
			return;
		}
		for (String w: worlds) {
			if (e.getTo().getWorld().getName().equalsIgnoreCase(w)) {
				e.setTo(e.getFrom());
				p.sendMessage(ChatColor.RED + "Masz bana na ten swiat! Konczy sie on: " + Hdb.getExpirationDate(p.getName()));
				e.setCancelled(true);
				return;
			}
		}
	}

	@Override
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Location before = p.getLocation();
		if (Hdb.getHardcoreWorlds().contains(p.getWorld().getName())) {

			// Respawning to default world (level-name in server.properties)
			e.setRespawnLocation(Bukkit.getWorlds().get(0).getSpawnLocation());

			p.sendMessage(ChatColor.RED + "Umarles w swiecie " + before.getWorld().getName());
			p.sendMessage(ChatColor.RED + "Twój ban na hardcore potrwa do: " + getBanDate());

			// This is needed to avoid respawn screen on joining after ban expires
			((CraftPlayer)p).getHandle().dead = false;
			p.setHealth(20);

			Hdb.ban(p.getName(), getBanDate());
		}
	}

	public static String getBanDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm");

		Date date = new Date();

		Calendar c = Calendar.getInstance();
		c.add(GregorianCalendar.MINUTE, Hdb.banTime());

		date.setTime(c.getTimeInMillis());
		String s = dateFormat.format(date);
		return s;
	}

	public static String getNowDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm");

		Date date = new Date();

		String s = dateFormat.format(date);
		return s;
	}
}