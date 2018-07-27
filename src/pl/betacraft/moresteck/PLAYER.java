package pl.betacraft.moresteck;

import java.io.File;
import java.util.List;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.util.config.Configuration;

public class PLAYER extends PlayerListener {
	public static Configuration admins = new Configuration(new File("plugins/BetaCraft", "admins"));

	public void onPlayerJoin(PlayerJoinEvent e) {
		admins.load();
		if (admins.getProperty("admins") != null) {
			List<String> admins = PLAYER.admins.getStringList("admins", null);
			if (admins.contains(e.getPlayer().getName())) {
				e.getPlayer().sendMessage("allowed mods:z-fly");
			}
		}
	}
}
